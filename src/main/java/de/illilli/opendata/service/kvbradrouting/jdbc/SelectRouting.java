package de.illilli.opendata.service.kvbradrouting.jdbc;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.commons.dbutils.handlers.BeanListHandler;

public class SelectRouting extends Select<RoutingDb> {

	public SelectRouting() throws SQLException, NamingException, IOException {
		queryString = "/selectRouting.sql";
		Object[] params = new Object[0];
		runSelect(new BeanListHandler<RoutingDb>(RoutingDb.class), params);
	}

	public SelectRouting(Integer number) throws SQLException, NamingException,
			IOException {
		queryString = "/selectRoutingByNumber.sql";
		Object[] params = new Object[] { number };
		runSelect(new BeanListHandler<RoutingDb>(RoutingDb.class), params);
	}

}
