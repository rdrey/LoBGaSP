/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mobiloc.lobgasp;

import com.vividsolutions.jts.geom.Point;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mobiloc.lobgasp.model.SpatialObject;
import org.mobiloc.lobgasp.osm.parser.OSMParser;
import org.mobiloc.lobgasp.osm.parser.model.OSM;

/**
 *
 * @author rainerdreyer
 */
public class SpatialProvider {

    List<SpatialObject> objects;

    void init()
    {
        //TODO find a nice spot to put map.osm if lobgasp is imported as package
        initFromFile("map.osm");
    }

    void initFromFile(String mapOsm)
    {
        OSM osm = null;

        try {
            osm = OSMParser.parse(mapOsm);
        } catch (Exception ex) {
            Logger.getLogger(SpatialProvider.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
    List<SpatialObject> provide(Point p, float radius)
    {
        
        return null;
    }

    

    void register(Class<? extends SpatialObject> source, Class<? extends SpatialObject> result)
    {
        System.out.println(source);
    }

}
