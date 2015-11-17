package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Before;

public class AskForBikesMapDependsOnModtimeTest {

	private static final Logger logger = Logger
			.getLogger(AskForBikesMapDependsOnModtimeTest.class);

	private static final long ONEDAY = 864000000;
	private static final long ONEHOUR = 3600000;
	private static final long SIXHOURS = 21600000;

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) throws MalformedURLException,
			IOException {
		long lastrun = System.currentTimeMillis() - SIXHOURS;
		AskForBikes askFor = new AskForBikesMapDependsOnModtime(lastrun);
		Map<Integer, List<BikeBo>> bikesMap = askFor.getBikesMap();
		logger.debug("size: " + bikesMap.size() + "; " + bikesMap);
	}

}
