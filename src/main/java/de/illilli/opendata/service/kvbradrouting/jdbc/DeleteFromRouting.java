package de.illilli.opendata.service.kvbradrouting.jdbc;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

public class DeleteFromRouting extends Delete {

	public DeleteFromRouting() throws SQLException, NamingException, IOException {
		queryString = "/deleteFromRouting.sql";
		update(new Object[] {});
	}
}
