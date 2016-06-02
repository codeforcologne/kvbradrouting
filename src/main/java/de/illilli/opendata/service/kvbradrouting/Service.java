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
	 * @throws ClassNotFoundException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/json")
	public String getJson() throws JsonParseException, JsonMappingException, IOException, SQLException, NamingException,
			ClassNotFoundException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Facade facade = new JsonFacade();
		return facade.getJson();
	}

	/**
	 * Dieser Service liefert für jedes erfasste Rad die gesammelten Werte zu
	 * <ul>
	 * <li>zurückgelegter Strecke</li>
	 * <li>verwendete Zeit</li>
	 * <li>Anzahl der Werte</li>
	 * </ul>
	 * 
	 * <p>
	 * Beispiel:
	 * <a href="http://localhost:8080/kvbradrouting/service/aggregated">
	 * /kvbradrouting/service/aggregated</a>
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
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/aggregated")
	public String getAggregated() throws JsonParseException, JsonMappingException, IOException, SQLException,
			NamingException, ClassNotFoundException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Facade facade = new AggregatedJsonFacade();
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
	 * @throws ClassNotFoundException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/json/{modtime}")
	public String getJson(@PathParam("modtime") Long modtime) throws JsonParseException, JsonMappingException,
			IOException, SQLException, NamingException, ClassNotFoundException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Facade facade = new JsonFacade(modtime);
		return facade.getJson();
	}

	/**
	 * This services returns the requested routing information in json
	 * representation of 'List&lt;RoutingBo&gt;' Object.
	 * <p>
	 * Bsp.: <a href="http://localhost:8080/kvbradrouting/service/bike/21004">
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
	 * @throws ClassNotFoundException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/bike/{number}")
	public String getJson(@PathParam("number") Integer number) throws JsonParseException, JsonMappingException,
			IOException, SQLException, NamingException, ClassNotFoundException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Facade facade = new JsonFacade(number);
		return facade.getJson();
	}

	/**
	 * <p>
	 * Bsp.: <a href="http://localhost:8080/kvbradrouting/service/geojson">
	 * /kvbradrouting/service/geojson</a>
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
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/geojson")
	public String getGeojson() throws JsonParseException, JsonMappingException, IOException, SQLException,
			NamingException, ClassNotFoundException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Facade facade = new GeoJsonFacade();
		return facade.getJson();
	}

	/**
	 * <p>
	 * Bsp.: <a href="http://localhost:8080/kvbradrouting/service/geojson/1">
	 * /kvbradrouting/service/geojson/&lt;days&gt;</a>
	 * </p>
	 * 
	 * @param days
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ClassNotFoundException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/geojson/{days}")
	public String getGeojson(@PathParam("days") short days) throws JsonParseException, JsonMappingException,
			IOException, SQLException, NamingException, ClassNotFoundException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Facade facade = new GeoJsonFacade(days);
		return facade.getJson();
	}

	/**
	 * <p>
	 * Bsp.:
	 * <a href="http://localhost:8080/kvbradrouting/service/geojson/bike/21617">
	 * /kvbradrouting/service/geojson/bike/&lt;number&gt;</a>
	 * </p>
	 * 
	 * @param number
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ClassNotFoundException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/geojson/bike/{number}")
	public String getGeojson(@PathParam("number") Integer number) throws JsonParseException, JsonMappingException,
			IOException, SQLException, NamingException, ClassNotFoundException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Facade facade = new GeoJsonFacade(number);
		return facade.getJson();
	}
}
