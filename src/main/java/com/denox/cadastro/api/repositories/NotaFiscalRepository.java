package com.denox.cadastro.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.denox.cadastro.api.domain.Cliente;
import com.denox.cadastro.api.domain.NotaFiscal;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Integer> {

	@Transactional(readOnly=true)
	Page<NotaFiscal> findByCliente(Cliente cliente, Pageable pageRequest);
}
