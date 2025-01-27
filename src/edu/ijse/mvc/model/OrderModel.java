/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.model;

import edu.ijse.mvc.db.DBConnection;
import edu.ijse.mvc.dto.OrderDetailDto;
import edu.ijse.mvc.dto.OrderDto;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author User
 */
public class OrderModel {
   
        public String placeOrder(OrderDto orderDto) throws Exception{
        Connection connection= DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            
            String orderSql = "INSERT INTO Orders VALUES (?,?,?)";
            PreparedStatement orderStatement = connection.prepareStatement(orderSql);
            orderStatement.setString(1, orderDto.getOrderId());
            orderStatement.setString(2, orderDto.getOrderDate());
            orderStatement.setString(3, orderDto.getCustId());
            
            boolean isOrderSaved = orderStatement.executeUpdate() > 0;
            
            if(isOrderSaved){
                boolean isOrderDetailSaved = true;
                
                for (OrderDetailDto orderDetailDto : orderDto.getOrderDetailDtos()) {
                    String orderDetailSQL = "INSERT INTO Orderdetail VALUES (?,?,?,?)";
                    PreparedStatement orderDetailStatement = connection.prepareStatement(orderDetailSQL);
                    orderDetailStatement.setString(1, orderDto.getOrderId());
                    orderDetailStatement.setString(2, orderDetailDto.getItemCode());
                    orderDetailStatement.setInt(3, orderDetailDto.getQty());
                    orderDetailStatement.setDouble(4, orderDetailDto.getDiscount());
                    
                    if(!(orderDetailStatement.executeUpdate()> 0)){
                        isOrderDetailSaved = false;
                    }
                }
                
                if(isOrderDetailSaved){
                    boolean isItemUpdated = true;
                    for (OrderDetailDto orderDetailDto : orderDto.getOrderDetailDtos()) {
                        String itemUpdateSql = "UPDATE item SET QtyOnHand = QtyOnHand - ? WHERE ItemCode = ?";
                        PreparedStatement itemStatement = connection.prepareStatement(itemUpdateSql);
                        itemStatement.setInt(1, orderDetailDto.getQty());
                        itemStatement.setString(2, orderDetailDto.getItemCode());
                    
                        if(!(itemStatement.executeUpdate()>0)){
                            isItemUpdated = false;
                        }
                    }
                    
                    if(isItemUpdated){
                        connection.commit();
                        return "Saved";
                    } else {
                        connection.rollback();
                        return "Item update Error";
                    }
                    
                } else {
                    connection.rollback();
                    return "Order Detail Save Error";
                }
                
            } else {
                connection.rollback();
                return "Order Save Error";
            }
            
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
