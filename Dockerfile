FROM openjdk:8-jre-slim

# Add the project jar & copy dependencies
ADD  target/testng-docker.jar testng-docker.jar
ADD  target/testng-docker-tests.jar testng-docker-tests.jar
ADD  target/libs libs

# Add the suite xmls
ADD target/test-classes/it.xml it.xml
ADD target/test-classes/e2e.xml e2e.xml

# Command line to execute the test
# Expects below ennvironment variables
# BROWSER = chrome / firefox
# LANGUAGE  = en / fr
# MODULE  = order-module / search-module

ARG aspectjVersion
ENV aspectjVersion ${aspectjVersion}

ENTRYPOINT java -cp testng-docker.jar:testng-docker-tests.jar:libs/* -javaagent:"/libs/aspectjweaver-$aspectjVersion.jar" -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000 -Dbrowser=$BROWSER -Dlanguage=$LANGUAGE org.testng.TestNG $MODULE
