package com.examen.vates.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocacionKeyResponse {
    private final String locationKey;
    private final String region;
    private final String pais;
    private final String ciudad;


    @JsonCreator
    public LocacionKeyResponse(@JsonProperty("Key") String locationKey,
                               @JsonProperty("Region") Map<String, String> region,
                               @JsonProperty("Country") Map<String, String> pais,
                               @JsonProperty("AdministrativeArea") Map<String, String> ciudad) {
        this.locationKey = locationKey;
        this.region = region.get("LocalizedName");
        this.pais = pais.get("LocalizedName");
        this.ciudad = ciudad.get("LocalizedName");

    }

    public String getLocationKey() {
        return locationKey;
    }

    public String getRegion() {
        return region;
    }

    public String getPais() {
        return pais;
    }

    public String getCiudad() {
        return ciudad;
    }
}

