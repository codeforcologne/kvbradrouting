package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.geojson.FeatureCollection;

import com.fasterxml.jackson.core.JsonProcessingException;

import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.kvbradrouting.jdbc.InsertLastRun;
import de.illilli.opendata.service.kvbradrouting.jdbc.SelectLastrun;

/**
 * Diese Facade ermittelt das Routing zwischen den Points der Fahrräder. Es
 * sollen nicht bei jedem Lauf alle points ermittelt werden. Daher wird beim
 * letzten Lauf ein Zeitstempel in der Datenbank gesetzt, der als Anhaltspunkt
 * für den nächsten Lauf dient.
 */
public class RoutingFacade implements Facade {

	private static final Logger logger = Logger.getLogger(RoutingFacade.class);

	private FeatureCollection featureCollection;

	public RoutingFacade() throws SQLException, NamingException, IOException,
			ClassNotFoundException {
		// Prüfe, wann der letzte Lauf war
		long lastrun = new SelectLastrun().getTime();
		// lese alle Änderungen nach dem letzten Lauf
		AskForBikes askForBikes = new AskForBikesMapDependsOnModtime(lastrun);
		// erstelle für alle Räder, bei denen sich was geändert hat ein
		// Routing über alle Points
		InsertRoutingCollectorByBike insertRouting = new InsertRoutingCollectorByBike(
				askForBikes);
		// vermerken, dass Daten geschrieben wurde
		int numberOfInserts = new InsertLastRun(
				insertRouting.getNumberOfInserts() + " inserted")
				.getNumberOfInserts();
		if (numberOfInserts > 0) {
			logger.info("number of bikes: " + askForBikes.getBikesMap().size());
		} else {
			logger.warn("unable to set lastrun; please check database");
		}
	}

	@Override
	public String getJson() throws JsonProcessingException {
		// TODO Auto-generated method stub
		return "Anzahl berechneter/ eingefügter Routings";
	}

}
