package com.springboot.finance.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EmpDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;
    private int salary;

    public EmpDAO(EmpDTO dto) {
        //Converting from Entity to DTO
        this.id = dto.getId();
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.salary = dto.getSalary();
    }

}
