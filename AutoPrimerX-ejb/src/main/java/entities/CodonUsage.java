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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Dany
 */
@Entity
public class CodonUsage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name = "id_codonUsage")
    private Long id;

    @NotNull(message = "Debe tener un nombre")
    private String nombreOrganismo;
    
    /// CODONES
    // FILA  U - U 
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String UUU_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double UUU_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String UCU_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double UCU_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String UAU_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double UAU_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String UGU_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double UGU_probabilidad;
    
    //FILA U-C
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String UUC_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double UUC_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String UCC_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double UCC_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String UAC_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double UAC_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String UGC_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double UGC_probabilidad;
    
    // FILA U - A
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String UUA_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double UUA_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String UCA_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double UCA_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String UAA_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double UAA_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String UGA_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double UGA_probabilidad;
    
    // FILA U - G
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String UUG_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double UUG_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String UCG_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double UCG_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String UAG_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double UAG_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String UGG_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double UGG_probabilidad;
    
    
    // FILA C - U
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String CUU_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double CUU_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String CCU_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double CCU_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String CAU_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double CAU_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String CGU_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double CGU_probabilidad;
    
    // FILA C - C
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String CUC_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double CUC_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String CCC_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double CCC_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String CAC_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double CAC_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String CGC_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double CGC_probabilidad;
    
    // FILA C - A
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String CUA_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double CUA_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String CCA_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double CCA_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String CAA_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double CAA_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String CGA_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double CGA_probabilidad;
    
    // FILA C - G
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String CUG_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double CUG_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String CCG_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double CCG_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String CAG_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double CAG_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String CGG_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double CGG_probabilidad;
    
    
    // FILA A - U
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String AUU_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double AUU_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String ACU_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double ACU_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String AAU_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double AAU_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String AGU_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double AGU_probabilidad;
    
    // FILA A - C
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String AUC_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double AUC_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String ACC_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double ACC_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String AAC_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double AAC_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String AGC_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double AGC_probabilidad;
    
    // FILA A - A
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String AUA_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double AUA_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String ACA_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double ACA_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String AAA_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double AAA_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String AGA_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double AGA_probabilidad;
    
    // FILA A - G
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String AUG_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double AUG_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String ACG_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double ACG_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String AAG_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double AAG_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String AGG_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double AGG_probabilidad;
    
    // FILA G - U
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String GUU_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double GUU_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String GCU_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double GCU_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String GAU_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double GAU_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String GGU_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double GGU_probabilidad;
    
    // FILA G - C
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String GUC_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double GUC_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String GCC_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double GCC_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String GAC_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double GAC_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String GGC_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double GGC_probabilidad;
    
    // FILA G - A
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String GUA_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double GUA_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String GCA_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double GCA_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String GAA_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double GAA_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String GGA_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double GGA_probabilidad;
    
    // FILA G - G
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String GUG_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double GUG_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String GCG_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double GCG_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String GAG_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double GAG_probabilidad;
    
    @Size(max=1)
    @NotNull(message = "Debe tener un letra")
    private String GGG_letraAminoacido;
    @NotNull(message = "Debe tener un valor de probabilidad")
    private Double GGG_probabilidad;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreOrganismo() {
        return nombreOrganismo;
    }

    public void setNombreOrganismo(String nombreOrganismo) {
        this.nombreOrganismo = nombreOrganismo;
    }

    public String getUUU_letraAminoacido() {
        return UUU_letraAminoacido;
    }

    public void setUUU_letraAminoacido(String UUU_letraAminoacido) {
        this.UUU_letraAminoacido = UUU_letraAminoacido;
    }

    public Double getUUU_probabilidad() {
        return UUU_probabilidad;
    }

    public void setUUU_probabilidad(Double UUU_probabilidad) {
        this.UUU_probabilidad = UUU_probabilidad;
    }

    public String getUCU_letraAminoacido() {
        return UCU_letraAminoacido;
    }

    public void setUCU_letraAminoacido(String UCU_letraAminoacido) {
        this.UCU_letraAminoacido = UCU_letraAminoacido;
    }

    public Double getUCU_probabilidad() {
        return UCU_probabilidad;
    }

    public void setUCU_probabilidad(Double UCU_probabilidad) {
        this.UCU_probabilidad = UCU_probabilidad;
    }

    public String getUAU_letraAminoacido() {
        return UAU_letraAminoacido;
    }

    public void setUAU_letraAminoacido(String UAU_letraAminoacido) {
        this.UAU_letraAminoacido = UAU_letraAminoacido;
    }

    public Double getUAU_probabilidad() {
        return UAU_probabilidad;
    }

    public void setUAU_probabilidad(Double UAU_probabilidad) {
        this.UAU_probabilidad = UAU_probabilidad;
    }

    public String getUGU_letraAminoacido() {
        return UGU_letraAminoacido;
    }

    public void setUGU_letraAminoacido(String UGU_letraAminoacido) {
        this.UGU_letraAminoacido = UGU_letraAminoacido;
    }

    public Double getUGU_probabilidad() {
        return UGU_probabilidad;
    }

    public void setUGU_probabilidad(Double UGU_probabilidad) {
        this.UGU_probabilidad = UGU_probabilidad;
    }

    public String getUUC_letraAminoacido() {
        return UUC_letraAminoacido;
    }

    public void setUUC_letraAminoacido(String UUC_letraAminoacido) {
        this.UUC_letraAminoacido = UUC_letraAminoacido;
    }

    public Double getUUC_probabilidad() {
        return UUC_probabilidad;
    }

    public void setUUC_probabilidad(Double UUC_probabilidad) {
        this.UUC_probabilidad = UUC_probabilidad;
    }

    public String getUCC_letraAminoacido() {
        return UCC_letraAminoacido;
    }

    public void setUCC_letraAminoacido(String UCC_letraAminoacido) {
        this.UCC_letraAminoacido = UCC_letraAminoacido;
    }

    public Double getUCC_probabilidad() {
        return UCC_probabilidad;
    }

    public void setUCC_probabilidad(Double UCC_probabilidad) {
        this.UCC_probabilidad = UCC_probabilidad;
    }

    public String getUAC_letraAminoacido() {
        return UAC_letraAminoacido;
    }

    public void setUAC_letraAminoacido(String UAC_letraAminoacido) {
        this.UAC_letraAminoacido = UAC_letraAminoacido;
    }

    public Double getUAC_probabilidad() {
        return UAC_probabilidad;
    }

    public void setUAC_probabilidad(Double UAC_probabilidad) {
        this.UAC_probabilidad = UAC_probabilidad;
    }

    public String getUGC_letraAminoacido() {
        return UGC_letraAminoacido;
    }

    public void setUGC_letraAminoacido(String UGC_letraAminoacido) {
        this.UGC_letraAminoacido = UGC_letraAminoacido;
    }

    public Double getUGC_probabilidad() {
        return UGC_probabilidad;
    }

    public void setUGC_probabilidad(Double UGC_probabilidad) {
        this.UGC_probabilidad = UGC_probabilidad;
    }

    public String getUUA_letraAminoacido() {
        return UUA_letraAminoacido;
    }

    public void setUUA_letraAminoacido(String UUA_letraAminoacido) {
        this.UUA_letraAminoacido = UUA_letraAminoacido;
    }

    public Double getUUA_probabilidad() {
        return UUA_probabilidad;
    }

    public void setUUA_probabilidad(Double UUA_probabilidad) {
        this.UUA_probabilidad = UUA_probabilidad;
    }

    public String getUCA_letraAminoacido() {
        return UCA_letraAminoacido;
    }

    public void setUCA_letraAminoacido(String UCA_letraAminoacido) {
        this.UCA_letraAminoacido = UCA_letraAminoacido;
    }

    public Double getUCA_probabilidad() {
        return UCA_probabilidad;
    }

    public void setUCA_probabilidad(Double UCA_probabilidad) {
        this.UCA_probabilidad = UCA_probabilidad;
    }

    public String getUAA_letraAminoacido() {
        return UAA_letraAminoacido;
    }

    public void setUAA_letraAminoacido(String UAA_letraAminoacido) {
        this.UAA_letraAminoacido = UAA_letraAminoacido;
    }

    public Double getUAA_probabilidad() {
        return UAA_probabilidad;
    }

    public void setUAA_probabilidad(Double UAA_probabilidad) {
        this.UAA_probabilidad = UAA_probabilidad;
    }

    public String getUGA_letraAminoacido() {
        return UGA_letraAminoacido;
    }

    public void setUGA_letraAminoacido(String UGA_letraAminoacido) {
        this.UGA_letraAminoacido = UGA_letraAminoacido;
    }

    public Double getUGA_probabilidad() {
        return UGA_probabilidad;
    }

    public void setUGA_probabilidad(Double UGA_probabilidad) {
        this.UGA_probabilidad = UGA_probabilidad;
    }

    public String getUUG_letraAminoacido() {
        return UUG_letraAminoacido;
    }

    public void setUUG_letraAminoacido(String UUG_letraAminoacido) {
        this.UUG_letraAminoacido = UUG_letraAminoacido;
    }

    public Double getUUG_probabilidad() {
        return UUG_probabilidad;
    }

    public void setUUG_probabilidad(Double UUG_probabilidad) {
        this.UUG_probabilidad = UUG_probabilidad;
    }

    public String getUCG_letraAminoacido() {
        return UCG_letraAminoacido;
    }

    public void setUCG_letraAminoacido(String UCG_letraAminoacido) {
        this.UCG_letraAminoacido = UCG_letraAminoacido;
    }

    public Double getUCG_probabilidad() {
        return UCG_probabilidad;
    }

    public void setUCG_probabilidad(Double UCG_probabilidad) {
        this.UCG_probabilidad = UCG_probabilidad;
    }

    public String getUAG_letraAminoacido() {
        return UAG_letraAminoacido;
    }

    public void setUAG_letraAminoacido(String UAG_letraAminoacido) {
        this.UAG_letraAminoacido = UAG_letraAminoacido;
    }

    public Double getUAG_probabilidad() {
        return UAG_probabilidad;
    }

    public void setUAG_probabilidad(Double UAG_probabilidad) {
        this.UAG_probabilidad = UAG_probabilidad;
    }

    public String getUGG_letraAminoacido() {
        return UGG_letraAminoacido;
    }

    public void setUGG_letraAminoacido(String UGG_letraAminoacido) {
        this.UGG_letraAminoacido = UGG_letraAminoacido;
    }

    public Double getUGG_probabilidad() {
        return UGG_probabilidad;
    }

    public void setUGG_probabilidad(Double UGG_probabilidad) {
        this.UGG_probabilidad = UGG_probabilidad;
    }

    public String getCUU_letraAminoacido() {
        return CUU_letraAminoacido;
    }

    public void setCUU_letraAminoacido(String CUU_letraAminoacido) {
        this.CUU_letraAminoacido = CUU_letraAminoacido;
    }

    public Double getCUU_probabilidad() {
        return CUU_probabilidad;
    }

    public void setCUU_probabilidad(Double CUU_probabilidad) {
        this.CUU_probabilidad = CUU_probabilidad;
    }

    public String getCCU_letraAminoacido() {
        return CCU_letraAminoacido;
    }

    public void setCCU_letraAminoacido(String CCU_letraAminoacido) {
        this.CCU_letraAminoacido = CCU_letraAminoacido;
    }

    public Double getCCU_probabilidad() {
        return CCU_probabilidad;
    }

    public void setCCU_probabilidad(Double CCU_probabilidad) {
        this.CCU_probabilidad = CCU_probabilidad;
    }

    public String getCAU_letraAminoacido() {
        return CAU_letraAminoacido;
    }

    public void setCAU_letraAminoacido(String CAU_letraAminoacido) {
        this.CAU_letraAminoacido = CAU_letraAminoacido;
    }

    public Double getCAU_probabilidad() {
        return CAU_probabilidad;
    }

    public void setCAU_probabilidad(Double CAU_probabilidad) {
        this.CAU_probabilidad = CAU_probabilidad;
    }

    public String getCGU_letraAminoacido() {
        return CGU_letraAminoacido;
    }

    public void setCGU_letraAminoacido(String CGU_letraAminoacido) {
        this.CGU_letraAminoacido = CGU_letraAminoacido;
    }

    public Double getCGU_probabilidad() {
        return CGU_probabilidad;
    }

    public void setCGU_probabilidad(Double CGU_probabilidad) {
        this.CGU_probabilidad = CGU_probabilidad;
    }

    public String getCUC_letraAminoacido() {
        return CUC_letraAminoacido;
    }

    public void setCUC_letraAminoacido(String CUC_letraAminoacido) {
        this.CUC_letraAminoacido = CUC_letraAminoacido;
    }

    public Double getCUC_probabilidad() {
        return CUC_probabilidad;
    }

    public void setCUC_probabilidad(Double CUC_probabilidad) {
        this.CUC_probabilidad = CUC_probabilidad;
    }

    public String getCCC_letraAminoacido() {
        return CCC_letraAminoacido;
    }

    public void setCCC_letraAminoacido(String CCC_letraAminoacido) {
        this.CCC_letraAminoacido = CCC_letraAminoacido;
    }

    public Double getCCC_probabilidad() {
        return CCC_probabilidad;
    }

    public void setCCC_probabilidad(Double CCC_probabilidad) {
        this.CCC_probabilidad = CCC_probabilidad;
    }

    public String getCAC_letraAminoacido() {
        return CAC_letraAminoacido;
    }

    public void setCAC_letraAminoacido(String CAC_letraAminoacido) {
        this.CAC_letraAminoacido = CAC_letraAminoacido;
    }

    public Double getCAC_probabilidad() {
        return CAC_probabilidad;
    }

    public void setCAC_probabilidad(Double CAC_probabilidad) {
        this.CAC_probabilidad = CAC_probabilidad;
    }

    public String getCGC_letraAminoacido() {
        return CGC_letraAminoacido;
    }

    public void setCGC_letraAminoacido(String CGC_letraAminoacido) {
        this.CGC_letraAminoacido = CGC_letraAminoacido;
    }

    public Double getCGC_probabilidad() {
        return CGC_probabilidad;
    }

    public void setCGC_probabilidad(Double CGC_probabilidad) {
        this.CGC_probabilidad = CGC_probabilidad;
    }

    public String getCUA_letraAminoacido() {
        return CUA_letraAminoacido;
    }

    public void setCUA_letraAminoacido(String CUA_letraAminoacido) {
        this.CUA_letraAminoacido = CUA_letraAminoacido;
    }

    public Double getCUA_probabilidad() {
        return CUA_probabilidad;
    }

    public void setCUA_probabilidad(Double CUA_probabilidad) {
        this.CUA_probabilidad = CUA_probabilidad;
    }

    public String getCCA_letraAminoacido() {
        return CCA_letraAminoacido;
    }

    public void setCCA_letraAminoacido(String CCA_letraAminoacido) {
        this.CCA_letraAminoacido = CCA_letraAminoacido;
    }

    public Double getCCA_probabilidad() {
        return CCA_probabilidad;
    }

    public void setCCA_probabilidad(Double CCA_probabilidad) {
        this.CCA_probabilidad = CCA_probabilidad;
    }

    public String getCAA_letraAminoacido() {
        return CAA_letraAminoacido;
    }

    public void setCAA_letraAminoacido(String CAA_letraAminoacido) {
        this.CAA_letraAminoacido = CAA_letraAminoacido;
    }

    public Double getCAA_probabilidad() {
        return CAA_probabilidad;
    }

    public void setCAA_probabilidad(Double CAA_probabilidad) {
        this.CAA_probabilidad = CAA_probabilidad;
    }

    public String getCGA_letraAminoacido() {
        return CGA_letraAminoacido;
    }

    public void setCGA_letraAminoacido(String CGA_letraAminoacido) {
        this.CGA_letraAminoacido = CGA_letraAminoacido;
    }

    public Double getCGA_probabilidad() {
        return CGA_probabilidad;
    }

    public void setCGA_probabilidad(Double CGA_probabilidad) {
        this.CGA_probabilidad = CGA_probabilidad;
    }

    public String getCUG_letraAminoacido() {
        return CUG_letraAminoacido;
    }

    public void setCUG_letraAminoacido(String CUG_letraAminoacido) {
        this.CUG_letraAminoacido = CUG_letraAminoacido;
    }

    public Double getCUG_probabilidad() {
        return CUG_probabilidad;
    }

    public void setCUG_probabilidad(Double CUG_probabilidad) {
        this.CUG_probabilidad = CUG_probabilidad;
    }

    public String getCCG_letraAminoacido() {
        return CCG_letraAminoacido;
    }

    public void setCCG_letraAminoacido(String CCG_letraAminoacido) {
        this.CCG_letraAminoacido = CCG_letraAminoacido;
    }

    public Double getCCG_probabilidad() {
        return CCG_probabilidad;
    }

    public void setCCG_probabilidad(Double CCG_probabilidad) {
        this.CCG_probabilidad = CCG_probabilidad;
    }

    public String getCAG_letraAminoacido() {
        return CAG_letraAminoacido;
    }

    public void setCAG_letraAminoacido(String CAG_letraAminoacido) {
        this.CAG_letraAminoacido = CAG_letraAminoacido;
    }

    public Double getCAG_probabilidad() {
        return CAG_probabilidad;
    }

    public void setCAG_probabilidad(Double CAG_probabilidad) {
        this.CAG_probabilidad = CAG_probabilidad;
    }

    public String getCGG_letraAminoacido() {
        return CGG_letraAminoacido;
    }

    public void setCGG_letraAminoacido(String CGG_letraAminoacido) {
        this.CGG_letraAminoacido = CGG_letraAminoacido;
    }

    public Double getCGG_probabilidad() {
        return CGG_probabilidad;
    }

    public void setCGG_probabilidad(Double CGG_probabilidad) {
        this.CGG_probabilidad = CGG_probabilidad;
    }

    public String getAUU_letraAminoacido() {
        return AUU_letraAminoacido;
    }

    public void setAUU_letraAminoacido(String AUU_letraAminoacido) {
        this.AUU_letraAminoacido = AUU_letraAminoacido;
    }

    public Double getAUU_probabilidad() {
        return AUU_probabilidad;
    }

    public void setAUU_probabilidad(Double AUU_probabilidad) {
        this.AUU_probabilidad = AUU_probabilidad;
    }

    public String getACU_letraAminoacido() {
        return ACU_letraAminoacido;
    }

    public void setACU_letraAminoacido(String ACU_letraAminoacido) {
        this.ACU_letraAminoacido = ACU_letraAminoacido;
    }

    public Double getACU_probabilidad() {
        return ACU_probabilidad;
    }

    public void setACU_probabilidad(Double ACU_probabilidad) {
        this.ACU_probabilidad = ACU_probabilidad;
    }

    public String getAAU_letraAminoacido() {
        return AAU_letraAminoacido;
    }

    public void setAAU_letraAminoacido(String AAU_letraAminoacido) {
        this.AAU_letraAminoacido = AAU_letraAminoacido;
    }

    public Double getAAU_probabilidad() {
        return AAU_probabilidad;
    }

    public void setAAU_probabilidad(Double AAU_probabilidad) {
        this.AAU_probabilidad = AAU_probabilidad;
    }

    public String getAGU_letraAminoacido() {
        return AGU_letraAminoacido;
    }

    public void setAGU_letraAminoacido(String AGU_letraAminoacido) {
        this.AGU_letraAminoacido = AGU_letraAminoacido;
    }

    public Double getAGU_probabilidad() {
        return AGU_probabilidad;
    }

    public void setAGU_probabilidad(Double AGU_probabilidad) {
        this.AGU_probabilidad = AGU_probabilidad;
    }

    public String getAUC_letraAminoacido() {
        return AUC_letraAminoacido;
    }

    public void setAUC_letraAminoacido(String AUC_letraAminoacido) {
        this.AUC_letraAminoacido = AUC_letraAminoacido;
    }

    public Double getAUC_probabilidad() {
        return AUC_probabilidad;
    }

    public void setAUC_probabilidad(Double AUC_probabilidad) {
        this.AUC_probabilidad = AUC_probabilidad;
    }

    public String getACC_letraAminoacido() {
        return ACC_letraAminoacido;
    }

    public void setACC_letraAminoacido(String ACC_letraAminoacido) {
        this.ACC_letraAminoacido = ACC_letraAminoacido;
    }

    public Double getACC_probabilidad() {
        return ACC_probabilidad;
    }

    public void setACC_probabilidad(Double ACC_probabilidad) {
        this.ACC_probabilidad = ACC_probabilidad;
    }

    public String getAAC_letraAminoacido() {
        return AAC_letraAminoacido;
    }

    public void setAAC_letraAminoacido(String AAC_letraAminoacido) {
        this.AAC_letraAminoacido = AAC_letraAminoacido;
    }

    public Double getAAC_probabilidad() {
        return AAC_probabilidad;
    }

    public void setAAC_probabilidad(Double AAC_probabilidad) {
        this.AAC_probabilidad = AAC_probabilidad;
    }

    public String getAGC_letraAminoacido() {
        return AGC_letraAminoacido;
    }

    public void setAGC_letraAminoacido(String AGC_letraAminoacido) {
        this.AGC_letraAminoacido = AGC_letraAminoacido;
    }

    public Double getAGC_probabilidad() {
        return AGC_probabilidad;
    }

    public void setAGC_probabilidad(Double AGC_probabilidad) {
        this.AGC_probabilidad = AGC_probabilidad;
    }

    public String getAUA_letraAminoacido() {
        return AUA_letraAminoacido;
    }

    public void setAUA_letraAminoacido(String AUA_letraAminoacido) {
        this.AUA_letraAminoacido = AUA_letraAminoacido;
    }

    public Double getAUA_probabilidad() {
        return AUA_probabilidad;
    }

    public void setAUA_probabilidad(Double AUA_probabilidad) {
        this.AUA_probabilidad = AUA_probabilidad;
    }

    public String getACA_letraAminoacido() {
        return ACA_letraAminoacido;
    }

    public void setACA_letraAminoacido(String ACA_letraAminoacido) {
        this.ACA_letraAminoacido = ACA_letraAminoacido;
    }

    public Double getACA_probabilidad() {
        return ACA_probabilidad;
    }

    public void setACA_probabilidad(Double ACA_probabilidad) {
        this.ACA_probabilidad = ACA_probabilidad;
    }

    public String getAAA_letraAminoacido() {
        return AAA_letraAminoacido;
    }

    public void setAAA_letraAminoacido(String AAA_letraAminoacido) {
        this.AAA_letraAminoacido = AAA_letraAminoacido;
    }

    public Double getAAA_probabilidad() {
        return AAA_probabilidad;
    }

    public void setAAA_probabilidad(Double AAA_probabilidad) {
        this.AAA_probabilidad = AAA_probabilidad;
    }

    public String getAGA_letraAminoacido() {
        return AGA_letraAminoacido;
    }

    public void setAGA_letraAminoacido(String AGA_letraAminoacido) {
        this.AGA_letraAminoacido = AGA_letraAminoacido;
    }

    public Double getAGA_probabilidad() {
        return AGA_probabilidad;
    }

    public void setAGA_probabilidad(Double AGA_probabilidad) {
        this.AGA_probabilidad = AGA_probabilidad;
    }

    public String getAUG_letraAminoacido() {
        return AUG_letraAminoacido;
    }

    public void setAUG_letraAminoacido(String AUG_letraAminoacido) {
        this.AUG_letraAminoacido = AUG_letraAminoacido;
    }

    public Double getAUG_probabilidad() {
        return AUG_probabilidad;
    }

    public void setAUG_probabilidad(Double AUG_probabilidad) {
        this.AUG_probabilidad = AUG_probabilidad;
    }

    public String getACG_letraAminoacido() {
        return ACG_letraAminoacido;
    }

    public void setACG_letraAminoacido(String ACG_letraAminoacido) {
        this.ACG_letraAminoacido = ACG_letraAminoacido;
    }

    public Double getACG_probabilidad() {
        return ACG_probabilidad;
    }

    public void setACG_probabilidad(Double ACG_probabilidad) {
        this.ACG_probabilidad = ACG_probabilidad;
    }

    public String getAAG_letraAminoacido() {
        return AAG_letraAminoacido;
    }

    public void setAAG_letraAminoacido(String AAG_letraAminoacido) {
        this.AAG_letraAminoacido = AAG_letraAminoacido;
    }

    public Double getAAG_probabilidad() {
        return AAG_probabilidad;
    }

    public void setAAG_probabilidad(Double AAG_probabilidad) {
        this.AAG_probabilidad = AAG_probabilidad;
    }

    public String getAGG_letraAminoacido() {
        return AGG_letraAminoacido;
    }

    public void setAGG_letraAminoacido(String AGG_letraAminoacido) {
        this.AGG_letraAminoacido = AGG_letraAminoacido;
    }

    public Double getAGG_probabilidad() {
        return AGG_probabilidad;
    }

    public void setAGG_probabilidad(Double AGG_probabilidad) {
        this.AGG_probabilidad = AGG_probabilidad;
    }

    public String getGUU_letraAminoacido() {
        return GUU_letraAminoacido;
    }

    public void setGUU_letraAminoacido(String GUU_letraAminoacido) {
        this.GUU_letraAminoacido = GUU_letraAminoacido;
    }

    public Double getGUU_probabilidad() {
        return GUU_probabilidad;
    }

    public void setGUU_probabilidad(Double GUU_probabilidad) {
        this.GUU_probabilidad = GUU_probabilidad;
    }

    public String getGCU_letraAminoacido() {
        return GCU_letraAminoacido;
    }

    public void setGCU_letraAminoacido(String GCU_letraAminoacido) {
        this.GCU_letraAminoacido = GCU_letraAminoacido;
    }

    public Double getGCU_probabilidad() {
        return GCU_probabilidad;
    }

    public void setGCU_probabilidad(Double GCU_probabilidad) {
        this.GCU_probabilidad = GCU_probabilidad;
    }

    public String getGAU_letraAminoacido() {
        return GAU_letraAminoacido;
    }

    public void setGAU_letraAminoacido(String GAU_letraAminoacido) {
        this.GAU_letraAminoacido = GAU_letraAminoacido;
    }

    public Double getGAU_probabilidad() {
        return GAU_probabilidad;
    }

    public void setGAU_probabilidad(Double GAU_probabilidad) {
        this.GAU_probabilidad = GAU_probabilidad;
    }

    public String getGGU_letraAminoacido() {
        return GGU_letraAminoacido;
    }

    public void setGGU_letraAminoacido(String GGU_letraAminoacido) {
        this.GGU_letraAminoacido = GGU_letraAminoacido;
    }

    public Double getGGU_probabilidad() {
        return GGU_probabilidad;
    }

    public void setGGU_probabilidad(Double GGU_probabilidad) {
        this.GGU_probabilidad = GGU_probabilidad;
    }

    public String getGUC_letraAminoacido() {
        return GUC_letraAminoacido;
    }

    public void setGUC_letraAminoacido(String GUC_letraAminoacido) {
        this.GUC_letraAminoacido = GUC_letraAminoacido;
    }

    public Double getGUC_probabilidad() {
        return GUC_probabilidad;
    }

    public void setGUC_probabilidad(Double GUC_probabilidad) {
        this.GUC_probabilidad = GUC_probabilidad;
    }

    public String getGCC_letraAminoacido() {
        return GCC_letraAminoacido;
    }

    public void setGCC_letraAminoacido(String GCC_letraAminoacido) {
        this.GCC_letraAminoacido = GCC_letraAminoacido;
    }

    public Double getGCC_probabilidad() {
        return GCC_probabilidad;
    }

    public void setGCC_probabilidad(Double GCC_probabilidad) {
        this.GCC_probabilidad = GCC_probabilidad;
    }

    public String getGAC_letraAminoacido() {
        return GAC_letraAminoacido;
    }

    public void setGAC_letraAminoacido(String GAC_letraAminoacido) {
        this.GAC_letraAminoacido = GAC_letraAminoacido;
    }

    public Double getGAC_probabilidad() {
        return GAC_probabilidad;
    }

    public void setGAC_probabilidad(Double GAC_probabilidad) {
        this.GAC_probabilidad = GAC_probabilidad;
    }

    public String getGGC_letraAminoacido() {
        return GGC_letraAminoacido;
    }

    public void setGGC_letraAminoacido(String GGC_letraAminoacido) {
        this.GGC_letraAminoacido = GGC_letraAminoacido;
    }

    public Double getGGC_probabilidad() {
        return GGC_probabilidad;
    }

    public void setGGC_probabilidad(Double GGC_probabilidad) {
        this.GGC_probabilidad = GGC_probabilidad;
    }

    public String getGUA_letraAminoacido() {
        return GUA_letraAminoacido;
    }

    public void setGUA_letraAminoacido(String GUA_letraAminoacido) {
        this.GUA_letraAminoacido = GUA_letraAminoacido;
    }

    public Double getGUA_probabilidad() {
        return GUA_probabilidad;
    }

    public void setGUA_probabilidad(Double GUA_probabilidad) {
        this.GUA_probabilidad = GUA_probabilidad;
    }

    public String getGCA_letraAminoacido() {
        return GCA_letraAminoacido;
    }

    public void setGCA_letraAminoacido(String GCA_letraAminoacido) {
        this.GCA_letraAminoacido = GCA_letraAminoacido;
    }

    public Double getGCA_probabilidad() {
        return GCA_probabilidad;
    }

    public void setGCA_probabilidad(Double GCA_probabilidad) {
        this.GCA_probabilidad = GCA_probabilidad;
    }

    public String getGAA_letraAminoacido() {
        return GAA_letraAminoacido;
    }

    public void setGAA_letraAminoacido(String GAA_letraAminoacido) {
        this.GAA_letraAminoacido = GAA_letraAminoacido;
    }

    public Double getGAA_probabilidad() {
        return GAA_probabilidad;
    }

    public void setGAA_probabilidad(Double GAA_probabilidad) {
        this.GAA_probabilidad = GAA_probabilidad;
    }

    public String getGGA_letraAminoacido() {
        return GGA_letraAminoacido;
    }

    public void setGGA_letraAminoacido(String GGA_letraAminoacido) {
        this.GGA_letraAminoacido = GGA_letraAminoacido;
    }

    public Double getGGA_probabilidad() {
        return GGA_probabilidad;
    }

    public void setGGA_probabilidad(Double GGA_probabilidad) {
        this.GGA_probabilidad = GGA_probabilidad;
    }

    public String getGUG_letraAminoacido() {
        return GUG_letraAminoacido;
    }

    public void setGUG_letraAminoacido(String GUG_letraAminoacido) {
        this.GUG_letraAminoacido = GUG_letraAminoacido;
    }

    public Double getGUG_probabilidad() {
        return GUG_probabilidad;
    }

    public void setGUG_probabilidad(Double GUG_probabilidad) {
        this.GUG_probabilidad = GUG_probabilidad;
    }

    public String getGCG_letraAminoacido() {
        return GCG_letraAminoacido;
    }

    public void setGCG_letraAminoacido(String GCG_letraAminoacido) {
        this.GCG_letraAminoacido = GCG_letraAminoacido;
    }

    public Double getGCG_probabilidad() {
        return GCG_probabilidad;
    }

    public void setGCG_probabilidad(Double GCG_probabilidad) {
        this.GCG_probabilidad = GCG_probabilidad;
    }

    public String getGAG_letraAminoacido() {
        return GAG_letraAminoacido;
    }

    public void setGAG_letraAminoacido(String GAG_letraAminoacido) {
        this.GAG_letraAminoacido = GAG_letraAminoacido;
    }

    public Double getGAG_probabilidad() {
        return GAG_probabilidad;
    }

    public void setGAG_probabilidad(Double GAG_probabilidad) {
        this.GAG_probabilidad = GAG_probabilidad;
    }

    public String getGGG_letraAminoacido() {
        return GGG_letraAminoacido;
    }

    public void setGGG_letraAminoacido(String GGG_letraAminoacido) {
        this.GGG_letraAminoacido = GGG_letraAminoacido;
    }

    public Double getGGG_probabilidad() {
        return GGG_probabilidad;
    }

    public void setGGG_probabilidad(Double GGG_probabilidad) {
        this.GGG_probabilidad = GGG_probabilidad;
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
        if (!(object instanceof CodonUsage)) {
            return false;
        }
        CodonUsage other = (CodonUsage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreOrganismo;
    }
    
}
