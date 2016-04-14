/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.vScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import jlRoomsCommon._beans.custBean;
import jlRoomsCommon._beans.custRmBean;
import jlRoomsCommon._beans.lookupBean;
import jlRoomsCommon.customerRoom.db.customerRmObj;
import jlRoomsCommon.lookup_sys.db.lookupSysObj;
import org.primefaces.context.RequestContext;
import web.jlr.mgr.Model.custRmListModel;
import web.jlr.mgr.eNum.eNumPageNav;
import web.jlr.mgr.jlrMgr;

/**
 *
 * @author Lloyd
 */
@ManagedBean
@ViewScoped
public class jlrMgrClientBooking  implements Serializable{
    private List<lookupBean> splitRate;
    private List<lookupBean> statusList;
    private custBean custBean;
    private custRmListModel custRmListModel;
    private custRmBean custRmBean;
    @ManagedProperty("#{jlrMgr}")
    private jlrMgr jlrMgr = null;
    public void setJlrMgr(jlrMgr jlrMgr) {
        this.jlrMgr = jlrMgr;
    }
    public void done(){
        jlrMgr.clientOptions();
    }
    public void editBookRes(){
        if (this.custRmBean ==  null){
            RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
            return;
        }
        jlrMgr.getJlrMgrBean().setCustRoomId(custRmBean.getCustRoomId());
        jlrMgr.getJlrMgrBean().setFlighId(custRmBean.getFltId());
        
        jlrMgr.getJlrMgrBean().setVendorType(custRmBean.getVendorType());
        jlrMgr.getJlrMgrBean().setVendorId(custRmBean.getVendorId());
       
        jlrMgr.getJlrMgrBean().setRoomType(custRmBean.getLoginId());
       
        switch(custRmBean.getVendorType()){
            case -34:
                            jlrMgr.setCenterHtml(
                                eNumPageNav.clientBooking_clientBookingPg04_items);
                            break;
                        case -33:
                             jlrMgr.setCenterHtml(
                                eNumPageNav.clientBooking_clientBookingPg04_ticket);
                            break;
            case -12:
            case -31:
            case -32:
                jlrMgr.setCenterHtml(eNumPageNav.flt_pg1_flightInfoEdit);
                break;
            default:
                jlrMgr.setCenterHtml(
                            eNumPageNav.clientBooking_clientBookingPg04_Detail);
                break;
        }
        
    }
    public void quickBookingDetailBack(){
        jlrMgr.roomType();
    }
    public void quickBookingDetailSave(){
        if (custRmBean.getEffDate() == null) custRmBean.setEffDate(Calendar.getInstance().getTime());
        //if (this.custRmBean.getCustRoomId() == 0) {
            (new qckBlock()).quickBookingDetailSaveNew(custRmBean, jlrMgr);
        //} else {
       //     (new customerRmObj(jlrMgr.getEMailKey())).update(custRmBean,jlrMgr.getObj());
       // }
        jlrMgr.reservation();
    }
    public void quickBookingDetailCancel(){
        jlrMgr.reservation();
    }
    public void addBookRes(){
      jlrMgr.getJlrMgrBean().setCustRoomId(0);
      jlrMgr.setCenterHtml(eNumPageNav.vendor_vendorPg0_type);
    }
    
    private void genCustRmListModel(){
        custRmListModel = new custRmListModel(
                ( new customerRmObj(jlrMgr.getEMailKey())).custRmBooking(
                jlrMgr.getClientBean().getCustId(),
                jlrMgr.getSponsorId(), 
                jlrMgr.getObj()));
    }
    public custRmListModel getCustRmListModel() {
        if (this.custRmListModel == null)  genCustRmListModel();
        return custRmListModel;
    }
    public custRmBean getCustRmBeanEdit(){
        if (custRmBean == null){
            int i = jlrMgr.getJlrMgrBean().getCustRoomId();
            if (i == 0){
                 setCustRm();
            } else {
                 custRmBean = (new customerRmObj(jlrMgr.getEMailKey())).getCustomerRoom(i, jlrMgr.getObj());
                 if (custRmBean == null) setCustRm();
            }
        }
        return custRmBean;
    }
    private void setCustRm(){
        custRmBean = (new qckBlock()).genCustRmBean(jlrMgr.getJlrMgrBean(), jlrMgr);
        //(new qckBlock()).quickBooking(custRmBean, jlrMgr);
        
        
    }
        
    /**
     * @return the custRmBean
     */
    public custRmBean getCustRmBean() {
        if (custRmBean == null) custRmBean = (new qckBlock()).genCustRmBean(jlrMgr.getJlrMgrBean(), jlrMgr);
        return custRmBean;
    }

    /**
     * @param custRmBean the custRmBean to set
     */
    public void setCustRmBean(custRmBean custRmBean) {
        this.custRmBean = custRmBean;
    }
    public List<lookupBean> getStatusList() {
        if (statusList == null) { 
            statusList =  (new lookupSysObj()).getLookupBeanVector(5, jlrMgr.getObj());
        }
        return statusList;
    }

    /**
     * @return the custBean
     */
    public custBean getCustBean() {
        if (custBean ==  null){
            custBean = jlrMgr.getClientBean();
            
        }
        return custBean;
    }

    /**
     * @param custBean the custBean to set
     */
    public void setCustBean(custBean custBean) {
        this.custBean = custBean;
    }
    public List<lookupBean> getSpliteRate() {
        if (getSplitRate() == null) {
            setSplitRate(new ArrayList<lookupBean>());
            getSplitRate().add(new lookupBean("No Split", 1));
            getSplitRate().add(new lookupBean("Split Cost (1/2)", 2));
            getSplitRate().add(new lookupBean("Split Cost (1/3)", 3));
            getSplitRate().add(new lookupBean("Split Cost (1/4)", 4));
        }
        return getSplitRate();
    }
    public List<lookupBean> getSplitRate() {
        return splitRate;
    }

    /**
     * @param splitRate the splitRate to set
     */
    public void setSplitRate(List<lookupBean> splitRate) {
        this.splitRate = splitRate;
    }
    
}
