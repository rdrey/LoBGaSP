/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mobiloc.lobgasp;

import com.vividsolutions.jts.geom.Point;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mobiloc.lobgasp.model.SpatialObject;
import org.mobiloc.lobgasp.osm.model.Building;
import org.mobiloc.lobgasp.osm.model.Road;
import org.mobiloc.lobgasp.osm.model.WaySchema;
import org.mobiloc.lobgasp.osm.parser.OSMParser;
import org.mobiloc.lobgasp.osm.parser.model.OSM;
import org.mobiloc.lobgasp.osm.parser.model.OSMNode;
import org.mobiloc.lobgasp.osm.parser.model.Way;
import org.mobiloc.lobgasp.util.HibernateUtil;

/**
 *
 * @author rainerdreyer
 */
public class SpatialProvider {

    HashMap<SpatialObject, SpatialObject> objects;

    public SpatialProvider() {
        objects = new HashMap<SpatialObject, SpatialObject>();
    }

    void init() {
        //TODO find a nice spot to put map.osm if lobgasp is imported as package
        initFromFile("map.osm");
    }

    void initFromFile(String mapOsm) {
        OSM osm = null;

        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = s.beginTransaction();

        try {
            osm = OSMParser.parse(mapOsm);
        } catch (Exception ex) {
            Logger.getLogger(SpatialProvider.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Nodes first
        for (OSMNode node : osm.getNodes()) {
            for (SpatialObject so : objects.keySet()) {
                if (so.xmlRule(node)) {
                    System.out.println("Found " + so.getClass());
                    Serializable save = s.save(objects.get(so).construct(node));
                }
            }
        }

        //Now ways
        Building building = new Building();
        Road road = new Road();

        for (Way way : osm.getWays()) {
            if (building.xmlRule(way)) {
                Building tempBuilding = new Building();
                tempBuilding.construct(way);
                s.save(tempBuilding);
            } else if (road.xmlRule(way)) {
                Road tempRoad = new Road();
                tempRoad.construct(way);
                s.save(tempRoad);
            } else {
                WaySchema dbWay = new WaySchema();
                dbWay.construct(way);
                s.save(dbWay);
            }
        }

        //Then relations

        tx.commit();

    }

    List<SpatialObject> provide(Point p, float radius) {

        return null;
    }

    void register(Class<? extends SpatialObject> source, Class<? extends SpatialObject> result) {
        try {
            objects.put(source.newInstance(), result.newInstance());
        } catch (InstantiationException ex) {
            Logger.getLogger(SpatialProvider.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(SpatialProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(source);
    }
}
