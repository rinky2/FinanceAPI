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


    public ResponseFinance<List<FinanceDTO>> getAllSalary() throws ResponseStatusException {

        List<FinanceDTO> salary = new ArrayList();
        ResponseFinance<List<FinanceDTO>> response = new ResponseFinance<>();
        List<FinanceDAO> salaryData = (List<FinanceDAO>) financeRepository.findAll();
        LOGGER.info("All Salary fetched:", salaryData);
        System.out.println(salaryData);
        try {
            if (salaryData.isEmpty()) {
                response.setBody(salary);
                response.setStatus(200);
                response.setMessage("No Salary in DB!");
            } else {
                for (int i = 0; i < salaryData.size(); i++) {
                    FinanceDAO financedb = salaryData.get(i);
                    FinanceDTO dto = new FinanceDTO(financedb);
                    salary.add(dto);
                }
                response.setBody(salary);
                response.setStatus(200);
                response.setMessage("Salary Displayed from the Database");
            }
        } catch (Exception e) {
            // System.out.println("Error occured:: "+ e.getStackTrace());
            //e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Salary in Database!!");
        }
        return response;

    }

    public ResponseFinance<FinanceDTO> getSalary(int id) {

        ResponseFinance<FinanceDTO> response = new ResponseFinance<>();
        Optional<FinanceDAO> opt = financeRepository.findById(id);
        try {
            if (!opt.isPresent()) {
                response.setMessage("Salary ID not Found!!");
            } else {
                FinanceDTO dto = new FinanceDTO(opt.get());
                response.setBody(dto);
                response.setMessage("Salary ID Data Found");
                response.setStatus(200);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No Salary in Database");
        }
        return response;

    }

    public ResponseFinance<FinanceDTO> addSalary(FinanceDTO financeDTO) {
        ResponseFinance<FinanceDTO> response = new ResponseFinance<>();
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
            response.setMessage("Salary Added Successfully!");
            response.setStatus(200);
            response.setBody(financeDTO);

        }
         catch (Exception e) {
            System.out.println("Exception");
        }
        return response;
        }

    public ResponseFinance<FinanceDTO> updateSalary(int id, FinanceDTO financeDTO) {

        ResponseFinance<FinanceDTO> response = new ResponseFinance<>();
        Optional<FinanceDAO> db = financeRepository.findById(id);
        if (db.isPresent()) {
            FinanceDAO t = db.get();
            financeRepository.save(t);

            t.setSalary(financeDTO.getSalary());
            response.setBody(financeDTO);
            response.setMessage("Updated the Salary for ID: " + id);
            response.setStatus(200);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Salary ID Not Found!!!!");
        }
        return response;
    }


 /*   public ResponseFinance<FinanceDTO> updatePartialSalary(int id, int salary) {
        ResponseFinance<FinanceDTO> response = new ResponseFinance<>();

        Optional<FinanceDAO> db = financeRepository.findById(id);
        if (db.isPresent()) {
            FinanceDAO financeDAO = db.get();
//            if (salary != null)
//                financeDAO.setSalary(salary);
            financeRepository.save(financeDAO);

            FinanceDTO dto = new FinanceDTO(db.get());
            response.setBody(dto);
            response.setMessage("Updated the Salary for Topic ID: " + id);
            response.setStatus(200);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TopicID Not Found in DB.");
        }
        return response;

    } */

    public ResponseFinance<FinanceDTO> deleteSalary(int id) {
        ResponseFinance<FinanceDTO> response = new ResponseFinance<>();
        Optional<FinanceDAO> db = financeRepository.findById(id);
        if (db.isPresent()) {
            FinanceDTO t = new FinanceDTO(db.get());
            response.setBody(t);
            response.setMessage("Salary deleted for id: " + id);
            response.setStatus(200);
            financeRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Salary ID Not Found in DB.");
        }
        return response;

    }
}
