/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.CodonEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Dany
 */
@Local
public interface CodonEntityFacadeLocal {

    void create(CodonEntity codonEntity);

    void edit(CodonEntity codonEntity);

    void remove(CodonEntity codonEntity);

    CodonEntity find(Object id);

    List<CodonEntity> findAll();

    List<CodonEntity> findRange(int[] range);

    int count();
    
}
