# Background story and motivation behind the application

Oracle Corporation provides excellent management tools for all of its products. Every software of Oracle has its own web-based management console and there is also a comprehensive solution (Oracle Enterprise Manager Grid Control) with which almost the whole infrastructure of an enterprise can be monitored.

The problem in my case is that as a developer I ofter do not have to access these excellent monitoring tools.

Thus information about incidents can be gathered posteriorly when it's too late and when business continuity has been already hampered for instance by a slowdown or a storage issue.

I started to develop this app when I was working with a legacy reporting system, which usually crashed every month or at best every two month. Its problem was in most cases that the usual monthly data processing strain the system with enormous amount of I/O operations (because of an unusable index or unanalyzed table) and overwhelmed its other neighbor systems.

Unfortunately I wasn't allowed to use any of fancy tools Oracle provides, so I had to figure out what the problem was with a pile of hand-written, ad-hoc SQLs using Oracle’s internal views.

After quite a few investigated my scripts slowly evolved to this application and it helped me realize if a crash was in the making and also provided a simple user interface for my favorite performance related queries.

# Used technologies and features

* Oracle Database 10g/11g
* Oracle Glassfish V3
* JDK 1.6
* JPA (Hibernate)
* JMX for monitoring
* JMS for recording audit trail asynchronously
* EJB 3
* GWT 2.4 for the user interface
* Velocity 1.5 (for generating e-mails about alerts)

# Functionality

At this time the user interface is in embryo, only the following screens are functional.

* Basic information of the database instance we are currently connected to.
* CPU and I/O usage history of the instance for the last one hour in 5 minutes snapshots.
* Top-N session ordered by CPU usage
* Top-N session ordered by IO usage
* Detailed lists about tablespaces and data files

# Planned features for future releases

### Further improvements

* Support RAC installations by using GV$ views instead of the traditional V$ views.
* Support accessing alert.log using a brand new dynamic performance view (X$DBGALERTEXT) which is a 11g feature.
* Include wicked alert.log messages in notifications e-mails.

### The application can send notification e-mails to a defined set of people about the following events.

* If the amount of renaming space (measured in percentage) of a particular tablespace is less than a given threshold.
* If the percentage CPU usage on the target system is higher than a given threshold.
* If the amount IO operations is higher than a given threshold.
