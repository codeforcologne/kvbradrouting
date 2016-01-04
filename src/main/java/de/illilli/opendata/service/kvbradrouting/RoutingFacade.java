package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;

import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.kvbradrouting.jdbc.InsertLastRunToDb;
import de.illilli.opendata.service.kvbradrouting.jdbc.SelectLastrunFromDb;

/**
 * Diese Facade ermittelt das Routing zwischen den Points der Fahrräder. Es
 * sollen nicht bei jedem Lauf alle points ermittelt werden. Daher wird beim
 * letzten Lauf ein Zeitstempel in der Datenbank gesetzt, der als Anhaltspunkt
 * für den nächsten Lauf dient.
 */
public class RoutingFacade implements Facade {

	private static final Logger logger = Logger.getLogger(RoutingFacade.class);

	private long lastrun;
	private AskForBikes askForBikes;
	private InsertRoutingCollector insertRouting;
	private InsertLastRun insertOfLastRun;

	public RoutingFacade() throws SQLException, NamingException, IOException,
			ClassNotFoundException {
		// Prüfe, wann der letzte Lauf war
		this.lastrun = new SelectLastrunFromDb().getTime();
		// lese alle Änderungen nach dem letzten Lauf
		this.askForBikes = new AskForBikesMapDependsOnModtime(lastrun);
		// erstelle für alle Räder, bei denen sich was geändert hat ein
		// Routing über alle Points
		this.insertRouting = new InsertRoutingCollectorByPair(
				new RouteAndInsertToDb());
		insertRouting.routeAndInsert(lastrun, askForBikes.getBikesMap());
		// vermerken, dass Daten geschrieben wurde
		this.insertOfLastRun = new InsertLastRunToDb(
				insertRouting.getNumberOfInserts());
		int numberOfInserts = insertOfLastRun.getNumberOfInserts();
		if (numberOfInserts > 0) {
			logger.info("number of bikes: " + askForBikes.getBikesMap().size());
		} else {
			logger.warn("unable to set lastrun; please check database");
		}
	}

	/**
	 * Konstruktor for testing purpose.
	 * 
	 * @param selectLastrun
	 * @param askForBikes
	 * @param insertRoutingCollector
	 * @param insertLastRun
	 */
	public RoutingFacade(SelectLastrun selectLastrun, AskForBikes askForBikes,
			InsertRoutingCollector insertRoutingCollector,
			InsertLastRun insertLastRun) {
		this.lastrun = selectLastrun.getTime();
		this.askForBikes = askForBikes;
		this.insertRouting = insertRoutingCollector;
		this.insertOfLastRun = insertLastRun;
	}

	@Override
	public String getJson() throws JsonProcessingException {
		return "Anzahl berechneter/ eingefügter Routings: "
				+ this.insertOfLastRun.getNumberOfInserts();
	}

}
