package br.com.fiap.cartaocredito.cartaocredito.entrypoints;

import br.com.fiap.cartaocredito.cartaocredito.domain.Aluno;
import br.com.fiap.cartaocredito.cartaocredito.domain.AlunoService;
import br.com.fiap.cartaocredito.cartaocredito.domain.ImportacaoArquivoAlunosService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private final ImportacaoArquivoAlunosService importacaoArquivoAlunosService;
    private final AlunoService alunoService;

    public AlunoController(ImportacaoArquivoAlunosService importacaoArquivoAlunosService, AlunoService alunoService) {
        this.importacaoArquivoAlunosService = importacaoArquivoAlunosService;
        this.alunoService = alunoService;
    }

    @GetMapping("{rm}")
    public AlunoDto obtemAlunoPorRm(@PathVariable Long rm) {
        try {
            Aluno aluno = alunoService.buscaAlunoPorRm(rm);
            return new AlunoDto(aluno.getRm(), aluno.getNome(), aluno.getNumeroCartao(), aluno.getDigitoVerificadorCartao());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastraAluno(@RequestBody() AlunoDto alunoDto) {
        try {
            alunoService.cadastraAluno(alunoDto.getRm(), alunoDto.getNome(), alunoDto.getNumeroCartao(), alunoDto.getDigitoCartao());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PostMapping("/importacao")
    public void importaAlunos(@RequestParam("file") MultipartFile file) {
        try {
            String conteudoArquivo = new String(file.getBytes(), StandardCharsets.UTF_8);
            importacaoArquivoAlunosService.importaAlunos(conteudoArquivo);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ocorreu um erro na importação do arquivo.", e);
        }
    }
}