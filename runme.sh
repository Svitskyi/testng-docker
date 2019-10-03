#!/usr/bin/env bash

#sudo apt-get install npm maven docker && npm install -g allure-commandline

mvn clean package && \

# docker run \
# -p 4444:4444 -p 5900:5900 --shm-size=1G \
# selenium/standalone-chrome-debug & && \

#docker run  -p 4444:4444 -p 5900:5900 --shm-size=1gb selenium/standalone-firefox-debug


# docker run \
# --net host \
# -v $( pwd )/target/allure-results:/allure-results \
# -e MODULE=testng.xml \
# -e BROWSER=chrome \
# testng-docker:latest && \
# (cd target/ && allure serve) \


 docker run \
 --net host \
 -v $( pwd )/target/allure-results:/allure-results \
 -e MODULE=testng.xml \
 -e BROWSER=firefox \
 testng-docker:latest && \
 (cd target/ && allure serve) \