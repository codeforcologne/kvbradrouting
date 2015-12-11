package de.illilli.opendata.service.kvbradrouting;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.illilli.opendata.service.Config;

/**
 * Diese Klasse ist die Schnittstelle für alle AskForBikes Implementierungen und
 * erwartet einen Stream. Die hier implementierte Methode getBikesMap() wandelt
 * die Struktur in eine Map-Struktur.
 */
public abstract class AskForBikes {

	String url = Config.getProperty("kvblive.bikesmap.url");
	InputStream inputStream;

	/**
	 * Diese Methode übernimmt die per InputStream gelieferten Bikes und wandelt
	 * sie mit Hilfe von Gson in ein Map<Integer, List<BikeBo>> Object.
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
		if (bikesMap == null) {
			bikesMap = new Hashtable<Integer, List<BikeBo>>();
		}
		return bikesMap;
	}
}
