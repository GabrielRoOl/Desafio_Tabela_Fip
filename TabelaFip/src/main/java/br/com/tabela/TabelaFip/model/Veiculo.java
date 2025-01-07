package br.com.tabela.TabelaFip.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Veiculo(
        @JsonAlias("Valor") String valor,
        @JsonAlias("Marca") String marca,
        @JsonAlias("Modelo") String modelo,
        @JsonAlias("AnoModelo") Integer anoModelo,
        @JsonAlias("Combustivel") String tipoCombustivel

) {
    @Override
    public String toString() {
        return "Modelo = " + modelo + ", marca = " + marca +
                ", Ano = " + anoModelo +
                ", valor = " + valor + '\'' +
                ", Combustivel = " + tipoCombustivel;
    }
}
