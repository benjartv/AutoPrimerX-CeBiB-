/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author MacBookPro
 */
@Entity
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotNull(message = "You must write your name")
    private String Nombre;
    @Size(max=500)
    @NotNull(message = "The message must have subject ")
    private String Asunto;
    @Size(max=1500)
    @NotNull(message = "The message must have comments ")
    private String Comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getAsunto() {
        return Asunto;
    }

    public void setAsunto(String Asunto) {
        this.Asunto = Asunto;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Comment[ id=" + id + " ]";
    }
    
}
