package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.junit.Before;

import de.illilli.opendata.service.kvbradrouting.jdbc.SelectLastrun;

public class SelectLastRunTest {

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) throws SQLException,
			NamingException, IOException {
		JndiProperties.setUpConnectionForJndi();

		SelectLastrun lastRun = new SelectLastrun();
		long last = lastRun.getTime();
		System.out.println(last);
	}

}
