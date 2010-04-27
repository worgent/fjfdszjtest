#!/bin/sh



echo ------------------------------------------------------------------------------
hostname
ifconfig eth0
su - oracle -c "sqlplus /nolog" << EOF
conn / as sysdba
select  * from ABCDE;
archive log list
select  * from system.t1;
exit
EOF

su - oracle -c "rman target /" <<EOF
list incarnation of database;
EOF


echo -------------------------------------------------------------------------------

