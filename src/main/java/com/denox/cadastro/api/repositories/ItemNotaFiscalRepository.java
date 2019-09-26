package com.denox.cadastro.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.denox.cadastro.api.domain.ItemNotaFiscal;

@Repository
public interface ItemNotaFiscalRepository extends JpaRepository<ItemNotaFiscal, Integer> {

}
