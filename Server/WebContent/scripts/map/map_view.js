/**  Global Scope Vars  **/
var map = undefined;
var map_name = undefined;
var popup = undefined;
var refresh = undefined;
var ajax_historical_contacts = undefined;
var ajax_contacts = undefined;
var ajax_own_ship_data = undefined;
var ajax_vessels = undefined;
var ajax_contact_vessels = undefined;
var contactsGroup = undefined;
var server_time = undefined;
var last_server_time = undefined;
var osdd_len = undefined;
var markers = undefined;
var show_trajectories = false;
var lines = [];
const refresh_rate = 3000;

/**  SCRIPT ***********************************************/
/**********************************************************/
/**********************************************************/

onInitialise();  // initialise function
setInterval(mainLoop,refresh_rate);  // main loop on interval


/**  Initialisation ***************************************/
/**********************************************************/
/**********************************************************/
function onInitialise() {
    refresh = 0;
    popup = L.popup();
    map_name = "map";
    map = L.map(map_name);
    L.tileLayer(
        "https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}" +
        "?access_token=" +
        "pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ" +
        ".rJcFIG214AriISLbB6B5aw",
                    {
                        maxZoom : 18,
                        attribution :
                            "Map data &copy; " +
                            "<a href=\"https://www.openstreetmap.org/\">" +
                            "OpenStreetMap</a> " +
                            "contributors, " +
                            "<a href=" +
                            "\"https://creativecommons.org" +
                            "/licenses/by-sa/2.0/\">" +
                            "CC-BY-SA</a>, " +
                            "Imagery Â© " +
                            "<a href=\"https://www.mapbox.com/\">" +
                            "Mapbox</a>",
                        id : "mapbox/streets-v11",
                        tileSize : 512,
                        zoomOffset : -1
                    }).addTo(map);
    addClickEvents();
    setViewToPos(undefined,undefined);
}

/**  Primary Methods **************************************/
/**********************************************************/
/**********************************************************/
function mainLoop(){
	refresh += 1;
    updateData();
	if(isDataReceived()){
		setOSDDLen();
		renderData();
	}
}

/**  Data Ajax Calls  *************************************/
/**********************************************************/
/**********************************************************/
function isDataReceived(){ 
	return (ajax_contacts !== undefined) && (ajax_own_ship_data !== undefined);
}

function updateData(){ 
	var bounds = map.getBounds();
    updateServerTime();
	
	if(show_trajectories){ 
		ajaxUpdateViewHistoricalContacts(bounds);
	} else { 
		ajax_historical_contacts = undefined;
	}
	
    ajaxUpdateOwnShipData();
    ajaxUpdateViewVessels(bounds);
    ajaxUpdateVesselsContactData(bounds);
    ajaxUpdateViewContacts(bounds);
}

function ajaxUpdateViewContacts(bounds){
    $.ajax({
           type: "get",
           url: "this_position_contacts_data",
           data: {bounds: JSON.stringify(bounds)},
           success: function(data){
               ajax_contacts = data;
           }
    });
    return ajax_contacts;
}

function ajaxUpdateViewHistoricalContacts(bounds){
    $.ajax({
           type: "get",
           url: "this_time_and_position_historical_contacts_data",
           data: {bounds: JSON.stringify(bounds)},
           success: function(data){
               ajax_historical_contacts = data;
           }
    });
    return ajax_historical_contacts;
}

function ajaxUpdateViewVessels(bounds){
    $.ajax({
           type: "get",
           url: "this_view_vessels_data",
           data: {bounds: JSON.stringify(bounds)},
           success: function(data){
               ajax_vessels = data;
           }
    });
    return ajax_vessels;
}

function ajaxUpdateOwnShipData(){
    $.ajax({
           type: "get",
           url: "own_ship_positional_data",
           data: {},
           success: function(data){
               ajax_own_ship_data = data;
           }
    });
    return ajax_own_ship_data;
}

function ajaxUpdateVesselsContactData(bounds){
    $.ajax({
           type: "get",
           url: "this_view_contact_vessels_data",
           data:{bounds: JSON.stringify(bounds)},
           success: function(data){
               ajax_contact_vessels = data;
           }
    });
    return ajax_contact_vessels;
}

function ajaxUpdateServerTime(){
    $.ajax({
           type: "get",
           url: "server_time_json",
           data:{},
           success: function(data){
               server_time = data;
           }
    });
    return server_time;
}

/**  Server Time ******************************************/
/**********************************************************/
/**********************************************************/
function isServerTimeCorrect(){ 
	return (ajax_own_ship_data[osdd_len].date_time.date.year 
        === server_time.date.year) &&
	(ajax_own_ship_data[osdd_len].date_time.date.month 
        === server_time.date.month) &&
	(ajax_own_ship_data[osdd_len].date_time.date.day 
        === server_time.date.day) &&
	(ajax_own_ship_data[osdd_len].date_time.time.hour 
        === server_time.time.hour) && 
	(ajax_own_ship_data[osdd_len].date_time.time.minute 
        === server_time.time.minute) &&
	(ajax_own_ship_data[osdd_len].date_time.time.second 
        === server_time.time.second);
}

function updateServerTime(){ 
	ajaxUpdateServerTime();
    if(last_server_time === server_time){ 
    	console.log("server time error");
    	if(server_time === undefined){
    		console.log("no server time");
    	} else {
    		server_time.time.second += (refresh_rate/1000); 
    	}
    }
    last_server_time = server_time;
}

/**  Vessel Info  *****************************************/
/**********************************************************/
/**********************************************************/

function getVesselInfo(contact){ 

	//TODO
	vess_id = undefined;
	//iterate through contact vessels and get contact_id
	for (var vessel_id in ajax_contact_vessels) {
	    if (ajax_contact_vessels.hasOwnProperty(vessel_id)) {
	       for(var contact_id in ajax_contact_vessels[vessel_id]){ 
	    	   if(contact_id === contact.contact_id){ 
	    		   vess_id = vessel_id;
	    	   }
	       }
	    }
	}
	//get vessel info for vessel id
	for(var vessel in ajax_vessels){ 
		if(ajax_vessels.hasOwnProperty(vessel)){ 
			if(vessel.vessel_id === vess_id){ 
				return vessel;
			}
		}
	}
	return undefined;
}

/**  Graphics *********************************************/
/**********************************************************/
/**********************************************************/

/**  Markers *********************************************/
function renderData() {
	var index = undefined;
	
	if(lines){ 
		clearLines();
	}
	
	if(markers){ 
		markers.clearLayers();
		map.removeLayer(markers);
	}
	markers = L.markerClusterGroup();
	
	if(isServerTimeCorrect()){ 
		marker = ownShipMarker(ajax_own_ship_data[osdd_len]);
		ownShipPopup(marker, ajax_own_ship_data[osdd_len]);
		marker.on("click", onMarkerClick);
		markers.addLayer(marker); 
		
		if(show_trajectories){
			createLine(ajax_own_ship_data);
		}
		
    } 
	
	for (index = 0; index < ajax_contacts.length; index += 1) {
		marker = genericContactMarker(ajax_contacts[index]);
		genericPopup(marker, ajax_contacts[index]);
		marker.on("click", onMarkerClick);
		markers.addLayer(marker);
	}
	
	if(show_trajectories && (ajax_historical_contacts !== undefined)){
		for( index =0; index < ajax_historical_contacts.length; index += 1){
			createLine(ajax_historical_contacts[index]);
		}
	}
	
	
	map.addLayer(markers);
	drawLines();
}

function genericContactMarker(contact){
    return L.marker([contact.latitude, contact.longitude], {
			//rotationAngle: validate_rotation(contact.heading),
			draggable: false
			});
}

function genericPopup(marker, contact){ 
	marker.bindPopup(
            "<b>Contact:</b><br />" +
            "<b>time: </b>" +
            formatNumber(contact.date_time.time.hour) + ":" +
			formatNumber(contact.date_time.time.minute) + ":" +
			formatNumber(contact.date_time.time.second) + 
            "<br />" +
            "<b>latitude: </b>"+
            contact.latitude +
            "<br /> " +
            "<b>longitude: </b>"+
            contact.longitude +
            "<br /> " +
			"<b>heading: </b>"+
            contact.heading +
            "<br /> " +
			"<b>depth: </b>"+
            contact.depth +
            "<br /> " +
			"<b>knots: </b>"+
            contact.knots +
            "<br /> " 
            );
}

function ownShipMarker(contact){
    return L.marker([contact.latitude, contact.longitude], {
				icon: subIcon,
				rotationAngle: validate_rotation(contact.heading),
                draggable: false
			});
}

function ownShipPopup(marker, contact){ 
		marker.bindPopup(
				"<b>Own Ship Data:</b><br />" +
				"<b>time: </b>" +
				formatNumber(contact.date_time.time.hour) + ":" +
				formatNumber(contact.date_time.time.minute) + ":" +
				formatNumber(contact.date_time.time.second) + 
				"<br />" +
				"<b>latitude: </b>"+
				contact.latitude +
				"<br /> " +
				"<b>longitude: </b>"+
				contact.longitude +
				"<br /> " +
				"<b>heading: </b>"+
				contact.heading +
				"<br /> " +
				"<b>depth: </b>"+
				contact.depth +
				"<br /> " +
				"<b>knots: </b>"+
				contact.knots +
				"<br /> " 
            );
}

function validate_rotation(heading){ 
	//TODO
	return heading;
}

function onMarkerClick(e){ 
	console.log(e);
	
	//	TODO
	//	e.latlng {lat:x, lng: y}
	//	createline for contact
}

/**  Icon Graphics *********************************************/
//TODO
function getIconForType(contact){
	
	if(getVesselInfo(contact).vessel_type 
        === "cargo-ship"){
		return smallShipIcon;
	} else if(getVesselInfo(contact).vessel_type 
        === "pleasure-craft"){
		return mediumShipIcon;
	} else if(getVesselInfo(contact).vessel_type 
        === "first-rate-ship-of-the-line"){
		return largeShipIcon;
	}
	
	return undefined;
}

var subIcon = L.icon({
    iconUrl: "./images/submarine.png",
    iconSize:     [17, 70],
    iconAnchor:   [8, 35],
    popupAnchor:  [0, -35]
});

var smallShipIcon = L.icon({
    iconUrl: "./images/ship_small.png",
    iconSize:     [6, 25],
    iconAnchor:   [4, 12],
    popupAnchor:  [0, -12]
});

var mediumShipIcon = L.icon({
    iconUrl: "./images/ship_med.png",
    iconSize:     [8, 50],
    iconAnchor:   [4, 25],
    popupAnchor:  [0, -25]
});

var largeShipIcon = L.icon({
    iconUrl: "./images/ship_large.png",
    iconSize:     [15, 104],
    iconAnchor:   [7, 52],
    popupAnchor:  [0, -52]
});

/**  Line Graphics **********************************************/

function createLine(historical_data_list) {
	// draw historical points
	lines.push(drawHistoricLine(historicalPointsList(historical_data_list)));
	// draw predicted line
	lines.push(drawPreictedLine(predictedPointsList(historical_data_list)));
}

function historicalPointsList(historical_data_list){ 
	var points_list = [];
	for (var index = 0; index < (historical_data_list.length - 1); index +=1) {
		var point = new L.LatLng(
                                historical_data_list[index].latitude, 
                                historical_data_list[index].longitude);
		points_list.push(point);
	}
	return points_list;
}

function predictedPointsList(historical_data_list){ 
	var points_list = [];
	var index = undefined;
	var predict_lat = undefined;
	var predict_lng = undefined;
	var delta_lat = undefined;
	var num_additional_predicted_points = undefined;
	
	delta_lat = generatePredictedLatitudeDelta(
                    historical_data_list[historical_data_list.length - 2], 
                    historical_data_list[historical_data_list.length - 1]);
	
	// first predicted point using historical data...
	predict_lat = historical_data_list[historical_data_list.length - 1].latitude 
                      + delta_lat;
	predict_lng = linearExtrapolate(
        getLatLngTuple(historical_data_list[historical_data_list.length - 1]), 
        getLatLngTuple(historical_data_list[historical_data_list.length - 2]), 
        predict_lat);
	points_list.push([predict_lat, predict_lng]);
	
	// second predicted point using historical data and last predicted point...
	predict_lat = points_list[points_list.length - 1][0] + delta_lat;
	predict_lng = linearExtrapolate(points_list[0], 
                      getLatLngTuple(
                      historical_data_list[historical_data_list.length - 1]), 
                      predict_lat);
	points_list.push([predict_lat, predict_lng]);
	
	// add points for iterations ... 
	num_additional_predicted_points = document.getElementById("rangeinput").value;
	for(index = 1; index <= num_additional_predicted_points; index++){ 
		//TODO perform in loop generating new points until num is reached
		predict_lat = points_list[index][0] + delta_lat;
		predict_lng = linearExtrapolate(
                points_list[index],points_list[index - 1] ,
                predict_lat)
		points_list.push([predict_lat, predict_lng]);	
	}	
	return points_list;
}

function getLatLngTuple(contact){ 
	return [contact.latitude , contact.longitude];
}

function generatePredictedLatitudeDelta(penultimate_contact, last_contact){ 
	return last_contact.latitude - penultimate_contact.latitude;
}

function linearExtrapolate(lat_lng1, lat_lng2, predicted_x){ 
	var y3 = lat_lng1[1] + 
                 (predicted_x - lat_lng1[0]) / 
                 (lat_lng2[0] - lat_lng1[0]) * 
                 (lat_lng2[1] - lat_lng1[1]);
	return y3;
}

function drawLines() {
	for(var index = 0; index < lines.length; index += 1){ 
		lines[index].addTo(map);
	}
}

function clearLines() {
	for(var index = 0; index < lines.length; index += 1){ 
		map.removeLayer(lines[index]);
	}
	
	lines = [];
}

function drawHistoricLine(points_list){ 
	
	//TODO "ZOOM SIZE" of line -- so you can't see it when you zoom out?
	return new L.Polyline(points_list, {
		color: "red",
		weight: 3,
		opacity: 0.5,
		smoothFactor: 1
	});
}

function drawPreictedLine(points_list){ 
	
	//TODO "ZOOM SIZE" of line -- so you can't see it when you zoom out?
	return new L.Polyline(points_list, {
		color: "green",
		weight: 3,
		opacity: 0.5,
		smoothFactor: 1
	});
}

function calculateFutureTrajectory(historical_data_list){ 
	//use last three trajectories combined with speed to create future pointlist
	//draw line of future pointlist 
	//add future line to lines
}

/**  Utils  **********************************************/
/**********************************************************/
/**********************************************************/
function setViewToPos(lat, lng){
    if((lat === undefined) || (lng === undefined)){
        // Default: Portsmouth Latitude and Longitude
        map.setView([50.785237, -1.29687], 10);
    } else{
        map.setView([lat, lng], 12);
    }
}

function ownShipRecenter() {
    if(ajax_own_ship_data === undefined){
        setViewToPos(undefined, undefined);
    } else {
        ship = ajax_own_ship_data[ajax_own_ship_data.length - 1];
        setViewToPos(ship.latitude, ship.longitude);
    }
}

function formatNumber(number){ 
	return ("0" + number).slice(-2);
}

function setOSDDLen(){ 
	osdd_len = ajax_own_ship_data.length - 1;
}
/**  Events  **********************************************/
/**********************************************************/
/**********************************************************/
function addClickEvents(){
    centre_btn = document.getElementById("recenterbtn");
    centre_btn.addEventListener("click", ownShipRecenter);
	
	trajectories_btn = document.getElementById("trajectoriesbtn");
	trajectories_btn.addEventListener("click", invertTrajectories);
}

function invertTrajectories(){ 
	show_trajectories = !show_trajectories;
}

function onMapClick(e) {
    popup.setLatLng(e.latlng).setContent(
    "This Location: " + 
    e.latlng.toString()).openOn(map);
}