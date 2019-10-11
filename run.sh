#!/usr/bin/env bash

mvn -V clean package -T 4 && \
docker-compose up --abort-on-container-exit

#(cd target/chrome && allure serve)
#(cd target/firefox && allure serve)