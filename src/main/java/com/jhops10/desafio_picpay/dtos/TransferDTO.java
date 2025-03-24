package com.jhops10.desafio_picpay.dtos;

import java.math.BigDecimal;

public record TransferDTO(BigDecimal value, Long payer, Long payee) {
}
