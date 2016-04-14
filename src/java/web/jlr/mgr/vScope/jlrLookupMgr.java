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
import jlRoomsCommon.lookup.db.lookupObj;
import jlRoomsCommon.mgrEnum;
import org.primefaces.context.RequestContext;
import web.jlr.mgr.Model.lookupListModel;
import web.jlr.mgr.eNum.eNumPageNav;
import web.jlr.mgr.jlrMgr;
import web.jlr.mgr.jlrMgrEnum;

/**
 *
 * @author Lloyd
 */
@ManagedBean
@ViewScoped
public class jlrLookupMgr implements Serializable{
    private List<lookupBean> flagStatusList;
    private lookupListModel lookupListModel;
    
    //@ManagedProperty("#{lookupBean}")
    private lookupBean lookupBean;
    
    @ManagedProperty("#{jlrMgr}")
    private jlrMgr jlrMgr = null;
    public void setJlrMgr(jlrMgr jlrMgr) {
        this.jlrMgr = jlrMgr;
    }
    public void lookupEditCancel(){
        switch(jlrMgr.getJlrMgrEnum().getAppType()){
            case jlrMgrEnum.reservation_id:
            case jlrMgrEnum.block:
                int i = lookupBean.getLookupRollupId();
                if (i == -12 || i == -31 || i== -32){
                    jlrMgr.setCenterHtml(eNumPageNav.maintenance_lookupListFltBlock);
                } else {
                    jlrMgr.setCenterHtml(eNumPageNav.maintenance_lookupListBlock);
                }
                
                break;
            default:
              jlrMgr.setCenterHtml(eNumPageNav.maintenance_lookupList);
              //jlrMgr.setCenterHtml(eNumPageNav.maintenance_lookupList,jlrMgr.getJlrMgrEnum().getLookupList());
                break;
        }
         
    }
    public void lookupEditSave(){
        lookupObj lookupObj = new lookupObj(jlrMgr.getEMailKey());
        if (lookupBean.getLookupRollupId() == -33 || lookupBean.getLookupRollupId() == -34){
            lookupBean.setVendor_id(jlrMgr.getJlrMgrBean().getVendorId());
        }
        lookupObj.updateLookup(lookupBean,jlrMgr.getObj());
        //(new lookupObj(jlrMgr.getEMailKey())).updateLookup(lookupBean,jlrMgr.getObj());
        lookupEditCancel();
    }
    public lookupListModel getLookupListModel(){
        if (lookupListModel == null)genLookupModel();
            
        return lookupListModel;
    }
    public lookupBean getEditBean() {
        if (lookupBean == null) {
            int i = jlrMgr.getJlrMgrBean().getRoomType();
            if (i == 0) {
                lookupBean = genlookupBean();

            } else {
                lookupBean = (new lookupObj(jlrMgr.getEMailKey())).getLookupBean(i, jlrMgr.getObj());
                if (lookupBean == null) {
                    lookupBean = genlookupBean();
                }
            }
        }
        return lookupBean;
    }
    /*
    
    */
    private lookupBean genlookupBean(){
        lookupBean b = new lookupBean();
        switch (jlrMgr.getJlrMgrEnum().getAppType()) {
            case jlrMgrEnum.block:
                case jlrMgrEnum.reservation_id:
                    b.setLookupRollupId(jlrMgr.getJlrMgrBean().getVendorType());
                    b.setLookupType(mgrEnum.sysVendor_LookupType.getType());
                    
                    break;
            }
        return b;
    }
    public void add(){
         jlrMgr.getJlrMgrBean().setRoomType(0);
         showEditScreen();
         
    }
    public void back() {
        int i = jlrMgr.getJlrMgrBean().getVendorType();
        if (i == -12 || i == -31 || i == -32) {
            jlrMgr.setCenterHtml(eNumPageNav.flt_pg0_viewFiights);
        } else {
            jlrMgr.setCenterHtml(eNumPageNav.vendor_vendorPg1_seek);
        }
    }
    public void edit(){
        if (this.lookupBean ==  null){
            RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
            return;
        }
        jlrMgr.getJlrMgrBean().setRoomType(lookupBean.getLookupId(true));
        showEditScreen();
    }
    private void showEditScreen(){
        switch(jlrMgr.getJlrMgrEnum().getAppType()){
            case jlrMgrEnum.block:
                jlrMgr.setCenterHtml(eNumPageNav.maintenance_lookupEditBlock);
                break;
            default:
              jlrMgr.setCenterHtml(eNumPageNav.maintenance_lookupEdit);
                break;
        }
        
    }
    public void cancel(){
        jlrMgr.reservation();
    }
    public void next(){
        if (this.lookupBean ==  null){
            RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
            return;
        }
        jlrMgr.getJlrMgrBean().setRoomType(lookupBean.getLookupId(true));
        jlrMgr.getJlrMgrBean().setRoomDesc(lookupBean.getDesc());
        jlrMgr.getJlrMgrBean().setBlockId(lookupBean.getBlock_id());
        jlrMgr.getJlrMgrBean().setCost(lookupBean.getRmCost());
        switch (jlrMgr.getJlrMgrEnum().getAppType()) {
            case jlrMgrEnum.block:
                blk();
                    break;
                case jlrMgrEnum.reservation_id:
                    res();
                    break;
        }
    }
    private void blk() {
        switch (lookupBean.getLookupRollupId()) {
            case -12:
            case -31:
            case -32:
                jlrMgr.setCenterHtml(eNumPageNav.blocks_blockVendorUpdateFlt);
                break;
            default:
                jlrMgr.setCenterHtml(eNumPageNav.blocks_blockVendorUpdate);
                break;
        }
    }
    private void res(){
         switch(lookupBean.getLookupRollupId()){
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
                             jlrMgr.setCenterHtml(
                                eNumPageNav.flt_pg1_flightInfoEdit);
                            break;
                        default:
                             jlrMgr.setCenterHtml(
                                eNumPageNav.clientBooking_clientBookingPg04_Detail);
                            break;
                    }
    }
    public void genLookupModel(){
            int i ;
             switch (jlrMgr.getJlrMgrEnum().getAppType()) {
                case jlrMgrEnum.reservation_id:
                    i = jlrMgr.getJlrMgrBean().getVendorType();
                    if (i == -34 || i == -33){
                         lookupListModel = new lookupListModel((
                            new lookupObj(jlrMgr.getEMailKey())).sqlLookupRollupVendorBlock(
                                    jlrMgr.getJlrMgrBean().getVendorType(),
                                    jlrMgr.getJlrMgrBean().getVendorId(),
                                    jlrMgr.getJlrMgrBean().getFlighId(),
                                    jlrMgr.getObj()));;
                    } else {
                        lookupListModel = new lookupListModel((
                            new lookupObj(jlrMgr.getEMailKey())).getLookupRollupListBlock(
                                    jlrMgr.getJlrMgrBean().getVendorType(),
                                    jlrMgr.getJlrMgrBean().getVendorId(),
                                    jlrMgr.getJlrMgrBean().getFlighId(),
                                    jlrMgr.getObj()));;
                    
                    }
                     break;
                case jlrMgrEnum.block:
                    i = jlrMgr.getJlrMgrBean().getVendorType();
                    if (i == -34 || i == -33){
                         lookupListModel = new lookupListModel((
                            new lookupObj(jlrMgr.getEMailKey())).sqlLookupRollupVendor(
                                    jlrMgr.getJlrMgrBean().getVendorType(),
                                    jlrMgr.getJlrMgrBean().getVendorId(),
                                    jlrMgr.getObj()));
                    } else {
                        lookupListModel = new lookupListModel((
                            new lookupObj(jlrMgr.getEMailKey())).getLookupRollupList(jlrMgr.getJlrMgrBean().getVendorType(),jlrMgr.getObj()));
                    
                    }
                   
                    break;
                default:
                    

                    break;
            }
            
            
            if (lookupListModel == null) lookupListModel = new lookupListModel();
        
        
    }
    /**
     * @return the lookupBean
     */
    public lookupBean getLookupBean() {
         //if (this.lookupBean == null) getEditBean();
        return lookupBean;
    }

    /**
     * @param lookupBean the lookupBean to set
     */
    public void setLookupBean(lookupBean lookupBean) {
        this.lookupBean = lookupBean;
    }
    public List<lookupBean> getFlagStatusList() {
        if (flagStatusList == null) {
            flagStatusList = flagStatusList = (new qckBlock()).getFlagStatusList();
        }
        return flagStatusList;
    }
}
