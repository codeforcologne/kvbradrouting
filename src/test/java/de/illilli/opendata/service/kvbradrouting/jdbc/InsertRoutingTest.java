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
		point[0] = 6.89339;
		point[1] = 50.94217;
		pointsArray.add(point);
		point = new Double[2];
		point[0] = 6.88955;
		point[1] = 50.94228;
		pointsArray.add(point);
		point = new Double[2];
		point[0] = 6.88949;
		point[1] = 50.94235;
		pointsArray.add(point);
		point = new Double[2];
		point[0] = 6.88944;
		point[1] = 50.94237;
		pointsArray.add(point);
		point = new Double[2];
		point[0] = 6.88951;
		point[1] = 50.94259;
		pointsArray.add(point);
		point = new Double[2];
		point[0] = 6.88984;
		point[1] = 50.94687;
		pointsArray.add(point);
		point = new Double[2];
		point[0] = 6.88836;
		point[1] = 50.94706;
		pointsArray.add(point);

		InsertRouting insert = new InsertRouting(number, timeInMillis,
				distance, pointsArray);
		int numberOfInserts = insert.getNumberOfInserts();
		System.out.println(numberOfInserts);

	}

}
