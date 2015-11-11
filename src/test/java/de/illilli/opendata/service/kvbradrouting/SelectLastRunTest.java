package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.junit.Before;

public class SelectLastRunTest {

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) throws SQLException,
			NamingException, IOException {
		SelectLastRun lastRun = new SelectLastRun();
		long last = lastRun.getLastRun();
		System.out.println(last);
	}

}
