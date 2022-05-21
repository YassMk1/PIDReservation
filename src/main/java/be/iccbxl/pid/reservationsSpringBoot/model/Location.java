package be.iccbxl.pid.reservationsSpringBoot.model;

import com.github.slugify.Slugify;

import javax.persistence.*;

@Entity
@Table(name="locations")
public class Location {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String slug;

    private String designation;
    private String address;

    @ManyToOne
    @JoinColumn(name="locality_id", nullable=false)
    private Locality locality;

    private String website;
    private String phone;

    protected Location() { }

    public Location(String slug, String designation, String address, Locality locality, String website, String phone) {
        Slugify slg = new Slugify();

        this.slug = slg.slugify(designation);
        this.designation = designation;
        this.address = address;
        this.locality = locality;
        this.website = website;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    private void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;

        Slugify slg = new Slugify();

        this.setSlug(slg.slugify(designation));
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Locality getLocality() {
        return locality;
    }

    public void setLocality(Locality locality) {
        this.locality.removeLocation(this);	//déménager de l’ancienne localité
        this.locality = locality;
        this.locality.addLocation(this);		//emménager dans la nouvelle localité
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Location [id=" + id + ", slug=" + slug + ", designation=" + designation
                + ", address=" + address	+ ", locality=" + locality + ", website="
                + website + ", phone=" + phone + "]";
    }

}
