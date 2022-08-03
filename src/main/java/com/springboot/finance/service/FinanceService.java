package com.springboot.finance.service;

import com.springboot.finance.model.FinanceDTO;
import com.springboot.finance.model.ResponseFinance;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

    @Service
    public interface FinanceService {

        ResponseFinance<List<FinanceDTO>> getAllSalary();

        ResponseFinance<FinanceDTO> getSalary(int id);

        ResponseFinance<FinanceDTO> addSalary(FinanceDTO financeDTO) throws FileNotFoundException, IOException;

        ResponseFinance<FinanceDTO> updateSalary(int id, FinanceDTO financeDTO);

        ResponseFinance<FinanceDTO> deleteSalary(int id);

//        ResponseFinance<FinanceDTO> updatePartialSalary(int id, int salary);
    }

