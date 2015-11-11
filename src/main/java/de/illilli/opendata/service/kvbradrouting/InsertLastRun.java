package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import de.illilli.jdbc.ConnectionFactory;

public class InsertLastRun {

	private static final Logger logger = Logger.getLogger(InsertLastRun.class);
	private int inserts;

	String queryString = "/insertLastRun.sql";

	public InsertLastRun(String comment) throws SQLException, NamingException,
			IOException {

		Connection conn = ConnectionFactory.getConnection();
		InputStream inputStream = this.getClass().getResourceAsStream(
				this.queryString);
		String sql = IOUtils.toString(inputStream);

		QueryRunner run = new QueryRunner();
		inserts = run.update(conn, sql, comment);

		conn.close();

		logger.info(inserts + " inserted");
	}
}
