# Temperature Converter

A Java 21 console application that converts temperatures and supports English, Finnish, Swedish, and Japanese.

## Features

- Convert Fahrenheit to Celsius.
- Convert Celsius to Fahrenheit.
- Detect extreme temperatures below −40°C or above 50°C.
- Select a language when the application starts.
- Accept a decimal point or decimal comma.
- Reject invalid numbers, NaN, and infinite values.
- Display results with two decimal places using the selected locale.

The `TemperatureConverter` class also provides a Kelvin-to-Celsius method. This conversion is not included in the console menu.

## Technologies

- Java 21
- Maven
- JUnit 5
- JaCoCo
- Jenkins
- Docker

## Project Structure

```text
Temperature_Console/
├── src/
│   ├── main/
│   │   ├── java/org/example/
│   │   │   ├── LocalizationApp.java
│   │   │   └── TemperatureConverter.java
│   │   └── resources/
│   │       ├── MessagesBundle_en_US.properties
│   │       ├── MessagesBundle_fi_FI.properties
│   │       ├── MessagesBundle_sv_SE.properties
│   │       └── MessagesBundle_ja_JP.properties
│   └── test/
│       └── java/org/example/
│           ├── LocalizationAppTest.java
│           └── TemperatureConverterTest.java
├── Dockerfile
├── Jenkinsfile
├── pom.xml
└── README.md
```

## Prerequisites

- JDK 21
- Maven
- Docker Desktop or Docker Engine for container execution
- Jenkins for running the pipeline

## Build and Run

Clone the repository:

```bash
git clone https://github.com/Khaleddamascene/Temperature_Console.git
cd Temperature_Console
```

Build the application, run tests, and generate coverage:

```bash
mvn clean verify
```

Run the application:

```bash
java -cp target/Temperature_Console-1.0-SNAPSHOT.jar org.example.LocalizationApp
```

## Localization

Choose a language at startup:

| Option | Language | Locale |
|---|---|---|
| 1 | English — default | en_US |
| 2 | Finnish | fi_FI |
| 3 | Swedish | sv_SE |
| 4 | Japanese | ja_JP |

Resource bundles are located in `src/main/resources/`.
Save all `.properties` files using UTF-8 encoding.

The application uses `ResourceBundle` to load translated messages and locale-aware formatting for numeric results.

### Japanese Example

Enter the following values when prompted:

```text
4
1
32
```

These select Japanese, Fahrenheit-to-Celsius conversion, and 32°F.

Expected result:

```text
摂氏の温度: 0.00 °C
```

Use a UTF-8-capable terminal and a font that supports Japanese characters.

## Unit Tests

Run the tests:

```bash
mvn test
```

Tests cover:

- Multiple Fahrenheit-to-Celsius inputs.
- Multiple Celsius-to-Fahrenheit inputs.
- Kelvin-to-Celsius conversion.
- Extreme-temperature boundaries and values inside and outside the limits.
- Language selection and default-language fallback.
- Console conversions and decimal-comma input.
- Invalid menu choices and invalid numeric input.

Exactly −40°C and 50°C are not considered extreme temperatures.

## JaCoCo Coverage

Generate the coverage report:

```bash
mvn clean verify
```

Open:

```text
target/site/jacoco/index.html
```

The verified report showed 100% line and branch coverage across the reported application classes. Coverage may change as the source code or tests change.

## Docker

Build the application before building the Docker image:

```bash
mvn clean verify
docker build -t temperature-console .
```

Run the container interactively:

```bash
docker run --rm -it temperature-console
```

The `-it` flags enable console input. The container exits after one conversion, and `--rm` removes the stopped container.

The Dockerfile uses a Java 21 runtime and starts:

```text
org.example.LocalizationApp
```

### Manual Docker Hub Publishing

Replace `YOUR_DOCKERHUB_USERNAME` with your Docker Hub username:

```bash
docker login
docker tag temperature-console YOUR_DOCKERHUB_USERNAME/temperature-console:latest
docker push YOUR_DOCKERHUB_USERNAME/temperature-console:latest
```

These commands publish manually. Automated Docker build and push stages still need to be added to the Jenkins pipeline.

## Jenkins Pipeline

The current pipeline:

1. Checks out the GitHub repository.
2. Runs `mvn clean verify` to compile, test, package, and generate coverage.
3. Publishes JUnit test results.
4. Publishes JaCoCo coverage.
5. Archives the HTML coverage report and application JAR.

### Jenkins Setup

- Configure a Maven installation named `Maven3`.
- Ensure the build agent uses JDK 21.
- Install the plugins required for Git, Pipeline, JUnit, and the `jacoco` step.
- Create a Pipeline job using this repository and `Jenkinsfile`.
- Run **Build Now**.

The current Jenkinsfile uses Windows `bat` commands. For a Linux or macOS agent, replace them with `sh`.

To complete automated Docker Hub publishing, the Jenkins agent also needs Docker access and securely configured Docker Hub credentials.

## Submission Evidence

Submit via Oma:

1. Screenshots showing successful unit tests and the JaCoCo coverage report.
2. A screenshot of the application running in a non-default language, such as Japanese.
3. The public GitHub repository link.

## Repository

https://github.com/Khaleddamascene/Temperature_Console