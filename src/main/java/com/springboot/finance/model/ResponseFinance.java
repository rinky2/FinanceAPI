package com.springboot.finance.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseFinance<S> extends FinanceDTO {
    private S body;
    private int status;
    private String message;

}
