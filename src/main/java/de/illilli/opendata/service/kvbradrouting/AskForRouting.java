package de.illilli.opendata.service.kvbradrouting;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.api.GraphHopperWeb;
import com.graphhopper.util.PointList;
import com.graphhopper.util.shapes.GHPoint;

import de.illilli.opendata.service.Config;

public class AskForRouting {

	private static final Logger logger = Logger.getLogger(AskForRouting.class);

	private PointList pointList;
	private long timeInMillis;
	private double distance;

	public AskForRouting(List<GHPoint> ghPointList) {

		GraphHopperWeb graphHopper = new GraphHopperWeb();
		// insert your key here
		graphHopper.setKey(Config.getProperty("graphhopper.licence.key"));
		// change timeout, default is 5 seconds
		graphHopper.getDownloader().setConnectTimeout(10, TimeUnit.SECONDS);

		// specify at least two coordinates
		GHRequest ghRequest = new GHRequest();
		for (GHPoint point : ghPointList) {
			ghRequest.addPoint(point);
		}
		// Set vehicle like car, bike and foot
		ghRequest.setVehicle("bike");
		// Optionally enable/disable elevation in output PointList, currently
		// bike and foot support elevation, default is false
		ghRequest.getHints().put("elevation", false);
		// Optionally enable/disable turn instruction information, defaults is
		// true
		ghRequest.getHints().put("instructions", false);
		// Optionally enable/disable path geometry information, default is true
		ghRequest.getHints().put("calcPoints", true);
		// note: turn off instructions and calcPoints if you just need the
		// distance or time information to make calculation and transmission
		// faster

		// Optionally set specific locale for instruction information, supports
		// already over 25 languages, defaults to English
		ghRequest.setLocale(Locale.GERMAN);

		GHResponse ghResponse = graphHopper.route(ghRequest);

		if (ghResponse.hasErrors()) {
			// handle or throw exceptions res.getErrors()
			return;
		}

		// get path geometry information (latitude, longitude and optionally
		// elevation)
		pointList = ghResponse.getPoints();
		// logger.debug(pointList);
		logger.debug(pointList.toGeoJson());
		// distance of the full path, in meter
		distance = ghResponse.getDistance();
		logger.debug(distance);
		// time of the full path, in milliseconds
		timeInMillis = ghResponse.getTime();
		logger.debug(timeInMillis);
	}

	public PointList getPointList() {
		return pointList;
	}

	public long getTimeInMillis() {
		return timeInMillis;
	}

	public double getDistance() {
		return distance;
	}

}
