package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.graphhopper.util.shapes.GHPoint;

public class InsertRoutingCollectorByBike extends InsertRoutingCollector {

	private static final Logger logger = Logger
			.getLogger(InsertRoutingCollectorByBike.class);

	public InsertRoutingCollectorByBike(RouteAndInsert routeAndInsert) {
		this.routeAndInsert = routeAndInsert;
	}

	@Override
	public void routeAndInsert(long lastrun, Map<Integer, List<BikeBo>> bikesMap)
			throws SQLException, NamingException, IOException,
			ClassNotFoundException {

		for (Map.Entry<Integer, List<BikeBo>> entry : bikesMap.entrySet()) {
			Integer number = entry.getKey();
			List<BikeBo> bikeBoList = entry.getValue();

			List<GHPoint> ghPointList = new ArrayList<GHPoint>();
			for (BikeBo bikeBo : bikeBoList) {
				GHPoint ghPoint = new GHPoint(bikeBo.getLat(), bikeBo.getLng());
				ghPointList.add(ghPoint);
			}

			routeAndInsert.run(number, ghPointList);
		}

	}
}
