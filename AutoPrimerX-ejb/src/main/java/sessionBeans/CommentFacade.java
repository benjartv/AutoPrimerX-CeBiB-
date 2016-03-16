/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.Comment;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MacBookPro
 */
@Stateless
public class CommentFacade extends AbstractFacade<Comment> implements CommentFacadeLocal {
    @PersistenceContext(unitName = "AutoPrimerX")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommentFacade() {
        super(Comment.class);
    }
    
}
