package br.com.fiap.cartaocredito.cartaocredito.domain;

import org.springframework.data.repository.CrudRepository;

public interface AlunoRepository extends CrudRepository<Aluno,Integer> {

    Aluno findByRm(Long rm);

}
