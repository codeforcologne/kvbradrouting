package de.illilli.opendata.service.kvbradrouting.jdbc;

import java.sql.Timestamp;

import org.postgis.PGgeometry;

/**
 * <pre>
 * number integer NOT NULL, 
 * timeinmillis bigint, 
 * distance double precision,
 * modtime timestamp DEFAULT current_timestamp ); SELECT AddGeometryColumn
 * geom LINESTRING
 * </pre>
 *
 */
public class RoutingDb {

	private int number;
	private long timeinmillis;
	private double distance;
	private Timestamp modtime;
	private PGgeometry geom;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public long getTimeinmillis() {
		return timeinmillis;
	}

	public void setTimeinmillis(long timeinmillis) {
		this.timeinmillis = timeinmillis;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Timestamp getModtime() {
		return modtime;
	}

	public void setModtime(Timestamp modtime) {
		this.modtime = modtime;
	}

	public PGgeometry getGeom() {
		return geom;
	}

	public void setGeom(PGgeometry geom) {
		this.geom = geom;
	}

	@Override
	public String toString() {
		return "RoutingDb [number=" + number + ", timeinmillis=" + timeinmillis
				+ ", distance=" + distance + ", modtime=" + modtime + ", geom="
				+ geom + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(distance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((geom == null) ? 0 : geom.hashCode());
		result = prime * result + ((modtime == null) ? 0 : modtime.hashCode());
		result = prime * result + number;
		result = prime * result + (int) (timeinmillis ^ (timeinmillis >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoutingDb other = (RoutingDb) obj;
		if (Double.doubleToLongBits(distance) != Double
				.doubleToLongBits(other.distance))
			return false;
		if (geom == null) {
			if (other.geom != null)
				return false;
		} else if (!geom.equals(other.geom))
			return false;
		if (modtime == null) {
			if (other.modtime != null)
				return false;
		} else if (!modtime.equals(other.modtime))
			return false;
		if (number != other.number)
			return false;
		if (timeinmillis != other.timeinmillis)
			return false;
		return true;
	}

}
