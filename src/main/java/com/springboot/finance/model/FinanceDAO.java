package com.springboot.finance.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "finance")
public class FinanceDAO{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="salary")
    private int salary;


    public FinanceDAO(FinanceDTO financeDTO) {
        this.id = financeDTO.getId();
        this.salary = financeDTO.getSalary();
    }
}
