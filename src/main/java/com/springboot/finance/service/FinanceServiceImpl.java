package com.springboot.finance.service;

import com.springboot.finance.converter.FinanceConverter;
import com.springboot.finance.model.FinanceDAO;
import com.springboot.finance.model.FinanceDTO;
import com.springboot.finance.model.ResponseFinance;
import com.springboot.finance.repository.FinanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FinanceServiceImpl implements FinanceService{

    @Autowired
    private FinanceRepository financeRepository;

    @Autowired
    private FinanceConverter financeConverter;

    private Logger LOGGER = LoggerFactory.getLogger(FinanceServiceImpl.class);


    public List<FinanceDTO> getAllSalary() throws ResponseStatusException {

        List<FinanceDTO> salary = new ArrayList();
        ResponseFinance<List<FinanceDTO>> response = new ResponseFinance<>();
        List<FinanceDAO> salaryData = (List<FinanceDAO>) financeRepository.findAll();
        LOGGER.info("All Salary fetched:", salaryData);
        System.out.println(salaryData);
        try {
            if (salaryData.isEmpty()) {
                LOGGER.warn("No data present!!");
            } else {
                for (int i = 0; i < salaryData.size(); i++) {
                    FinanceDAO financedb = salaryData.get(i);
                    FinanceDTO dto = new FinanceDTO(financedb);
                    salary.add(dto);
                }

            }
        } catch (Exception e) {
            // System.out.println("Error occured:: "+ e.getStackTrace());
            //e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Salary in Database!!");
        }
        return salary;
    }

    public FinanceDTO getSalary(int id) {


        Optional<FinanceDAO> opt = financeRepository.findById(id);
        FinanceDTO dto = new FinanceDTO();
        try {
            if (!opt.isPresent()) {
                LOGGER.info("Salary for that id not present!");
            } else {
               opt.get();
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No Salary in Database");
        }
            return dto;

    }

    public void addSalary(FinanceDTO financeDTO) {
        FinanceDAO financeDAO = new FinanceDAO(financeDTO);
        try {
            List<FinanceDAO> topicDb = (List<FinanceDAO>) financeRepository.findAll();
            for (int i = 0; i < topicDb.size(); i++) {
                FinanceDAO finance = topicDb.get(i);
                if ((financeDTO.getId() == (finance.getId()))) {
                    LOGGER.warn("Salary ID Already present!");
                    throw new ResponseStatusException(HttpStatus.FOUND, "Salary ID Already present!");
                }
            }
            financeRepository.save(financeDAO);
            LOGGER.info("Salary saved successfully");

        }
         catch (Exception e) {
            System.out.println("Exception");
        }

        }

    public void updateSalary(int id, FinanceDTO financeDTO) {


        Optional<FinanceDAO> db = financeRepository.findById(id);
        if (db.isPresent()) {
            FinanceDAO t = db.get();
            financeRepository.save(t);

            t.setSalary(financeDTO.getSalary());

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Salary ID Not Found!!!!");
        }

    }


    public void deleteSalary(int id) {

        Optional<FinanceDAO> db = financeRepository.findById(id);
        if (db.isPresent()) {
            FinanceDTO t = new FinanceDTO(db.get());

            financeRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Salary ID Not Found in DB.");
        }


    }
}
