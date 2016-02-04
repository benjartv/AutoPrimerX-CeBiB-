/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Dany
 */
@Entity
public class Aminoacido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name = "id_aminoacido")
    private Long id;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String letraAminoacido;
    
    @NotNull(message = "Debe pertenecer a un aminoacido")
    @JoinColumn(nullable = false)
    @ManyToOne
    private TipoOrganismo tipoOrganismo;
    
    @OneToMany(cascade = ALL,mappedBy ="aminoacido")
    private List<CodonEntity> codonEntity;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLetraAminoacido() {
        return letraAminoacido;
    }

    public void setLetraAminoacido(String letraAminoacido) {
        this.letraAminoacido = letraAminoacido;
    }

    public TipoOrganismo getTipoOrganismo() {
        return tipoOrganismo;
    }

    public void setTipoOrganismo(TipoOrganismo tipoOrganismo) {
        this.tipoOrganismo = tipoOrganismo;
    }

    public List<CodonEntity> getCodonEntity() {
        return codonEntity;
    }

    public void setCodonEntity(List<CodonEntity> codonEntity) {
        this.codonEntity = codonEntity;
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
        if (!(object instanceof Aminoacido)) {
            return false;
        }
        Aminoacido other = (Aminoacido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Aminoacido[ id=" + id + " ]";
    }
    
}
