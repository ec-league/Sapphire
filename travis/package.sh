#!/bin/bash

mvn clean cobertura:cobertura -s settings.xml -Ptravis

bash <(curl -s https://codecov.io/bash)

mvn sonar:sonar
