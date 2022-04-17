# Homework 2
1. To run tests for Android app type:
```
mvn clean test -P native
```
2. To run tests for Chrome search type:
```
mvn clean test -P web 
```
# Homework 3
## Preparation
Before running the tests, configure the url to connect to Epam Mobile Cloud or Sauce Labs.
To do this, enter the following data in [authorization.properties](src/test/resources/properties/authorization.properties):
1. To connect to EPAM Mobile Cloud, write project.name.epam and token.epam from your personal account Epam Mobile Cloud.
2. To connect to South Labs, write username.sauceLabs and token.sauceLabs from your personal account Sauce Labs.
## Running on Epam Mobile Cloud
1. To run tests for Android app type:
```
mvn clean test -P cloudNativeAndroid
```
2. To run tests for iOS app type:
```
mvn clean test -P cloudNativeiOS
```
3. To run tests for Google search on Android type:
```
mvn clean test -P cloudWebAndroid
```
4. To run tests for Google search on iOS type:
```
mvn clean test -P cloudWebiOS
```
## Running on Sauce Labs
1. To run tests for Android app type:
```
mvn clean test -P sauceLabsNativeAndroid
```
2. To run tests for iOS app type:
```
mvn clean test -P sauceLabsNativeiOS
```
3. To run tests for Google search on Android type:
```
mvn clean test -P sauceLabsWebAndroid
```
4. To run tests for Google search on iOS type:
```
mvn clean test -P sauceLabsWebiOS
```
