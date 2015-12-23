package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.graphhopper.util.shapes.GHPoint;

import de.illilli.opendata.service.kvbradrouting.jdbc.InsertRouting;

public class RouteAndInsertToDb implements RouteAndInsert {

	int numberOfInserts;

	public RouteAndInsertToDb() throws ClassNotFoundException, SQLException,
			NamingException, IOException {
	}

	@Override
	public void run(Integer number, List<GHPoint> ghPointList)
			throws ClassNotFoundException, SQLException, NamingException,
			IOException {
		// fuelle die pointList
		AskForRouting askFor = new AskForRouting(ghPointList);
		// schreibe das Ergebnis für jedes Fahrrad als LineString in die
		// Datenbank
		if (askFor != null && askFor.getGeoJsonList() != null
				&& askFor.getPointList() != null) {
			List<Double[]> points = askFor.getGeoJsonList();
			// aber vorher loesche die alten Werte
			if (askFor.getPointList().getSize() > 1) {
				// check for number of points to insert; if less than two points
				// an
				// exception is thrown
				InsertRouting insertRouting = new InsertRouting(number,
						askFor.getTimeInMillis(), askFor.getDistance(), points);
				numberOfInserts = insertRouting.getNumberOfInserts();
			}
		}
	}

	@Override
	public int getNumberOfInserts() {
		return numberOfInserts;
	}

}
