#!/bin/bash

#* this script is intended to run inside the container at startup. do not execute it on host

cd /app
find . -name \*.class -type f -delete
javac *.java
java Game