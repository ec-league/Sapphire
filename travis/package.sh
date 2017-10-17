#!/bin/bash

mvn clean install cobertura:cobertura -s settings.xml -Ptravis

bash <(curl -s https://codecov.io/bash)
