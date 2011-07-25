/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mobiloc.lobgasp.osm.model;

import javax.persistence.*;
import org.mobiloc.lobgasp.model.SpatialObject;

/**
 *
 * @author rainerdreyer
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class POI extends SpatialObject{

    

}
