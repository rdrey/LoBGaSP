/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mobiloc.lobgasp.osm.model;

import javax.persistence.*;
import org.mobiloc.lobgasp.model.SpatialObject;
import org.mobiloc.lobgasp.osm.parser.model.AbstractNode;
import org.mobiloc.lobgasp.osm.parser.model.OSMNode;

/**
 *
 * @author rainerdreyer
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Pub extends POI {

    private String name;

    @Override
    public boolean xmlRule(AbstractNode in) {

        if (in.tags.containsKey("amenity")
                && in.tags.get("amenity").equalsIgnoreCase("pub")) {
            return true;
        }
        return false;
    }

    @Override
    public SpatialObject construct(AbstractNode in)
    {
        this.setName(in.tags.get("name"));
        this.setGeom(((OSMNode) in).getGeom());
        return this;
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
