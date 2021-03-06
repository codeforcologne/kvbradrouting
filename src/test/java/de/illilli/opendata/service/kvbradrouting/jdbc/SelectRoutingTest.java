package de.illilli.opendata.service.kvbradrouting.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.junit.Before;

import de.illilli.opendata.service.kvbradrouting.JndiProperties;

public class SelectRoutingTest {

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) throws IOException, SQLException, NamingException, ClassNotFoundException {
		JndiProperties.setUpConnectionForJndi();
		Select<RoutingDao> select = new SelectRouting();
		List<RoutingDao> routingList = select.getDbObjectList();
		System.out.println(routingList);
	}

}
