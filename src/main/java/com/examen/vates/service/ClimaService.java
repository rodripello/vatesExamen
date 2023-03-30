package com.examen.vates.service;

import com.examen.vates.model.Clima;
import com.examen.vates.repository.ClimaRepository;
import com.examen.vates.response.ClimaResponse;
import com.examen.vates.response.LocacionKeyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ClimaService {

    @Autowired
    private ClimaRepository climaRepository;

    private final String apiKey = "nbjT0H9BWv9piErpTC9imV1G5QswtVTI";
    private static final String LOCACION_URL = "http://dataservice.accuweather.com/locations/v1/";
    private static final String CLIMA_URL = "http://dataservice.accuweather.com/currentconditions/v1/";
    private final RestTemplate restTemplate;

    public LocacionKeyResponse getLocacionKey(String codigoPais, String ciudad) {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromHttpUrl(LOCACION_URL + codigoPais + "/search")
                .queryParam("apikey", apiKey)
                .queryParam("q", ciudad);
       ResponseEntity<List<LocacionKeyResponse>> responseEntity =
                restTemplate.exchange(uriBuilder.build().encode().toUri(),
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<LocacionKeyResponse>>() {
                        });
        List<LocacionKeyResponse> locations = responseEntity.getBody();

        return locations.stream().findFirst().get();
    }

    public Clima getClimaLocacion(String locationKey,String region,String pais, String ciudad)  {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(CLIMA_URL + locationKey)
                .queryParam("apikey", apiKey);
        ResponseEntity<List<ClimaResponse>> response = restTemplate.exchange(
                uriBuilder.build().encode().toUri(),
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<ClimaResponse>>() {
                });
        List<ClimaResponse> clima = response.getBody();
        Clima climaModel = new Clima();
        climaModel.setRegion(region);
        climaModel.setPais(pais);
        climaModel.setCiudad(ciudad);
        climaModel.setFechaObservacion(clima.stream().findFirst().get().getFechaObservacion());
        climaModel.setClimaDescripcion(clima.stream().findFirst().get().getClimaDescripcion());
        climaModel.setTemperatura(clima.stream().findFirst().get().getTemperatura());
        climaModel.setLlueve(clima.stream().findFirst().get().getLlueve());
        climaRepository.save(climaModel);
        return climaModel;
    }
}
