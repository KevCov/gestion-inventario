package com.cibertec.gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.gestion.entity.Abastecimiento;

@Repository
public interface AbastecimientoRepository extends JpaRepository<Abastecimiento, Integer> {

}
