package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.graphhopper.util.shapes.GHPoint;

import de.illilli.opendata.service.kvbradrouting.jdbc.InsertRouting;

public class InsertRoutingCollector {

	int numberOfInserts;

	void routeAndInsert(Integer number, List<GHPoint> ghPointList)
			throws SQLException, NamingException, IOException,
			ClassNotFoundException {
		// fuelle die pointList
		AskForRouting askFor = new AskForRouting(ghPointList);
		// schreibe das Ergebnis für jedes Fahrrad als LineString in die
		// Datenbank
		List<Double[]> points = askFor.getGeoJsonList();
		// aber vorher loesche die alten Werte
		InsertRouting insertRouting = new InsertRouting(number,
				askFor.getTimeInMillis(), askFor.getDistance(), points);

		numberOfInserts = insertRouting.getNumberOfInserts();
	}

	public int getNumberOfInserts() {
		return numberOfInserts;
	}

}
