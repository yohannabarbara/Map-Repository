package com.example.denuncia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.denuncia.model.Denuncia;

@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {
}
