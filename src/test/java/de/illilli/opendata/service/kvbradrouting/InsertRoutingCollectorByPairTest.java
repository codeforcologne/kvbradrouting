package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class InsertRoutingCollectorByPairTest {

	private static final Logger logger = Logger
			.getLogger(InsertRoutingCollectorByPairTest.class);

	private static final long ONEDAY = 864000000;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testGetNumberOfInserts() throws ClassNotFoundException,
			IOException, SQLException, NamingException {
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/bikesmap.json");

		Gson gson = new Gson();
		Type type = new TypeToken<Map<Integer, List<BikeBo>>>() {
		}.getType();
		String json = IOUtils.toString(inputStream);
		Map<Integer, List<BikeBo>> bikesMap = gson.fromJson(json, type);

		InsertRoutingCollector insertRouting = new InsertRoutingCollectorByPair(
				bikesMap);
		int numberOfInserts = insertRouting.getNumberOfInserts();
		logger.info(numberOfInserts);
	}

	/**
	 * Erstellt eine BikesMap serialisiert sie und schreibt das Ergebnis in das
	 * Wurzelverzeichnis des Projektes. Die Datei kann dann nach
	 * src/main/resources kopiert werden.
	 * 
	 * @param args
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static void main(String[] args) throws MalformedURLException,
			IOException {
		JndiProperties.setUpConnectionForJndi();

		long lastrun = System.currentTimeMillis() - ONEDAY;
		AskForBikes askForBikes = new AskForBikesMapDependsOnModtime(lastrun);

		Type type = new TypeToken<Map<Integer, List<BikeBo>>>() {
		}.getType();

		String json = new Gson().toJson(askForBikes.getBikesMap(), type);
		PrintWriter out = new PrintWriter("bikesmap.json");
		out.println(json);
		out.close();
		logger.info(json);
	}

}
