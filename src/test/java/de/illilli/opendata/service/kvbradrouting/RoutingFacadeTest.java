package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.junit.Before;

import de.illilli.opendata.service.Facade;

public class RoutingFacadeTest {

	private static final Logger logger = Logger
			.getLogger(RoutingFacadeTest.class);

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException, NamingException, IOException {

		JndiProperties.setUpConnectionForJndi();

		Facade facade = new RoutingFacade();
		String json = facade.getJson();
		logger.debug(json);
	}

}
