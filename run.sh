#!/bin/bash

#* execute the build.sh script before this one
current_dir=$(pwd)

docker run --rm -i --name java-game \
    -v $current_dir:/app \
    --add-host host.docker.internal:host-gateway -v /tmp/.X11-unix:/tmp/.X11-unix \
    strategy_game:jdk8

#* run below command in seperate terminal on host before running container
# socat TCP-LISTEN:6000,reuseaddr,fork UNIX-CLIENT:/tmp/.X11-unix/X0