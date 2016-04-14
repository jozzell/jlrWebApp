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
import jlRoomsCommon._beans.lookupBean;
import jlRoomsCommon._beans.sponsorHotelBean;
import jlRoomsCommon.sponsorHotel.db.sponsorHotelObj;
import org.primefaces.context.RequestContext;
import web.jlr.mgr.Model.sponsorHotelListModel;
import web.jlr.mgr.eNum.eNumPageNav;
import web.jlr.mgr.jlrMgr;

/**
 *
 * @author Lloyd
 */
@ManagedBean
@ViewScoped
public class jlrSponsorHotel implements Serializable{
    private List<lookupBean> flagStatusList;
    private sponsorHotelBean sponsorHotelBean;
     private sponsorHotelListModel sponsorHotelListModel;
    @ManagedProperty("#{jlrMgr}")
    private jlrMgr jlrMgr = null;
    public void setJlrMgr(jlrMgr jlrMgr) {
        this.jlrMgr = jlrMgr;
    }
    public void editSponsorHotel(){
       if (! ok()) return;
       jlrMgr.setCenterHtml(eNumPageNav.sponsorHotel_SponsorHotelV2Pg02_edit);
               
      
    }
    private boolean ok(){
         if (this.sponsorHotelBean == null){
             RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
             return false;
         } else {
             RequestContext.getCurrentInstance().execute("PF('dialogWidget').hide()");
             jlrMgr.setSponsorHotelId(this.sponsorHotelBean.getSponsor_hotel_id());
             return true;
         }
    }
     public void vendorRoommate(){
        if (sponsorHotelBean == null){
            RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
            
        } else {
            set();
            jlrMgr.setCenterHtml(eNumPageNav.RptRoommate_Vendor);
        }
    }
      public void rptBookingRpt(){
        if (sponsorHotelBean == null){
            RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
            
        } else {
            set();
            jlrMgr.setCenterHtml(eNumPageNav.RptClientVendor);
        }
    }
      private void set(){
          jlrMgr.setJlrDisp(sponsorHotelBean.getVendorName());
          jlrMgr.setVendor(sponsorHotelBean.getVendorName()+" "+this.sponsorHotelBean.getVendorTypeDesc(), sponsorHotelBean.getVendor_id());
      }
     public void vendorPayment(){
        if (sponsorHotelBean == null){
            RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
            
        } else {
            set();
            jlrMgr.setCenterHtml(eNumPageNav.RptVendorPayment);
        }
    }
    public void paymentSponsorHotel(){
         if (this.sponsorHotelBean == null){
            RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
            return;
        }
        set();
    
    }
   
    
    public void rptBookingRpt1StNight(){
        if (sponsorHotelBean == null){
            RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
            
        } else {
            set();
            jlrMgr.setCenterHtml(eNumPageNav.Rpt1stNight);
        }
        //
    }
    public sponsorHotelListModel getSponsorHotelListModel() {
        if (sponsorHotelListModel == null) genSponsorHotelList();
        return sponsorHotelListModel;
     }
    public void sponsorHotelEditCancel(){
        jlrMgr.rootSponsorHotel();
    }
    public void sponsorHotelEditSave(){
        (new sponsorHotelObj(jlrMgr.getEMailKey())).update(sponsorHotelBean, jlrMgr.getObj());
        sponsorHotelEditCancel();
    }
    public void genSponsorHotelList(){
         //iface.getRptMgr_V2().genCalendarAll();
         sponsorHotelListModel =  new sponsorHotelListModel((new sponsorHotelObj(jlrMgr.getEMailKey())).getSponsorHotelVectorAll(
                 jlrMgr.getSponsorId(), 
                 jlrMgr.getObj()));
         
     }

    /**
     * @return the sponsorHotelBean
     */
    public sponsorHotelBean getSponsorHotelBean() {
        if (sponsorHotelBean == null && jlrMgr.getSponsorHotelId() > 0){
            sponsorHotelBean = (new sponsorHotelObj(jlrMgr.getEMailKey())).getSpoinsorHotelBean(jlrMgr.getSponsorHotelId(), jlrMgr.getObj());
        }
        if (sponsorHotelBean == null){
            sponsorHotelBean = new sponsorHotelBean();
            
        }
        return sponsorHotelBean;
    }

    /**
     * @param sponsorHotelBean the sponsorHotelBean to set
     */
    public void setSponsorHotelBean(sponsorHotelBean sponsorHotelBean) {
        this.sponsorHotelBean = sponsorHotelBean;
    }
    public List<lookupBean> getFlagStatusList() {
        if (flagStatusList == null) {
            flagStatusList = flagStatusList = (new qckBlock()).getFlagStatusList();
        }
        return flagStatusList;
    }
}
