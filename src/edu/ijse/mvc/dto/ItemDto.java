/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.dto;

/**
 *
 * @author User
 */
public class ItemDto {
    
    private String itemCode;
    private String Description;
    private String packSize;
    private double unitPrice;
    private int QOH;

    public ItemDto() {
    }

    public ItemDto(String itemCode, String Description, String packSize, double unitPrice, int QOH) {
        this.itemCode = itemCode;
        this.Description = Description;
        this.packSize = packSize;
        this.unitPrice = unitPrice;
        this.QOH = QOH;
    }

    /**
     * @return the itemCode
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * @param itemCode the itemCode to set
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * @return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     * @return the packSize
     */
    public String getPackSize() {
        return packSize;
    }

    /**
     * @param packSize the packSize to set
     */
    public void setPackSize(String packSize) {
        this.packSize = packSize;
    }

    /**
     * @return the unitPrice
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return the QOH
     */
    public int getQOH() {
        return QOH;
    }

    /**
     * @param QOH the QOH to set
     */
    public void setQOH(int QOH) {
        this.QOH = QOH;
    }

    @Override
    public String toString() {
        return "ItemDto{" + "itemCode=" + itemCode + ", Description=" + Description + ", packSize=" + packSize + ", unitPrice=" + unitPrice + ", QOH=" + QOH + '}';
    }
    
    
    
    
}
