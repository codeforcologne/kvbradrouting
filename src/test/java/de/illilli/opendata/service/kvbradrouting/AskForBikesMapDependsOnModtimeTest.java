package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AskForBikesMapDependsOnModtimeTest {

	private static final Logger logger = Logger
			.getLogger(AskForBikesMapDependsOnModtimeTest.class);

	private static final long ONEDAY = 864000000;
	private static final long ONEHOUR = 3600000;
	private static final long SIXHOURS = 21600000;
	private static final long FROMTHEBEGINNING = 0;

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Diese Methode erlaubt es Daten aus der Datenbank auszulesen und für die
	 * weitere Verarbeitung z.B. in Tests zur Verfügung zu stellen.
	 * 
	 * @param args
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static void main(String[] args) throws MalformedURLException,
			IOException {
		// long lastrun = System.currentTimeMillis() - SIXHOURS;
		AskForBikes askFor = new AskForBikesMapDependsOnModtime(
				FROMTHEBEGINNING);
		Map<Integer, List<BikeBo>> bikesMap = askFor.getBikesMap();
		Gson gson = new Gson();
		String json = gson.toJson(bikesMap);
		PrintWriter out = new PrintWriter("bikesmap.json");
		out.print(json);
		out.close();
		logger.debug("size: " + bikesMap.size() + "; " + bikesMap);
	}

	@Test
	public void testForBikesMapCount() throws IOException {
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/bikesmap.json");
		Type type = new TypeToken<Map<Integer, List<BikeBo>>>() {
		}.getType();
		Gson gson = new Gson();
		Map<Integer, List<BikeBo>> bikesMap = gson.fromJson(
				IOUtils.toString(inputStream), type);
		int expected = 775;
		int actual = bikesMap.size();
		Assert.assertEquals(expected, actual);
	}

}
