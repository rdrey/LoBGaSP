package org.mobiloc.lobgasp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.mobiloc.lobgasp.util.HibernateUtil;
import org.hibernate.Transaction;
import org.hibernatespatial.criterion.SpatialRestrictions;
import org.mobiloc.lobgasp.osm.model.Building;
import org.mobiloc.lobgasp.osm.model.Pub;
import org.mobiloc.lobgasp.osm.model.Road;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        SpatialProvider sp = new SpatialProvider();
        //TODO interface for custom objects
        sp.register(Pub.class, Pub.class);
        sp.initFromFile("campus.osm");

        //

        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = s.beginTransaction();

        List so = s.createQuery("from Pub").list();
        System.out.println(((Pub) so.get(0)).getName());

        serializeResults(Pub.class, "pub.out", s);
        serializeResults(Road.class, "roads.out", s);
        serializeResults(Building.class, "buildings.out", s);

        tx.commit();
    }

    private static void serializeResults(Class object, String toFile, Session s) {
        FileOutputStream fos = null;
        Criteria query = s.createCriteria(object);
        //This is how to later filter by geometry:
        //        query.add(SpatialRestrictions.within("geom", filter));
        List results = query.list();

        try {
            fos = new FileOutputStream(toFile);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(results);
            out.close();
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
