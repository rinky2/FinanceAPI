package com.springboot.finance.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FinanceDTO {

    private int id;
    private int salary;

    public FinanceDTO(FinanceDAO financeDAO) {

        //Converting from Entity to DTO
        this.id = financeDAO.getId();
        this.salary = financeDAO.getSalary();
    }
}
