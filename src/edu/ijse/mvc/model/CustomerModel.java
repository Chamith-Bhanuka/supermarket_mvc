/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.model;

import edu.ijse.mvc.db.DBConnection;
import edu.ijse.mvc.dto.CustomerDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author User
 */
public class CustomerModel {
    
    public String saveCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO Customer VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareCall(sql);
        
        statement.setString(1, customerDto.getId());
        statement.setString(2, customerDto.getTitle());
        statement.setString(3, customerDto.getName());
        statement.setString(4, customerDto.getDOB());
        statement.setDouble(5,    customerDto.getSalary());
        statement.setString(6, customerDto.getAddress());
        statement.setString(7, customerDto.getCity());
        statement.setString(8, customerDto.getProvince());
        statement.setString(9, customerDto.getPostalCode());
        
        int resp = statement.executeUpdate();
        return resp > 0 ? "Successfully Saved" : "Fail";
         
    }
    
    public String deleteCustomer(String CustID) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "delete from Customer where CustID = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, CustID);

        int resp = statement.executeUpdate();
        return resp > 0 ? "Successfully Deleted" : "Fail";
    }
    
     public String updateCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "update Customer set CustTitle = ?, CustName = ?, DOB = ?, salary = ?, CustAddress = ?, City = ?, Province = ?, PostalCode = ? where CustID = ?";
        
        PreparedStatement statement = connection.prepareCall(sql);
        
        statement.setString(9, customerDto.getId());
        statement.setString(1, customerDto.getTitle());
        statement.setString(2, customerDto.getName());
        statement.setString(3, customerDto.getDOB());
        statement.setDouble(4, customerDto.getSalary());
        statement.setString(5, customerDto.getAddress());
        statement.setString(6, customerDto.getCity());
        statement.setString(7, customerDto.getProvince());
        statement.setString(8, customerDto.getPostalCode());

        int resp = statement.executeUpdate();
        return resp > 0 ? "Successfully Updated" : "Fail";
    }
     
     public CustomerDto searchCustomer(String CustID) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select * from Customer where CustID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, CustID);
        
        ResultSet rst = statement.executeQuery();
        
        if(rst.next()){
            CustomerDto customerDto = new CustomerDto();
            customerDto.setId(rst.getString("CustID"));
            customerDto.setTitle(rst.getString("CustTitle"));
            customerDto.setName(rst.getString("CustName"));
            customerDto.setDOB(rst.getString("DOB"));
            customerDto.setSalary(rst.getDouble("salary"));
            customerDto.setAddress(rst.getString("CustAddress"));
            customerDto.setCity(rst.getString("City"));
            customerDto.setProvince(rst.getString("Province"));
            customerDto.setPostalCode(rst.getString("PostalCode"));
            
            return customerDto;
        }
        return null;
    }
     
     public List<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select * from Customer";
        PreparedStatement statement = connection.prepareCall(sql);
        
        ResultSet rst = statement.executeQuery();
        ArrayList<CustomerDto> customerDtos = new ArrayList<>();
        
        while(rst.next()){
            CustomerDto customerDto = new CustomerDto();
            customerDto.setId(rst.getString("CustID"));
            customerDto.setTitle(rst.getString("CustTitle"));
            customerDto.setName(rst.getString("CustName"));
            customerDto.setDOB(rst.getString("DOB"));
            customerDto.setSalary(rst.getDouble("salary"));
            customerDto.setAddress(rst.getString("CustAddress"));
            customerDto.setCity(rst.getString("City"));
            customerDto.setProvince(rst.getString("Province"));
            customerDto.setPostalCode(rst.getString("PostalCode"));
            
            customerDtos.add(customerDto);
        }
        return customerDtos;
    }
    
}
