package br.com.fiap.cartaocredito.cartaocredito.domain.arquivoImportacao;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArquivoImportacao {
    private List<Linha> linhas;

    public ArquivoImportacao(String arquivo) {
        String[] linhas = arquivo.split("\r\n\r");
        List<String> linhasValidas = filtraLinhasArquivo(linhas);

        this.linhas = linhasValidas.stream()
                .map(linha -> new Linha(extraiNomeAluno(linha), extraiRmAluno(linha), extraiNumeroCartaoCredito(linha), extraiDigitoCartaoCredito(linha))
                ).collect(Collectors.toList());
    }

    public List<Linha> getLinhas() {
        return linhas;
    }

    private List<String> filtraLinhasArquivo(String[] linhas) {
        return Stream.of(linhas)
                .filter( /*Ignora linhas vazias*/
                        linha -> linha.length() > 0)
                .filter( /*Ignora linhas iniciadas com o caracter -*/
                        linha -> !linha.substring(0, 1).equals("-"))
                .filter(linha -> !linha.equals("\u001A\r")
                ).collect(Collectors.toList());
    }

    private String extraiNomeAluno(String linha) {
        return linha.substring(0, 41).trim();
    }

    private Long extraiRmAluno(String linha) {
        return Long.parseLong(linha.substring(41, 48));
    }

    private String extraiNumeroCartaoCredito(String linha) {
        return linha.substring(49, 52);
    }

    private String extraiDigitoCartaoCredito(String linha) {
        return linha.substring(53, 55);
    }
}
