package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class AskForBikesMap extends AskForBikes {

	public AskForBikesMap() throws MalformedURLException, IOException {
		this.inputStream = new URL(url).openStream();
	}

	public AskForBikesMap(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
