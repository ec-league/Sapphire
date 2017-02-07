#!/bin/bash

mvn clean cobertura:cobertura -s settings.xml -Ptravis

bash <(curl -s https://codecov.io/bash)

mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent package sonar:sonar -s settings.xml
