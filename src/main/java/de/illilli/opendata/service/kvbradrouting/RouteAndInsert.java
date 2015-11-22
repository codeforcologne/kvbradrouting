package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.graphhopper.util.shapes.GHPoint;

public interface RouteAndInsert {

	void run(Integer number, List<GHPoint> ghPointList)
			throws ClassNotFoundException, SQLException, NamingException,
			IOException;

	int getNumberOfInserts();
}
