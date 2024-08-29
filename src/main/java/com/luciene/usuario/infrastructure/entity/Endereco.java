package com.luciene.usuario.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name="rua")
    private String rua;

    @Column(name="numero")
    private Long numero;

    @Column(name="complemento", length = 10)
    private String complemento;

    @Column(name="cidade", length = 150)
    private String cidade;

    @Column(name="estado", length = 2)
    private String Estado;

    @Column(name="cep",length = 9)
    private String cep;

}
