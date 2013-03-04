package ua.org.romashchenko.study6.importerdata.model;

/**
 *
 * @author Christina Romashchenko
 */
public class Address {

    private long id;
    private String country;
    private String region;
    private String city;
    private String postcode;

    public Address(String country, String region, String city, String postcode) {
        this.country = country;
        this.region = region;
        this.city = city;
        this.postcode = postcode;
    }

    public Address(long id, String country, String region, String city, String postcode) {
        this(country, region, city, postcode);
        this.id = id;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return "Address{" + "id=" + id + ", country=" + country + ", region=" + region + ", city=" + city + ", postcode=" + postcode + '}';
    }
}
