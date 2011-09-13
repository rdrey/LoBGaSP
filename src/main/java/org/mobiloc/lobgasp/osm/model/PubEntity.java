/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mobiloc.lobgasp.osm.model;

import javax.persistence.*;
import org.mobiloc.lobgasp.model.SpatialDBEntity;
import org.mobiloc.lobgasp.osm.parser.model.AbstractNode;
import org.mobiloc.lobgasp.osm.parser.model.OSMNode;

/**
 *
 * @author rainerdreyer
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PubEntity extends POIEntity {

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
    public SpatialDBEntity construct(AbstractNode in)
    {
        this.setName(in.tags.get("name"));
        this.setGeom(((OSMNode) in).getGeom());
        return this;
    }

    public Pub toSimple() {
        return new Pub(name, getGeom());
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
