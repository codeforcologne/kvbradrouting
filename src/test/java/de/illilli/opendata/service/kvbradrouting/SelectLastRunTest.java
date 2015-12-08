package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.junit.Before;

import de.illilli.opendata.service.kvbradrouting.jdbc.SelectLastrunFromDb;

public class SelectLastRunTest {

	private static final Logger logger = Logger
			.getLogger(SelectLastRunTest.class);

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) throws SQLException,
			NamingException, IOException {
		JndiProperties.setUpConnectionForJndi();

		SelectLastrunFromDb lastRun = new SelectLastrunFromDb();
		long last = lastRun.getTime();
		logger.debug("lastRun: " + new Date(last).toString());
	}

}
