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
@Table(schema = "ZOOLY", name = "BIOMETRIA")
public class Biometria implements Serializable {

    @Id
    @Column(name = "COD_BIOMETRIA")
    private Integer codigo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_ANIMAL")
    private Animal animal;

    @Column(name = "PESO")
    private String peso;

    @Column(name = "COMPRIMENTO")
    private String comprimento;

    @Column(name = "ALTURA")
    private String altura;

    @Column(name = "OBSERVACAO")
    private String observacao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DTHR_BIOMETRIA")
    private Date dataDaBiometria;
    
}
