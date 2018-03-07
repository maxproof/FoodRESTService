package domain;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

public class RESTClient {

	// REST Service Constants
	private static final String REST_URL = "http://localhost/FoodRestService/rs";
	private static final int SERVER_PORT = 8080;
	private static final String PATH = "foods";

	// ivars
	private WebTarget target;

	/**
	 * Set up the connection.
	 * 
	 */
	public RESTClient() {
		URI uri;
		Client client;
		
		// create the URL
		uri = UriBuilder.fromUri(REST_URL)
				.port(SERVER_PORT)
				.build();

		// create a client
		client = ClientBuilder.newClient();

		// create a target
		target = client.target(uri)
				.path(PATH);
	}

	/*
	 * Get all foods.
	 * 
	 * JSON: curl -X GET -H "Accept: application/json"
	 * http://localhost:8080/FoodRestServiceProject/rs/foods -v
	 * 
	 * 
	 * XML: curl -X GET -H "Accept: application/xml"
	 * http://localhost:8080/FoodRestServiceProject/rs/foods -v
	 * 
	 * 
	 * @param mediaType JSON or XML.
	 * @return
	 * 
	 */
	public Response getAll(String mediaType) {
		return target.request(mediaType)
				.get();
	}

	/*
	 * Get a single food.
	 * 
	 * JSON: curl -X GET -H "Accept: application/json"
	 * http://localhost:8080/FoodRestServiceProject/rs/foods/onion -v
	 * 
	 * XML: curl -X GET -H "Accept: application/xml"
	 * http://localhost:8080/FoodRestServiceProject/rs/foods/onion -v
	 * 
	 * @param food A food name.
	 * @param mediaType JSON or XML.
	 * @return
	 * 
	 */
	public Response get(String food, String mediaType) {
		return target.path(food)
				.request(mediaType)
				.get();
	}

	/**
	 * Create a food.
	 * 
	 * JSON: curl -X POST --data-binary
	 * "{\"calories\":150,\"name\":\"apple_pie\",\"servingSize\":\"1 slice\"}"
	 * -H "Content-Type: application/json"
	 * http://localhost:8080/FoodRestServiceProject/rs/foods -v
	 * 
	 * XML: curl -X POST --data-binary
	 * "<food><calories>40</calories><name>turnip</name><servingSize>3 slices</servingSize></food>"
	 * -H "Content-Type: application/xml"
	 * http://localhost:8080/FoodRestServiceProject/rs/foods -v
	 * 
	 * @param foodName
	 * @param calories
	 * @param servingSize
	 * @return
	 * 
	 */
	public Response post(String foodName, int calories, String servingSize) {
		Food food = new Food(foodName, calories, servingSize);
		Entity<Food> entity = Entity.entity(food, MediaType.APPLICATION_XML);
		return target.request()
				.post(entity);
	}

	/**
	 * Update a food.
	 * 
	 * JSON: curl -X PUT --data-binary
	 * "{\"calories\":150,\"name\":\"apple_pie\",\"servingSize\":\"1 slice\"}"
	 * -H "Content-Type: application/json"
	 * http://localhost:8080/FoodRestServiceProject/rs/foods -v
	 * 
	 * XML: curl -X PUT --data-binary
	 * "<food><calories>40</calories><name>turnip</name><servingSize>3 slices</servingSize></food>"
	 * -H "Content-Type: application/xml"
	 * http://localhost:8080/FoodRestServiceProject/rs/foods -v
	 * 
	 * @param foodName
	 * @param calories
	 * @param servingSize
	 * @return
	 * 
	 */
	public Response put(String foodName, int calories, String servingSize) {
		Food food = new Food(foodName, calories, servingSize);
		Entity<Food> entity = Entity.entity(food, MediaType.APPLICATION_XML);
		return target.request()
				.put(entity);
	}

	/**
	 * Delete a food.
	 * 
	 * curl -X DELETE
	 * http://localhost:8080/FoodRestServiceProject/rs/foods/onion -v
	 * 
	 * @param foodName
	 * 
	 */
	public Response delete(String foodName) {
		return target.path(foodName)
				.request()
				.delete();
	}

}
