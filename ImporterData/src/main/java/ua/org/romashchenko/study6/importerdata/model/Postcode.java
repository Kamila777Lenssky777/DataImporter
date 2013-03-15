/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.org.romashchenko.study6.importerdata.model;

/**
 *
 * @author Christina Romashchenko
 */
public class Postcode {

    private String postcode;

    public Postcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return "Postcode{" + "postcode=" + postcode + '}';
    }
    
}
