package de.illilli.opendata.service.kvbradrouting.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.postgis.LineString;
import org.postgis.PGgeometry;
import org.postgis.Point;

import de.illilli.jdbc.ConnectionFactory;

public class InsertRouting {

	private static final Logger logger = Logger.getLogger(InsertRouting.class);
	private int inserts;

	String queryString = "/insertRouting.sql";

	public InsertRouting(int number, long timeInMillis, double distance,
			List<Double[]> points) throws SQLException, NamingException,
			IOException, ClassNotFoundException {

		Connection conn = ConnectionFactory.getConnection();
		InputStream inputStream = this.getClass().getResourceAsStream(
				this.queryString);
		String sql = IOUtils.toString(inputStream);

		Point[] pointArray = new Point[points.size()];
		for (int i = 0; i < points.size(); i++) {
			Double[] point = points.get(i);
			pointArray[i] = new Point(point[0], point[1]);
		}
		LineString lineString = new LineString(pointArray);
		lineString.srid = 4326;

		// ((org.postgresql.PGConnection) conn).addDataType("geometry",
		// Class.forName("org.postgis.PGgeometry"));

		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, number);
		preparedStatement.setLong(2, timeInMillis);
		preparedStatement.setDouble(3, distance);
		preparedStatement.setObject(4, new PGgeometry(lineString));

		inserts = preparedStatement.executeUpdate();

		conn.close();

		logger.info(inserts + " inserted");

	}

	public int getNumberOfInserts() {
		return inserts;
	}

}
