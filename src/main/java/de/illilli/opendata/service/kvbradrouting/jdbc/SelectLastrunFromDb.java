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
import de.illilli.opendata.service.kvbradrouting.SelectLastrun;

public class SelectLastrunFromDb implements SelectLastrun {

	String queryString = "/selectLastRun.sql";
	private KvbradroutingDao kvradroutingDb;

	public SelectLastrunFromDb() throws SQLException, NamingException,
			IOException {
		Connection conn = ConnectionFactory.getConnection();
		InputStream inputStream = this.getClass().getResourceAsStream(
				queryString);
		String sql = IOUtils.toString(inputStream);
		QueryRunner query = new QueryRunner();
		ResultSetHandler<KvbradroutingDao> handler = new BeanHandler<KvbradroutingDao>(
				KvbradroutingDao.class);
		kvradroutingDb = query.query(conn, sql, handler);
	}

	@Override
	public long getTime() {
		if (kvradroutingDb == null) {
			return 0;

		} else {
			return kvradroutingDb.getModtime().getTime();
		}
	}

}
