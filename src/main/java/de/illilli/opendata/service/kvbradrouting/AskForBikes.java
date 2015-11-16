package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.illilli.opendata.service.Config;

/**
 * Diese Klasse implementiert die Methode getBikesMap().
 */
public abstract class AskForBikes {

	String url = Config.getProperty("kvblive.bikesmap.url");
	InputStream inputStream;

	/**
	 * This class
	 * 
	 * @return
	 * @throws IOException
	 */
	public Map<Integer, List<BikeBo>> getBikesMap() throws IOException {
		Gson gson = new Gson();
		Type type = new TypeToken<Map<Integer, List<BikeBo>>>() {
		}.getType();
		String json = IOUtils.toString(inputStream);
		Map<Integer, List<BikeBo>> bikesMap = gson.fromJson(json, type);
		return bikesMap;
	}

}
