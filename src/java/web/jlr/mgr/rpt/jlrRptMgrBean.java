/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.rpt;

import java.io.Serializable;
import java.util.List;
import jlRoomsCommon._beans.rptItineraryBean;
import jlRoomsCommon.vendorObjTypesENum;

/**
 *
 * @author Lloyd
 */
public class jlrRptMgrBean  implements Serializable{
    
    private final vendorObjTypesENum vendorObjTypesENum;
    private final List<rptItineraryBean> list;
    public jlrRptMgrBean(List<rptItineraryBean> list,vendorObjTypesENum eNum) {
        this.list = list;
        vendorObjTypesENum = eNum;
        
    }
    public String renderTable(){
        if (list == null || list.isEmpty()) return "false";
        return "true";
    }
    /**
     * @return the jsfRptBeanBook
     */
    public List<rptItineraryBean> getJsfRptBeanBook() {
        return list;
    }
   
    /**
     * @return the vendorObjTypesENum
     */
    public vendorObjTypesENum getVendorObjTypesENum() {
        return vendorObjTypesENum;
    }
}
