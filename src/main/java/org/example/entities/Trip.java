package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
public class Trip {

	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private String description;

	@ManyToOne
	private User user;
	@OneToMany
	// todo lazy init globally
	private Collection<Place> places = new ArrayList<>();

	public Trip() {
	}

	public Trip(String name, String description, User user, Collection<Place> places) {
		this.name = name;
		this.description = description;
		this.user = user;
		this.places = places;
	}

	@Override
	public String toString() {
		return "Trip{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", user=" + user +
				", places=" + places +
				'}';
	}
}
