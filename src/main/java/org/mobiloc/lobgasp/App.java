package org.mobiloc.lobgasp;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.mobiloc.lobgasp.util.HibernateUtil;
import java.util.Iterator;
import org.hibernate.Transaction;
import org.mobiloc.lobgasp.model.SpatialObject;
import org.mobiloc.lobgasp.osm.model.Pub;
import org.mobiloc.lobgasp.osm.parser.OSMParser;
import org.mobiloc.lobgasp.osm.parser.model.OSM;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = s.beginTransaction();
        Iterator l = s.createSQLQuery("SELECT ST_AsText(ST_Transform(way,94326)), name FROM planet_osm_point"
                + " WHERE amenity like 'pub' OR amenity like 'bar'"
                + " ORDER BY osm_id").list().iterator();

        while (l.hasNext()) {
            Object[] row = (Object[]) l.next();
            String loc = (String) row[0];
            String name = (String) row[1];

            System.out.println(name + ": " + loc);
        }
        try {
            OSM osm = OSMParser.parse("src/test/map.osm");
            System.out.println(osm.getNodes().iterator().next().tags);
            System.out.println(osm.getNodes().iterator().next().getLocation());

        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

        SpatialProvider sp = new SpatialProvider();
        sp.register(SpatialObject.class, SpatialObject.class);

        SpatialObject o = new SpatialObject();
        o.lng = 18.5695866999999;
        o.lat = -33.9764125;
        Long id = (Long) s.save(o);

        Pub p = new Pub();
        p.lng = 18.5695866999999;
        p.lat = -33.9764125;
        p.setName("Mzoli's Place2");
        s.save(p);

        List so = s.createQuery("from Pub").list();
        System.out.println(((Pub) so.get(0)).getName());

        tx.commit();
    }
}
