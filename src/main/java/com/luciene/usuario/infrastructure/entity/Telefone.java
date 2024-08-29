package com.luciene.usuario.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="telefone")
public class Telefone {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

   @Column(name="numero", length = 10)
    private String numero;

   @Column(name = "ddd", length = 3)
    private String ddd;
}
