/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mobiloc.lobgasp.osm.model;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import javax.persistence.*;
import org.mobiloc.lobgasp.model.SpatialObject;

/**
 *
 * @author rainerdreyer
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class POI extends SpatialObject{
    
    //double lat, lon;

//    @Override
//    public void setGeom(Geometry geom)
//    {
//        super.setGeom(geom);
//        lat = ((Point) geom).getY();
//        lon = ((Point) geom).getX();
//    }

    

}
