package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class AskForBikesMapDependsOnModtime extends AskForBikes {

	public AskForBikesMapDependsOnModtime(long lastrun)
			throws MalformedURLException, IOException {
		String urlString = url + "?lastrun=" + lastrun;
		this.inputStream = new URL(urlString).openStream();
	}

	public AskForBikesMapDependsOnModtime(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
