package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Place {

	// todo: there can only be one place per trip without a "from".
	//  froms have to be unique. a place can only be used as from once. Otherwise u could go 2 places at the same time


	@Id
	@GeneratedValue(/*strategy = GenerationType.IDENTITY*/) // todo: this everywhere
	private Long id;

	private Date timeArrived;
	private Date timeLeft;
	// Could me markdown at some point
	private String description;

	// todo: use decimal, maybe? https://stackoverflow.com/questions/1196415/what-datatype-to-use-when-storing-latitude-and-longitude-data-in-sql-databases
	private Float latitude;
	private Float longitude;

	// todo: not nullable
	@ManyToOne
	private Trip trip;
	@OneToOne
	private Place from;
	@OneToOne
	// todo: use Enum
	private TransportationMode arrivedBy;
	@OneToMany
	private Collection<Image> images;

	// coordinates / place name

	// todo: not all yet
	public Place(Trip trip, Date timeArrived, Date timeLeft, String description, Float latitude, Float longitude, Place from) {
		this.trip = trip;
		this.timeArrived = timeArrived;
		this.timeLeft = timeLeft;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
		this.from = from;
	}
}
