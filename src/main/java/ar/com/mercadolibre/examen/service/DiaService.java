package ar.com.mercadolibre.examen.service;

import ar.com.mercadolibre.examen.models.Dia;
import ar.com.mercadolibre.examen.repository.DiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiaService {
    @Autowired
    private DiaRepository diaRepository;

    public Dia save(Dia dia) {
        Optional<Dia> document = findByDia(dia.getDia());
        if(document.isPresent()){
            document.get().setClima(dia.getClima());
            diaRepository.save(document.get());
            return document.get();
        } else{
            diaRepository.save(dia);
            return dia;
        }
    }

    @Cacheable("findByDia")
    public Optional<Dia> findByDia(int dia) {
        return diaRepository.findByDia(dia);
    }

    @Cacheable("findByClima")
    public List<Dia> findByClima(String clima) {
        return diaRepository.findByClima(clima);
    }
}
