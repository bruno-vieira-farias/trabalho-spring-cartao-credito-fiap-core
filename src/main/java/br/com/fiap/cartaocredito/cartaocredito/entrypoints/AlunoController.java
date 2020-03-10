package br.com.fiap.cartaocredito.cartaocredito.entrypoints;

import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @PostMapping
    public void importaAlunos(@RequestParam("file") MultipartFile file) throws IOException {

        String content = new String(file.getBytes(), "UTF-8");

        String[] linhas = content.split("\r\n\r");

        Stream.of(linhas)
                .filter(linha -> linha.length() > 0)
                .filter(linha -> !linha.substring(0, 1).equals("-"))
                .filter(linha -> !linha.equals("\u001A\r"))
                .forEach(
                linha ->{
                    System.out.println(extraiNome(linha) + extraiCodigo1(linha) + extraiCodigo2(linha));
                } );
    }

    private String extraiNome(String linha){
        return linha.substring(0,42);
    }

    private String extraiCodigo1(String linha){
        return linha.substring(43,49);
    }

    private String extraiCodigo2(String linha){
        return linha.substring(50,55);
    }
}