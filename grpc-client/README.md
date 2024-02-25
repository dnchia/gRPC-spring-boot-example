![Spring](https://img.shields.io/badge/Spring-6DB33F?style=rounded&logo=spring&logoColor=white) ![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=rounded&logo=Apache%20Maven&logoColor=white) ![Java](https://img.shields.io/badge/Java-ED8B00?style=rounded&logo=openjdk&logoColor=white)
# grpc-client
This project serves as an example of the generation and usage of a grpc-client.
That needs an existing example, provided in the project, that must be the same used on the server side.

### Requirements
Maven and Java 21 are used on this project, however, any 9+ Java version should be compatible.

### Usage
#### Compile using:
```
mvn clean package
```
or
```
mvn clean install
```
to have installed in the local repository.

**To execute, grpc-server must be up and running.**
In GrpcClientApplication exists a method call that can be uncommented to execute with the jar.

However, the use cases are on the tests.

#### Execute using Maven:
```
mvn spring-boot:run
```

or

#### Execute using Java directly (modify to include the correct version):

```
cd target

java -jar grpc-client-X.X.X.jar
```

