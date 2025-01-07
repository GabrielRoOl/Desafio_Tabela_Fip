package br.com.tabela.TabelaFip.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Dados (String codigo, String nome){
    @Override
    public String toString() {
        return "Código: " + codigo + " Nome: " + nome ;
    }
}
