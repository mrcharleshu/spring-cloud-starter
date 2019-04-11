#!/bin/sh

function runProfile() {
    profile=$1
    nohup mvn spring-boot:run -P${profile} &
}

runProfile service-1
sleep 10
runProfile service-2
sleep 10
runProfile service-3
sleep 10
runProfile service-4