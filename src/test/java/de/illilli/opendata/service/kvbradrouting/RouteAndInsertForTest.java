package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.graphhopper.util.shapes.GHPoint;

public class RouteAndInsertForTest implements RouteAndInsert {

	private static final Logger logger = Logger
			.getLogger(RouteAndInsertForTest.class);

	@Override
	public void run(Integer number, List<GHPoint> ghPointList)
			throws ClassNotFoundException, SQLException, NamingException,
			IOException {
		logger.info("number: " + number);
		logger.info("ghPointList: " + ghPointList);

	}

	@Override
	public int getNumberOfInserts() {
		return 0;
	}

}
