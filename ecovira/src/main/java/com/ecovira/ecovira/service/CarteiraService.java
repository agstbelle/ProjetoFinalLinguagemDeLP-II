
package com.ecovira.ecovira.service;

import org.springframework.stereotype.Service;

import com.ecovira.ecovira.repository.PessoaRepository;
import com.ecovira.ecovira.repository.CarteiraRepository;
import com.ecovira.ecovira.repository.TransacaoCreditoRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ecovira.ecovira.model.Pessoa;
import com.ecovira.ecovira.model.Carteira;
import com.ecovira.ecovira.model.EcoCoin;
import com.ecovira.ecovira.model.TipoTransacao;
import com.ecovira.ecovira.model.TransacaoCredito;
@Service
public class CarteiraService {

    private final PessoaRepository pessoaRepository;
    private final CarteiraRepository carteiraRepository;
    private final TransacaoCreditoRepository transacaoCreditoRepository;

    public CarteiraService(
        PessoaRepository pessoaRepository,
        CarteiraRepository carteiraRepository,
        TransacaoCreditoRepository transacaoCreditoRepository
    ) 
    {
        this.pessoaRepository = pessoaRepository;
        this.carteiraRepository = carteiraRepository;
        this.transacaoCreditoRepository = transacaoCreditoRepository;
    }

@Transactional
public void adicionarCreditos(Long pessoaId, long quantidade, String descricao) {

    // Valida a quantidade
    if (quantidade <= 0) {
        throw new IllegalArgumentException(
            "A quantidade deve ser maior que zero."
        );
    }

    Pessoa pessoa = pessoaRepository.findById(pessoaId)
        .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada."));

    Carteira carteira = pessoa.getCarteira();

    if (carteira == null) {
        throw new IllegalArgumentException("Pessoa não possui carteira.");
    }

    EcoCoin valor = new EcoCoin(quantidade);

    carteira.adicionarCreditos(valor);

    carteiraRepository.save(carteira);

    TransacaoCredito transacao = new TransacaoCredito(
        valor,
        TipoTransacao.ENTRADA,
        descricao,
        carteira
    );

    transacaoCreditoRepository.save(transacao);



}


@Transactional
public void utilizarCreditos(Long pessoaId, long quantidade, String descricao) {

    if (quantidade <= 0) {
        throw new IllegalArgumentException(
            "A quantidade deve ser maior que zero."
        );
    }

    Pessoa pessoa = pessoaRepository.findById(pessoaId)
        .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada."));

    Carteira carteira = pessoa.getCarteira();

    if (carteira == null) {
        throw new IllegalArgumentException("Pessoa não possui carteira.");
    }
   //retirar ecoCoins do saldo e registrar a saída no histórico 
    EcoCoin valor = new EcoCoin(quantidade);

    carteira.utilizarCreditos(valor);

    carteiraRepository.save(carteira);

    TransacaoCredito transacao = new TransacaoCredito(
        valor,
        TipoTransacao.SAIDA,
        descricao,
        carteira
    );

    transacaoCreditoRepository.save(transacao);
}



    
}
