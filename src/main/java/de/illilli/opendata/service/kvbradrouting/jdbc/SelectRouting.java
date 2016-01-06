package de.illilli.opendata.service.kvbradrouting.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.NamingException;

import org.apache.commons.dbutils.handlers.BeanListHandler;

public class SelectRouting extends Select<RoutingDao> {

	public SelectRouting() throws SQLException, NamingException, IOException {
		queryString = "/selectRouting.sql";
		Object[] params = new Object[0];
		runSelect(new BeanListHandler<RoutingDao>(RoutingDao.class), params);
	}

	public SelectRouting(Long modtime) throws SQLException, NamingException, IOException {
		queryString = "/selectRoutingByModtime.sql";
		Object[] params = new Object[] { new Timestamp(modtime) };
		runSelect(new BeanListHandler<RoutingDao>(RoutingDao.class), params);
	}

	public SelectRouting(Integer number) throws SQLException, NamingException, IOException {
		queryString = "/selectRoutingByNumber.sql";
		Object[] params = new Object[] { number };
		runSelect(new BeanListHandler<RoutingDao>(RoutingDao.class), params);
	}

}
