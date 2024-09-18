/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.controller;

import edu.ijse.mvc.dto.CustomerDto;
import edu.ijse.mvc.model.CustomerModel;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author User
 */
public class CustomerController {
    
    private final CustomerModel CUSTOMER_MODEL = new CustomerModel();

     
    public List<CustomerDto> getAllCustomers() throws ClassNotFoundException, SQLException{
        List<CustomerDto> customerDtos = CUSTOMER_MODEL.getAllCustomer();
        return customerDtos;
    }

    public CustomerDto searchCustomer(String custID) throws SQLException, ClassNotFoundException{
        CustomerDto dto = CUSTOMER_MODEL.searchCustomer(custID);
        return dto;
    }

    public String saveCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        String resp = CUSTOMER_MODEL.saveCustomer(customerDto);
        return resp;
    }
    
    public String deleteCustomer(String custID)  throws SQLException, ClassNotFoundException {
        String resp = CUSTOMER_MODEL.deleteCustomer(custID);
        return resp;
    }
    
    public String updateCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        String resp = CUSTOMER_MODEL.updateCustomer(customerDto);
        return resp;
    }
    
}
