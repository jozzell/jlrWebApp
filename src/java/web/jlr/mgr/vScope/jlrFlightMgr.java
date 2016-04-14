/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.vScope;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import jlRoomsCommon._beans.custRmBean;
import jlRoomsCommon._beans.flightInfoBean;
import jlRoomsCommon._beans.lookupBean;
import jlRoomsCommon._beans.sponsorHotelBean;
import jlRoomsCommon.customerRoom.db.customerRmObj;
import jlRoomsCommon.flights.db.flightObj;
import jlRoomsCommon.lookup_sys.db.lookupSysObj;
import jlRoomsCommon.vendorObjTypesENum;
import org.primefaces.context.RequestContext;
import web.jlr.mgr.Model.flightInfoListModel;
import web.jlr.mgr.eNum.eNumPageNav;
import web.jlr.mgr.jlrMgr;
import web.jlr.mgr.jlrMgrBean;

/**
 *
 * @author Lloyd
 */
@ManagedBean
@ViewScoped
public class jlrFlightMgr implements Serializable{
    private custRmBean custRmBean;
    private List<lookupBean> statusList;
    private flightInfoBean flightInfoBean=null;
    private flightInfoListModel flightInfoListModel;
    private vendorObjTypesENum vendorObjTypesENum;
    @ManagedProperty("#{jlrMgr}")
    private jlrMgr jlrMgr = null;
    public void setJlrMgr(jlrMgr jlrMgr) {
        this.jlrMgr = jlrMgr;
    }

    public String getDispHeader(){
       jlrMgrBean b = jlrMgr.getJlrMgrBean();
       return b.getClientName()+ " New "+b.getRoomDesc()+ "("+b.getVendorName()+")"+ b.getVendorDesc();
    }
    public void fltSave(){
        if (this.flightInfoBean.getFltId() == 0){
            jlrMgrBean b = jlrMgr.getJlrMgrBean();
            sponsorHotelBean sponsorHotelBean = (new qckBlock()). genSponsorHotelBean(
                jlrMgr.getSponsorId(),
                b.getVendorId(), 
                b.getVendorType(),jlrMgr);
            
            flightInfoBean.setSponsorHotelId(sponsorHotelBean.getSponsor_hotel_id());
            flightInfoBean.setSponsorId(jlrMgr.getSponsorId());
            flightInfoBean.setVendorId(b.getVendorId());
            flightInfoBean.setFltType(b.getVendorType());
            flightInfoBean.setHotelId(sponsorHotelBean.getSponsor_hotel_id());
           
                
                
        }
        new flightObj(jlrMgr.getEMailKey()).updateFlight(flightInfoBean,jlrMgr.getObj());
        fltCancel();
    }
    public void fltCancel(){
         jlrMgr.setCenterHtml(eNumPageNav.flt_pg0_viewFiights);
    }
    public void back(){
        //jlrMgr.setCenterHtml("maintenance/lookupList.xhtml");
        jlrMgr.setCenterHtml(eNumPageNav.vendor_vendorPg1_seek_Ftl);
        
    }
    public void cancel(){
         fltCancel();
    }
    public void edit(){
        if (!isOk()) return;
        jlrMgr.getJlrMgrBean().setFlighId(
                this.flightInfoBean.getFltId());
        jlrMgr.setCenterHtml(eNumPageNav.flt_pg0_flightInfoEdit);
        
    }
   
    public void next(){
        if (!isOk()) return;
        jlrMgr.getJlrMgrBean().setSponsorHotelId(flightInfoBean.getSponsorHotelId());
        jlrMgr.getJlrMgrBean().setFlighId(this.flightInfoBean.getFltId(),
                flightInfoBean.getFltNumber(),
                flightInfoBean.DST());
        jlrMgr.getJlrMgrBean().setFlightNumber(
                vendorObjTypesENum.getSubRmDesc()+" "+
                flightInfoBean.getFltNumber()+" "+
                flightInfoBean.DST()+" Departing "+
                flightInfoBean.getFltDepartureDateString());
        //jlrMgr.setCenterHtml("flt/pg1_flightInfoEdit.xhtml");
        jlrMgr.setCenterHtml(eNumPageNav.maintenance_lookupListFlt);
        
    }
    private boolean isOk(){
        if (this.flightInfoBean == null){
            RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
            return false;
        }
        RequestContext.getCurrentInstance().execute("PF('dialogWidget').hide()");
        return true;
    }
    public void add(){
        jlrMgr.getJlrMgrBean().setFlighId(0,"","");
        jlrMgr.setCenterHtml(eNumPageNav.flt_pg0_flightInfoEdit);
    }
     public String backButton(){
        if (this.custRmBean.getCustRoomId() == 0) return "true";
        return "false";
    }
    /*
   
    
    public void editSave(){
       if (this.getFlightInfoBean().getFltId() == 0  || this.getCustRmBean().getCustRoomId()==0){
           saveNewFlight();
       } else if (this.getCustRmBean().getCustRoomId() != 0){
          (new qckBlock()).setCustRmBeanParm(custRmBean);
          (new customerRmObj(jlrMgr.getEMailKey())).update(custRmBean,jlrMgr.getObj());
          jlrMgr.reservation();
       }
    }
    
    private void saveNewFlightwww(){
        qckBlock qckBlock = new qckBlock();
        //qckBlock.quickBooking(custRmBean, jlrMgr);
        qckBlock.quickBookingDetailSaveNew(custRmBean, flightInfoBean, jlrMgr);
        jlrMgr.reservation();
    }
    
   
    */
      public void editSave(){
          (new qckBlock()).setCustRmBeanParm(custRmBean);
          if (custRmBean.getCustRoomId() > 0){
              (new customerRmObj(jlrMgr.getEMailKey())).update(custRmBean,jlrMgr.getObj());
          } else {
              (new qckBlock()).quickBookingDetailSaveNew(custRmBean, jlrMgr);
          }
          jlrMgr.reservation();
          
          
         
     
    }
      public void editCancel(){
        jlrMgr.reservation();
    }
    public void editBack(){
         jlrMgr.setCenterHtml(
                                eNumPageNav.maintenance_lookupListFlt
                                );
    }
    private void genModel(){
       
        
        flightInfoListModel = new flightInfoListModel((new flightObj(jlrMgr.getEMailKey())).sqlGetVendorFlights(
                jlrMgr.getJlrMgrBean().getVendorId(),
                jlrMgr.getSponsorId(),
                jlrMgr.getObj()));   
    }
    /**
     * @return the flightInfoListModel
     */
    public flightInfoListModel getFlightInfoListModel() {
        if (flightInfoListModel==null) genModel();
        return flightInfoListModel;
    }

    
    public flightInfoBean getFlightInfoBean() {
        if (flightInfoBean == null){
            int i = jlrMgr.getJlrMgrBean().getFlighId();
            if (i == 0){
                flightInfoBean = new flightInfoBean();
            } else{
                flightInfoBean = (new flightObj(jlrMgr.getEMailKey())).getFlightInfoBean(i, jlrMgr.getObj());
            }
        }
        return flightInfoBean;
    }
    public vendorObjTypesENum getVendorObjTypesENum(){
        if (vendorObjTypesENum == null) genENum();
        return vendorObjTypesENum;
    }
    public String getHeader(){
       if (vendorObjTypesENum == null){
           genENum();
       }
       return vendorObjTypesENum.getSubRmDesc();
    }
    private void genENum(){
       switch(jlrMgr.getJlrMgrBean().getVendorType()){
               case -31:
                   vendorObjTypesENum = vendorObjTypesENum.CRUISE;
                   break;
               case -32:
                   vendorObjTypesENum =vendorObjTypesENum.RAILROAD;
                   break;
               default:
                   vendorObjTypesENum = vendorObjTypesENum.AIRLINE;
                   break;
           }
    }
    /**
     * @param flightInfoBean the flightInfoBean to set
     */
    public void setFlightInfoBean(flightInfoBean flightInfoBean) {
        this.flightInfoBean = flightInfoBean;
    }

    /**
     * @return the statusList
     */
   
    public List<lookupBean> getStatusList() {
        if (statusList == null) { 
            statusList =  (new lookupSysObj()).getLookupBeanVector(5, jlrMgr.getObj());
        }
        return statusList;
    }

    
    public custRmBean getCustRmBean() {
        if (custRmBean == null) {
            int i = jlrMgr.getJlrMgrBean().getCustRoomId();
            if ( i == 0){
                jlrMgrBean b = jlrMgr.getJlrMgrBean();
                custRmBean = (new qckBlock()).genCustRmBean(jlrMgr.getJlrMgrBean(), jlrMgr);
               
            } else {
                custRmBean=  (new customerRmObj(jlrMgr.getEMailKey())).getCustomerRoom(i, jlrMgr.getObj());
            }
        }
        return custRmBean;
    }
    
    
}
