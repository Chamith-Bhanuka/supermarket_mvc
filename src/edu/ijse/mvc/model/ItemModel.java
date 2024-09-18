/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.model;

import edu.ijse.mvc.db.DBConnection;
import edu.ijse.mvc.dto.ItemDto;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author User
 */
public class ItemModel {
    
    public ArrayList<ItemDto> getAll() throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select * from Item";
        PreparedStatement statement = connection.prepareCall(sql);
        
        ResultSet rst = statement.executeQuery();
        ArrayList<ItemDto> itemDtos = new ArrayList<>();
        
        while(rst.next()){
            ItemDto itemDto = new ItemDto();
            itemDto.setItemCode(rst.getString("ItemCode"));
            itemDto.setDescription(rst.getString("Description"));
            itemDto.setPackSize(rst.getString("PackSize"));
            itemDto.setUnitPrice(rst.getDouble("UnitPrice"));
            itemDto.setQOH(rst.getInt("QtyOnHand"));
            
            itemDtos.add(itemDto);
        }
        return itemDtos;
    }
    
    public String save(ItemDto dto) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO Item VALUES (?,?,?,?,?)";
        PreparedStatement statement = connection.prepareCall(sql);
        
        statement.setString(1, dto.getItemCode());
        statement.setString(2, dto.getDescription());
        statement.setString(3, dto.getPackSize());
        statement.setDouble(4, dto.getUnitPrice());
        statement.setInt(5, dto.getQOH());
        
        int resp = statement.executeUpdate();
        return resp > 0 ? "Success" : "Fail";
    }

    public ItemDto getItem(String itemCode) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select * from Item where ItemCode = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, itemCode);
        
        ResultSet rst = statement.executeQuery();
        
        if(rst.next()){
            ItemDto itemDto = new ItemDto();
            itemDto.setItemCode(rst.getString("ItemCode"));
            itemDto.setDescription(rst.getString("Description"));
            itemDto.setPackSize(rst.getString("PackSize"));
            itemDto.setUnitPrice(rst.getDouble("UnitPrice"));
            itemDto.setQOH(rst.getInt("QtyOnHand"));
            
            return itemDto;
        }
        return null;
    }

    public String delete(String itemCode) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "delete from Item where ItemCode = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, itemCode);

        int resp = statement.executeUpdate();
        return resp > 0 ? "Success" : "Fail";
    }

    public String update(ItemDto dto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "update Item set Description = ?, PackSize = ?, UnitPrice = ?, QtyOnHand = ? where ItemCode = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(5, dto.getItemCode());
        statement.setString(1, dto.getDescription());
        statement.setString(2, dto.getPackSize());
        statement.setDouble(3, dto.getUnitPrice());
        statement.setInt(4, dto.getQOH());

        int resp = statement.executeUpdate();
        return resp > 0 ? "Success" : "Fail";
    }
    
}
