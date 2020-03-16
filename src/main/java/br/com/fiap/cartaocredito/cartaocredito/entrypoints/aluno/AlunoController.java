package br.com.fiap.cartaocredito.cartaocredito.entrypoints.aluno;

import br.com.fiap.cartaocredito.cartaocredito.domain.entity.Aluno;
import br.com.fiap.cartaocredito.cartaocredito.domain.service.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
            return new AlunoDto(aluno.getRm(), aluno.getNome());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastraAluno(@RequestBody() AlunoDto alunoDto) {
        try {
            alunoService.cadastraAluno(alunoDto.getRm(), alunoDto.getNome());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}