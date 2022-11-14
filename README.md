# Store Management Tool


## General information

This is a demo project.
It's an example of building a basic REST API that acts as a store management tool.

### How to install

Clone the repository
https://github.com/florin4t/StorePOC
and check out the branch which has all the features: 
`feature/Nov-2022`.

### How to build

The project is built using Apache Maven.
There is no need of extra maven configuration, all the dependencies are taken from maven central.

Building possibilities:
* from your preferred IDE's UI
* from the command line:
`mvn clean install`

## Running the application

`ManagementAppApplication` needs to be run as a SpringBoot application.

The easiest way is from your IDE. 

#### Pre-start config
**Note**: Two properties need to be customized for the application to start correctly:
1. the server port 
   * by default, the application will try to run on port `8090`: `server.port=8090`.
     * If this port is not available a different port needs to be configured, or the port can be set to `0` and the application will find a free port automatically.
       * **Note**: check the logs for the port that was automatically selected, it will be needed later to call the API.
2. the embedded H2 database's URL
   1. for a Unix based system: `spring.datasource.url=jdbc:h2:~/myH2/tempDB;DB_CLOSE_ON_EXIT=FALSE`
      * `~/myH2` needs to point to a valid directory.
   2. for Windows: `spring.datasource.url=jdbc:h2:file:C:/myH2/tempDB;DB_CLOSE_ON_EXIT=FALSE`
      * `C:/myH2` needs to point to a directory which the user has permission to access.


## Configuration

* All the configuration properties are located in the application.properties file in `src/main/resources`.

* There is a 2nd file called application-secure.properties.
   * it does not have any custom properties but it is used to run the application with the `secure` profile which requires user authentication.
   * **Note**: running with the secure profile is done by setting the active profile to `secure`.
     * e.g. as a VM argument: `-Dspring.profiles.active=secure`
     * when running in `secure` mode, there are 2 users which can be used
       * a basic user which can only view products and can not add, modify or delete products (credentials: `basic/basic`)
       * a user with full access (credentials: `admin/admin`)


## Using the API

    API usage

**Important Note**:
- the GET operations can be tested from any browser.
- the other type of requests can be tested in various ways:
  - `curl` requests from the command line
  - 3rd party tools, e.g. `Postman`
  - from the IDE, by writing requests in a special file `requests.http`
  - from the API documentation, is one is provided e.g. http://localhost:8090/swagger-ui/#/store-product-controller

### Api Operations

1. get all products
   
    curl -X GET http://localhost:8090/api/product/all


2. create a new product


   invalid request: curl -X POST http://localhost:8090/api/product/add -d '{"id":null,"name":null,"price":null,"currency":null}' -H 'Content-Type: application/json'
    

   valid request: curl -X POST http://localhost:8090/api/product/add -d '{"id": null, "name":"Product 1", "price":100, "currency":"USD"}' -H 'Content-Type: application/json'


3. find a product by its ID

   curl -X GET http://localhost:8090/api/product/1

   curl -X GET http://localhost:8090/api/product/99999999

   curl -X GET http://localhost:8090/api/product/INVALIDID


4. update a product by its ID

   curl -X PUT http://localhost:8090/api/product/20 -H 'Content-Type: application/json' -d '{"id":null,"name":"New Product","price":123.456,"currency":"EUR"}'


5. delete a product by ID


   curl -X DELETE http://localhost:8090/api/product/26
   
   curl -X DELETE http://localhost:8090/api/product/INVALID_ID
   

6. Testing with Security


Just add the user details to any Curl command:

e.g.:  
curl -X GET http://localhost:8090/api/product/all --user basic:basic
 
curl -X POST http://localhost:8090/api/product/add -d '{"id": null, "name":"Product 1", "price":100, "currency":"USD"}' -H 'Content-Type: application/json' --user admin:admin


## Swagger Documentation
A draft of the API documentation can be found here: http://localhost:8090/swagger-ui/#/store-product-controller