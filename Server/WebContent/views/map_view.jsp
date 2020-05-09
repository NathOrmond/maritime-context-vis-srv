<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Map View</title>

	<!-- JQUERY -->
	<script	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	
	<!-- CSS Styling -->
	<link rel="stylesheet" href="./css/map_theme.css" type="text/css" />
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" 	href="https://unpkg.com/leaflet@1.6.0/dist/leaflet.css" integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ==" crossorigin="" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="./css/MarkerCluster.css" type="text/css" />
	<link rel="stylesheet" href="./css/MarkerCluster.Default.css" type="text/css" />

	<!-- JS -->
	<script src="https://unpkg.com/leaflet@1.6.0/dist/leaflet.js" integrity="sha512-gZwIG9x3wUXg2hdXF6+rVkLF/0Vi9U8D2Ntg4Ga5I5BZpVkVxlJWbSQtXPSiUTtC0TjtGOmxa1AJPuV0CPthew==" crossorigin=""></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<script src="./scripts/map/marker_rotate.js"> </script>
	<script src="./scripts/map/leaflet.markercluster.js"> </script>
	
</head>

<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark" role="navigation">

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="/Server">Home<span class="sr-only">(current)</span></a></li>
				<li class="nav-item"><a class="nav-link" href="#">Views</a></li>
				<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Options </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="#">Heat Maps</a> 
						<a class="dropdown-item" href="#">Shipping Lanes</a>
					</div>
				</li>
				<li class="nav-item"><a class="nav-link" href="#">Meta</a></li>
			</ul>
		</div>
	</nav>

	<div class="padding" id="map-container">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-8">
					<div id="map"></div>
				</div>
				<div class="col">
					<div class="col-md-4">
						<h2>Options</h2>
					</div>
					<br>
					<div class="col-md-4">
						<button id="recenterbtn" type="button" class="btn btn-primary btn-block">Centre</button>
					</div>
					<br>
					<div class="col-md-4">
						<button id="trajectoriesbtn" type="button" class="btn btn-primary btn-block">Trajectories</button>
					</div>
					<br>
					<div id="slider" class="col-md-4">
						<input class="btn-block" id="rangeinput" type="range" min="50" max="1000" value="475"/>
					</div>
				</div>
			</div>
		</div>
	</div>
	


	<!-- ECMAScripts -->
	<script src="./scripts/map/map_view.js"></script>

</body>

<!-- Footer -->
<footer class="page-footer font-small unique-color-dark pt-4">



</footer>
<!-- Footer -->
</html>
