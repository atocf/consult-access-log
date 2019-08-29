# Angular + Spring Boot 2.1.7.RELEASE

This example app shows how to build aCRUD app with Spring Boot 2.1.7.RELEASE, Spring Batch, PostgreSql, Lombok, Swagger 2.0 e Angular.

**Prerequisites:** [Java 8](https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html) and [Node.js](https://nodejs.org/). 

## Getting Started

To install this example application, run the following commands:

```bash
git clone https://github.com/atocf/consult-access-log
cd consult-access-log
```

This will get a copy of the project installed locally. To install all of its dependencies and start each app, follow the instructions below.

To run the API, cd into the `consult-client` folder and run:
 
```bash
./mvnw spring-boot:run
```

To run the client, cd into the `consult-api` folder and run:
 
```bash
npm install
```
```bash
npm start
```

## License

Apache 2.0, see [LICENSE](LICENSE).
