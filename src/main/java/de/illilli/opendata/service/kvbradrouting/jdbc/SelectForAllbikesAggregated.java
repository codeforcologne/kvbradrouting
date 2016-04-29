package de.illilli.opendata.service.kvbradrouting.jdbc;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import de.illilli.opendata.service.kvbradrouting.AggregatedBo;

public class SelectForAllbikesAggregated extends Select<AggregatedBo> {

	public SelectForAllbikesAggregated() throws SQLException, NamingException, IOException {
		queryString = "/selectForAllbikesAggregated.sql";
		Object[] params = new Object[0];
		runSelect(new BeanListHandler<AggregatedBo>(AggregatedBo.class), params);
	}

}
