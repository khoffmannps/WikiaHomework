WikiaHomework
=============
#####Run the test from the command line:

* Download and install Java Developers Kit and Maven on your machine
* Be sure to have Firefox installed (I used Firefox 31)
* Download WikiaHomework repository, open command line, cd to the repository and type "mvn test"
##### Run with different browsers

Tests will by default run with Firefox. To run with Chrome, do the following:

* Be sure to have Chrome installed on you computer (I used version 37.0.2062.120 m)
* Download ChromeDriver appropriate for your system from http://chromedriver.storage.googleapis.com/index.html (I used version 2.9)
* In repository in src/main/resources find file wikiahomework.properties and edit the following properties:
- set chromedriverpath to point to chromedriver in your filesystem
- set drivertype to "chrome"
* To return to using Firefox, change back property "drivertype" to "firefox"
