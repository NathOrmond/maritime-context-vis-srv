CREATE TABLE IF NOT EXISTS users (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	username VARCHAR(50) NOT NULL
); 




CREATE TABLE IF NOT EXISTS vessels( 
	vessel_id BIGSERIAL NOT NULL PRIMARY KEY,
	vessel_name VARCHAR(120) NOT NULL,
	vessel_type VARCHAR(50) NOT NULL, 
	vessel_info VARCHAR(120)
);




CREATE TABLE IF NOT EXISTS contacts( 
	contact_id BIGSERIAL NOT NULL PRIMARY KEY,
	date_time TIMESTAMP NOT NULL,
	latitude DOUBLE PRECISION NOT NULL,
	longitude DOUBLE PRECISION NOT NULL,
	cog FLOAT,
	heading FLOAT NOT NULL,
	depth INT,
	knots DOUBLE PRECISION
);




CREATE TABLE IF NOT EXISTS contact_vessels(
	contact_id BIGSERIAL NOT NULL REFERENCES contacts(contact_id) ON UPDATE CASCADE ON DELETE CASCADE,
	vessel_id BIGSERIAL NOT NULL REFERENCES vessels(vessel_id) ON UPDATE CASCADE ON DELETE CASCADE,
	PRIMARY KEY (contact_id, vessel_id)
);



INSERT INTO users (username) values ('test');
INSERT INTO users (username) values ('root');
INSERT INTO users (username) values ('captain');
INSERT INTO vessels(vessel_name, vessel_type, vessel_info) values ('the oil check', 'tanker', 'running drugs');
INSERT INTO vessels(vessel_name, vessel_type, vessel_info) values ('bismark', 'destroyer', 'avoid this big boii');
INSERT INTO vessels(vessel_name, vessel_type, vessel_info) values ('pyotr pyetrovich', 'russian submarine', 'you cant see me');


INSERT INTO vessels(vessel_name, vessel_type, vessel_info) values ('fisher-man', 'trawler', 'fighting spaniards #brexit');

INSERT INTO contacts (date_time, latitude, longitude, cog, heading, depth, knots) values ('2019-06-10T00:00:00',50.785237,-1.29687,257.97483,275.97483,87,4.386803712);
INSERT INTO contact_vessels(contact_id, vessel_id) values ((SELECT contact_id from contacts WHERE date_time='2019-06-10T00:00:00' AND latitude=50.785237), (SELECT vessel_id from vessels WHERE vessel_type='trawler'));

INSERT INTO contacts (date_time, latitude, longitude, cog, heading, depth, knots) values ('2019-06-10T00:00:01',50.7852313,-1.296900826,257.9748681,264.9748681,82,4.386803712);
INSERT INTO contact_vessels(contact_id, vessel_id) values ((SELECT contact_id from contacts WHERE date_time='2019-06-10T00:00:01' AND latitude=50.7852313), (SELECT vessel_id from vessels WHERE vessel_type='trawler'));

INSERT INTO contacts (date_time, latitude, longitude, cog, heading, depth, knots) values ('2019-06-10T00:00:02',50.7852256,-1.296931651,257.9749062,233.9749062,79,4.390684121);
INSERT INTO contact_vessels(contact_id, vessel_id) values ((SELECT contact_id from contacts WHERE date_time='2019-06-10T00:00:02' AND latitude=50.7852256), (SELECT vessel_id from vessels WHERE vessel_type='trawler'));

INSERT INTO contacts (date_time, latitude, longitude, cog, heading, depth, knots) values ('2019-06-10T00:00:03',50.7852199,-1.296962477,257.9749443,237.9749443,83,4.386803712);
INSERT INTO contact_vessels(contact_id, vessel_id) values ((SELECT contact_id from contacts WHERE date_time='2019-06-10T00:00:03' AND latitude=50.7852199), (SELECT vessel_id from vessels WHERE vessel_type='trawler'));