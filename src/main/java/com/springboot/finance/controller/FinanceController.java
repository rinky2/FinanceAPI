package com.springboot.finance.controller;

import com.springboot.finance.model.FinanceDTO;
import com.springboot.finance.model.ResponseFinance;
import com.springboot.finance.service.FinanceService;
import com.springboot.finance.service.FinanceServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
public class FinanceController {

    @Autowired //dependency injection
    private FinanceService financeService;

    // Resource endpoint url of project 'EmployeeApi'
    public static final String EMPLOYEE_URL = "http://localhost:8081/emp/";


//    @Bean
//    public RestTemplate getRestTemplate(){
//        return new RestTemplate();
//    }

    @Autowired
    private RestTemplate restTemplate;


    private Logger LOGGER = LoggerFactory.getLogger(FinanceController.class);

    @GetMapping("/salary") //will be converted to json by spring mvc
    public ResponseEntity<List<FinanceDTO>> getAllSalary()
    {

        ResponseEntity<List<FinanceDTO>> response = restTemplate.exchange(EMPLOYEE_URL, HttpMethod.GET,
                HttpEntity.EMPTY, new ParameterizedTypeReference<List<FinanceDTO>>() {
                });

        return ResponseEntity.status(HttpStatus.OK).body(response.getBody());

    }

    @GetMapping("/salary/{id}")
    public FinanceDTO getTopic(@PathVariable int id){
        return financeService.getSalary(id);
    }

    @PostMapping("/salary")
    public ResponseEntity<FinanceDTO> addTopic(@RequestBody FinanceDTO financeDTO) throws Exception//pick this instance from request payload
    {
        HttpEntity<FinanceDTO> request = new HttpEntity<>(financeDTO);
        ResponseEntity<FinanceDTO> response = restTemplate.exchange(EMPLOYEE_URL, HttpMethod.POST, request,
                FinanceDTO.class);
        return response;
      // financeService.addSalary(financeDTO);
    }

    @PutMapping( "/salary/{id}")
    public ResponseEntity<FinanceDTO> updateTopic(@RequestBody FinanceDTO financeDTO, @PathVariable int id)//pick this instance from request payload
    {
        HttpEntity<FinanceDTO> request = new HttpEntity<>(financeDTO);
        ResponseEntity<FinanceDTO> response = restTemplate.exchange(EMPLOYEE_URL+"/"+id , HttpMethod.PUT, request,
                FinanceDTO.class);
        return response;
    // financeService.updateSalary(id, financeDTO);
    }

    @DeleteMapping("/salary/{id}")
    public ResponseEntity<Map> deleteSalary(@PathVariable int id)
    {
        return restTemplate.exchange(EMPLOYEE_URL +"/"+id , HttpMethod.DELETE,
                HttpEntity.EMPTY, Map.class);

        //financeService.deleteSalary(id);
    }


}
