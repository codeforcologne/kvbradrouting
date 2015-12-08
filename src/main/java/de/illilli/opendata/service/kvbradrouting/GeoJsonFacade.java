package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.LineString;
import org.geojson.LngLatAlt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.kvbradrouting.jdbc.RoutingDb;
import de.illilli.opendata.service.kvbradrouting.jdbc.Select;
import de.illilli.opendata.service.kvbradrouting.jdbc.SelectRouting;

public class GeoJsonFacade implements Facade {

	private FeatureCollection featureCollection;

	public GeoJsonFacade(Integer number) {
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
		featureCollection = new FeatureCollection();
		Select<RoutingDb> select = new SelectRouting();
		List<RoutingDb> routingList = select.getDbObjectList();
		for (RoutingDb routingDb : routingList) {
			Feature feature = new Feature();
			LineString lineString = new LineString();
			org.postgis.LineString pgLineString = (org.postgis.LineString) routingDb
					.getGeom().getGeometry();
			org.postgis.Point[] points = pgLineString.getPoints();
			for (org.postgis.Point point : points) {
				LngLatAlt element = new LngLatAlt(point.getX(), point.getY());
				lineString.add(element);
			}
			feature.setGeometry(lineString);
			Map<String, Object> properties = new Hashtable<String, Object>();
			properties.put("distance", routingDb.getDistance() / 1000);
			properties.put("number", routingDb.getNumber());
			properties.put("time", routingDb.getTimeinmillis() / 1000);
			feature.setProperties(properties);
			featureCollection.add(feature);
		}
	}

	@Override
	public String getJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(featureCollection);
	}

}
