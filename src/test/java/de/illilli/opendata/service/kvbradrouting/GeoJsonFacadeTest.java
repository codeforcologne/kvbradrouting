package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.junit.Before;

import de.illilli.opendata.service.Facade;

public class GeoJsonFacadeTest {

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) throws IOException, SQLException,
			NamingException {
		JndiProperties.setUpConnectionForJndi();
		Facade facade = new GeoJsonFacade();
		String json = facade.getJson();
		System.out.println(json);
	}

}
