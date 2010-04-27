echo "export JAVA_HOME=$ORACLE_HOME/jdk" >> ~/.bash_profile

. ~/.bash_profile

echo $JAVA_HOME

cd $ORACLE_HOME/oc4j/j2ee/isqlplus/application-deployments/isqlplus

$JAVA_HOME/bin/java -Djava.security.properties=$ORACLE_HOME/oc4j/j2ee/home/config/jazn.security.props -jar $ORACLE_HOME/oc4j/j2ee/home/jazn.jar -user "iSQL*Plus DBA/admin" -password welcome -shell 
