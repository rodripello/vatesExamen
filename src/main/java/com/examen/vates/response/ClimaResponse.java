package com.examen.vates.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ClimaResponse {

    @JsonIgnore
    @JsonProperty("fechaObservacion")
    private final String fechaObservacion;

    @JsonIgnore
    @JsonProperty("llueve")
    private final Boolean llueve;

    @JsonIgnore
    @JsonProperty("climaDescripcion")
    private final String climaDescripcion;

    @JsonIgnore
    @JsonProperty("temperatura")
    private final String temperatura;

    @JsonCreator
    public ClimaResponse(@JsonProperty("LocalObservationDateTime") String fechaObservacion,
                   @JsonProperty("HasPrecipitation") Boolean llueve,
                   @JsonProperty("WeatherText") String climaDescripcion,
                   @JsonProperty("Temperature") Map<String, Map<String, String>> temperatura) {
        this.fechaObservacion = fechaObservacion;
        this.llueve = llueve;
        this.climaDescripcion = climaDescripcion;
        this.temperatura = temperatura.get("Metric").get("Value");
    }

    public String getFechaObservacion() {
        return fechaObservacion;
    }

    public Boolean getLlueve() {
        return llueve;
    }

    public String getClimaDescripcion() {
        return climaDescripcion;
    }

    public String getTemperatura() {
        return temperatura;
    }
}
