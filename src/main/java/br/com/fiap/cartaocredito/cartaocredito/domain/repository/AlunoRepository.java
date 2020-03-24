package br.com.fiap.cartaocredito.cartaocredito.domain.repository;

import br.com.fiap.cartaocredito.cartaocredito.domain.entity.Aluno;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface AlunoRepository extends CrudRepository<Aluno, Long> {

    Aluno findByRm(Long rm);

    boolean existsByRm(Long rm);

    boolean existsAllByRmIn(Set<Long> rms);

    void deleteById(Long rm);
}
