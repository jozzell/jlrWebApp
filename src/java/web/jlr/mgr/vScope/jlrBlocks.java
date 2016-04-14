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
import jlRoomsCommon._beans.blockBean;
import jlRoomsCommon._beans.flightInfoBean;
import jlRoomsCommon._beans.lookupBean;
import jlRoomsCommon._beans.sponsorHotelBean;
import jlRoomsCommon.block.db.blockObj;
import jlRoomsCommon.flights.db.flightObj;
import jlRoomsCommon.lookup_sys.db.lookupSysObj;
import jlRoomsCommon.vendorObjTypesENum;
import org.primefaces.context.RequestContext;
import web.jlr.mgr.Model.blockListModel;
import web.jlr.mgr.eNum.eNumPageNav;
import web.jlr.mgr.jlrMgr;
import web.jlr.mgr.jlrMgrBean;

/**
 *
 * @author Lloyd
 */
@ManagedBean
@ViewScoped
public class jlrBlocks implements Serializable{
    private vendorObjTypesENum eNum ;
    private List<lookupBean> statusList;
    private List<lookupBean> flagStatusList;
    private blockBean blockBean;
    private flightInfoBean flightInfoBean;
    private blockListModel  blockListModel;
    @ManagedProperty("#{jlrMgr}")
    private jlrMgr jlrMgr = null;
    public void setJlrMgr(jlrMgr jlrMgr) {
        this.jlrMgr = jlrMgr;
    }
    

    public void addNewBlock(){
        jlrMgr.setBlockId(0);
        jlrMgr.setFltId(0);
        jlrMgr.setCenterHtml(eNumPageNav.vendor_vendorPg0_typeBlock); 
        //jlrMgr.setCenterBlockHtml("blocks/blockVendorUpdate.xhtml");
        
    }
    public void editBlock(){
        if (this.blockBean == null){
            RequestContext.getCurrentInstance().execute("PF('dialogWidgetBlockEdit').show()");
           
        }else {
            jlrMgr.setBlockId(blockBean.getBlockId());
            jlrMgr.setFltId(blockBean.getFltId());
            if (blockBean.getFltId() > 0){
                jlrMgr.setCenterHtml(eNumPageNav.blocks_blockVendorUpdateFlt);
            } else {
                jlrMgr.setCenterHtml(eNumPageNav.blocks_blockVendorUpdate);
            }
            
        }
    }
    public void blockRpt(){
        jlrMgr.setCenterHtml(eNumPageNav.RptBlock);
    }
    public void newBlockEditCancel(){
         jlrMgr.setCenterHtml(eNumPageNav.blocks_BlockV2Pg00);
    }
    public void saveBlock() {
        int fltID = 0;
        if (blockBean.getBlockId() <= 0) {
            sponsorHotelBean s = (new qckBlock()).genSponsorHotelBean(jlrMgr.getSponsorId(), blockBean.getVendorId(),  blockBean.getVendorType(), jlrMgr);
            if (this.flightInfoBean != null) {
                if (flightInfoBean.getFltId() == 0) {
                    flightInfoBean.setSponsorHotelId(s.getSponsor_hotel_id());
                    flightInfoBean.setSponsorId(jlrMgr.getSponsorId());
                    flightInfoBean.setVendorId(blockBean.getVendorId());
                    flightInfoBean.setFltType(blockBean.getVendorType());
                    flightInfoBean.setHotelId(s.getSponsor_hotel_id());
                } 
            }
            blockBean.setSponsorHotelId(s.getSponsor_hotel_id());
        }
        if (flightInfoBean != null) {
            fltID = new flightObj(jlrMgr.getEMailKey()).updateFlight(flightInfoBean, jlrMgr.getObj());
            blockBean.setFltId(fltID);
        }
        (new blockObj(jlrMgr.getEMailKey())).updateBlock(blockBean, jlrMgr.getObj());
        newBlockEditCancel();
    }
    private void genModel(){
        
        blockListModel = new blockListModel((new blockObj(jlrMgr.getEMailKey())).getBlockEvents(jlrMgr.getSponsorId(),jlrMgr.getObj()));
        if (blockListModel == null) blockListModel = new blockListModel();
    }
    /**
     * @return the blockBean
     */
    public blockBean getBlockBean() {
        if (blockBean == null && jlrMgr.getBlockId() != 0){
            blockBean = (new blockObj(jlrMgr.getEMailKey())).getBlock(jlrMgr.getBlockId(), jlrMgr.getObj());
        }
        if (blockBean == null){
            jlrMgrBean i = jlrMgr.getJlrMgrBean();
            blockBean = new blockBean();
            blockBean.setSponsor(jlrMgr.getSponsorId());
            blockBean.setLookupId(i.getRoomType());
            blockBean.setLookupDesc(i.getRoomDesc());
            blockBean.setVendorId(i.getVendorId());
            blockBean.setVendor(i.getVendorDesc());
            blockBean.setVendorType(i.getVendorType());
        } 
        return blockBean;
    }

    /**
     * @param blockBean the blockBean to set
     */
    public void setBlockBean(blockBean blockBean) {
        this.blockBean = blockBean;
    }

    /**
     * @return the blockListModel
     */
    public blockListModel getBlockListModel() {
        if(this.blockListModel == null)genModel();
        return blockListModel;
    }
    public List<lookupBean> getStatusList() {
        if (statusList == null) { 
            statusList =  (new lookupSysObj()).getLookupBeanVector(5, jlrMgr.getObj());
        }
        return statusList;
    }

    /**
     * @return the vendorObjTypesENum
     */
    public vendorObjTypesENum getVendorObjTypesENum() {
        if (eNum == null){
            vendorObjTypesENum x = vendorObjTypesENum.DEFAULT;
            eNum = x.getENUM(this.getBlockBean().getVendorType());
        }
        return eNum;
    }
     public List<lookupBean> getFlagStatusList() {
        if (flagStatusList == null) {
            flagStatusList = (new qckBlock()).getFlagStatusList();
           
        }
        return flagStatusList;
    }

    /**
     * @return the flightInfoBean
     */
    public flightInfoBean getFlightInfoBean() {
        if (flightInfoBean == null && jlrMgr.getFltId() > 0){
            flightInfoBean = (new flightObj(jlrMgr.getEMailKey())).getFlightInfoBean(jlrMgr.getFltId(), jlrMgr.getObj());
        }
        if (flightInfoBean == null) flightInfoBean = new flightInfoBean();
        return flightInfoBean;
    }

    /**
     * @param flightInfoBean the flightInfoBean to set
     */
    public void setFlightInfoBean(flightInfoBean flightInfoBean) {
        this.flightInfoBean = flightInfoBean;
    }
}
