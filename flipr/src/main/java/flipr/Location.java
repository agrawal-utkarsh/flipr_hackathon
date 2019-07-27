package flipr;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Location {

	private Double lat;
	private Double longi;
	
	public Location()
	{
		
	}

	public Location(Double lat, Double longi) {
		super();
		this.lat = lat;
		this.longi = longi;
	}

	@Override
	public String toString() {
		return "Location [lat=" + lat + ", longi=" + longi + "]";
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLongi() {
		return longi;
	}

	public void setLongi(Double longi) {
		this.longi = longi;
	}


}
