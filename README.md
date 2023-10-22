<p>To start the app, have java 21 installed on classpath and run:<br>
<b> ./gradlew clean build -Pappid={api key} bootRun </b></p>
<p>To run test: <b> appid={api key} ./gradlew clean build</b></p>

<p>The service consist of 3 modules: <b>Homeweather, Openweather and Config</b><br>
Openweather connects to openweathermap.org and Homeweather talks to Openweather and reactively,in a non-blocking<br>
way saves the objects into db</p>