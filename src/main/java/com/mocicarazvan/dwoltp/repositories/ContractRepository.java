package com.mocicarazvan.dwoltp.repositories;

import com.mocicarazvan.dwoltp.models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {
}