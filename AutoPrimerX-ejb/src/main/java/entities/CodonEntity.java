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
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Dany
 */
@Entity
public class CodonEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name = "id_codon")
    private Long id;
    
    @Size(max=3)
    @NotNull(message = "Debe tener un nombre")
    private String nombre_codon;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double probabilidad;
    
    @NotNull(message = "Debe pertenecer a un aminoacido")
    @JoinColumn(nullable = false)
    @ManyToOne
    private Aminoacido aminoacido;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre_codon() {
        return nombre_codon;
    }

    public void setNombre_codon(String nombre_codon) {
        this.nombre_codon = nombre_codon;
    }

    public Double getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(Double probabilidad) {
        this.probabilidad = probabilidad;
    }

    public Aminoacido getAminoacido() {
        return aminoacido;
    }

    public void setAminoacido(Aminoacido aminoacido) {
        this.aminoacido = aminoacido;
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
        if (!(object instanceof CodonEntity)) {
            return false;
        }
        CodonEntity other = (CodonEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CodonEntity[ id=" + id + " ]";
    }
    
}
