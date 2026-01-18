package org.example.repository;

import org.example.model.StatusZadania;
import org.example.model.Zadanie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZadanieRepozytorium extends JpaRepository<Zadanie, Long> {

    List<Zadanie> findByStatus(StatusZadania status);
}