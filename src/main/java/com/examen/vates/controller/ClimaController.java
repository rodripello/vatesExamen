package com.examen.vates.controller;


import com.examen.vates.response.ClimaResponse;
import com.examen.vates.response.LocacionKeyResponse;
import com.examen.vates.service.ClimaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class ClimaController {

    @Autowired
    ClimaService climaService;

    @GetMapping(value = "/clima",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getClima(@RequestParam("codigoPais") String codigoPais,
                                      @RequestParam("ciudad") String ciudad){
        LocacionKeyResponse locacionKey =climaService.getLocacionKey(codigoPais,ciudad);
        if (locacionKey == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(climaService.getClimaLocacion(locacionKey.getLocationKey(),
                locacionKey.getRegion(), locacionKey.getPais(),locacionKey.getCiudad()), HttpStatus.OK);
    }
}
