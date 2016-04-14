/*
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
import jlRoomsCommon._beans.custPayBean;
import jlRoomsCommon._beans.custPaymentBean;
import jlRoomsCommon._beans.lookupBean;
import jlRoomsCommon.customerPayment.db.customerPaymentObj;
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
public class jlrMgrClientPayment  implements Serializable {
    private List<lookupBean> flagStatusList;
    
    
    @ManagedProperty("#{jlrMgr}")
    private jlrMgr jlrMgr = null;
    public void setJlrMgr(jlrMgr jlrMgr) {
        this.jlrMgr = jlrMgr;
    }
    private custPayBean custPayBean;
    List<lookupBean>
            catList,methodList,typeList;
    lookupListModel
            catagoryLookupListModel,
            methodLookupListModel,
            typeLookupListModel;
    private lookupBean methodBean;
    private lookupBean typeBean;
    private lookupBean catogoryBean;
    private lookupBean lookupBean;
    
    private boolean isClient(){
        
        return jlrMgr.getJlrMgrEnum().getAppType() == jlrMgrEnum.reservation_id;
    }
    public  List<lookupBean> getMethodLookupList(){
        if (methodList == null){
            methodList = (new lookupObj(jlrMgr.getEMailKey())).getLookupRollupList(-17,jlrMgr.getObj());
        }
        return methodList;
    }
    public lookupListModel getMethodLookupListModel(){
       if (methodLookupListModel == null){
           methodLookupListModel = new lookupListModel(getMethodLookupList());
       }
       return methodLookupListModel;
    }
    public  List<lookupBean> getCatagoryLookupList(){
        if (catList == null){
            catList = (new lookupObj(jlrMgr.getEMailKey())).getLookupTypeRollupList(
                            jlrMgr.getJlrMgrClientPaymentBean().getClientType(), 
                            jlrMgr.getJlrMgrClientPaymentBean().getTypeId(),
                            jlrMgr.getObj());
        }
        return catList;
    }
    public lookupListModel getCatagoryLookupListModel(){
        if (catagoryLookupListModel == null){
            catagoryLookupListModel = new lookupListModel(getCatagoryLookupList() );
        }
        return catagoryLookupListModel;
    }
    public lookupListModel getPaymentTypeModel() {
        if (typeLookupListModel == null) {
            if (jlrMgr.getJlrMgrClientPaymentBean().isClient()){
                setPaymentTypeListClient(jlrMgr.getClientBean().getDisplayFullName());
            } else {
                setPaymentTypeListVendor(jlrMgr.getVendorDesc());
            }
        }
        return typeLookupListModel;
    }

   

    /**
     * @return the methodBean
     */
    public lookupBean getMethodBean() {
        return methodBean;
    }

    /**
     * @param methodBean the methodBean to set
     */
    public void setMethodBean(lookupBean methodBean) {
        this.methodBean = methodBean;
    }

    /**
     * @return the typeBean
     */
    public lookupBean getTypeBean() {
        return typeBean;
    }

    /**
     * @param typeBean the typeBean to set
     */
    public void setTypeBean(lookupBean typeBean) {
        this.typeBean = typeBean;
    }

    /**
     * @return the catogoryBean
     */
    public lookupBean getCatogoryBean() {
        return catogoryBean;
    }

    /**
     * @param catogoryBean the catogoryBean to set
     */
    public void setCatogoryBean(lookupBean catogoryBean) {
        this.catogoryBean = catogoryBean;
    }
    private void setPaymentTypeListClient(String who){
        List<lookupBean> list = new ArrayList<lookupBean>();
        list.add(new lookupBean("Payment Received From '" + who + "'", mgrEnum.SYS_PAYMENT.getType(), "Payment"));
        list.add(new lookupBean("Apply $(Fee) to " + who + " Account", mgrEnum.SYS_PROC_FEE.getType() * -1, "Fee"));
        list.add(new lookupBean("Deduct $(Fee) from " + who + " Account", mgrEnum.SYS_PROC_FEE.getType(), "Credit"));
        list.add(new lookupBean("Refund Sent to '" + who + "'", mgrEnum.SYS_PAYMENT.getType() * -1 , "Refund"));
        typeLookupListModel = new lookupListModel(list);
    }
    public String getCategoryExample(){
        String str = "";
        int i = mgrEnum.SYS_PAYMENT.getType();
        switch (jlrMgr.getJlrMgrClientPaymentBean().getTypeId()){
            case 10:
                str = "'1st Night Deposit'";
                break;
            case -9:
                str = "'Return Check Fee'";
                break;
            case 9:
                str = "'Coupon'";
                break;
            case -10:
                str = "'Refund'";
                break;
            default:
           
                
                break;
        }
        return str;
        
    }
     public String getCategoryExampleVendor(){
        String str = "";
        int i = mgrEnum.SYS_PAYMENT.getType();
        switch (jlrMgr.getJlrMgrClientPaymentBean().getTypeId()){
            case 10:
                str = "'Down Payment'";
                break;
            case -10:
                str = "'Refund'";
                break;
            default:
           
                
                break;
        }
        return str;
        
    }
    private void setPaymentTypeListVendor(String who){
        List<lookupBean> list = new ArrayList<lookupBean>();
        list.add(new lookupBean("Payment Sent to '" + who + "'", mgrEnum.SYS_PAYMENT.getType() * -1, "Refund"));
        list.add(new lookupBean("Refund Received From '" + who + "'", mgrEnum.SYS_PAYMENT.getType(), "Payment"));
        //list.add(new lookupBean("Apply $(Fee) to " + who + " Account", mgrEnum.SYS_PROC_FEE.getType() * -1, "Fee"));
        //list.add(new lookupBean("Deduct $(Fee) from " + who + " Account", mgrEnum.SYS_PROC_FEE.getType(), "Credit"));
       
        typeLookupListModel = new lookupListModel(list);
    }
    public boolean isOk(final lookupBean b){
        if (b == null){
            RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
            return false;
        } else {
            RequestContext.getCurrentInstance().execute("PF('dialogWidget').hide()");
            return true;
        }
    }
    // ===========================================================================
    public void cancel(){
       jlrMgr.clientOptions();
    }
    public void typeNextSkip(){
        if (!isOk(this.typeBean)) return;
        jlrMgr.getJlrMgrClientPaymentBean().setTypeDesc(typeBean.getDesc());
        jlrMgr.getJlrMgrClientPaymentBean().setTypeRoot(typeBean.getMiscDesc());
        jlrMgr.getJlrMgrClientPaymentBean().setTypeId(typeBean.getLookupId());
        jlrMgr.setCenterHtml(isClient() ? eNumPageNav.clientPayment_payment_Pg04 :eNumPageNav.clientPayment_payment_Pg04_Block);
    }
    public void typeNext(){
        if (!isOk(this.typeBean)) return;
        jlrMgr.getJlrMgrClientPaymentBean().setTypeDesc(typeBean.getDesc());
        jlrMgr.getJlrMgrClientPaymentBean().setTypeRoot(typeBean.getMiscDesc());
        jlrMgr.getJlrMgrClientPaymentBean().setTypeId(typeBean.getLookupId());
        jlrMgr.setCenterHtml(isClient() ? eNumPageNav.clientPayment_payment_Pg02_Category : eNumPageNav.clientPayment_payment_Pg02_Category_Block);
    }
    // ===========================================================================
    public void methodBack(){
       jlrMgr.setCenterHtml(isClient() ? eNumPageNav.clientPayment_payment_Pg02_Category : eNumPageNav.clientPayment_payment_Pg02_Category_Block);
    }
    public void methodNext(){
        if (!isOk(this.methodBean)) return;
         jlrMgr.getJlrMgrClientPaymentBean().setMethodId(methodBean.getLookupId());
         jlrMgr.setCenterHtml(isClient() ? eNumPageNav.clientPayment_payment_Pg04 : eNumPageNav.clientPayment_payment_Pg04_Block);
    }
    public void methodEdit(){
       if (!isOk(this.methodBean)) return;
        jlrMgr.getJlrMgrClientPaymentBean().setEditLookup(methodBean.getLookupId(), false);
       edit(true);
    }
    public void methodAdd(){
        jlrMgr.getJlrMgrClientPaymentBean().setEditLookup(0, false);
        edit(true);
    }
    // ===========================================================================
    public void categoryNext(){
        if(!isOk(this.catogoryBean)) return;
        jlrMgr.getJlrMgrClientPaymentBean().setCategoryId(this.catogoryBean.getLookupId());
        //jlrMgr.getJlrMgrClientPaymentBean().setMethodId(this.catogoryBean.getLookupId());
        jlrMgr.setCenterHtml(isClient() ? eNumPageNav.clientPayment_payment_Pg03_Method : eNumPageNav.clientPayment_payment_Pg03_Method_Block);
    }
    public void categoryBack(){
         jlrMgr.setCenterHtml(isClient() ? eNumPageNav.clientPayment_payment_Pg01_Type : eNumPageNav.clientPayment_payment_Pg01_Type_Block);
    }
    public void categoryAdd(){
        jlrMgr.getJlrMgrClientPaymentBean().setEditLookup(0, true);
        edit(false);
    }
    public void categoryEdit(){
        if(!isOk(this.catogoryBean)) return;
        jlrMgr.getJlrMgrClientPaymentBean().setEditLookup(catogoryBean.getLookupId(), true);
        edit(false);
    }
    // ===========================================================================
    private void edit(boolean method){
        if (method){
            jlrMgr.setCenterHtml(isClient() ? eNumPageNav.clientPayment_payment_LookupEditMethod : eNumPageNav.clientPayment_payment_LookupEditMethod_Block);
        } else {
            jlrMgr.setCenterHtml(isClient() ? eNumPageNav.clientPayment_payment_LookupEditCatergory : eNumPageNav.clientPayment_payment_LookupEditCatergory_Block);
        }
    }
    public void editCancel(){
        if (jlrMgr.getJlrMgrClientPaymentBean().isCategory()){
            jlrMgr.setCenterHtml(isClient() ? eNumPageNav.clientPayment_payment_Pg02_Category : eNumPageNav.clientPayment_payment_Pg02_Category_Block);
        } else {
            jlrMgr.setCenterHtml(isClient() ? eNumPageNav.clientPayment_payment_Pg03_Method : eNumPageNav.clientPayment_payment_Pg03_Method_Block);
        }
    }
    public void editSave(){
       
            (new lookupObj(jlrMgr.getEMailKey())).updateLookup(getLookupBean(), jlrMgr.getObj());
       
        
        editCancel();
    }

    /**
     * @return the editBean
     */
    public void genLookBean() {
        if (lookupBean != null) {
            return ;
        }
        int i = jlrMgr.getJlrMgrClientPaymentBean().getLookupId();
        if (i > 0) {

            setLookupBean((new lookupObj(jlrMgr.getEMailKey())).getLookupBean(i, jlrMgr.getObj()));
        }
        if (i == 0 || lookupBean == null) {
            setLookupBean(new lookupBean());
            if (jlrMgr.getJlrMgrClientPaymentBean().isCategory()) {
                lookupBean.setLookupType(jlrMgr.getJlrMgrClientPaymentBean().getClientType());
                lookupBean.setLookupRollupId(jlrMgr.getJlrMgrClientPaymentBean().getTypeId());
                lookupBean.setMiscDesc("Category");
            } else {
                lookupBean.setLookupType(-17);
                lookupBean.setLookupRollupId(-17);
                lookupBean.setMiscDesc("Payment Method");
            }
        }
        
    }
    public List<lookupBean> getFlagStatusList() {
        if (flagStatusList == null) {
            flagStatusList = (new qckBlock()).getFlagStatusList();
           
        }
        return flagStatusList;
    }

    /**
     * @return the custPayBean
     */
    public custPayBean getCustPayBean() {
        if (custPayBean == null) {
            custPayBean = new custPayBean();
            jlrMgrClientPaymentBean i = jlrMgr.getJlrMgrClientPaymentBean();
            custPayBean.setPaymentType(i.getTypeId());
            custPayBean.setPaymentCategory(i.getCategoryId());
            custPayBean.setPaymentRollup(i.getMethodId());
        }
        return custPayBean;
    }

    /**
     * @return the lookupBean
     */
    public lookupBean getLookupBean() {
        if (this.lookupBean == null) genLookBean();
        return lookupBean;
    }

    /**
     * @param lookupBean the lookupBean to set
     */
    public void setLookupBean(lookupBean lookupBean) {
        this.lookupBean = lookupBean;
    }
    public void customerPaymentBack(){
        jlrMgr.setCenterHtml(isClient() ? eNumPageNav.clientPayment_payment_Pg03_Method : eNumPageNav.clientPayment_payment_Pg03_Method_Block);
    }
    public void customerPaymentCancel(){
        jlrMgr.clientOptions();
    }
    public void customerPaymentSave(){
        custPaymentBean b = new custPaymentBean();
        b.setDbTimestamp(custPayBean.getDate());
        b.setNote(custPayBean.getNote());
        b.setComment(custPayBean.getComment());
        
        b.setAmtRec(Math.abs(custPayBean.getAmount()) * (custPayBean.getPaymentType() < 0 ? -1 : 1));
        
        b.setPaymentType(custPayBean.getPaymentType());
        b.setChkType(custPayBean.getPaymentRollup());
        b.setLookupId(custPayBean.getPaymentCategory());
       
        b.setSponsor(jlrMgr.getSponsorId());
        if (jlrMgr.getJlrMgrClientPaymentBean().isClient()){
            b.setCustId(jlrMgr.getClientBean().getCustId());
        } else {
           b.setVendorId(jlrMgr.getVendorId());
        }
        
        if (Math.abs(custPayBean.getPaymentType()) == mgrEnum.SYS_PAYMENT.getType()) {
           (new customerPaymentObj(jlrMgr.getEMailKey())).addPayment(b, jlrMgr.getObj());
        } else {
            (new customerPaymentObj(jlrMgr.getEMailKey())).addPaymentBlock(b, jlrMgr.getObj());
        }
        jlrMgr.clientOptions();
    }
    
   
    
}
