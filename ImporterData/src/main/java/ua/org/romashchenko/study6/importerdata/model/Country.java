/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.org.romashchenko.study6.importerdata.model;

/**
 *
 * @author Christina Romashchenko
 */
public class Country {

    private String country_name;
    private String country_code;

    public Country(String country_name, String country_code) {
        this.country_name = country_name;
        this.country_code = country_code;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    @Override
    public String toString() {
        return "Country{" + "country_name=" + country_name + ", country_code=" + country_code + '}';
    }
    
}
