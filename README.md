# PRISM

The PRISM application is a payment processing service that is used by various internal systems (e.g. expense 
reimbursement, payroll, etc.). It allows other internal applications to register themselves with it by having 
those systems provide an organization name and a secret key. The system confirms app registration by providing 
the organization's generated ID and auth code. This code is linked to the organization and is contained with 
the token that is provided during authentication, to confirm access privileges to certain resource endpoints. The 
secret key provided by the registering application is used as a backup in case the generated auth code is lost or
compromised. 

Authenticated applications are able to add employees to their PRISM organization and maintain their information. 
Payment requests can be sent to the PRISM application by providing the payee's (employee) ID and the payment amount; 
valid requests will yield a successful response containing a generated payment ID that can be used by the client system.

## Tech Stack

This application is a RESTful API built using Java 8 with Spring Boot, along with various other Spring 
projects, and is documented using OpenAPI/Swagger. Both local and remote deployments are facilitated by Docker, 
which is used for containerization and maximizes portability across various environments.

## Local Deployment Instructions

To run this application locally, simply open a terminal in the root directory of this repository and run the command: 
`sh local-startup.sh`. The application will be available at `http://localhost:5000/prism`. Please refer to the API
documentation provided by OpenAPI at `http://localhost:5000/prism/swagger-ui/index.html`.

Additionally, you may find [this Postman collection](https://github.com/220509-web-dev/prism/blob/main/src/main/resources/Prism.postman_collection.json)
useful for helping you to understand what valid requests to the core PRISM endpoints look like. If you have questions, 
issues, or comments regarding this application please feel free to open an issue on this repository.
