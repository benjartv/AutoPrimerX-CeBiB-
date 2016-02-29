/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.Enzyme;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author MacBookPro
 */
@Local
public interface EnzymeFacadeLocal {

    void create(Enzyme enzyme);

    void edit(Enzyme enzyme);

    void remove(Enzyme enzyme);

    Enzyme find(Object id);

    List<Enzyme> findAll();

    List<Enzyme> findRange(int[] range);

    int count();
    
}
