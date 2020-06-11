#!/bin/bash

./mvnw clean package

docker build -f docker/Dockerfile . -t tecrubesi-topic