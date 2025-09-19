# LoadBalancer

A basic layer 4 load balancer, that accepts requests from many clients and distributes these requests among many servers.
It currently uses a simple round-robin algorithm to select the next available server from a list of servers.   
If it detects that a server is no longer available it will remove this server from the list of available servers.

## Prerequisites 
- Java 21
- Maven
- A unix environment (Windows Subsystem For Linux, MacOS, Linux)
- Netcat or some other program to simulate sending and receiving network requests

## Tech Stack
- Java 21
- Maven for managing dependencies  - Tested with v3.8.7
- SLF4J & logback for logging
- Junit & Mockito for testing

## Building
- `mvn clean install`
  - Removes the target directory before re compiling and packages the application in JAR file and adds the JAR to the local maven repository.
  This will also run the tests and all static analysis tools described below

## Running
1. Build the application with `mvn clean install`
2. Open 3 separate terminal instance to simulate 3 servers with netcat:
   - `nc -lk 8080` - Server 1
   - `nc -lk 8081` - Server 2
   - `nc -lk 8082` - Server 3
3. Start the application `java -jar target/LoadBalancer-1.0-SNAPSHOT.jar`
4. Open terminal instance to simulate sending requests from a client
   - `nc 127.0.0.1 3000` - Client 1
   - `nc 127.0.0.1 3001` - Client 2
   - `nc 127.0.0.1 3002` - Client 3
5. After creating either of the available clients we can enter some text in the client terminal and press enter, then finally cancel the netcat command to close the connection.

## Static Analysis
### Spotless
Spotless is a formatter, this application uses Google Java format.  
- `mvn spotless`
  - Runs the formatter, reporting on any violations.
- `mvn spotless:apply`
  - Runs the formatter, automatically fixing any issues.

### Checkstyle
Checks source code for coding issues, this application uses the Google rule set, with the addition of rules around the use of final.   
- `mvn checkstyle:check`
  - Runs checkstyle, reporting on any violations.

### Spotbugs
Checks compiled bytecode for coding issues;
- `mvn spotbugs:spotbugs`
  - Runs spotbugs, reporting on any violations.


### PMD
Checks source code for coding issues
- `mvn pmd:pmd`
  - Runs pmd, reporting on any violations

## Tests
- `mvn clean test`

## Documentation
- `mvn javadoc:javadoc`
  - will generate API documentation which can be accessed from `target/reports/apidocs/index.html`

## Application Properties
TODO - fill out after implementing task 10 below


### Next Steps for V1
1. Add error handling/validation then update tests
2. Update app event loop so it no longer uses an infinite loop
3. Update documentation & integrate javadoc
4. Migrate to virtual threads
5. Migrate to Java 25 LTS
6. Enhance logging
7. Add graceful shutdown 
8. Add health check handler
9. Update server availability checker to use PING & re add a server if the server recovers
10. Add configuration file for storing client & server details
11. Add mvnw
12. Containerise 
13. Created a weighted load balancer
14. Finish writing tests