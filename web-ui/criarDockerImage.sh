#!/bin/bash

ng build --prod

docker build -t web-ui .