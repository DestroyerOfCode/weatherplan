To start the app, ensure that Java 21 is installed on the classpath and run:

```bash
./gradlew clean build -Pappid={api key} bootRun
```

To run tests:

```bash
appid={api key} ./gradlew clean build
```

To make changes:

```zsh
./build-and-deploy
```

The service consists of 3 modules: Homeweather, Openweather, and Config.
Openweather connects to openweathermap.org, and Homeweather talks to
Openweather and reactively, in a non-blocking way, saves the
objects into the database.

Possible requests:
request to get all users and get all their respective current weather by
coords and send sms to their number

```bash
http POST :8083/twilio/bulk/send  
```

Get all current weather by city names located in resource file

```bash
http :8083/open/current/all
```

Save the current weather in db currentWeather which you fetch from open weather module

```bash
http POST :8083/home/current/save\?lat\=49.136372\&lon\=20.24386
```

Save the current weather in db currentWeather which you fetch from open weather module

```bash
http POST :8083/home/current/bulk/save
```

Get current weather by coords

```bash
http :8083/open/current\?lat\=20.25\&lon\=49.14
```