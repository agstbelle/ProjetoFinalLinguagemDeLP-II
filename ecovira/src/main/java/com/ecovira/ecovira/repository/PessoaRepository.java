
package com.ecovira.ecovira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecovira.ecovira.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
