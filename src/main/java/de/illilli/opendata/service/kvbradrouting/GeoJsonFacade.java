package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.geojson.FeatureCollection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.kvbradrouting.jdbc.SelectRouting;

public class GeoJsonFacade implements Facade {

	static final int ONEDAY = 1000 * 60 * 60 * 24;

	private FeatureCollection featureCollection;

	public GeoJsonFacade(Integer number) throws SQLException, NamingException, IOException, ClassNotFoundException {
		featureCollection = new RoutingFeatureCollection(new SelectRouting(number)).getFeatureCollection();
	}

	public GeoJsonFacade(short days) throws SQLException, NamingException, IOException, ClassNotFoundException {
		long time = System.currentTimeMillis() - (days * GeoJsonFacade.ONEDAY);
		featureCollection = new RoutingFeatureCollection(new SelectRouting(time)).getFeatureCollection();
	}

	/**
	 * Lese alle Daten aus der Tabelle Routing aus und gebe sie formatiert als
	 * geoJson zurück.
	 * 
	 * @throws IOException
	 * @throws NamingException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public GeoJsonFacade() throws SQLException, NamingException, IOException, ClassNotFoundException {
		featureCollection = new RoutingFeatureCollection(new SelectRouting()).getFeatureCollection();
	}

	@Override
	public String getJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(featureCollection);
	}

}
