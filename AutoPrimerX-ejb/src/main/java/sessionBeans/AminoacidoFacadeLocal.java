/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.Aminoacido;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Dany
 */
@Local
public interface AminoacidoFacadeLocal {

    void create(Aminoacido aminoacido);

    void edit(Aminoacido aminoacido);

    void remove(Aminoacido aminoacido);

    Aminoacido find(Object id);

    List<Aminoacido> findAll();

    List<Aminoacido> findRange(int[] range);

    int count();
    
}
