package de.illilli.opendata.service.kvbradrouting;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.LineString;
import org.geojson.LngLatAlt;

import de.illilli.opendata.service.kvbradrouting.jdbc.RoutingDao;
import de.illilli.opendata.service.kvbradrouting.jdbc.Select;

public class RoutingFeatureCollection {

	private FeatureCollection featureCollection;

	public RoutingFeatureCollection(Select<RoutingDao> select) {
		featureCollection = new FeatureCollection();

		List<RoutingDao> routingList = select.getDbObjectList();
		for (RoutingDao routingDb : routingList) {
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
			DecimalFormat df = new DecimalFormat("#.000");
			String distance = df.format(routingDb.getDistance() / 1000);
			properties.put("distance", distance);
			properties.put("number", routingDb.getNumber());
			String time = new SimpleDateFormat("mm:ss").format(new Date(
					routingDb.getTimeinmillis()));
			properties.put("time", time);
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
