/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.Enzyme;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MacBookPro
 */
@Stateless
public class EnzymeFacade extends AbstractFacade<Enzyme> implements EnzymeFacadeLocal {
    @PersistenceContext(unitName = "AutoPrimerX")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EnzymeFacade() {
        super(Enzyme.class);
    }
    
}
