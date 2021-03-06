package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.kvbradrouting.jdbc.SelectRouting;

/**
 * This Facade returns RoutingCollections with different selections:
 * <ol>
 * <li>get all routings available in Database</li>
 * <li>get routings dependend from modtime; just get newer ones</li>
 * <li>get routings dependend from number</li>
 * </ol>
 *
 */
public class JsonFacade implements Facade {

	private List<RoutingBo> routingList;

	public JsonFacade(Integer number) throws SQLException, NamingException, IOException, ClassNotFoundException {
		routingList = new RoutingCollection(new SelectRouting(number)).getRoutingList();
	}

	public JsonFacade() throws SQLException, NamingException, IOException, ClassNotFoundException {
		routingList = new RoutingCollection(new SelectRouting()).getRoutingList();
	}

	public JsonFacade(Long modtime) throws SQLException, NamingException, IOException, ClassNotFoundException {
		routingList = new RoutingCollection(new SelectRouting(modtime)).getRoutingList();
	}

	@Override
	public String getJson() throws JsonProcessingException {
		Gson gson = new Gson();
		return gson.toJson(routingList);
	}

}
