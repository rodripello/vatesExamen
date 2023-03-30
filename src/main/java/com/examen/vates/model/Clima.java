package com.examen.vates.model;


import javax.persistence.*;

@Entity
@Table(name = "clima")
public class Clima {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "region")
    private String region;
    @Column(name = "pais")
    private String pais;
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "fechaObservacion")
    private String fechaObservacion;
    @Column(name = "llueve")
    private Boolean llueve;
    @Column(name = "climaDescripcion")
    private String climaDescripcion;
    @Column(name = "temperatura")
    private String temperatura;

    public Clima() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getFechaObservacion() {
        return fechaObservacion;
    }

    public void setFechaObservacion(String fechaObservacion) {
        this.fechaObservacion = fechaObservacion;
    }

    public Boolean getLlueve() {
        return llueve;
    }

    public void setLlueve(Boolean llueve) {
        this.llueve = llueve;
    }

    public String getClimaDescripcion() {
        return climaDescripcion;
    }

    public void setClimaDescripcion(String climaDescripcion) {
        this.climaDescripcion = climaDescripcion;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }
}
