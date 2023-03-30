package com.examen.vates.restTemplate;

import com.examen.vates.response.ClimaResponse;
import com.examen.vates.response.LocacionKeyResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class testRestTemplateClima {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private final String apiKey = "nbjT0H9BWv9piErpTC9imV1G5QswtVTI";
    private static final String CLIMA_URL = "http://dataservice.accuweather.com/currentconditions/v1/";


    @Test
    public void testGetHotelesDisponiblesSuccess() throws URISyntaxException
    {
        //Ingresamos la locationKey obtenidos en nuestras request
        String locationKey="10531";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json, application/*+json");
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromHttpUrl(CLIMA_URL + locationKey)
                .queryParam("apikey", apiKey);
        ResponseEntity<List<ClimaResponse>> respuesta =
                testRestTemplate.exchange(uriBuilder.build().encode().toUri(),
                        HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<List<ClimaResponse>>() {
                        });
        ClimaResponse clima = respuesta.getBody().get(0);
        assertNotNull(clima);
        assertEquals(HttpStatus.OK,respuesta.getStatusCode());
    }
}
