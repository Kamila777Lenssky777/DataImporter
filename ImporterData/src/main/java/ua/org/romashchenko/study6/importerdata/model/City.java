/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.org.romashchenko.study6.importerdata.model;

/**
 *
 * @author Christina Romashchenko
 */
public class City {

    private String city_name;
    private Region region;
    private Postcode postcode;

    public City(String city_name, Region region, Postcode postcode) {
        this.city_name = city_name;
        this.region = region;
        this.postcode = postcode;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Postcode getPostcode() {
        return postcode;
    }

    public void setPostcode(Postcode postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return "City{" + "city_name=" + city_name + ", region=" + region + ", postcode=" + postcode + '}';
    }
}
