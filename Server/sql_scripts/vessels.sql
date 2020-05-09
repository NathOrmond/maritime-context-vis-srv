CREATE TABLE IF NOT EXISTS vessels( 
	vessel_id BIGSERIAL NOT NULL PRIMARY KEY,
	vessel_name VARCHAR(120) NOT NULL,
	vessel_type VARCHAR(50) NOT NULL, 
	vessel_info VARCHAR(120)
);