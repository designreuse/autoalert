Required steps to deploy Auto Alert application
=====

# Contents of the installation package

autoalert-dba Database and tablespace creating scripts.
autoalert-sql SQL scripts which create database schemas and objects
autoalert Java source files and compiled EAR

# Software requirements

* Oracle RDBMS 10g/11g
* Oracle Glassfish V3

* JDK 1.6
This guide shows the way of installation using UNIX tools but it can be accomplished similarly on a Windows box as well.

# Checkout source package

Unpack the package to a convenient location.

```
$ mkdir –p work/autoalert
$ cd work
$ svn --username youruser co https://svn.geekinaction.info/autoalert/tags/1.0.0 autoalert
```

# Create database schemas and objects

Check environment of Oracle RDBMS.

```
$ env | grep ^ORACLE
ORACLE_SID=ORCL102T
ORACLE_BASE=/usr/local/oracle
ORACLE_HOME=/usr/local/oracle/product/rdbms102
```

Create tablespaces

```
$ cd autoalert-dba
$ sqlplus / as sysdba
SQL> @create_tablespaces.sql /var/local/oracle/oradata/ORCL102T
```

When creating schemas and database objects, the script will ask for the password of the newly
created database users.

```
$ cd ../autoalert-sql
$ sqlplus / as sysdba
SQL> @install_autoalert.sql
# Create users
Password for AUTOALERT user ( default is nhz6mju7 ):
Password for AUTOALERT_JDBC user ( default is nhz6mju7 ):
...
```

Check for errors (it should return no output).

```
$ find . ­name "*.log" ­exec grep ­Hn "ORA" {} \;
```

Compile the application

For building the application Apache Maven 2 needs to be installed on the target machine.

```
$ cd ../autoalert
$ mvn package
...
[INFO] Building jar: /usr/lib/oracle/work/autoalert/autoalert/autoalert-ear/target/autoalert.ear
[INFO]
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary:
[INFO] ------------------------------------------------------------------------
[INFO] AutoAlert ............................................. SUCCESS [1.313s]
[INFO] Autoalert EJB module .................................. SUCCESS [3.659s]
[INFO] Autoalert Web module .................................. SUCCESS [1:00.280s]
[INFO] Autoalert EAR module .................................. SUCCESS [2.877s]
[INFO] ------------------------------------------------------------------------
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESSFUL
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 1 minute 8 seconds
[INFO] Finished at: Thu Jan 12 17:23:55 UTC 2012
[INFO] Final Memory: 39M/93M
[INFO] ------------------------------------------------------------------------
```

After Maven has finished assembling the application, notice the full path where the EAR file has
been placed.

# Create and prepare Glassfish domain

Create a new Glassfish domain for the application, the name of the newly created domain included in the application configuration. Should you want to create the new domain with a
different name, be aware of modifying the following files.

```
$ find . ! -name "*.svn*" -exec grep -li "autoalert-domain" {} \;
./autoalert-web/target/classes/log4j.xml
./autoalert-web/target/autoalert-web-1.0.0-RELEASE/WEB-INF/classes/log4j.xml
./pom.xml
```

Create the new domain.

```
$ asadmin create-domain autoalert-domain
Domain autoalert-domain created.
Domain autoalert-domain admin port is 4848.
Domain autoalert-domain admin user is "admin".
Command create-domain executed successfully.
```

Oracle’s JDBC driver should be placed to the domain’s lib/ext directory. Log4j must be placed to
the lib directory due to a Glassfish bug.

```
$ cp ~/.m2/repository/log4j/log4j/1.2.16/log4j-1.2.16.jar \
$GLASSFISH_HOME/domains/autoalert-domain/lib
$ cp $ORACLE_HOME/jdbc/lib/ojdbc14.jar \
$GLASSFISH_HOME/domains/autoalert-domain/lib/ext
```

Let’s start the new domain.

```
$ asadmin start-domain autoalert-domain
Waiting for DAS to start ............
Started domain: autoalert-domain
Domain location: /usr/lib/oracle/glassfish/3.0.1/glassfish/domains/autoalert-domain
Log file: /usr/lib/oracle/glassfish/3.0.1/glassfish/domains/autoalert-domain/logs/server.log
Admin port for the domain: 4848
Command start-domain executed successfully.
```

Create JDBC resources.

```
$ asadmin create-jdbc-connection-pool \
--datasourceclassname oracle.jdbc.pool.OracleDataSource \
--restype javax.sql.DataSource \
--property
User=autoalert:Password=nhz6mju7:URL=\"jdbc:oracle:thin:@10.100.8.203:1521:ORCL102T\" \
AA-CP
Command create-jdbc-connection-pool executed successfully.

$ asadmin create-jdbc-resource --connectionpoolid AA-CP jdbc/aaDS
Command create-jdbc-resource executed successfully.
```
