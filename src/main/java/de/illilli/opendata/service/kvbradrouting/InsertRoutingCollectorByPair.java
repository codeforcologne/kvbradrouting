package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.graphhopper.util.shapes.GHPoint;

/**
 * Diese Klasse gruppiert die Liste der übergebenen Fahrrad-Positionen zu Paaren
 * und fragt mit dieser Information den Graphhoper Server nach Routing
 * Informationen. Danach wird diese Route in die Datenbank geschrieben.
 */
public class InsertRoutingCollectorByPair extends InsertRoutingCollector {

	private static final Logger logger = Logger
			.getLogger(InsertRoutingCollectorByPair.class);

	/**
	 * Der Übergabeparameter legt fest, welche Implemtierung für Routing und
	 * Inserting der ermittelten Daten verwendet werden soll. Auf diese Weise
	 * ist es möglich diese beiden Elemente z.B. für einen Test mit einer
	 * funktionslosen Implementierung zu betreiben.
	 * 
	 * @param routeAndInsert
	 */
	public InsertRoutingCollectorByPair(RouteAndInsert routeAndInsert) {
		this.routeAndInsert = routeAndInsert;
	}

	@Override
	public void routeAndInsert(long lastrun, Map<Integer, List<BikeBo>> bikesMap)
			throws SQLException, NamingException, IOException,
			ClassNotFoundException {

		super.numberOfInserts = 0;
		Integer number = new Integer(-1);

		for (Map.Entry<Integer, List<BikeBo>> entry : bikesMap.entrySet()) {

			number = entry.getKey();
			List<BikeBo> bikeBoList = entry.getValue();
			boolean firstRun = true;

			GHPoint ghPoint = null;
			List<GHPoint> ghPointList = new ArrayList<GHPoint>();
			for (BikeBo bikeBo : bikeBoList) {
				if (firstRun) {
					// nur wenn neue number
					firstRun = false;
					ghPointList = new ArrayList<GHPoint>();
					ghPoint = new GHPoint(bikeBo.getLat(), bikeBo.getLng());
				} else {
					// nimm ghPoint vom vorherigen Lauf
					ghPointList.add(ghPoint);
					// hole den naechsten
					ghPoint = new GHPoint(bikeBo.getLat(), bikeBo.getLng());
					ghPointList.add(ghPoint);
					logger.info("insert [" + number + "]: " + ghPointList);
					if (bikeBo.getTimestamp().getTime() >= lastrun) {
						routeAndInsert.run(number, ghPointList);
						super.numberOfInserts++;
					}
					ghPointList = new ArrayList<GHPoint>();
				}
				if (bikeBo.getTimestamp().getTime() < lastrun) {
					break;
				}
			}
		}
	}
}
