package com.examen.vates.repository;


import com.examen.vates.model.Clima;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.util.AssertionErrors;


@DataJpaTest
public class climaRepositoryTests {

    @Autowired
    ClimaRepository climaRepository;

    @Test
    void testSaveClimaLocation(){
        //Recibimos una nueva peticion a guardar
        Clima clima = new Clima();
        clima.setRegion("Europa");
        clima.setPais("Francia");
        clima.setCiudad("Paris");
        clima.setFechaObservacion("29/03/2023");
        clima.setTemperatura("29.3");
        clima.setLlueve(true);
        clima.setClimaDescripcion("Cielo parcialmente nublado");

        //Almacenamos la informacion en la base de datos
        Clima climaSave =climaRepository.save(clima);

        //Evaluamos que no sea null
        Assertions.assertTrue(climaSave != null);

        //Evaluamos id>0
        Assertions.assertTrue(climaSave.getId() > 0);
    }
}
