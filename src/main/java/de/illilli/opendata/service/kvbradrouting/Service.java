package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import de.illilli.opendata.service.Facade;

@Path("/")
public class Service {

	private final static Logger logger = Logger.getLogger(Service.class);

	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	/**
	 * <p>
	 * Beispiel:
	 * <code>curl -X PUT http://localhost:8080/kvbradrouting/service/put</code>
	 * </p>
	 * 
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ClassNotFoundException
	 */
	@PUT
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/put")
	public String putKvbradRouting() throws JsonParseException, JsonMappingException, IOException, SQLException,
			NamingException, ClassNotFoundException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Facade facade = new RoutingFacade();
		return facade.getJson();
	}

	/**
	 * <p>
	 * Bsp.: <a href="http://localhost:8080/kvbradrouting/service/json">
	 * /kvbradrouting/service/json</a>
	 * </p>
	 * 
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/json")
	public String getJson()
			throws JsonParseException, JsonMappingException, IOException, SQLException, NamingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Facade facade = new JsonFacade();
		return facade.getJson();
	}

	/**
	 * <p>
	 * Bsp.: <a href="http://localhost:8080/kvbradrouting/service/json/0">
	 * /kvbradrouting/service/json</a>
	 * </p>
	 * 
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/json/{modtime}")
	public String getJson(@PathParam("modtime") Long modtime)
			throws JsonParseException, JsonMappingException, IOException, SQLException, NamingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Facade facade = new JsonFacade();
		return facade.getJson();
	}

	/**
	 * <p>
	 * Bsp.: <a href="http://localhost:8080/kvbradrouting/service/bike/21617">
	 * /kvbradrouting/service/json/21617</a>
	 * </p>
	 * 
	 * 
	 * @param number
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/bike/{number}")
	public String getJson(@PathParam("number") Integer number)
			throws JsonParseException, JsonMappingException, IOException, SQLException, NamingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Facade facade = new JsonFacade(number);
		return facade.getJson();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/geojson")
	public String getGeojson()
			throws JsonParseException, JsonMappingException, IOException, SQLException, NamingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Facade facade = new GeoJsonFacade();
		return facade.getJson();
	}

	/**
	 * <p>
	 * Bsp.:
	 * <a href="http://localhost:8080/kvbradrouting/service/geojson/21617">
	 * /kvbradrouting/service/geojson/21617</a>
	 * </p>
	 * 
	 * @param number
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/geojson/{number}")
	public String getGeojson(@PathParam("number") Integer number)
			throws JsonParseException, JsonMappingException, IOException, SQLException, NamingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Facade facade = new GeoJsonFacade(number);
		return facade.getJson();
	}
}
