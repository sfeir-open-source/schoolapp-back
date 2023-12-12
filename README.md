# SchoolApp backend

## Project documentation

SchoolApp is a web application to provide a support for all SFEIR schools.

## Installation

To run this project, you can do it with Intellij or Docker, but before you need to precise environment variables.

### Environment variables

| **Id**                                                | **Example value**                       |
|-------------------------------------------------------|-----------------------------------------|
| SERVER_PORT                                           | 8080                                    |
| SPRING_DATA_MONGODB_DATABASE                          | school-app                              |
| SPRING_DATA_MONGODB_URI                               | mongodb+srv://sfeir                     |
| SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_AUDIENCES   | example-data.apps.googleusercontent.com |
| SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_ISSUER-URI  | https://accounts.google.com             |
| SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_JWK-SET-URI | https://googleapis.com/oauth2/v3/certs  |

## Routes

All the routes of the spring boot CRUD are available on
this [swagger](https://app.swaggerhub.com/apis/SOMNYP/school-app)

## Contributing"

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.