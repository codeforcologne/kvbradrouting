package de.illilli.opendata.service.kvbradrouting.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.postgis.LineString;
import org.postgis.Point;

import de.illilli.jdbc.ConnectionFactory;

public class InsertRouting {

	private static final Logger logger = Logger.getLogger(InsertRouting.class);
	private int inserts;

	String queryString = "/insertRouting.sql";

	public InsertRouting(int number, long timeInMillis, double distance,
			Point[] points) throws SQLException, NamingException, IOException {

		Connection conn = ConnectionFactory.getConnection();
		InputStream inputStream = this.getClass().getResourceAsStream(
				this.queryString);
		String sql = IOUtils.toString(inputStream);

		// PGgeometry geomGgeometry = new PGgeometry();
		LineString lineString = new LineString(points);

		QueryRunner run = new QueryRunner();
		inserts = run.update(conn, sql, number, timeInMillis, distance,
				lineString);

		conn.close();

		logger.info(inserts + " inserted");

	}

	public int getNumberOfInserts() {
		return inserts;
	}

}
