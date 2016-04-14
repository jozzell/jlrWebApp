/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.jlrMgrPDF_Make;

import cv.bisc.db.dbMgr;
import jlRoomsCommon._beans.custBean;
import jlRoomsCommon.bookingTable;

/**
 *
 * @author lmeans
 */
public class jlrMgrPDF_itinerary_Bean {
    private int id;
    private int sponsor;
    private String key;
    private bookingTable bookingTable;
    private dbMgr db;
    public jlrMgrPDF_itinerary_Bean(int id,int sponsor,String key,bookingTable bookingTable,dbMgr db){
        this.id = id;
        this.sponsor = sponsor;
        this.key = key;
        this.bookingTable = bookingTable;
        this.db = db;
    }

   

    /**
     * @return the sponsor
     */
    public int getSponsor() {
        return sponsor;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @return the bookingTable
     */
    public bookingTable getBookingTable() {
        return bookingTable;
    }

    /**
     * @return the db
     */
    public dbMgr getDb() {
        return db;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
}
