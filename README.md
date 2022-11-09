Store Management Tool


    Info

This is an API that acts as a store management tool.


    Configuration

For some basic configuration, see the application.properties file.



    API usage

1. get all products
   
    http://localhost:8090/api/product/all


2. create a new product

    curl -X POST http://localhost:8090/api/product/add -d '{"id":null,"name":null,"price":null,"currency":null}' -H 'Content-Type: application/json'
