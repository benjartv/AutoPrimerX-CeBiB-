/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.CodonEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Dany
 */
@Stateless
public class CodonEntityFacade extends AbstractFacade<CodonEntity> implements CodonEntityFacadeLocal {
    @PersistenceContext(unitName = "AutoPrimerX")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CodonEntityFacade() {
        super(CodonEntity.class);
    }
    
}
