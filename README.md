# Spring Boot Kafka Example

This sample SpringBoot application shows how to use basic configuration to set up a producer and consumer.
It uses Gradle for build and dependency management.

## Key Components
* REST Controller
* Kafka Consumer

## Rest Controller
### POST endpoint
* Entry point that creates and produces Command Messages for downstream processing
* Params: `repeatTimes` - default value of 1
* Request Pattern: `curl --location --request POST 'http://localhost:8080/messages?repeatTimes=1' \
  --header 'Content-Type: application/json' \
  --data-raw '{
  "firstName": "John",
  "lastName": "Smith",
  "age": "25"
  }'`
* Response Pattern: 200 OK

## Kafka Consumer
* Central listener configuration that ties processing functionality to message topics
* Demonstrates standard message driven architectural flows

## Docker Desktop
1. HomeBrew: https://formulae.brew.sh/cask/docker
2. Mac: https://docs.docker.com/desktop/mac/install/

## Kafka Tooling
1. Offset Explorer: https://formulae.brew.sh/cask/offset-explorer
2. Kafka CLI (provided via install): https://formulae.brew.sh/formula/kafka

## jENV
1. Documentation: https://www.jenv.be/
2. Installation: https://formulae.brew.sh/formula/jenv

## Java 11
The application should be run using JDK 11. Follow these steps for developer setup:

1. Install OpenJDK 11
  1. `brew tap adoptopenjdk/openjdk`
  2. `brew install adoptopenjdk11`
  3. JDK 11 should appear under `/Library/Java/JavaVirtualMachines`
2. Set JDK 11 as the Project SDK in your IntelliJ
  1. Go to File > Project Structure
  2. Under 'SDKs,' add JDK 11 to your list of SDKs (if you haven't already)
  3. Under 'Project,' select JDK 11 as the Project SDK
3. For command line usage, use `jenv` to set the local version of JDK
  1. `brew install jenv`
    - After install make sure you have the following added to your startup script (`~/.bashrc`, `~/.bash-profile`, `~/.zshrc`, etc, run `source` if necessary to refresh terminal):
      ```
          export PATH="$HOME/.jenv/bin:$PATH"
          eval "$(jenv init -)"
      ```
  3. `jenv add /Library/Java/JavaVirtualMachines/adoptopenjdk-11.jdk/Contents/Home/`
  4. Navigate to the application project directory and run `jenv local 11`
  5. Run `java -version` to verify shell is using Java 11
  6. If the version is not correct, ensure you don't have JAVA_HOME/bin set in your PATH via your startup script (`~/.bashrc`, `~/.bash-profile`, `~/.zshrc`, etc)
    - Remove this
    - Rerun your startup script e.g. `. ~/.zshrc` or start a new terminal
    - Execute 3.3 and 3.4 again
4. If you run into issues in IntelliJ, try the following steps:
  1. Go to File > Project Structure
  2. Under 'Modules', ensure that the language level is at 11 for all modules.
  3. If this doesn't work, the "nuclear option" is to exit IntelliJ and delete your `.idea` folder from the root.
    * This will remove any build configs, custom settings for this project, so be sure to take a backup if you want, or be prepared to re-create the run configs.
    * It will take a while for IntelliJ to start up the next time you enter, as it will need to re-index, etc.

## Docker
This code includes a `docker-compose.yml` file, so you can use Docker Compose to start up Kafka without installing anything.


### Kafka Setup
The application is nice enough to establish topics as it is stands up, but the topics will be created with a single partition.
This is useful when running a single instance of the application, but is not overly valuable when running multiple instances since
partitions are assigned to only one instance of the application.

A script has been provided to overcome this limitation, but it has a prerequisite of having kafka tooling installed on your machine (see Kafka CLI in Kafka Tooling section).

Once the prerequisite has been met, execute `./docker/kafka/createTopics.sh`

This will leverage the Kafka CLI to generate the required topics with 10 partitions each.

## Gradle
Gradle is the tool that is used to manage dependencies and to build artifacts

### Build Project
1. Open terminal and navigate to project directory
2. Execute `./gradlew bootJar`
    1. Generates `.jar` artifact
    2. Location: `build/libs/demo-0.0.1-SNAPSHOT.jar`

## Run Application from Command Line
1. Open terminal and navigate to project directory
2. Launch
   1. Open terminal and navigate to project directory
   2. Execute `java -jar build/libs/demo-0.0.1-SNAPSHOT.jar`
   3. By default, it will launch the application using port 8080
3. Launch using specific port
   1. When port 8080 is in use, or when executing multiple instances of the application
   2. Execute `java -jar -Dserver.port=[desired port number] build/libs/demo-0.0.1-SNAPSHOT.jar`
4. If code is adjusted the `.jar` file must be regenerated using `./gradlew bootJar`, otherwise you'll feel ashamed after an hour of wondering why your changes did not take effect

### Run Application from IntelliJ
Navigate to `com.example.demo.Application` in the project explorer. Right-click on the file and select `Run 'Application.main()'`
This will launch the application from the IDE. The application can also be launched in debug mode by right-clicking the file and selecting `Debug 'Application.main()'`.  

