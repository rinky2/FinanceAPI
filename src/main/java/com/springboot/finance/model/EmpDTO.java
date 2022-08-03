package com.springboot.finance.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class EmpDTO {

        private int id;
        private String name;
        private String email;
        private int salary;

        public EmpDTO(EmpDAO dao) {

            this.id = dao.getId();
            this.name = dao.getName();
            this.email = dao.getEmail();
            this.salary = dao.getSalary();
        }

}

