package ar.com.mercadolibre.examen.models;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Accessors(chain = true)
public class Dia {
    @Id
    private String id;
    @Indexed(name = "idx_dia", background = true)
    private int dia;
    @Indexed(name = "idx_clima", background = true)
    private String clima;
}