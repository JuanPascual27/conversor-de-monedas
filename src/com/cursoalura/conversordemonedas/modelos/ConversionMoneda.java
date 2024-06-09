package com.cursoalura.conversordemonedas.modelos;

public record ConversionMoneda(String base_code,
                               String target_code,
                               double conversion_rate,
                               double conversion_result) {
}
