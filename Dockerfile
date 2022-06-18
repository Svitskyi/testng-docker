FROM openjdk:8-jre-slim

ADD  target/testng-docker.jar testng-docker.jar
ADD  target/testng-docker-tests.jar testng-docker-tests.jar
ADD  target/libs libs

ADD target/test-classes/e2e.xml e2e.xml

ARG aspectjVersion
ENV aspectjVersion ${aspectjVersion}

ENTRYPOINT java -cp testng-docker.jar:testng-docker-tests.jar:libs/* -javaagent:"/libs/aspectjweaver-$aspectjVersion.jar" -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000 -Dbrowser=$BROWSER -Dlanguage=$LANGUAGE org.testng.TestNG "e2e.xml"
