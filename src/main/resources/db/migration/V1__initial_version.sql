CREATE TABLE gps_location (
  id BIGINT AUTO_INCREMENT,
  user_id INT,

  latitude DOUBLE,
  longitude DOUBLE,

  device_id VARCHAR(255),
  model VARCHAR(255),
  battery_level VARCHAR(10),
  ip_address VARCHAR(255),
  gps_timestamp DATETIME,

  created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

  PRIMARY KEY(id)
)
