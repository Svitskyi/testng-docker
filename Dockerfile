FROM openjdk:8-jre-slim

# Add the project jar & copy dependencies
ADD  target/testng-docker.jar testng-docker.jar
ADD  target/testng-docker-tests.jar testng-docker-tests.jar
ADD  target/libs libs

# Add the suite xmls
ADD target/test-classes/testng.xml testng.xml

# Command line to execute the test
# Expects below ennvironment variables
# BROWSER = chrome / firefox
# MODULE  = order-module / search-module

ENTRYPOINT java -cp testng-docker.jar:testng-docker-tests.jar:libs/* -javaagent:"/libs/aspectjweaver-1.9.4.jar" -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000 -Dbrowser=$BROWSER org.testng.TestNG $MODULE
