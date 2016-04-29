package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.kvbradrouting.jdbc.Select;
import de.illilli.opendata.service.kvbradrouting.jdbc.SelectForAllbikesAggregated;

public class AggregatedJsonFacade implements Facade {

	private static final Logger logger = Logger.getLogger(AggregatedJsonFacade.class);
	private List<AggregatedBo> list = new ArrayList<>();

	public AggregatedJsonFacade() throws SQLException, NamingException, IOException {
		Select<AggregatedBo> select = new SelectForAllbikesAggregated();
		list = select.getDbObjectList();
		logger.info("SelectForAllbikesAggregated: '" + list.size() + "' items found");
	}

	@Override
	public String getJson() throws JsonProcessingException {
		Gson gson = new Gson();
		return gson.toJson(list);
	}

}
