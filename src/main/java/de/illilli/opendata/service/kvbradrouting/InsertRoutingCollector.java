package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

public abstract class InsertRoutingCollector {

	int numberOfInserts;
	RouteAndInsert routeAndInsert;

	abstract void routeAndInsert(long lastrun,
			Map<Integer, List<BikeBo>> bikesMap) throws SQLException,
			NamingException, IOException, ClassNotFoundException;

	public int getNumberOfInserts() {
		return numberOfInserts;
	}

}
