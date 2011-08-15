package org.mobiloc.lobgasp;

import java.util.List;
import org.hibernate.Session;
import org.mobiloc.lobgasp.util.HibernateUtil;
import org.hibernate.Transaction;
import org.mobiloc.lobgasp.osm.model.Pub;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        /*
        Iterator l = s.createSQLQuery("SELECT ST_AsText(ST_Transform(way,94326)), name FROM planet_osm_point"
                + " WHERE amenity like 'pub' OR amenity like 'bar'"
                + " ORDER BY osm_id").list().iterator();

        while (l.hasNext()) {
            Object[] row = (Object[]) l.next();
            String loc = (String) row[0];
            String name = (String) row[1];

            System.out.println(name + ": " + loc);
        }
         *
         */

//        try {
//            OSM osm = OSMParser.parse("map.osm");
//            System.out.println(osm.getNodes().iterator().next().tags);
//            System.out.println(osm.getNodes().iterator().next().getLocation());
//
//        } catch (Exception ex) {
//            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
//        }

        SpatialProvider sp = new SpatialProvider();
        sp.register(Pub.class, Pub.class);
        sp.init();

        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = s.beginTransaction();

        List so = s.createQuery("from Pub").list();
        System.out.println(((Pub) so.get(0)).getName());

        tx.commit();
    }
}
