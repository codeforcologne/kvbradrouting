package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.geojson.FeatureCollection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.graphhopper.util.PointList;
import com.graphhopper.util.shapes.GHPoint;

import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.kvbradrouting.jdbc.SelectLastRun;

/**
 * Diese Facade ermittelt das Routing zwischen den Points der Fahrräder. Es
 * sollen nicht bei jedem Lauf alle points ermittelt werden.
 */
public class RoutingFacade implements Facade {

	private static final Logger logger = Logger.getLogger(RoutingFacade.class);

	private FeatureCollection featureCollection;

	public RoutingFacade() throws SQLException, NamingException, IOException {
		// 1. Prüfe, wann der letzte Lauf war
		long lastRun = new SelectLastRun().getLastRun();
		// 2. lese alle Änderungen nach dem letzten Lauf
		// 3. erstelle für alle Räder, bei denen sich was geändert hat ein
		// Routing über alle Points
		List<GHPoint> ghPointList = new ArrayList<GHPoint>();
		// fuelle die pointList
		AskForRouting askFor = new AskForRouting(ghPointList);
		// 4. schreibe das Ergebnis als LineString in die Datenbank
		PointList pointList = askFor.getPointList();
	}

	@Override
	public String getJson() throws JsonProcessingException {
		// TODO Auto-generated method stub
		return "Anzahl berechneter/ eingefügter Routings";
	}

}
