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

	private FeatureCollection featureCollection;

	public GeoJsonFacade(Integer number) throws SQLException, NamingException,
			IOException {
		featureCollection = new RoutingFeatureCollection(new SelectRouting(
				number)).getFeatureCollection();
	}

	/**
	 * Lese alle Daten aus der Tabelle Routing aus und gebe sie formatiert als
	 * geoJson zurück.
	 * 
	 * @throws IOException
	 * @throws NamingException
	 * @throws SQLException
	 */
	public GeoJsonFacade() throws SQLException, NamingException, IOException {
		featureCollection = new RoutingFeatureCollection(new SelectRouting())
				.getFeatureCollection();
	}

	@Override
	public String getJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(featureCollection);
	}

}
