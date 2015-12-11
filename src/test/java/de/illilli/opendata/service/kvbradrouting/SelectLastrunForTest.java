package de.illilli.opendata.service.kvbradrouting;

import java.util.Date;

public class SelectLastrunForTest implements SelectLastrun {

	@Override
	public long getTime() {
		return new Date(0).getTime();
	}
}
