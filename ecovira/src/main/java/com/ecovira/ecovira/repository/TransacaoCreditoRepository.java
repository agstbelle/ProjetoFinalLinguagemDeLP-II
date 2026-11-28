
package com.ecovira.ecovira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecovira.ecovira.model.TransacaoCredito;

import java.util.List;

public interface TransacaoCreditoRepository extends JpaRepository<TransacaoCredito, Long> {

    List<TransacaoCredito> findByCarteiraIdOrderByDataHoraDesc(Long carteiraId);

}
