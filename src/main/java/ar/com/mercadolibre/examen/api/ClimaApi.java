package ar.com.mercadolibre.examen.api;

import ar.com.mercadolibre.examen.models.Dia;
import ar.com.mercadolibre.examen.service.DiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.isNull;

@RestController
@RequestMapping(path = "/clima")
public class ClimaApi {

    @Autowired
    private DiaService diaService;

    @GetMapping
    public ResponseEntity<Map<String, String>> condicionClimatica(@RequestParam(value = "dia", required = false) int dia){
        Map<String, String> response = new HashMap<>();
        if(isNull(dia)){
            dia = LocalDate.now().getDayOfYear();
        }

        Optional<Dia> document = diaService.findByDia(dia);
        if(document.isPresent()){
            response.put("dia", String.valueOf(dia));
            response.put("clima", document.get().getClima());
        } else{
            response.put("error", "No se encontró el clima para el dìa " + dia);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(response);
    }
}
