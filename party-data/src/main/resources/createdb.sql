CREATE PLUGGABLE DATABASE partypdb
ADMIN USER partyadm IDENTIFIED BY tiger
STORAGE (MAXSIZE 2G)
DEFAULT TABLESPACE party
DATAFILE '/opt/oracle/oradata/ORCLCDB/partypdb/party01.dbf' SIZE 250M AUTOEXTEND ON
PATH_PREFIX = '/opt/oracle/oradata/ORCLCDB/partypdb/'
FILE_NAME_CONVERT = ('/opt/oracle/oradata/ORCLCDB/pdbseed/', '/opt/oracle/oradata/ORCLCDB/partypdb/');

GRANT CONNECT TO partyadm;