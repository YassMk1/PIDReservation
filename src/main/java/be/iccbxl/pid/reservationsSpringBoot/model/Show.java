package be.iccbxl.pid.reservationsSpringBoot.model;

import java.util.List;
import java.util.ArrayList;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.github.slugify.Slugify;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="shows")
@Getter
@Setter
public class Show {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(unique=true)
	private String slug;

	private String title;
	private String description;

	@Column(name="poster_url")
	private String posterUrl;

	@OneToMany(targetEntity=Representation.class, mappedBy="show")
	private List<Representation> representations = new ArrayList<>();
	
	/**
	 * Lieu de création du spectacle
	 */
	@ManyToOne
	@JoinColumn(name="location_id", nullable=true)
	private Location location;
	
	@ManyToMany(mappedBy = "shows")
	private List<ArtistType> artistTypes = new ArrayList<>();
	
	private boolean bookable;
	private double price;
	
	/**
	 * Date de création du spectacle
	 */
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	/**
	 * Date de modification du spectacle
	 */
	@Column(name="updated_at")
	private LocalDateTime updatedAt;

	@ManyToOne
	@JoinColumn(name="category_id", nullable=false)
	private Category category;

	public Show() { }
	
	public Show(String title, String description, String posterUrl, Location location, boolean bookable,
			double price) {
		Slugify slg = new Slugify();
		
		this.slug = slg.slugify(title);
		this.title = title;
		this.description = description;
		this.posterUrl = posterUrl;
		this.location = location;
		this.bookable = bookable;
		this.price = price;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = null;
	}

	public void setTitle(String title) {
		this.title = title;
		
		Slugify slg = new Slugify();
		
		this.setSlug(slg.slugify(title));
	}

	public void setLocation(Location location) {
		this.location.removeShow(this);	//déménager de l’ancien lieu
		this.location = location;
		this.location.addShow(this);		//emménager dans le nouveau lieu
	}

	public Show addRepresentation(Representation representation) {
		if(!this.representations.contains(representation)) {
			this.representations.add(representation);
			representation.setShow(this);
		}
		
		return this;
	}
	
	public Show removeRepresentation(Representation representation) {
		if(this.representations.contains(representation)) {
			this.representations.remove(representation);
			if(representation.getLocation().equals(this)) {
				representation.setLocation(null);
			}
		}
		
		return this;
	}
	
	public Show addArtistType(ArtistType artistType) {
		if(!this.artistTypes.contains(artistType)) {
			this.artistTypes.add(artistType);
			artistType.addShow(this);
		}
		
		return this;
	}
	
	public Show removeArtistType(ArtistType artistType) {
		if(this.artistTypes.contains(artistType)) {
			this.artistTypes.remove(artistType);
			artistType.getShows().remove(this);
		}
		
		return this;
	}


	@Override
	public String toString() {
		return "Show [id=" + id + ", slug=" + slug + ", title=" + title 
			+ ", description=" + description + ", posterUrl=" + posterUrl + ", location=" 
			+ location + ", bookable=" + bookable + ", price=" + price
			+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", representations=" + representations.size() +"]";
	}
	
}

