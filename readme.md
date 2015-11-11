#KVB Rad Routing

Dieser Service liest über kvbradlive die bereits gespeicherten Daten aus, gruppiert sie nach Bike-Numbers zu Paaren und ermittelt die die Route zwischen diesen Punkten.

## Routing Engine

Zum Routing wird die Software graphhopper verwendet.

## Database

	CREATE TABLE kvbradrouting (
	    comment      varchar(256),
	    modtime      timestamp DEFAULT current_timestamp
	);

	
## Test

### Tests mit Datenbank

Da zur Zeit keine Integration Test Stage zur Verfügung steht, sind alle Tests, die eineDatenbank voraussetzt als main codiert. Um eine Datenbankverbindung hierfür zur Verfügung stellen zu können, muss die Datei src/test/resources/jndi.properties.template in src/test/resources/jndi.properties kopiert und die entsprechenden Parameter zur Datenbank gesetzt werden.

Weiterhin muss die ConnectionFactory für die Verwendung im Test-Betrieb konfiguriert werden.