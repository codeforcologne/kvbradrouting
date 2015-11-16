package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.geojson.FeatureCollection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.graphhopper.util.shapes.GHPoint;

import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.kvbradrouting.jdbc.InsertRouting;
import de.illilli.opendata.service.kvbradrouting.jdbc.SelectLastrun;

/**
 * Diese Facade ermittelt das Routing zwischen den Points der Fahrräder. Es
 * sollen nicht bei jedem Lauf alle points ermittelt werden.
 */
public class RoutingFacade implements Facade {

	private static final Logger logger = Logger.getLogger(RoutingFacade.class);

	private FeatureCollection featureCollection;

	public RoutingFacade() throws SQLException, NamingException, IOException,
			ClassNotFoundException {
		// Prüfe, wann der letzte Lauf war
		long lastrun = new SelectLastrun().getTime();
		// lese alle Änderungen nach dem letzten Lauf
		AskForBikes askForBikes = new AskForBikesMapDependsOnModtime(lastrun);
		// erstelle für alle Räder, bei denen sich was geändert hat ein
		// Routing über alle Points
		for (Integer number : askForBikes.getBikesMap().keySet()) {
			List<GHPoint> ghPointList = new ArrayList<GHPoint>();
			GHPoint ghPoint = new GHPoint();
			// fuelle die pointList
			AskForRouting askFor = new AskForRouting(ghPointList);
			// schreibe das Ergebnis für jedes Fahrrad als LineString in die
			// Datenbank
			List<Double[]> points = askFor.getGeoJsonList();
			new InsertRouting(number, askFor.getTimeInMillis(),
					askFor.getDistance(), points);
		}

	}

	@Override
	public String getJson() throws JsonProcessingException {
		// TODO Auto-generated method stub
		return "Anzahl berechneter/ eingefügter Routings";
	}

}
