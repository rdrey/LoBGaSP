/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mobiloc.lobgasp.osm.model;

import javax.persistence.*;
import org.mobiloc.lobgasp.osm.parser.model.AbstractNode;

/**
 *
 * @author rainerdreyer
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Pub extends POI {

    private String name;

    boolean xmlRule(AbstractNode in) {

        if (in.tags.containsKey("amenity")
                && in.tags.get("amenity").equalsIgnoreCase("pub")) {
            return true;
        }
        return false;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
