package service;

import java.net.URI;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import domain.Food;
import domain.FoodList;
import domain.FoodManager;

/**
 * The jax-rs service, deployable to GlassFish, which tracks food.
 * 
 * @author taylor
 *
 */
@Path("/foods")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Stateless
public class FoodRestService {
	@Context
	private UriInfo uriInfo;

	/**
	 * Create a food and add it to the food manager.
	 * 
	 * @param food
	 *            the food to create
	 * @return the status and url of the food
	 */
	@POST
	public Response create(Food food) {
		Response response = Response.notModified()
				.build();

		if (food == null)
			throw new BadRequestException();

		if (FoodManager.find(food.getName()) == null) {
			FoodManager.add(food);
			URI uri = uriInfo.getAbsolutePathBuilder()
					.path(food.getName())
					.build();
			response = Response.created(uri)
					.build();
		}
		return response;
	}

	/**
	 * Update a food with new information.
	 * 
	 * @param food
	 *            the food to update.
	 * @return status code
	 */
	@PUT
	public Response update(Food food) {
		if (food == null)
			throw new BadRequestException();
		FoodManager.delete(food.getName());
		FoodManager.add(food);
		return Response.ok()
				.build();
	}

	/**
	 * Get a food.
	 * 
	 * @param name
	 *            the food to get.
	 * @return the status and food.
	 */
	@GET
	@Path("{name}")
	public Response get(@PathParam("name") String name) {
		Food food = FoodManager.find(name);
		if (food == null)
			throw new NotFoundException();
		return Response.ok(food)
				.build();
	}

	/**
	 * Get all foods in the food manager.
	 * 
	 * @return xml or json of all foods
	 */
	@GET
	public Response getAll() {
		FoodList foodList = FoodManager.getFoods();
		GenericEntity<List<Food>> list = new GenericEntity<List<Food>>(foodList) {
		};
		return Response.ok(list)
				.build();
	}

	/**
	 * Delete a food.
	 * 
	 * @param name
	 *            food to delete
	 * @return status of the operation
	 */
	@DELETE
	@Path("{name}")
	public Response delete(@PathParam("name") String name) {
		Food food = FoodManager.find(name);
		if (food == null)
			throw new NotFoundException();
		FoodManager.delete(name);
		return Response.noContent()
				.build();
	}

}
