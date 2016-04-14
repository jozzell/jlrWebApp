/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.vScope;

import java.util.ArrayList;
import java.util.List;
import jlRoomsCommon._beans.blockBean;
import jlRoomsCommon._beans.custRmBean;
import jlRoomsCommon._beans.flightInfoBean;
import jlRoomsCommon._beans.lookupBean;
import jlRoomsCommon._beans.sponsorHotelBean;
import jlRoomsCommon.block.db.blockObj;
import jlRoomsCommon.customerRoom.db.customerRmObj;
import jlRoomsCommon.flights.db.flightObj;
import jlRoomsCommon.sponsorHotel.db.sponsorHotelObj;
import jlRoomsCommon.JlRoomsDataObjects;
import jlRoomsCommon.vendorObjTypesENum;
import web.jlr.mgr.jlrMgr;
import web.jlr.mgr.jlrMgrBean;

/**
 *
 * @author Lloyd
 */
public class qckBlock {
    public void genBlock(){
        
    }
    public List<lookupBean> getFlagStatusList() {
       
           List<lookupBean>  flagStatusList = new ArrayList<lookupBean>();
            flagStatusList.add(new lookupBean("Active", 0));
            flagStatusList.add(new lookupBean("Non-Active", 1));
       
        return flagStatusList;
    }
    public custRmBean genCustRmBean(jlrMgrBean i, jlrMgr jlrMgr) {
        custRmBean b = new custRmBean();
        double amt = vendorObjTypesENum.DEFAULT.getENUM(i.getVendorType()).getProcFee(jlrMgr.getSponsorBean());
        b.setSponsor(jlrMgr.getSponsorId());
        b.setLookupId(i.getRoomType());
        b.setVendorId(i.getVendorId());
        b.setVendorType(i.getVendorType());
        b.setCustId(i.getClientId());
        b.setCustRoomType(-6);
        b.setBlockHotelFee(amt);
        b.setFltId(i.getFlighId());
        b.setBlockId(i.getBlockId());
        b.setSponsorHotelId(i.getSponsorHotelId());
        b.setLookupDesc(i.getRoomDesc());
        b.setVendor(i.getVendorDesc());
        b.setFullName(i.getClientName());
        b.setRmCost(i.getCost());
        if (i.getBlockId() > 0 && i.getFlighId() != 0) {
            blockBean bean = (new blockObj(jlrMgr.getEMailKey())).getBlock(i.getBlockId(), jlrMgr.getObj());
            if (bean != null) {
                b.setEffDate(bean.getEffDate());
                b.setEndDate(bean.getEndDate());
                b.setRmCost(bean.getRmCost());
                b.setBlockHotelFee(bean.getHotelFee() != 0 ? bean.getHotelFee() : amt);
                b.setBlockProcessFee(bean.getNoFeeAppyied() == 1 ? .0001 : bean.getProcessFee());
                b.setBlockServiceCharge(bean.getServiceCharge());
            }
        }
       
        return b;
    }
    public void quickBooking111(custRmBean custRmBean,jlrMgr jlrMgr){
        
        sponsorHotelBean sponsorHotelBean = genSponsorHotelBean(
                jlrMgr.getSponsorId(),
                custRmBean.getVendorId(), 
                custRmBean.getVendorType(),jlrMgr);
        blockBean blockBean = (new blockObj(jlrMgr.getEMailKey())).grabBlock( 
                jlrMgr.getSponsorId(), 
                custRmBean.getLookupId(), 
                sponsorHotelBean.getSponsor_hotel_id(), 
                jlrMgr.getObj());
        custRmBean.setSponsorHotelId(sponsorHotelBean.getSponsor_hotel_id());
        if (blockBean !=null){
            custRmBean.setRmCost(blockBean.getRmCost());
            custRmBean.setFltId(blockBean.getFltId());
            custRmBean.setBlockId(blockBean.getBlockId());
        }
        
    }
    public void quickBookingDetailSaveNew(custRmBean custRmBean,jlrMgr jlrMgr){
        setCustRmBeanParm(custRmBean);
        if (custRmBean.getSponsorHotelId() == 0){
            sponsorHotelBean sponsorHotelBean = genSponsorHotelBean(
                jlrMgr.getSponsorId(),
                custRmBean.getVendorId(), 
                custRmBean.getVendorType(),jlrMgr);
            custRmBean.setSponsorHotelId(sponsorHotelBean.getSponsor_hotel_id());
        }
        
        if (custRmBean.getBlockId() <= 0){
            blockBean blockBean = new blockBean();
            blockBean.setSponsorHotelId(custRmBean.getSponsorHotelId());
            blockBean.setLookupId(custRmBean.getLookupId());
            blockBean.setSponsor(jlrMgr.getSponsorId());
            blockBean.setRmCost(custRmBean.getRmCost());
            blockBean.setFltId(custRmBean.getFltId());
            
            if (custRmBean.getFltId() != 0){
                blockBean.setFltDispStr(jlrMgr.getJlrMgrBean().getDst());
                blockBean.setFltNumber(jlrMgr.getJlrMgrBean().getFlightNumber());
            }
            custRmBean.setBlockId((new blockObj(jlrMgr.getEMailKey())).updateBlock(blockBean,jlrMgr.getObj()));
           
        }
        (new customerRmObj(jlrMgr.getEMailKey())).update(custRmBean,jlrMgr.getObj());
        
        
    }
    public  void setCustRmBeanParm(custRmBean custRmBean) {
        if (custRmBean.getCustRoomType() == -5 || custRmBean.getCustRoomType() == -6) {
            int dy = 1;
            if (custRmBean.getEffDate() != null && custRmBean.getEndDate() != null) {
                dy = (new JlRoomsDataObjects()).daysBetween(custRmBean.getEffDate(), custRmBean.getEndDate());
                if (dy <= 0) {
                    dy = 1;
                }
            }

            custRmBean.setDays(dy);
            if (custRmBean.getVendorType() == vendorObjTypesENum.HOTEL.getType() 
                    || custRmBean.getVendorType() == vendorObjTypesENum.CARRENTAL.getType()){
                 custRmBean.setBlockCostCnt((custRmBean.getCustRoomCnt() * dy) / custRmBean.getRoommateSplit());
                    custRmBean.setTotalCost((custRmBean.getRmCost() * custRmBean.getCustRoomCnt() * dy) / custRmBean.getRoommateSplit());
            } else {
                custRmBean.setBlockCostCnt(custRmBean.getCustRoomCnt() / custRmBean.getRoommateSplit());
                custRmBean.setTotalCost((custRmBean.getRmCost() * custRmBean.getCustRoomCnt()) / custRmBean.getRoommateSplit());
            }
            
            
           
        } else {
            custRmBean.setCustRoomCnt(0);
            custRmBean.setDays(0);
            custRmBean.setBlockCostCnt(0);
            custRmBean.setTotalCost(0);
        }

    }
    public sponsorHotelBean genSponsorHotelBean(int sponsor_id,int vendor_id,int vendor_type,jlrMgr jlrMgr){
        sponsorHotelBean sponsorHotelBean = (new sponsorHotelObj(jlrMgr.getEMailKey())).getSponsorHotelVendorBean(vendor_id, sponsor_id, jlrMgr.getObj());
        if (sponsorHotelBean == null || sponsorHotelBean.getSponsor_hotel_id() == 0){
            sponsorHotelObj sponsorHotelObj = new sponsorHotelObj(jlrMgr.getEMailKey());
            sponsorHotelBean = new sponsorHotelBean();
            sponsorHotelBean.setSponsor_id(sponsor_id);
            sponsorHotelBean.setVendor_id(vendor_id);
            sponsorHotelBean.setVendorType(vendor_type);
            sponsorHotelObj.update(sponsorHotelBean, jlrMgr.getObj());
            sponsorHotelBean = sponsorHotelObj.getSponsorHotelVendorBean(vendor_id, sponsor_id, jlrMgr.getObj());
        }
        return sponsorHotelBean;
    }
}
