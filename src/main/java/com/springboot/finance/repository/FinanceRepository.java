package com.springboot.finance.repository;

import com.springboot.finance.model.FinanceDAO;
import org.springframework.data.jpa.repository.JpaRepository;
public interface FinanceRepository extends JpaRepository<FinanceDAO, Integer> {

}
