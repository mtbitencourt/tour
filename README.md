Xpto Tour Project
==============================


Compile
=======

This application is compiled by [Maven](http://maven.apache.org/) and run over [TomEE](http://tomee.apache.org/) Web Profile.

### Compile and package the application

    mvn package

### Warning

### Run only unit tests

    mvn test

### Run unit and integration tests

	mvn test-integration -P integration

Obs: only integration tests it's not possible (yet), the unit tests will always run together

### Complete build with compile, package, test and check code

	mvn verify -P integration

### Run the application

    mvn tomee:run

Stop the application with `CTRL+C`

Obs: The application needs to be packaged first

### Accessing a running application

    http://localhost:8080/xptotour/

### Initial data

the application runs with a in memory database and the data will be randomly generated in the startup

###

The `Ajuda` button shows the users who can do logon in the application using the form in main tab

Also can logon using other form in the second tab with
	email: admin@email.org
	password: admin
