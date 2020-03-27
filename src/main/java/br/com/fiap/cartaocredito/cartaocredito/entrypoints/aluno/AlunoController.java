package br.com.fiap.cartaocredito.cartaocredito.entrypoints.aluno;

import br.com.fiap.cartaocredito.cartaocredito.domain.entity.Aluno;
import br.com.fiap.cartaocredito.cartaocredito.domain.service.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping("{rm}")
    public AlunoDto obtemAlunoPorRm(@PathVariable Long rm) {
        try {
            Aluno aluno = alunoService.buscaAlunoPorRm(rm);
            return new AlunoDto(aluno.getRm(), aluno.getNome(), aluno.getCodigoTurma());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping("/codigo-turma/{codigoTurma}")
    public List<AlunoDto> obtemAlunosPorCodigoTurma(@PathVariable String codigoTurma){
        try {
            List<Aluno> alunos = alunoService.buscaAlunosCodigoTurma(codigoTurma);
            List<AlunoDto> alunosDto = alunos.stream()
                    .map(it -> new AlunoDto(it.getRm(), it.getNome(), it.getCodigoTurma()))
                    .collect(Collectors.toList());

            return alunosDto;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastraAluno(@RequestBody AlunoDto alunoDto) {
        try {
            alunoService.cadastraAluno(alunoDto.getRm(), alunoDto.getNome(),alunoDto.getCodigoTurma());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PutMapping
    public void atualizaAluno(@RequestBody AlunoDto alunoDto) {
        try {
            alunoService.atualizaAluno(alunoDto.getRm(), alunoDto.getNome());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @DeleteMapping("{rm}")
    public void deletaAluno(@PathVariable Long rm) {
        try {
            alunoService.deletaALuno(rm);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

}