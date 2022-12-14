# Restaurant finder
This project is based upon Spring Boot, Docker and openApi.

## Structure
The project consists of four microservices:
- restaurant-service,
- food-service,
- configserver,
- eurekaserver,

and a small java library for common classes. In addition, the API is described via openApi and the environment is setup via docker.

The purpose of each microservice is described in the following chapters.

### configserver
The configserver is a microservice that is based on the Spring Cloud Config project. It provides nearly all configuration 
parameters for all other microservices. They can be stored in the filesystem or on github or in any other subversion tool 
or JDBC compatible database. In this project, they are stored in the filesystem.

The mapping from the configuration parameters to the suitable microservice is done via the parameter `spring.application.name`.
For instance, the configuration for the restaurant-service is stored in a file called `restaurant.yaml` and the spring.application.name
property in the microservice `restaurant-service` is `restaurant`.

Thus, all other microservices depend on the configserver. Therefore, it has to be started first. If we have a different 
configuration for a different profile (e.g. `docker`), the concept is the same as with the application.yaml-naming (e.g 
`restaurant-docker.yaml`).

### eurekaserver
Service discovery and registration inside this setup is done using Spring Cloud Netflix Eureka. That is, every other microservice
in this collection of microservices has to register itself at the eurekaserver. The eurekaserver also handles load balancing.
All microservices that are supposed to register themselves at the eurekaserver need to have some configuration. This can be
seen in the file `restaurant.yaml`.

### restaurant-service
The restaurant-service is a microservice that offers a simple REST-API for CRUD operations. It uses the food-service via a feign client

The API is described via openAPI. Therefore, the API has to be built via an openAPI-command. For this purpose, we use a
maven plugin. With following command, the API can be generated

`
mvn generate-sources
`

### food-service
The food-service is a microservice that offers a simple REST-API for CRUD operations.

The API is described via openAPI. Therefore, the API has to be built via an openAPI-command. For this purpose, we use a
maven plugin. With following command, the API can be generated

`
mvn generate-sources
`

If you want to change the API, do not edit the generated classes but instead edit the `restaurant.yaml` or  `food.yaml` file, that is 
located at the root level of this get repository inside the folder `openapi_spec`.

## Security
Keycloak is set up as identity provider in this scenario. It can be started via docker. During the docker start process a
realm is created. This includes the creation of a client in the realm for the gatewayserver and a user 
(ben, benspassword). So, if http://localhost:8072/dmichel90/restaurants/v1/restaurants is called, the user will be prompted 
to login. After a successful login, the user will be redirected to the original request. The food-service does not 
requrire any kind of authorization right now. In theory, the food-service is not supposed to be open to the 'outside world' 
or it has to be secured, too. (The OpenFeignClient has to be updated in this case.)

## Docker
All surrounding components like keycloak and the database can be started via docker with the command

`
docker-compose up
`

If services should also be started that have a profile, the profile has to be given via

`
docker-compose up --profile <<PROFILE_NAME>>
`

for instance `docker-compose up --profile sonar`, if you want to start sonarqube too.

## Development
For development purposes, start the surrounding components (postgresql, keycloak, zipkin and postgresql for keycloak) via
docker as described above (without profile).