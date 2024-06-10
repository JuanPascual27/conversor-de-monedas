package com.cursoalura.conversordemonedas.modelos;

public record ConversionMonedaDTO(String base_code,
                                  String target_code,
                                  double conversion_rate,
                                  double conversion_result) {
}
