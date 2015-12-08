package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.junit.Before;

import de.illilli.opendata.service.kvbradrouting.jdbc.InsertLastRunToDb;

public class InsertLastRunTest {

	private static final Logger logger = Logger
			.getLogger(InsertLastRunTest.class);

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) throws SQLException,
			NamingException, IOException {
		JndiProperties.setUpConnectionForJndi();

		InsertLastRunToDb lastRun = new InsertLastRunToDb(10);
		int numberOfInserts = lastRun.getNumberOfInserts();
		logger.debug("numberOfInserts: " + numberOfInserts);
	}

}
