#!/bin/bash

mvn clean cobertura:cobertura -s settings.xml -Ptravis

bash <(curl -s https://codecov.io/bash)

mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent package sonar:sonar -s settings.xml -Dsonar.core.codeCoveragePlugin=jacoco -Dsonar.jacoco.reportPath=tests/jacoco-exec/jacoco.exec -Dsonar.dynamicAnalysis=reuseReports -Dsonar.surefire.reportsPath=tests/test-reports
