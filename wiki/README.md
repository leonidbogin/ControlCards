This version doesn't have try/catch blocks. 
If the database in the file is connected incorrectly hibernate.cfg.xml or if the table structure in the database is incorrect, an error of 500 will occur.
---------------------------------------------------------------------------------
ojdbc6 is installed via pom. You need to install it manually via Maven.
mvn install:install-file -Dfile=ojdbc6.jar -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0 -Dpackaging=jar
---------------------------------------------------------------------------------
