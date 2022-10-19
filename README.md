# selenium-assignment

Introduction about the framework:

Design: Page Factory
Language: Java
TestRunner: TestNG
Report: Allure Report
Logging: util.logging

Folder Structure:

allure-reports: Contains the html report

drivers: contains the driver(chrome)

src/main/java/base:

    1. TestBase - Base class for test contains the test configurations like driver initiation,teardown,etc
    2. TestConfiguration - Reads the value from property file
    3. TestListener - Class file for testng listeners to add screenshots,etc
    
src/main/java/pages:

    1. AmazonPage - Class file contains all elements for the page with Page Factory design and related actions for that page
    
tes/java/com/selenium/assignment:

    1. AssesmentTest - test class file which call the actions from the page class
    
    Used testng annonation priority for running the tests

config.properties: Property file to maintain the test config data

docker-compose.yml: Docker compose file to have standalone chrome to run via docker.
    
    In Config.Properties file change browser name to "remote"
    
    Run docker-compose up -d command before running the test
    
    Now run the test with below command
    

How to run Tests?

Run the below command: mvn test

Generate Report: allure serve
