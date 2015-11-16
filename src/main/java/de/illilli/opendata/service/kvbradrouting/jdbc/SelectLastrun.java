package de.illilli.opendata.service.kvbradrouting.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.ConnectionFactory;

public class SelectLastrun {

	String queryString = "/selectLastRun.sql";
	private KvbradroutingDb kvradroutingDb;

	public SelectLastrun() throws SQLException, NamingException, IOException {
		Connection conn = ConnectionFactory.getConnection();
		InputStream inputStream = this.getClass().getResourceAsStream(
				queryString);
		String sql = IOUtils.toString(inputStream);
		QueryRunner query = new QueryRunner();
		ResultSetHandler<KvbradroutingDb> handler = new BeanHandler<KvbradroutingDb>(
				KvbradroutingDb.class);
		kvradroutingDb = query.query(conn, sql, handler);
	}

	public long getTime() {
		return kvradroutingDb.getModtime().getTime();
	}

}
