#!/bin/bash

mvn clean cobertura:cobertura -s settings.xml -Ptravis

bash <(curl -s https://codecov.io/bash)

mvn clean install -s settings.xml

mvn sonar:sonar -s settings.xml
