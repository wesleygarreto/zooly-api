package br.com.truvainfo.zoolyapi.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "ZOOLY", name = "ANIMAL")
public class Animal implements Serializable {

    @Id
    @Column(name = "COD_ANIMAL")
    private Integer codigo;

    @Column(name = "NOME_POPULAR")
    private String nomePopular;

    @Column(name = "NOME_CIENTIFICO")
    private String nomeCientifico;

    @Column(name = "OBSERVACAO")
    private String observacao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DTHR_ENTRADA")
    private Date dataDeEntrada;

}
