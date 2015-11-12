package de.illilli.opendata.service.kvbradrouting.jdbc;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.junit.Before;
import org.postgis.Point;

import de.illilli.opendata.service.kvbradrouting.JndiProperties;

public class InsertRoutingTest {

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) throws IOException, SQLException,
			NamingException {

		JndiProperties.setUpConnectionForJndi();
		int number = 1;
		long timeInMillis = 1;
		double distance = 1.0;
		Point[] points = new Point[2];
		points[0] = new Point(0, 1);
		points[1] = new Point(1, 0);

		InsertRouting insert = new InsertRouting(number, timeInMillis,
				distance, points);
		int numberOfInserts = insert.getNumberOfInserts();
		System.out.println(numberOfInserts);

	}
}
