package com.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.model.Clube;

@Repository
public interface ClubeRepository extends JpaRepository<Clube, Long> {

}