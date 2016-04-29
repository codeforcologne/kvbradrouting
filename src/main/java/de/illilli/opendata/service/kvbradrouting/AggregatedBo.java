package de.illilli.opendata.service.kvbradrouting;

public class AggregatedBo {

	private int number;
	private long timeinmillis;
	private long distance;
	private int countdata;

	public AggregatedBo() {

	}

	public AggregatedBo(int number) {
		this.number = number;
	}

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

	public long getDistance() {
		return distance;
	}

	public void setDistance(long distance) {
		this.distance = distance;
	}

	public int getCountdata() {
		return countdata;
	}

	public void setCountdata(int countdata) {
		this.countdata = countdata;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + countdata;
		result = prime * result + (int) (distance ^ (distance >>> 32));
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
		AggregatedBo other = (AggregatedBo) obj;
		if (number != other.number)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AggregatedBo [number=" + number + ", timeinmillis=" + timeinmillis + ", distance=" + distance
				+ ", countdata=" + countdata + "]";
	}

}
