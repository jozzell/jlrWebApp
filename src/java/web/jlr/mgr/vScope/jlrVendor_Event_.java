/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.vScope;

import java.util.ArrayList;
import java.util.List;
import jlRoomsCommon._beans.lookupBean;
import jlRoomsCommon.vendorObjTypesENum;

/**
 *
 * @author lmeans
 */
public class jlrVendor_Event_ {
    private List<lookupBean> list;
    
public void genVendorList(){
    lookupBean l = new lookupBean();
       l.setLookupType(-3);
       l.setLookupId(-3);
       l.setDesc("Event Sponsor");
       list = new ArrayList<lookupBean>();
        getList().add(l);
        gen(vendorObjTypesENum.HOTEL);
        gen(vendorObjTypesENum.CARRENTAL);
        gen(vendorObjTypesENum.AIRLINE);
        gen(vendorObjTypesENum.TICKETS);
        gen(vendorObjTypesENum.merchandise);
        gen(vendorObjTypesENum.RAILROAD);
        gen(vendorObjTypesENum.CRUISE);
    }
public void gen(vendorObjTypesENum x){
       lookupBean l = new lookupBean();
       l.setLookupType(x.getType());
       l.setLookupId(x.getType());
       l.setDesc(x.getRoomDesc());
       if (getList() == null) list = new ArrayList();
        getList().add(l);
    }
 /**
     * @return the list
     */
    public List<lookupBean> getList() {
        if (list == null) genVendorList();
        return list;
    }
}
