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

	public InsertRoutingCollectorByPair(Map<Integer, List<BikeBo>> bikesMap,
			long lastrun) throws ClassNotFoundException, IOException,
			SQLException, NamingException {

		for (Map.Entry<Integer, List<BikeBo>> entry : bikesMap.entrySet()) {
			Integer number = entry.getKey();
			List<BikeBo> bikeBoList = entry.getValue();
			boolean firstRun = true;

			GHPoint ghPoint = null;
			List<GHPoint> ghPointList = new ArrayList<GHPoint>();
			for (BikeBo bikeBo : bikeBoList) {
				if (firstRun) {
					ghPointList = new ArrayList<GHPoint>();
					ghPoint = new GHPoint(bikeBo.getLat(), bikeBo.getLng());
					ghPointList.add(ghPoint);
					firstRun = false;
				} else {
					ghPoint = new GHPoint(bikeBo.getLat(), bikeBo.getLng());
					ghPointList.add(ghPoint);
					firstRun = true;
					logger.info("insert [" + number + "]: " + ghPointList);
					if (bikeBo.getTimestamp().getTime() >= lastrun) {
						// routeAndInsert(number, ghPointList);
					}
				}
			}
		}
	}
}
