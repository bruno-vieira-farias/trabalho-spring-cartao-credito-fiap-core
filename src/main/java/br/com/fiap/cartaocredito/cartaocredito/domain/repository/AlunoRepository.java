package br.com.fiap.cartaocredito.cartaocredito.domain.repository;

import br.com.fiap.cartaocredito.cartaocredito.domain.entity.Aluno;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface AlunoRepository extends CrudRepository<Aluno, Long> {

    Aluno findByRm(Long rm);

    List<Aluno> findByCodigoTurma(String codigoTurma);

    boolean existsByRm(Long rm);

    boolean existsAllByRmIn(Set<Long> rms);

    void deleteById(Long rm);
}
