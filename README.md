This is a TAF example for API and UI tests

Resources for testing:
API: https://reqres.in/
UI: https://www.saucedemo.com/

PRECONDITIONS:

In order to run tests you should have:
1. local postgres DB (see application.yml)
2. local RabbitMQ 
3. local Jenkins


API TESTS:

In order to run tests from terminal, use the following command:
 ./gradlew :requres-pipeline-tests:clean :requres-pipeline-tests:test --info

In order to run tests from terminal with Allure, use the following command:
./gradlew :requres-pipeline-tests:clean :requres-pipeline-tests:test --info :requres-pipeline-tests:allureReport :requres-pipeline-tests:allureServe

In order to run tests from terminal with tags, use the following command:
 ./gradlew :requres-pipeline-tests:clean :requres-pipeline-tests:test --info '-Dcucumber.filter.tags="{TEST_TAG}"'  

In order to run tests from terminal with tags and with Allure, use the following command:
./gradlew :requres-pipeline-tests:clean :requres-pipeline-tests:test --info '-Dcucumber.filter.tags="{TEST_TAG}"' :requres-pipeline-tests:allureReport :requres-pipeline-tests:allureServe


UI TESTS:

In order to run tests from terminal, use the following command:
./gradlew :saucedemo-ui-pipeline-tests:clean :saucedemo-ui-pipeline-tests:test --info

In order to run tests from terminal with Allure, use the following command:
./gradlew :saucedemo-ui-pipeline-tests:clean :saucedemo-ui-pipeline-tests:test --info :saucedemo-ui-pipeline-tests:allureReport :saucedemo-ui-pipeline-tests:allureServe

In order to run tests from terminal with tags, use the following command:
./gradlew :saucedemo-ui-pipeline-tests:clean :saucedemo-ui-pipeline-tests:test --info '-Dcucumber.filter.tags="{TEST_TAG}"'

In order to run tests from terminal with tags and with Allure, use the following command:
./gradlew :saucedemo-ui-pipeline-tests:clean :saucedemo-ui-pipeline-tests:test --info '-Dcucumber.filter.tags="{TEST_TAG}"' :saucedemo-ui-pipeline-tests:allureReport :saucedemo-ui-pipeline-tests:allureServe