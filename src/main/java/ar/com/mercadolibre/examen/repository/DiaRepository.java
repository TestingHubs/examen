package ar.com.mercadolibre.examen.repository;

import ar.com.mercadolibre.examen.models.Dia;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface  DiaRepository extends MongoRepository<Dia, String> {
    List<Dia> findByClima(String clima);
    Optional<Dia> findByDia(int dia);
}
