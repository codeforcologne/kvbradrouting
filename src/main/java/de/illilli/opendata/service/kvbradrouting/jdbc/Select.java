package de.illilli.opendata.service.kvbradrouting.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.ConnectionFactory;
import de.illilli.jdbc.DbUtilsBeanListHandler;

public abstract class Select<T> {

	String queryString;

	private List<T> dbObjectList = new ArrayList<T>();

	void runSelect(BeanListHandler<T> beanListHandler, Object... params)
			throws SQLException, NamingException, IOException, ClassNotFoundException {

		Connection conn = ConnectionFactory.getConnection();
		InputStream inputStream = this.getClass().getResourceAsStream(queryString);
		String sql = IOUtils.toString(inputStream);

		((org.postgresql.PGConnection) conn).addDataType("geometry", Class.forName("org.postgis.PGgeometry"));

		DbUtilsBeanListHandler<T> rsh = new DbUtilsBeanListHandler<T>(conn, beanListHandler, sql, params);
		dbObjectList = rsh.getList();

		conn.close();
	}

	public List<T> getDbObjectList() {
		return dbObjectList;
	}

}
