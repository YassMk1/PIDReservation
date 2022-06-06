package be.iccbxl.pid.reservationsSpringBoot.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@Entity
@Table(name="localities")
public class Locality {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@NotEmpty
	@Size(min = 1, max = 6)
	private String postalCode;

	@NotEmpty
	@Size(min = 1, max = 60)
	private String locality;

	@OneToMany( targetEntity=Location.class, mappedBy="locality" )
	private List<Location> locations = new ArrayList<>();

	public Locality(String postalCode, String locality) {
		this.postalCode = postalCode;
		this.locality = locality;
	}

	public Locality addLocation(Location location) {
		if(!this.locations.contains(location)) {
			this.locations.add(location);
			location.setLocality(this);
		}

		return this;
	}

	public Locality removeLocation(Location location) {
		if(this.locations.contains(location)) {
			this.locations.remove(location);
			if(location.getLocality().equals(this)) {
				location.setLocality(null);
			}
		}

		return this;
	}

}
