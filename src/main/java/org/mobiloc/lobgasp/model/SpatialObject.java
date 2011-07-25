/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mobiloc.lobgasp.model;

import com.vividsolutions.jts.geom.Geometry;
import java.io.Serializable;
import javax.persistence.*;
//import org.hibernate.annotations.Entity;

/**
 *
 * @author rainerdreyer
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class SpatialObject implements Serializable {
    Long id;

    @Id @GeneratedValue(strategy=GenerationType.TABLE)
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    Geometry geom;
    public double lat, lng;
    

}
