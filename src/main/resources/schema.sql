DROP TABLE IF EXISTS phonenumbers;
  
CREATE TABLE phonenumbers (
  id INT PRIMARY KEY,
  number VARCHAR(250) NOT NULL,
  isvalid BIT NOT NULL,
  correctionorerror VARCHAR(250) DEFAULT NULL
);