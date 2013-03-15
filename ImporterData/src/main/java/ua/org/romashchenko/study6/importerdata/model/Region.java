/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.org.romashchenko.study6.importerdata.model;

/**
 *
 * @author Christina Romashchenko
 */
public class Region {

    private String region_name;
    private String region_code;
    private Country country;

    public Region(String region_name, String region_code, Country country) {
        this.region_name = region_name;
        this.region_code = region_code;
        this.country = country;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getRegion_code() {
        return region_code;
    }

    public void setRegion_code(String region_code) {
        this.region_code = region_code;
    }

    @Override
    public String toString() {
        return "Region{" + "region_name=" + region_name + ", region_code=" + region_code + ", country=" + country + '}';
    }
    
    
}
