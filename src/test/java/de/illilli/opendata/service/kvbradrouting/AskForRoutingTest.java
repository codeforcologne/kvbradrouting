package de.illilli.opendata.service.kvbradrouting;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;

import com.graphhopper.util.PointList;
import com.graphhopper.util.shapes.GHPoint;

public class AskForRoutingTest {

	private static final Logger logger = Logger
			.getLogger(AskForRoutingTest.class);

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) {
		List<GHPoint> ghPointList = new ArrayList<GHPoint>();
		ghPointList.add(new GHPoint(50.937702, 6.983464617));
		ghPointList.add(new GHPoint(50.93676388, 6.967987017));
		ghPointList.add(new GHPoint(50.93865232, 6.820693817));
		AskForRouting askFor = new AskForRouting(ghPointList);
		PointList pointList = askFor.getPointList();
		for (Double[] d : pointList.toGeoJson()) {
			logger.debug("[" + d[0] + "," + d[1] + "]");
		}

	}
}
