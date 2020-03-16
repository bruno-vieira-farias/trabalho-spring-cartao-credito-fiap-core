package br.com.fiap.cartaocredito.cartaocredito.entrypoints.importacaoArquivo;

import br.com.fiap.cartaocredito.cartaocredito.domain.service.ImportacaoArquivoAlunoCartaoCreditoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/importacao")
public class ImportaArquivoController {

    private final ImportacaoArquivoAlunoCartaoCreditoService importacaoArquivo;

    public ImportaArquivoController(ImportacaoArquivoAlunoCartaoCreditoService importacaoArquivo) {
        this.importacaoArquivo = importacaoArquivo;
    }

    @PostMapping()
    public void importaAlunos(@RequestParam("file") MultipartFile file) {
        try {
            String conteudoArquivo = new String(file.getBytes(), StandardCharsets.UTF_8);
            importacaoArquivo.importaArquivoAlunoCartaoCredito(conteudoArquivo);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ocorreu um erro na importação do arquivo.", e);
        }
    }

}