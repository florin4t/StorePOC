Store Management Tool


    Info

This is an API that acts as a store management tool.


    Configuration

For some basic configuration, see the application.properties file.



    API usage

1. get all products
   
    curl -X GET http://localhost:8090/api/product/all


2. create a new product


   invalid request: curl -X POST http://localhost:8090/api/product/add -d '{"id":null,"name":null,"price":null,"currency":null}' -H 'Content-Type: application/json'
    

   valid request: curl -X POST http://localhost:8090/api/product/add -d '{"id": null, "name":"Product 1", "price":100, "currency":"USD"}' -H 'Content-Type: application/json'


3. find a product by its ID

   curl -X http://localhost:8090/api/product/1

   curl -X GET http://localhost:8090/api/product/99999999

   curl -X GET http://localhost:8090/api/product/INVALIDID
