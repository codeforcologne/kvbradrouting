package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import com.graphhopper.util.shapes.GHPoint;

public class InsertRoutingCollectorByBike extends InsertRoutingCollector {

	public InsertRoutingCollectorByBike(Map<Integer, List<BikeBo>> bikesMap)
			throws ClassNotFoundException, IOException, SQLException,
			NamingException {

		for (Map.Entry<Integer, List<BikeBo>> entry : bikesMap.entrySet()) {
			Integer number = entry.getKey();
			List<BikeBo> bikeBoList = entry.getValue();

			List<GHPoint> ghPointList = new ArrayList<GHPoint>();
			for (BikeBo bikeBo : bikeBoList) {
				GHPoint ghPoint = new GHPoint(bikeBo.getLat(), bikeBo.getLng());
				ghPointList.add(ghPoint);
			}

			routeAndInsert(number, ghPointList);
		}

	}
}
