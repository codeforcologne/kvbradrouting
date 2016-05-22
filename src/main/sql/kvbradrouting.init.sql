CREATE TABLE kvbradrouting (
    numberofinsert      integer,
    modtime      timestamp DEFAULT current_timestamp
);
CREATE TABLE routing (
	number       integer NOT NULL,
	timeinmillis bigint,
	distance     double precision,
	modtime      timestamp DEFAULT current_timestamp
);
SELECT AddGeometryColumn ('public','routing','geom',4326,'LINESTRING',2);
