package com.springboot.finance.converter;

import com.springboot.finance.model.FinanceDAO;
import com.springboot.finance.model.FinanceDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FinanceConverter {
    public FinanceDTO entityToDto(FinanceDAO financeDAO) {
        return FinanceDTO.builder()
                .id(financeDAO.getId())
                .salary(financeDAO.getSalary())
                .build();

    }

    public List<FinanceDTO> entityToDto(List<FinanceDAO> topicDAO) {
        return topicDAO.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
    }


    public FinanceDAO dtoToEntity(FinanceDTO financeDTO) {
        return FinanceDAO.builder()
                .id(financeDTO.getId())
                .salary(financeDTO.getSalary())
                .build();
    }

    public List<FinanceDAO> dtoToEntity(List<FinanceDTO> dto) {
        return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }

}
