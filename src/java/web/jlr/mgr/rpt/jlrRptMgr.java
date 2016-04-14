/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.rpt;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import jlRoomsCommon._beans.rptItineraryBean;
import jlRoomsCommon.rpt.db.rpt_Itinerary_db;
import jlRoomsCommon.vendorObjTypesENum;
import web.jlr.mgr.jlrMgr;

/**
 *
 * @author Lloyd
 */
@ManagedBean
@RequestScoped
public class jlrRptMgr  implements Serializable {
    private jlrRptMgrBean
            itemBean,
            ticketBean,
            carBean,
            hotelBean,
            airlineBean,
            trainBean;
    @ManagedProperty("#{jlrMgr}")
    private jlrMgr jlrMgr = null;
    public void setJlrMgr(jlrMgr m) {
        jlrMgr = m;
    }
    public jlrMgr getJlrMgr(){
        return jlrMgr;
    }
    public jlrRptMgrBean getTrainBean(){
        if (trainBean == null){
            List<rptItineraryBean> list = (new rpt_Itinerary_db(jlrMgr.getEMailKey())).getFlt(
                    jlrMgr.getJlrMgrBean().getClientId(), 
                    jlrMgr.getSponsorId(),
                    -32, 
                    jlrMgr.getObj());
            
            trainBean = new jlrRptMgrBean(list, vendorObjTypesENum.RAILROAD);
        }
        return trainBean;
    }
     public jlrRptMgrBean getAirlineBean(){
        if (airlineBean == null){
            List<rptItineraryBean> list = (new rpt_Itinerary_db(jlrMgr.getEMailKey())).getFlt(
                    jlrMgr.getJlrMgrBean().getClientId(), 
                    jlrMgr.getSponsorId(),
                    -12, 
                    jlrMgr.getObj());
            
            airlineBean = new jlrRptMgrBean(list, vendorObjTypesENum.AIRLINE);
        }
        return airlineBean;
    }
      public jlrRptMgrBean getHotelBean(){
        if (hotelBean == null){
            List<rptItineraryBean> list = (new rpt_Itinerary_db(jlrMgr.getEMailKey())).getFlt(
                    jlrMgr.getJlrMgrBean().getClientId(), 
                    jlrMgr.getSponsorId(),
                    -2, 
                    jlrMgr.getObj());
            
            hotelBean = new jlrRptMgrBean(list, vendorObjTypesENum.HOTEL);
        }
        return hotelBean;
    }
      public jlrRptMgrBean getCarBean(){
        if (carBean == null){
            List<rptItineraryBean> list = (new rpt_Itinerary_db(jlrMgr.getEMailKey())).getFlt(
                    jlrMgr.getJlrMgrBean().getClientId(), 
                    jlrMgr.getSponsorId(),
                    -13, 
                    jlrMgr.getObj());
            
            carBean = new jlrRptMgrBean(list, vendorObjTypesENum.HOTEL);
        }
        return carBean;
    }
      
       public jlrRptMgrBean getTicketBean(){
        if (ticketBean == null){
            List<rptItineraryBean> list = (new rpt_Itinerary_db(jlrMgr.getEMailKey())).getFlt(
                    jlrMgr.getJlrMgrBean().getClientId(), 
                    jlrMgr.getSponsorId(),
                    -33, 
                    jlrMgr.getObj());
            
            ticketBean = new jlrRptMgrBean(list, vendorObjTypesENum.TICKETS);
        }
        return ticketBean;
    }
        public jlrRptMgrBean getItemBean(){
        if (itemBean == null){
            List<rptItineraryBean> list = (new rpt_Itinerary_db(jlrMgr.getEMailKey())).getFlt(
                    jlrMgr.getJlrMgrBean().getClientId(), 
                    jlrMgr.getSponsorId(),
                    -34, 
                    jlrMgr.getObj());
            
            itemBean = new jlrRptMgrBean(list, vendorObjTypesENum.TICKETS);
        }
        return itemBean;
    }
}
