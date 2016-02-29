/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author MacBookPro
 */
@Entity
public class Enzyme implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_enzyme")
    private Long id;
    
    @NotNull(message = "Debe tener un nombre")
    private String nameEnzyme;
    
    @NotNull(message = "Debe tener un secuencia")
    private String dnaTarget;
    
    private String alt_dnaTarget;
    
    @NotNull(message = "Debe indicar la de bases extra")
    private int numBasePair;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameEnzyme() {
        return nameEnzyme;
    }

    public void setNameEnzyme(String nameEnzyme) {
        this.nameEnzyme = nameEnzyme;
    }

    public String getDnaTarget() {
        return dnaTarget;
    }

    public void setDnaTarget(String dnaTarget) {
        this.dnaTarget = dnaTarget;
    }

    public String getAlt_dnaTarget() {
        return alt_dnaTarget;
    }

    public void setAlt_dnaTarget(String alt_dnaTarget) {
        this.alt_dnaTarget = alt_dnaTarget;
    }

    public int getNumBasePair() {
        return numBasePair;
    }

    public void setNumBasePair(int numBasePair) {
        this.numBasePair = numBasePair;
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
        if (!(object instanceof Enzyme)) {
            return false;
        }
        Enzyme other = (Enzyme) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nameEnzyme;
    }
    
}
