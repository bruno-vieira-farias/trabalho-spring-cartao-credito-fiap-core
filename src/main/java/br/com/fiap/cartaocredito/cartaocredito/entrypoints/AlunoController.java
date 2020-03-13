package br.com.fiap.cartaocredito.cartaocredito.entrypoints;

import br.com.fiap.cartaocredito.cartaocredito.domain.ImportacaoArquivoAlunosService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private final ImportacaoArquivoAlunosService importacaoArquivoAlunosService;

    public AlunoController(ImportacaoArquivoAlunosService importacaoArquivoAlunosService) {
        this.importacaoArquivoAlunosService = importacaoArquivoAlunosService;
    }

    //Contrato consiste em enviar o arquivo no formato especifico
    @PostMapping
    public void importaAlunos(@RequestParam("file") MultipartFile file) {

        try {
            String conteudoArquivo = new String(file.getBytes(), StandardCharsets.UTF_8);
            importacaoArquivoAlunosService.importaAlunos(conteudoArquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}