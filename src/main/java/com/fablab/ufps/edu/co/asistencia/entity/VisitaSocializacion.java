/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fablab.ufps.edu.co.asistencia.entity;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 *
 * @author jerson
 */
@Entity
@Table(name = "visita_socializacion")
@NamedQueries({
    @NamedQuery(name = "VisitaSocializacion.findAll", query = "SELECT v FROM VisitaSocializacion v"),
    @NamedQuery(name = "VisitaSocializacion.findById", query = "SELECT v FROM VisitaSocializacion v WHERE v.id = :id"),
    @NamedQuery(name = "VisitaSocializacion.findByFecha", query = "SELECT v FROM VisitaSocializacion v WHERE v.fecha = :fecha"),
    @NamedQuery(name = "VisitaSocializacion.findByOtroPrograma", query = "SELECT v FROM VisitaSocializacion v WHERE v.otroPrograma = :otroPrograma")})
public class VisitaSocializacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "otro_programa")
    private String otroPrograma;
    @JoinColumn(name = "id_persona", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Persona idPersona;
    @JoinColumn(name = "id_programa_academico", referencedColumnName = "id")
    @ManyToOne
    private ProgramaAcademico idProgramaAcademico;
    @JoinColumn(name = "id_universidad", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Universidad idUniversidad;

    public VisitaSocializacion() {
    }

    public VisitaSocializacion(Integer id) {
        this.id = id;
    }

    public VisitaSocializacion(Integer id, Date fecha) {
        this.id = id;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getOtroPrograma() {
        return otroPrograma;
    }

    public void setOtroPrograma(String otroPrograma) {
        this.otroPrograma = otroPrograma;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    public ProgramaAcademico getIdProgramaAcademico() {
        return idProgramaAcademico;
    }

    public void setIdProgramaAcademico(ProgramaAcademico idProgramaAcademico) {
        this.idProgramaAcademico = idProgramaAcademico;
    }

    public Universidad getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(Universidad idUniversidad) {
        this.idUniversidad = idUniversidad;
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
        if (!(object instanceof VisitaSocializacion)) {
            return false;
        }
        VisitaSocializacion other = (VisitaSocializacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fablab.ufps.edu.co.asistencia.entity.VisitaSocializacion[ id=" + id + " ]";
    }
    
}
