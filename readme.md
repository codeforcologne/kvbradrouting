#KVB Rad Routing

Dieser Service liest über kvbradlive die bereits gespeicherten Daten aus, gruppiert sie nach Bike-Numbers zu Paaren und ermittelt mit Hilfe von graphhopper die Route zwischen diesen Punkten.

## Services

### Json Service

Es ist möglich die durch die Routing Funktion erstellten Daten im Json-Format über den REST-Endpoint /kvbrouting/service/json abzufragen. Wenn nur die Information eines Fahrrades abgefragt werden soll, muss der die Nummer dem Pfad hinzugfügt werden: /kvbrouting/service/json/{number}

### Insert Service

Das Routing wird über den REST-EndPoint '/kvbrouting/service/insert' ausgelöst. Durch seinen Aufruf, werden zunächst alle Fahrräder bestimmt, deren Positionen sich seit dem letzten Durchgang verändert haben. Danach wird auf alle gespeicherten Standorte das Routing durchgeführt. Die ermittelten Werte werden als LINESTRING in der Datenbank gespeichert. Wenn dieser Service wiederholt ausgeführt werden soll, sollte dies z.B. mit Hilfe des cron erfolgten.

### GeoJson Service

Über den REST-EndPoint /kvbrouting/service/geojson lassen sich alle routing-Informationen abfragen. Durch erweitern der Abfrage um die Nummer des Fahrrads wird nur die Linie angezeigt, die einem Fahrrad zuordnenbar ist.

## Routing Engine

Zur Bestimmung des Routing wird die Software [graphhopper](https://graphhopper.com/) verwendet. Hier wird der Service von Graphhopper selbst verwendet. Daher ist es notwendig einen Lizenzschlüssel zu beantragen. Dieser wird in der Datei config.properties dem Schlüssel graphhopper.licence.key zugewiesen. In Abhängigkeit von der Lizenz ist es möglich, dass nur 500 Routing-Abfragen pro Tag zugelassen sind. Dies bedeutet, dass ggf. nicht für alle Fahrräder das Routing innerhalb eines Tages bestimmt werden kann.

## Database

Für die Verwendung dieses Service wird eine PostgreSQL mit PostGis benötigt und zwei Datenbank Tabellen. 

### Tabelle für die Zeitpunkt der letzten Abfrage

	CREATE TABLE kvbradrouting (
	    numberofinsert      integer,
	    modtime      timestamp DEFAULT current_timestamp
	);

### Tabelle für die Routing-Ergebnisse

	CREATE TABLE routing (
    	number       integer NOT NULL,
    	timeinmillis bigint,
    	distance     double precision,
    	modtime      timestamp DEFAULT current_timestamp
	);
	SELECT AddGeometryColumn ('public','routing','geom',4326,'LINESTRING',2);
	
## Test

Da zur Zeit keine Integration Test Stage zur Verfügung steht, sind alle Tests, die eineDatenbank voraussetzt als main codiert. Um eine Datenbankverbindung hierfür zur Verfügung stellen zu können, muss die Datei src/test/resources/jndi.properties.template in src/test/resources/jndi.properties kopiert und die entsprechenden Parameter für die Datenbank-Verbindung gesetzt werden.

Weiterhin muss im Test die Verbindung für die Verwendung im Test-Betrieb konfiguriert werden. Dies erfolgt durch Aufruf von 		

	JndiProperties.setUpConnectionForJndi();

Dadurch werden die connection Parameter zum Auslesen per JNDI gesetzt. Das Anfordern der Datenbankverbindung erfolgt dann innerhalb der ausführenden Klasse.