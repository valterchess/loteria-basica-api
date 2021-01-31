package com.zup.orange.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zup.orange.domain.model.Loteria;

@Repository
public interface loteriaRepository extends JpaRepository<Loteria, Long>{

}
