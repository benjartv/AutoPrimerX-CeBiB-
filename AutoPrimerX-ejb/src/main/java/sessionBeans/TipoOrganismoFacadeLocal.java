/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.TipoOrganismo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Dany
 */
@Local
public interface TipoOrganismoFacadeLocal {

    void create(TipoOrganismo tipoOrganismo);

    void edit(TipoOrganismo tipoOrganismo);

    void remove(TipoOrganismo tipoOrganismo);

    TipoOrganismo find(Object id);

    List<TipoOrganismo> findAll();

    List<TipoOrganismo> findRange(int[] range);

    int count();
    
}
