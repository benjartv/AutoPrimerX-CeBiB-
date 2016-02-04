/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.CodonUsage;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Dany
 */
@Local
public interface CodonUsageFacadeLocal {

    void create(CodonUsage codonUsage);

    void edit(CodonUsage codonUsage);

    void remove(CodonUsage codonUsage);

    CodonUsage find(Object id);

    List<CodonUsage> findAll();

    List<CodonUsage> findRange(int[] range);

    int count();
    
}
