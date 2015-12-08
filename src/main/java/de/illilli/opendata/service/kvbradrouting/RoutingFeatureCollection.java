package de.illilli.opendata.service.kvbradrouting;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.LineString;
import org.geojson.LngLatAlt;

import de.illilli.opendata.service.kvbradrouting.jdbc.RoutingDb;
import de.illilli.opendata.service.kvbradrouting.jdbc.Select;

public class RoutingFeatureCollection {

	private FeatureCollection featureCollection;

	public RoutingFeatureCollection(Select<RoutingDb> select) {
		featureCollection = new FeatureCollection();

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

	public FeatureCollection getFeatureCollection() {
		return featureCollection;
	}

	public void setFeatureCollection(FeatureCollection featureCollection) {
		this.featureCollection = featureCollection;
	}

}
