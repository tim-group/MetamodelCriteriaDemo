This is a little project to demonstrate a few DSLs (by which i mean convenient APIs, probably with a fluent flavour) for querying data held in JPA_ persistence units.

This project is built using Gradle_, and is compatible with version 1.0.

The query APIs used are:

- JPQL (as a control)
- `JPA criteria queries`_ using a `static metamodel`_
- LIQUidFORM_
- QueryDSL_

Note that some of the APIs use generated code. This must be generated before the code can be compiled. This is accomplished with the following Gradle tasks:

- ``generateMetamodel`` - Generates the JPA2 static metamodel classes
- ``generateQueryDSL`` - Generates the QueryDSL query types

The build script makes the standard ``compileJava`` task dependent on these, so a normal build will run them. If you want to work in an IDE, you should run these tasks (or a full build) before opening the project.

In order to run this code, you will need a suitable database. The build script can set one up using the H2 database; run the ``applySql`` task to do this. This sets the database up at ``build/db/com.timgroup.jpa.h2.db``. Alternatively, you can generate the SQL DDL for the entity classes using the ``generateSql`` task, which produces ``build/sql/com.timgroup.jpa.sql``, and which you can apply to a database manually. 

Programs you can run are:

``com.timgroup.jpa.insert.CreateData``
	Inserts a load of test data into the database
``com.timgroup.jpa.insert.DumpData``
	Dumps the contents of the database
`com.timgroup.jpa.query.JPQLQueries`
	Runs queries written in JPQL
`com.timgroup.jpa.query.MetamodelCriteriaQueries`
	Runs queries written using the JPA static metamodel and criteria API
`com.timgroup.jpa.query.LiquidformQueries`
	Runs queries written using LIQUidFORM
`com.timgroup.jpa.query.TartedUpLiquidformQueries`
	Runs queries written using LIQUidFORM with a couple of helper functions to make them read better
`com.timgroup.jpa.query.QueryDSLQueries`
	Runs queries written using QueryDSL

Some interesting comparison of QueryDSL and the JPA criteria API can be found at <http://relation.to/10262.lace#comments> and <http://www.eclipse.org/forums/index.php/m/555770/>.

.. _JPA: http://docs.oracle.com/javaee/6/tutorial/doc/bnbpz.html
.. _Gradle: http://www.gradle.org/
.. _JPQL: http://docs.oracle.com/javaee/6/tutorial/doc/bnbtg.html
.. _JPA criteria queries: http://docs.oracle.com/javaee/6/tutorial/doc/gjitv.html
.. _static metamodel: http://docs.oracle.com/javaee/6/tutorial/doc/gjiup.html
.. _LIQUidFORM: http://code.google.com/p/liquidform/
.. _QueryDSL: http://www.querydsl.com/
