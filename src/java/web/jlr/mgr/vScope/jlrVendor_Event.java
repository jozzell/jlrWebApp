/*

disabled="#{jlBookingMgr_V2.appCustPaymentMethond.DISABLED_BROWSE_EVENT_NEXT}"




 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.vScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import jlRoomsCommon._beans.sponsorBean;
import jlRoomsCommon._beans.vendorBean;
import jlRoomsCommon.sponsor.db.sponsorObj;
import jlRoomsCommon.vendor.db.vendorObj;
import org.primefaces.context.RequestContext;
import web.jlr.mgr.Model.sponsorListModel;
import web.jlr.mgr.Model.vendorListModel;
import web.jlr.mgr.eNum.eNumPageNav;
import web.jlr.mgr.jlrMgr;

/**
 *
 * @author Lloyd
 */
@ManagedBean
@ViewScoped
public class jlrVendor_Event extends jlrVendor_Event_ implements Serializable {
    private vendorListModel vendorListModel;
    private String pass,chkPass;
    private sponsorBean sponsorBean;
    private vendorBean vendorBean;
    private sponsorListModel sponsorListModel;
    private String seek;
    @ManagedProperty("#{jlrMgr}")
    private jlrMgr jlrMgr = null;
    public void setJlrMgr(jlrMgr jlrMgr) {
        this.jlrMgr = jlrMgr;
    }
    // ============================================================
    public void password(){
        jlrMgr.setCenterHtml(eNumPageNav.password);
    }
    public void passwordCancel(){
        jlrMgr.setCenterHtml(eNumPageNav.eventMgrEvent);
    }
    public void passwordSave(){
        if (pass != null) pass = pass.trim().toLowerCase();
        if (chkPass != null) chkPass = chkPass.trim().toLowerCase();
         if(pass == null || pass.trim().length() == 0 || chkPass== null || chkPass.trim().length() == 0 || !pass.endsWith(chkPass)) {
             RequestContext.getCurrentInstance().execute("PF('dialogWidgetbasicDialogBlockMsg').show()");
          
         } else {
             jlrMgr.updatePassword(pass);
         }
        
    }
    // ============================================================
    public void editCompanyInfo(){
        jlrMgr.setCenterHtml(eNumPageNav.company_info_Edit);
    }
    public void eventListNext(){
        if(sponsorBean == null){
             RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
        } else {
            jlrMgr.setSponsor(sponsorBean.getSponsorId());
        }
    }
    public void eventNew(){
        jlrMgr.setSeek("Search....");
        jlrMgr.setSponsor();
        jlrMgr.setCenterHtml(eNumPageNav.eventNew);
    }
    // ============================================================
    public void saveVendorBean(){
        if (vendorBean == null || vendorBean.getVendorName() == null || vendorBean.getVendorName().trim().length() == 0){
             RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
        } else {
            (new vendorObj(jlrMgr.getEMailKey())).updateVendor(vendorBean, jlrMgr.getObj());
            jlrMgr.getJlrMgrBean().setSeek(vendorBean.getVendorName());
            jlrMgr.setCenterHtml(eNumPageNav.eventVendor_Pg2);
        }
    }
    public void cancelVendorBean(){
        jlrMgr.setCenterHtml(eNumPageNav.eventVendor_Pg1);
    }
    public vendorListModel grabCurrentVendor() {
        if (vendorListModel == null){
            vendorBean b = (new vendorObj(jlrMgr.getEMailKey())).getVendorBean(jlrMgr.getSponsorBean().getVendorId(), jlrMgr.getObj());
            List<vendorBean> ary = new ArrayList<vendorBean>();
            ary.add(b);
            vendorListModel = new vendorListModel(ary);
        }
        
        return vendorListModel;
    }
    public vendorBean getEditVendor(){
        if(vendorBean == null){
            int i = jlrMgr.getVendorId();
            if (i != 0){
                
                vendorBean = (new vendorObj(jlrMgr.getEMailKey())).getVendorBean(i, jlrMgr.getObj());
            }
            
        }
        if (vendorBean == null) {
            vendorBean = new vendorBean();
            vendorBean.setVendorType(-3);
        }
        return vendorBean;
        
    }
    public void editVendor(){
        if (vendorBean == null || vendorBean.getVendorId() == 0){
            RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
            
        } else {
            jlrMgr.setVendorId(vendorBean.getVendorId());
            jlrMgr.setCenterHtml(eNumPageNav.eventVendor_edit);
        }
    }
    public void newVendor(){
        jlrMgr.setVendorId(0);
        jlrMgr.setCenterHtml(eNumPageNav.eventVendor_edit);
    }
   
    public void pickSelect(){
        if (vendorBean == null || vendorBean.getVendorId() == 0){
            RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
            
        } else {
            jlrMgr.saveSponsorBean(vendorBean.getVendorId());
        }
    }
    public void seekSet(){
        jlrMgr.getJlrMgrBean().setSeek(seek);
        jlrMgr.setCenterHtml(eNumPageNav.eventVendor_Pg2);
        //if (this.seek == null) seek = "";
        //vendorListModel = new  vendorListModel((new vendorObj(jlrMgr.getEMailKey())).getVendorBean(seek, jlrMgr.getObj()));
    }
    public vendorListModel getSeekVendorListModel(){
        if (vendorListModel == null){
            seek= jlrMgr.getJlrMgrBean().getSeek();
            if (seek == null) seek = "";
            vendorListModel = new  vendorListModel((new vendorObj(jlrMgr.getEMailKey())).getVendorBean(seek, jlrMgr.getObj()));
        }
        return vendorListModel;
    }
public sponsorListModel getSponsorListModel(){
    
         if (sponsorListModel == null){
         
         
         sponsorListModel= new sponsorListModel( (new sponsorObj(jlrMgr.getEMailKey())).genEventList(jlrMgr.getSiteId(),jlrMgr.isDemo() ,jlrMgr.getObj()));
         }
         if (sponsorListModel == null) sponsorListModel= new sponsorListModel();
         
      return sponsorListModel;
     }
    /**
     * @return the vendorListModel
     */
    

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

    /**
     * @return the vendorBean
     */
    public vendorBean getVendorBean() {
        if(vendorBean == null) vendorBean = new vendorBean();
        return vendorBean;
    }

    /**
     * @param vendorBean the vendorBean to set
     */
    public void setVendorBean(vendorBean vendorBean) {
        this.vendorBean = vendorBean;
    }

    /**
     * @return the vendorListModel
     */
    public vendorListModel getVendorListModel() {
        return vendorListModel;
    }

   

    /**
     * @return the sponsorBean
     */
    public sponsorBean getSponsorBean() {
        return sponsorBean;
    }

    /**
     * @param sponsorBean the sponsorBean to set
     */
    public void setSponsorBean(sponsorBean sponsorBean) {
        this.sponsorBean = sponsorBean;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the chkPass
     */
    public String getChkPass() {
        return chkPass;
    }

    /**
     * @param chkPass the chkPass to set
     */
    public void setChkPass(String chkPass) {
        this.chkPass = chkPass;
    }
    
}
