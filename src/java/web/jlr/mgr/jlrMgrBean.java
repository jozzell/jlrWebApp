/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr;

import java.io.Serializable;
import web.jlr.mgr.vScope.jlrMgrClientPaymentBean;

/**
 *
 * @author Lloyd
 */
public class jlrMgrBean implements Serializable{
    private String vendorDescV2,vendorDesc;
    private int vendorId,custRoomId;
    private int vendorType;
    private double cost;
    private String jlrDisp="jlRooms";
    private String VendorName;
    private String seek;
    private int roomType;
    private String roomDesc;
    private String clientName;
    private int clientId;
    private boolean flt=false;
    private int blockId;
    private int flighId;
    private int sponsorHotelId;
    private String flightNumber,dst;
    
    private jlrMgrClientPaymentBean jlrMgrClientPaymentBean;
    
    public jlrMgrBean(){
        
    }
    public void setClient(int id,String name){
        this.clientId = id;
        this.clientName = name;
    }
    public void reset(){
        setFlighId(0);
        custRoomId = 0;
    }
    public String getVendorDescV2() {
        return vendorDescV2;
    }

    /**
     * @param vendorDesc the vendorDesc to set
     */
    public void setVendorDescV2(String vendorDesc) {
        this.vendorDescV2 = vendorDesc;
    }

    /**
     * @return the vendorId
     */
    public int getVendorId() {
        return vendorId;
    }

    /**
     * @param vendorId the vendorId to set
     */
    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    /**
     * @return the vendorType
     */
    public int getVendorType() {
        return vendorType;
    }

    /**
     * @param vendorType the vendorType to set
     */
    public void setVendorType(int vendorType) {
        this.vendorType = vendorType;
    }

    /**
     * @return the VendorName
     */
    public String getVendorName() {
        return VendorName;
    }

    /**
     * @param VendorName the VendorName to set
     */
    public void setVendorName(String VendorName) {
        this.VendorName = VendorName;
    }

    /**
     * @return the roomType
     */
    public int getRoomType() {
        return roomType;
    }

    /**
     * @param roomType the roomType to set
     */
    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }

    /**
     * @return the roomDesc
     */
    public String getRoomDesc() {
        return roomDesc;
    }

    /**
     * @param roomDesc the roomDesc to set
     */
    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc;
    }

    /**
     * @return the custRoomId
     */
    public int getCustRoomId() {
        return custRoomId;
    }

    /**
     * @param custRoomId the custRoomId to set
     */
    public void setCustRoomId(int custRoomId) {
        this.custRoomId = custRoomId;
    }

    /**
     * @return the clientName
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * @param clientName the clientName to set
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * @return the clientId
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * @param clientId the clientId to set
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * @return the flt
     */
    public boolean isFlt() {
        return flt;
    }

    /**
     * @param flt the flt to set
     */
    public void setFlt(boolean flt) {
        this.flt = flt;
    }

    /**
     * @return the flighId
     */
    public int getFlighId() {
        return flighId;
    }

    public void setFlighId(int i){
        this.flighId = i;
    }
    public void setFlighId(int flighId,int custRmId) {
        this.setFlighId(flighId);
        this.custRoomId = custRmId;
    }

    /**
     * @return the flightNumber
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * @param flightNumber the flightNumber to set
     */
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     * @param flighId the flighId to set
     */
    public void setFlighId(int flighId,String num,String dst) {
        this.flighId = flighId;
        this.flightNumber = num;
        this.dst = dst;
        
    }

    /**
     * @return the blockId
     */
    public int getBlockId() {
        return blockId;
    }

    /**
     * @param blockId the blockId to set
     */
    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

    /**
     * @return the sponsorHotelId
     */
    public int getSponsorHotelId() {
        return sponsorHotelId;
    }

    /**
     * @param sponsorHotelId the sponsorHotelId to set
     */
    public void setSponsorHotelId(int sponsorHotelId) {
        this.sponsorHotelId = sponsorHotelId;
    }

    /**
     * @return the dst
     */
    public String getDst() {
        return dst;
    }

    /**
     * @param dst the dst to set
     */
    public void setDst(String dst) {
        this.dst = dst;
    }

    /**
     * @return the jlrMgrClientPaymentBean
     */
    public jlrMgrClientPaymentBean getJlrMgrClientPaymentBean() {
        
        return jlrMgrClientPaymentBean;
    }

    /**
     * @param jlrMgrClientPaymentBean the jlrMgrClientPaymentBean to set
     */
    public void setJlrMgrClientPaymentBean(jlrMgrClientPaymentBean jlrMgrClientPaymentBean) {
        this.jlrMgrClientPaymentBean = jlrMgrClientPaymentBean;
    }

   

    /**
     * @return the vendorDesc
     */
    public String getVendorDesc() {
        return vendorDesc;
    }

    /**
     * @param vendorDesc the vendorDesc to set
     */
    public void setVendorDesc(String vendorDesc) {
        this.vendorDesc = vendorDesc;
    }

    /**
     * @return the seek
     */
    public String getSeek() {
        return seek;
    }

    /**
     * @param seek the seek to set
     */
    public void setSeek(String seek) {
        this.seek = seek;
    }
    public void setJlrDisp(String str){
        jlrDisp = str;
    }
    public String getJlrDisp(String str){
        
        return (jlrDisp == null ? "":this.jlrDisp)+( str == null ? "":" "+str);
    }

    /**
     * @return the eJlrMgrEnum
     */
   

    /**
     * @return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(double cost) {
        this.cost = cost;
    }
}
