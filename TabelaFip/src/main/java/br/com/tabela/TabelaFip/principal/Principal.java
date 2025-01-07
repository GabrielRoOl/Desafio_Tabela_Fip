package br.com.tabela.TabelaFip.principal;

import br.com.tabela.TabelaFip.model.Dados;
import br.com.tabela.TabelaFip.model.Modelos;
import br.com.tabela.TabelaFip.service.ConsumoApi;
import br.com.tabela.TabelaFip.service.ConverteDados;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    Scanner sc = new Scanner(System.in);

    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private final String URL_MODELO = "/modelos/";
    private final String URL_ANO = "/anos/";

    public void exibeMenu(){
        var menu = """
                *** OPÇÕES ***
                Carro
                Moto
                Caminhão
                
                Digite uma das opções para consultar: """;
        System.out.println(menu);
        var opcao = sc.nextLine();

        String endereco;

        if(opcao.toLowerCase().contains("carr")){
            endereco = URL_BASE + "carros/marcas/";
        } else if(opcao.toLowerCase().contains("mot")){
            endereco = URL_BASE + "motos/marcas/";
        } else {
            endereco = URL_BASE + "caminhoes/marcas/";
        }

        var json = consumo.obterDados(endereco);

        var marcas = conversor.obterLista(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.print("\nInform o código da marca para consulta: ");
        var codigo = sc.nextLine();

        endereco = endereco + codigo + URL_MODELO;

        json = consumo.obterDados(endereco);
        var modeloLista = conversor.obterDados(json, Modelos.class);

        System.out.println("\nModelos da marca: ");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::nome))
                .forEach(System.out::println);

        System.out.print("\nQual o nome do carro que deseja? ");

        var nomeVeiculo = sc.nextLine();

        List<Dados> modelosFiltrados = modeloLista.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\nModelos filtrados:\n--==--=--=--=--=--=--=--=--=--=--=--==--=--=--=--=--=--=--=--=--=--=\n");

        modelosFiltrados.forEach(System.out::println);

        System.out.print("\nTecle o código do modelo desejado: ");
        var codigoModelo = sc.nextLine();

        endereco = endereco + codigoModelo + URL_ANO;
        json = consumo.obterDados(endereco);

        List<Dados> anos = conversor.obterLista(json, Dados.class);


    }
}
