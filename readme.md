# Similar Products Service

This service provides information about similar products based on a given product ID. It is Dockerized and can be easily executed using Docker Compose.

## Running the Service

To run the service, follow these steps:

1. Make sure you have Docker  installed on your machine.

2. Clone the repository of your service from GitHub.

3. Open a terminal and navigate to the root directory of the project.

4. Run the following command to start the service:

   ```bash
   docker-compose up -d --build similar_products
   ```

   This will start the necessary containers and get the similar products service up and running.

5. Once the containers are running, you can access the following endpoints:

   - `/product/{productId}/similar`: Returns a list of products similar to the product with the specified ID.

     Example request: GET http://localhost:5000/product/4/similar

     Example response:
     ```json  
     [
      {
         "id": "1",
         "name": "Shirt",
         "price": 9.99,
         "availability": true
      },
      {
         "id": "2",
         "name": "Dress",
         "price": 19.99,
         "availability": true
       }
     ]
     ```
     
		Example request Not Found: GET http://localhost:5000/product/64/similar
	
		If the specified product ID is not found, the service will return a 404 status code with the following response:
	  Example response:
		  
	    ```json
	     Product Not found
	    ```

# Swagger

Swagger is integrated into the Similar Products Service to provide detailed documentation and facilitate API exploration.

To access the Swagger UI, use the following URL:

Localhost: http://localhost:5000/swagger-ui.html
Swagger is integrated into the Similar Products Service to provide detailed documentation and facilitate API exploration.

Feel free to experiment with the API using the Swagger UI and refer to the documentation for more information on the available endpoints and their usage.
