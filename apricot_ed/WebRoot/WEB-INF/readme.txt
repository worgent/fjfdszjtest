先在server.xml配置数据源，然后在web.xml配置资源，例程如下：   
  server.xml:   
  <Context   path="/deoa"   docBase="F:/oaplatform/jsp"   debug="0"   
  reloadable="false"   crossContext="true">   
  <!--   <Resource   name="jdbc/myDB"   auth="Container"   type="javax.sql.DataSource"/>   
    <ResourceParams   name="jdbc/myDB">   
      <parameter>   
    <name>factory</name>   
    <value>org.objectweb.jndi.DataSourceFactory</value>   
      </parameter>   
    <parameter>   
  <name>driverClassName</name>   
  <value>org.gjt.mm.mysql.Driver</value>   
    </parameter>   
    <parameter>   
    <name>url</name>       
    <value>jdbc:mysql://localhost/deoa?user=root&amp;password=root_pwd&amp;useUnicode=true&amp;characterEncoding=gb2312</value>   
    </parameter>   
    <parameter>   
  <name>username</name>   
  <value>root</value>   
    </parameter>   
    <parameter>   
  <name>password</name>   
  <value>root_pwd</value>   
    </parameter>   
    <parameter>   
  <name>maxActive</name>   
  <value>50</value>   
    </parameter>   
    <parameter>   
  <name>maxIdle</name>   
  <value>20</value>   
    </parameter>   
    <parameter>   
  <name>maxWait</name>   
  <value>-1</value>   
    </parameter>   
    </ResourceParams>   -->   
    <Resource   name="UserTransaction"   auth="Container"   type="javax.transaction.UserTransaction"/>   
    <ResourceParams   name="UserTransaction">   
  <parameter>   
      <name>factory</name>   
      <value>org.objectweb.jotm.UserTransactionFactory</value>   
  </parameter>   
  <parameter>   
      <name>jotm.timeout</name>   
      <value>60</value>   
  </parameter>   
    </ResourceParams>   
  </Context>   
    
  web.xml:   
    
  <resource-ref>   
        <description>Datasource   Connecton</description>   
        <res-ref-name>jdbc/myDB</res-ref-name>   
        <res-type>javax.sql.DataSource</res-type>   
        <res-auth>Container</res-auth>   
    </resource-ref>   