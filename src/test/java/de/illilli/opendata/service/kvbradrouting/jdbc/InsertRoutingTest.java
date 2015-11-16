package de.illilli.opendata.service.kvbradrouting.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.junit.Before;

import de.illilli.opendata.service.kvbradrouting.JndiProperties;

public class InsertRoutingTest {

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) throws IOException, SQLException,
			NamingException, ClassNotFoundException {

		JndiProperties.setUpConnectionForJndi();
		int number = 1;
		long timeInMillis = 1;
		double distance = 1.0;
		List<Double[]> pointsArray = new ArrayList<Double[]>();
		Double[] point = new Double[2];
		point[0] = 0.0;
		point[1] = 1.0;
		pointsArray.add(point);
		point = new Double[2];
		point[0] = 1.0;
		point[1] = 0.0;
		pointsArray.add(point);

		InsertRouting insert = new InsertRouting(number, timeInMillis,
				distance, pointsArray);
		int numberOfInserts = insert.getNumberOfInserts();
		System.out.println(numberOfInserts);

	}
}
