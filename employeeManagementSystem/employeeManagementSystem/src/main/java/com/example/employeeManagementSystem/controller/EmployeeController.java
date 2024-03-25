package com.example.employeeManagementSystem.controller;

import com.example.employeeManagementSystem.DTO.EmployeeDTO;
import com.example.employeeManagementSystem.DTO.ResponseDTO;
import com.example.employeeManagementSystem.service.EmployeeService;
import com.example.employeeManagementSystem.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {

    @Autowired
    private ResponseDTO responseDTO;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping( "/saveEmployee")
    public ResponseEntity saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        try{
            String res= employeeService.saveEmployee(employeeDTO);
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("success");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("All ready Registered");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

            }else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

            }
        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @PutMapping("/updateEmployee")
    public ResponseEntity updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        try {
            String res= employeeService.updateEmployee(employeeDTO);
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("success");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);

            }else if(res.equals("01")){
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("No User in the table");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);

            }
            else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);

            }
        }catch(Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteEmployee/{empId}")
    public ResponseEntity deleteEmployee (@PathVariable int empId){
        try {
            String res= employeeService.deleteEmployee(empId);
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("success");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);

            }else if(res.equals("01")){
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("No User in the table");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);

            }
            else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);

            }
        }catch(Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/getAllEmployee")
    public ResponseEntity getAllEmployee(){
        try{
            List<EmployeeDTO>employeeDTOList=employeeService.getAllEmployee();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("success");
            responseDTO.setContent(employeeDTOList);
            return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
        }catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/searchbyid/{empId}")
    public ResponseEntity searchbyId (@PathVariable int empId){
        try{
            EmployeeDTO employeeDTO=employeeService.searchId(empId);
            if(employeeDTO != null){
                responseDTO.setCode("00");
                responseDTO.setMessage("success");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
            }
            else{
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Employee in this ID");
                responseDTO.setContent(null);
                return new ResponseEntity (responseDTO,HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Error");
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);


        }
    }

}
