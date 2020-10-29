package com.sid.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sid.entities.Operation;

public interface OperationRepository extends JpaRepository<Operation, String>{

	@Query("SELECT o from Operation o where o.compte.codeCompte=:x order by o.dateOperation desc ")
	public Page<Operation> listeOperaton(@Param("x")String codeCpte,Pageable pageable);
}
