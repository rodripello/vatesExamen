package com.examen.vates.restTemplate;

import com.examen.vates.response.LocacionKeyResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class testRestTemplateLocacion {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private final String apiKey = "nbjT0H9BWv9piErpTC9imV1G5QswtVTI";
    private static final String LOCACION_URL = "http://dataservice.accuweather.com/locations/v1/";


    @Test
    public void testGetLocationApi() throws URISyntaxException
    {
        //Se ingresa el codigo pais y la ciudad a buscar la localizacion
        String codigoPais="AR";
        String ciudad="Salta";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json, application/*+json");
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromHttpUrl(LOCACION_URL + codigoPais + "/search")
                .queryParam("apikey", apiKey)
                .queryParam("q", ciudad);
        ResponseEntity<List<LocacionKeyResponse>> respuesta =
                testRestTemplate.exchange(uriBuilder.build().encode().toUri(),
                        HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<List<LocacionKeyResponse>>() {
                        });
        LocacionKeyResponse locations = respuesta.getBody().get(0);
        assertEquals("South America",locations.getRegion());
        assertEquals("Argentina",locations.getPais());
        assertEquals("Salta",locations.getCiudad());
        assertEquals(HttpStatus.OK,respuesta.getStatusCode());
    }
}
