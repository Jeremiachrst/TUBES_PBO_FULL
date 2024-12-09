package com.iseng.web.repository;

import com.iseng.web.models.Peserta;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RepositoriPeserta extends JpaRepository<Peserta, Long> {
    boolean existsByNim(String nim); // Query untuk mengecek apakah NIM ada di database
    Peserta findByNim(String nim);
}

