create table SCHEDULE_TB(
ID_SCHEDULE bigint(50) NOT NULL AUTO_INCREMENT,
SCHEDULE_NAME   varchar(60) NOT NULL,
DESCRIPTION   varchar(2000) NOT NULL,
STATUS			varchar(15),
DT_REGISTER  TIMESTAMP,
DT_UPDATE  TIMESTAMP,
PRIMARY KEY (`ID_SCHEDULE`));