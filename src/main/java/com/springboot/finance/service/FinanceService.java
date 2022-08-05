package com.springboot.finance.service;

import com.springboot.finance.model.FinanceDTO;
import com.springboot.finance.model.ResponseFinance;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

    @Service
    public interface FinanceService {

       List<FinanceDTO> getAllSalary();

       FinanceDTO getSalary(int id);

        void addSalary(FinanceDTO financeDTO) throws FileNotFoundException, IOException;

        void updateSalary(int id, FinanceDTO financeDTO);

        void deleteSalary(int id);

//        ResponseFinance<FinanceDTO> updatePartialSalary(int id, int salary);
    }

