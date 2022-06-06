package be.iccbxl.pid.reservationsSpringBoot.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@Entity
@Table(name="types")
public class Type {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

  @NotEmpty
	@Size(min = 1, max = 60)
	private String type;

	@ManyToMany
	@JoinTable(
		  name = "artist_type",
		  joinColumns = @JoinColumn(name = "type_id"),
		  inverseJoinColumns = @JoinColumn(name = "artist_id"))
	private List<Artist> artists = new ArrayList<>();

	public Type(String type) {
		this.type = type;
	}

	public List<Artist> getArtists() {
		return artists;
	}

	public Type addArtist(Artist artist) {
		if(!this.artists.contains(artist)) {
			this.artists.add(artist);
			artist.addType(this);
		}

		return this;
	}

	public Type removeType(Artist artist) {
		if(this.artists.contains(artist)) {
			this.artists.remove(artist);
			artist.getTypes().remove(this);
		}

		return this;
	}


}
