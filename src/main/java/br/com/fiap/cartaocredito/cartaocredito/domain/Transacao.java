package br.com.fiap.cartaocredito.cartaocredito.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
public class Transacao {
    @Id
    private Integer id;
    private ZonedDateTime dataHoraCriacao;
    private Long numeroCartao;
    private Long digitoCartao;
    private BigDecimal valor;
    private StatusTransacao status;
}
