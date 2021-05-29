CREATE TABLE testers(testerId BIGINT PRIMARY KEY, firstName VARCHAR(255) NOT NULL, lastName VARCHAR(255) NOT NULL, country CHAR(2) NOT NULL, lastLogin TIMESTAMP) AS SELECT * FROM CSVREAD('classpath:initialData/testers.csv');

CREATE TABLE devices(deviceId BIGINT PRIMARY KEY, description VARCHAR(255) NOT NULL) AS SELECT * FROM CSVREAD('classpath:initialData/devices.csv');

CREATE TABLE bugs(bugId BIGINT PRIMARY KEY, deviceId BIGINT NOT NULL REFERENCES devices, testerId BIGINT NOT NULL REFERENCES testers) AS SELECT * FROM CSVREAD('classpath:initialData/bugs.csv');

CREATE TABLE tester_device(testerId BIGINT REFERENCES testers, deviceId BIGINT REFERENCES devices, PRIMARY KEY (testerId, deviceId)) AS SELECT * FROM CSVREAD('classpath:initialData/tester_device.csv');

CREATE INDEX idx_testers_country ON testers(country);

CREATE INDEX idx_devices_description ON devices(description);