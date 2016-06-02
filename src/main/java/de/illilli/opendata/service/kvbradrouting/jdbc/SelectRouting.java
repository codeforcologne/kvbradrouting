package de.illilli.opendata.service.kvbradrouting.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.NamingException;

import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 * This class retrieves routing information from database. There are three
 * possibilities:
 * <ol>
 * <li>get all routings available in Database</li>
 * <li>get routings dependend from modtime; just get newer ones</li>
 * <li>get routings dependend from number</li>
 * </ol>
 *
 */
public class SelectRouting extends Select<RoutingDao> {

	public SelectRouting() throws SQLException, NamingException, IOException, ClassNotFoundException {
		queryString = "/selectRouting.sql";
		Object[] params = new Object[0];
		runSelect(new BeanListHandler<RoutingDao>(RoutingDao.class), params);
	}

	public SelectRouting(Long modtime) throws SQLException, NamingException, IOException, ClassNotFoundException {
		queryString = "/selectRoutingByModtime.sql";
		Object[] params = new Object[] { new Timestamp(modtime) };
		runSelect(new BeanListHandler<RoutingDao>(RoutingDao.class), params);
	}

	public SelectRouting(Integer number) throws SQLException, NamingException, IOException, ClassNotFoundException {
		queryString = "/selectRoutingByNumber.sql";
		Object[] params = new Object[] { number };
		runSelect(new BeanListHandler<RoutingDao>(RoutingDao.class), params);
	}

}
