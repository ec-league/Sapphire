#!/bin/bash

mvn clean install -s settings.xml -Ptravis

bash <(curl -s https://codecov.io/bash)
