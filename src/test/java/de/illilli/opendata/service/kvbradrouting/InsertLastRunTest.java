package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.junit.Before;

public class InsertLastRunTest {

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) throws SQLException,
			NamingException, IOException {
		JndiProperties.setUpConnectionForJndi();

		InsertLastRun lastRun = new InsertLastRun("test");
		int numberOfInserts = lastRun.getNumberOfInserts();
		System.out.println(numberOfInserts);
	}

}
