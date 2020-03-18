package br.com.fiap.cartaocredito.cartaocredito.domain.repository;

import br.com.fiap.cartaocredito.cartaocredito.domain.entity.Aluno;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlunoRepository extends CrudRepository<Aluno, Integer> {

    Aluno findByRm(Long rm);

    boolean existsByRm(Long rm);

//    boolean existsByRm(List<Long> rms);
}
