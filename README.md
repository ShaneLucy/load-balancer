# LoadBalancer

## Prerequisites 

## Tech Stack

## Building

## Running
nc -lk server port for server
nc 127.0.0.1 client port for client
## Application Properties

### TODOs
1. Finish writing tests
2. Add error handling/validation then update tests
3. Update app event loop so it no longer uses an infinite loop
4. Update documentation & integrate javadoc
5. Migrate to virtual threads
6. Migrate to Java 25 LTS
7. Enhance logging
8. Add graceful shutdown 
9. Add health check handler
10. Add configuration file for storing client & server details
11. Add mvnw
12. Containerise 
13. Created a weighted load balancer
14. Refactor load balancer creation to use design pattern - probably factory