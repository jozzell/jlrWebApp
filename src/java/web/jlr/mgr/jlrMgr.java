
/*


ALTER TABLE `jlrooms_dbaccount` 
ADD COLUMN `file_path` VARCHAR(300) NULL;
update jlroomsc_root.jlrooms_dbaccount
set file_path = '/home/jlroomsc/images/jlr' where db_id != 0;


li91fmuja1mvsujrpnyt91xkn   qrch4f  uniquet3@yahoo.com  welcome3
gx422au41sffq6lo52123r2bt   pgaqrv  lmeans@optonline.com  123

 */
package web.jlr.mgr;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import jlRoomsCommon._beans.custBean;
import jlRoomsCommon._beans.lookupBean;
import jlRoomsCommon._beans.propertiesBean;
import jlRoomsCommon._beans.sponsorBean;
import jlRoomsCommon._beans.userAccountBean;
import jlRoomsCommon._beans.vendorBean;
import jlRoomsCommon._jlroot.serialsCompanyObj;
import jlRoomsCommon._jlroot.serialsObj;
import jlRoomsCommon._testConnection;
import jlRoomsCommon.bookingTable;
import jlRoomsCommon.customer.db.custObj;
import jlRoomsCommon.mgrEnum;
import jlRoomsCommon.sponsor.db.sponsorObj;
import jlRoomsCommon.vendor.db.vendorObj;
import jlRoomsCommon.vendorObjTypesENum;
import obj.reusableObj;
import org.primefaces.context.RequestContext;
import web.jlr.mgr.eNum.eNumAppNav;
import web.jlr.mgr.eNum.eNumPageNav;
import web.jlr.mgr.eNum.eNumPageNavDisplay;
import web.jlr.mgr.eNum.eNumWorkFlow;
import web.jlr.mgr.vScope.jlrMgrClientPaymentBean;

/**
 *
 * @author lmeans
 */
@ManagedBean
@SessionScoped
public final class jlrMgr extends jlrMgr_Interface_DB implements Serializable {
    private jlrMgrBean mgrBean = new jlrMgrBean();
    private eNumAppNav appNav = eNumAppNav.login;
    private eNumPageNav centerHtml =eNumPageNav.loginPage_01 ;//eNumPageNav.client_home; //eNumPageNav.customer_custPg01_Seek;
    private boolean debug = true,demo=true;
    private jlrMgrEnum eJlrMgrEnum;
    
    private custBean userLoginBean;
    private vendorBean companyBean;
    private userAccountBean userAccountBean;
    private sponsorBean sponsorBean;
    private eNumPageNav  doneEditingClientENum=null;
    private final String jndiRoot = "jdbc/jlroomsc_root"; //"jdbc/";
    private String siteId = null; //"jdbc/";
    private int tabPtr=0,jlrMgrPtr=0,workFlwPtr=0,tabCust=0;
    private int iChkConCntr=0;
    private int  roommateId = 0,blockId,sponsorHotelId,fltId,vendorId,sponsor_id;
    
    
    public jlrMgr() {
        _testConnection();
        seteJlrMgrEnum(jlrMgrEnum.roommate);
       
        if (debug) {
            //loginDemo(2);
        }
    }
    public void demoMode(){
        this.setDemo(true);
        if (userLoginBean.getPassWordChk() == null || userLoginBean.getPassWordChk().trim().length() == 0 || !checkEMailFormat(userLoginBean.getPassWordChk())){
            RequestContext.getCurrentInstance().execute("PF('dialogWidget_4').show()");
        } else {
            this.siteId = (new reusableObj()).getUnixName(userLoginBean.getPassWordChk());
             demoModeBean();
             switchDB();
             appNav = eNumAppNav.brwUnlimited;
             this.setCenterHtml(eNumPageNav.eventMgrEvent);
        }
    }
    private void demoModeBean(){
         companyBean = new vendorBean();
         companyBean.setVendorName("jlRooms.com");
         companyBean.setEmailKey(this.siteId);
         userLoginBean = new custBean();
         userLoginBean.setFirstName("John");
         userLoginBean.setLastName("Doe");
         userLoginBean.setEMail("LMeans@optonline.com");
         userLoginBean.setEmailKey(this.siteId);
         userAccountBean = new userAccountBean();
         userAccountBean.setJndi("jdbc/jlroomsc_demo");
         
    }
     public void login(){
        serialsCompanyObj ser = new serialsCompanyObj();
        
        String pass = userLoginBean.getPassWord();
        String user = userLoginBean.getUserName();
        if (siteId != null) siteId = siteId.trim().toLowerCase();
        if  (siteId == null || siteId.trim().length() == 0 ||
            pass == null || pass.trim().length() == 0 ||
            user == null || user.trim().length() == 0){
            userLoginBean.setPassWord(null);
            RequestContext.getCurrentInstance().execute("PF('dialogWidget_1').show()");
            //this.setDialogMsg("Sorry your missing user name,password or Site ID",msgLogin);
            return;
        }
        userLoginBean = ser.getCustBean(user, siteId, this.getObjRoot(),true);
        if (userLoginBean == null){
            RequestContext.getCurrentInstance().execute("PF('dialogWidget_2').show()");
            //this.setDialogMsg("Sorry, '"+user+"' dont't have an account",msgLogin);
          
        } else {
            if (!userLoginBean.getPassWord().equalsIgnoreCase(pass)){
                userLoginBean.setPassWord(null);
                RequestContext.getCurrentInstance().execute("PF('dialogWidget_3').show()");
                //this.setDialogMsg("Sorry, Invalid Password",msgLogin);
              
            } else {
                setDemo(false);
                (new serialsObj()).insertLogin(getIP(userLoginBean.getKeyStr()), user, getObjRoot());
             setCompany();
            userAccountBean = (new serialsObj()).getUserAccountBean(companyBean.getDbId(), this.getObjRoot(), true);
            appNav = eNumAppNav.brwUnlimited;
              this.setCenterHtml(eNumPageNav.eventMgrEvent);
            }
        }
        
       
    }
     public void forgotPassword(){
       String user = userLoginBean.getUserName();
       if (!checkEMailFormat(user)){
           RequestContext.getCurrentInstance().execute("PF('dialogWidget_5').show()");
           return;
       }
       custBean b = (new serialsCompanyObj()).getCustEMailBean( userLoginBean.getUserName(), this.getObjRoot(), true);
       if (b == null || b.getCustId() == 0){
           RequestContext.getCurrentInstance().execute("PF('dialogWidget_5').show()");
           return;
       }
       boolean ok = sendUserInfo(b);
       if (!ok){
           RequestContext.getCurrentInstance().execute("PF('dialogWidget_5').show()");
       } else {
           RequestContext.getCurrentInstance().execute("PF('dialogWidget_6').show()");
       }
     }
     public boolean sendUserInfo(custBean b){
        companyBean = (new serialsCompanyObj()).getCompanyBean(b.getVendorID(), this.getObjRoot(), true);
        userAccountBean  = (new serialsObj()).getUserAccountBean(companyBean.getDbId(), this.getObjRoot(), true);
        System.out.println(userAccountBean.getPath());
        boolean ok=false;
        try {
            String path = this.getPath()+"/password.pdf";
            String[] mail = new String[1];
            mail[0] = b.getEMail();
            if (!checkEMailFormat(mail[0])) return false;
            bookingTable booking = new bookingTable();
            booking.openWithFile(
                    (new reusableObj()).getUnixName(b.getDisplayFullName()),
                    path,
                    userAccountBean.getPath(),
                    false);
            booking.setVendor(this.getCompanyBean());
            booking.setCustBean(b);
            booking.sendEmail(new propertiesBean(), mail, booking.getFileName(),"JLRooms Password Reset");
            ok = true;
        } catch (Exception ex) {
            
            Logger.getLogger(jlrMgr.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
         
     }
     public void updatePassword(String pass){
         this.userLoginBean.setPassWord(pass);
         (new serialsCompanyObj()).updateCustomerInfo(userLoginBean, this.getObjRoot(), true);
         
         userLoginBean = ( new serialsCompanyObj()).getCustBean(userLoginBean.getUserName(), siteId, this.getObjRoot(),true);
         sendUserInfo(userLoginBean);
         setCenterHtml(eNumPageNav.eventMgrEvent);
     }
     private boolean checkEMailFormat(String email){
         if(email == null ||email.trim().length() == 0 || !email.contains("@") || !email.contains(".")) return false;
         return true;
     }
     private String getIP(String str){
        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        String ip = httpServletRequest.getRemoteAddr();
        if (ip == null) return str;
        return ip;
    }
    public void logout() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        //ec.redirect(ec.getRequestContextPath()+jlBookingMgr_V2_URL.logoutURL);
        FacesContextForward("/jlrWebApp/", FacesContext.getCurrentInstance());
    }
    
    public void _testConnection (){
        _testConnection a = new _testConnection();
        a.dbRoom(getObj());
        a.dbRoot(getObjRoot());

        if ((this.isDemo() && this.iChkConCntr >= 3) || iChkConCntr(false) >= 6){
            this.logout();
        }
        
        //a = new _testConnection(this.getObjRoot());
    }
    
    private synchronized int iChkConCntr(boolean reset){
        
        if (reset){
            iChkConCntr = 0;
        } else {
            iChkConCntr++;
        }
        
        return iChkConCntr;
    }
    private void loginDemoXXX(int spId) {
        try {
            sponsor_id = spId;
            serialsCompanyObj serialsCompanyObj = new serialsCompanyObj();
            userLoginBean = serialsCompanyObj.getCustBean("uniquet3@yahoo.com", getSiteId(), this.getObjRoot(), true);
            companyBean = serialsCompanyObj.getCompanyBean(this.getUserLoginBean().getVendorID(), this.getObjRoot(), true);
            //sponsorBean = genSponsorBean();
            ///companyBean.setEmailKey(companyBean.getEmailKey()+"demo");
            userAccountBean = (new serialsObj()).getUserAccountBean(companyBean.getDbId(), this.getObjRoot(), true);
        } catch (Exception ex) {
            Logger.getLogger(jlrMgr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setCompany(){
        if (!isDemo()) {
            companyBean= (new serialsCompanyObj()).getCompanyBean(this.getUserLoginBean().getVendorID(), this.getObjRoot(), true);
        } else {
             demoMode();
        }
       
    }
    // =====================================================================
    public void closeEvent(){
        mgrBean = new jlrMgrBean();
        appNav = eNumAppNav.brwUnlimited;
        sponsorBean = getNewSponsorBean();
        this.setCenterHtml(eNumPageNav.eventMgrEvent);
        
    }
     public void editEvent(){
        setCenterHtml(eNumPageNav.eventPg02_edit);
    }
    public void editEventVendor(){
        setCenterHtml(eNumPageNav.eventVendor_Pg1);
    }
    // ==================================================
    public void saveSponsorBean(int i){
        sponsorBean.setVendorId(i);
        try {
            (new sponsorObj(this.getEMailKey())).update(sponsorBean, getObj());
            if(this.sponsorBean.getSponsorId() == 0){
                this.setCenterHtml(eNumPageNav.eventMgrEvent);
            } else {
                sponsorBean = genSponsorBean() ;
                appNav = eNumAppNav.app;
                this.clientHome();
            }
        } catch (Exception ex) {
            Logger.getLogger(jlrMgr.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void saveSponsorBean(sponsorBean bean){
        try {
            (new sponsorObj(this.getEMailKey())).update(bean, getObj());
            sponsorBean = genSponsorBean() ;
            clientHome();
        } catch (Exception ex) {
            Logger.getLogger(jlrMgr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public sponsorBean genSponsorBean() throws Exception{
        return (new sponsorObj(this.getEMailKey())).sponsorGetEvent(sponsor_id, getEMailKey(),this.getObj());
    }
    public void  setSponsor(){
        sponsorBean = getNewSponsorBean();
        //sponsorBean.setSponsorDesc("New Event");
    }
    public void setSponsor(int i){
       
        try {
            sponsor_id = i;
            sponsorBean = genSponsorBean() ;
            appNav = eNumAppNav.app;
            clientHome();
           
        } catch (Exception ex) {
            Logger.getLogger(jlrMgr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private sponsorBean getNewSponsorBean(){
       sponsorBean bean = new sponsorBean();
       bean.setKeyStr(siteId);
       return bean;
    }
    // ==================================================
    public String getPath(){
        return (new StringBuilder())
        .append("/tmpPdf/")
        .append((new SimpleDateFormat("yyyy/MM/dd/")).format(Calendar.getInstance().getTime()))
        //.append("/")
        .append(this.getuserAccountBean().getId())
        .append("-")
        .append((new SimpleDateFormat("HHmmssSSSZ")).format(Calendar.getInstance().getTime()))
        .append("/").toString()
        ;
    }
    // ========================================
    public boolean isWorkFlow(){
        return (jlrMgrPtr < 1 || jlrMgrPtr > 3);
    }
    public void workFlow(){
        centerHtml.setShowFlow(true);
        this.foward();
    }
    public void workFlowBack(){
        centerHtml.setShowFlow(false);
        this.foward();
    }
    
    public void workFlowClientBooking(){
        centerHtml.getNav().setWk(eNumWorkFlow.work_flow_ClientBooking);
         workFlow();
    }
    public void workFlowClientPayment(){
        centerHtml.getNav().setWk(eNumWorkFlow.work_flow_Client_Payment);
         workFlow();
    }
    public void workFlowClientRoommate(){
        centerHtml.getNav().setWk(eNumWorkFlow.work_flow_Client_Roommate);
         workFlow();
    }
// ========================================
    public jlrMgrBean getJlrMgrBean(){
        if (mgrBean == null){
            mgrBean = new jlrMgrBean();
        }
        return mgrBean;
    }
    public jlrMgrEnum getJlrMgrEnum(){
        return geteJlrMgrEnum();
    }
    // ========================================
    public void clientHome(){
         
        reset(0,(this.sponsorBean.getVendorName() == null?"":this.sponsorBean.getVendorName()+" ")+this.sponsorBean.getSponsorDesc());
        this.setCenterHtml(eNumPageNav.client_home);
    }
    // ===========================================
    public void rootClientReset(){
        //int i = getJlrMgrBean().getClientId();
        //String name = getJlrMgrBean().getClientName();
        //jlrMgrBean = new jlrMgrBean(i,name);
        setJlrDisp(getJlrMgrBean().getClientName());
        //jlrMgrClientPaymentBean = new jlrMgrClientPaymentBean();
    }
    // ==========================================================
    public void rootWorkFlow() {
        reset(4,"jlRoom Work Flow");
        this.setCenterHtml(eNumPageNav.flow_home);
    }
    public void rootClient() {
        if (this.getJlrMgrBean().getClientId() <= 0) {
            getJlrMgrBean().setSeek("Search....");
           
        }
        reset(1,"Client Search");
        seteJlrMgrEnum(jlrMgrEnum.reservation);
        //eJlrMgrEnum =jlrMgrEnum.reservation;
        this.setCenterHtml(eNumPageNav.customer_custPg01_Seek);
    }
    public void rootBlock(){
        seteJlrMgrEnum(jlrMgrEnum.blockMaint);
        //eJlrMgrEnum = jlrMgrEnum.blockMaint;
        reset(2,null);
        this.setCenterHtml(eNumPageNav.blocks_BlockV2Pg00);
    }
    public void rootSponsorHotel(){
        seteJlrMgrEnum(jlrMgrEnum.sponsorHotelMaint);
        //eJlrMgrEnum = jlrMgrEnum.sponsorHotelMaint;
        reset(3,"Vendor Block Mgmt Wizard");
        this.setCenterHtml(eNumPageNav.sponsorHotel_SponsorHotelV2Pg01);
    }
    public boolean isResetTab(){
        return jlrMgrPtr == 0;
    }
    public void resetTab(){
        this.jlrMgrPtr=0;
    }
    private void reset(int i,String str){
        setJlrDisp(str);
        this.jlrMgrPtr=i;
        //mgrBean = new jlrMgrBean();
        getJlrMgrBean().setClientId(-1);
    }
    public void setJlrDisp(String str){
        getJlrMgrBean().setJlrDisp(str);
       
    }
    public String getJlrDisp(){
        return getJlrMgrBean().getJlrDisp(this.centerHtml.getHeader());
        //return (jlrDisp == null ? "":this.jlrDisp)+( this.centerHtml.getHeader() == null ? "":" "+this.centerHtml.getHeader());
    }
    // ========================================
    public void clientPayment(){
        getJlrMgrBean().setJlrMgrClientPaymentBean( new jlrMgrClientPaymentBean(true, mgrEnum.PAYMENT_TYPE_CUSTOMER.getType()));
        //jlrMgrClientPaymentBean = new jlrMgrClientPaymentBean(true, mgrEnum.PAYMENT_TYPE_CUSTOMER.getType());
        setCenterHtml(eNumPageNav.clientPayment_payment_Pg01_Type);
    }
    public void reservation(){
        seteJlrMgrEnum(jlrMgrEnum.reservation);
        //eJlrMgrEnum = jlrMgrEnum.reservation;
        setCenterHtml(eNumPageNav.clientBooking_clientBookingPg01_List);
            
    }
    public void roomMate() {
        seteJlrMgrEnum(jlrMgrEnum.roommate);
        //eJlrMgrEnum = jlrMgrEnum.roommate;
        setCenterHtml(eNumPageNav.rm_roommate);
        
    }
    public void roomType(){
        setCenterHtml(eNumPageNav.maintenance_lookupList);
    }
    public void clientOptions() {
        
        if (geteJlrMgrEnum().getAppType() == jlrMgrEnum.sponsorHotel){
            setCenterHtml(eNumPageNav.sponsorHotel_SponsorHotelV2Pg01);
        } else {
            getJlrMgrBean().reset();
            
            setCenterHtml(eNumPageNav.customer_custPg02);
        }
        
    }

    public void clientOptionsDone() {
        //this.seek = this.getClientBean().getLastName();
        rootClient();
        //setCenterHtml(eNumPageNav.customer_custPg01_Seek);
    }
    // ========================================
    public void setVendor(String desc,int id){
        getJlrMgrBean().setVendorDesc(desc);
        //this.vendorDesc = desc;
        this.vendorId = id;
        setCenterHtml(eNumPageNav.sponsorHotel_SponsorHotelV2Pg02_Payment);
    }
    public void vendorPayment(){
        getJlrMgrBean().setJlrMgrClientPaymentBean(new jlrMgrClientPaymentBean(false, mgrEnum.PAYMENT_TYPE_VENDOR.getType()));
        //jlrMgrClientPaymentBean = new jlrMgrClientPaymentBean(false, mgrEnum.PAYMENT_TYPE_VENDOR.getType());
        setCenterHtml(eNumPageNav.clientPayment_payment_Pg01_Type_Block);
    }
    public String getVendorMaintnaceType(){
        return vendorObjTypesENum.DEFAULT.getENUM(getJlrMgrBean().getVendorType()).getMaintnaceType();
    }
    // ========================================
    public custBean getUserLoginBean() {

        if (userLoginBean == null) {
            userLoginBean = new custBean();
        }
        return userLoginBean;
    }

    public userAccountBean getuserAccountBean() {
        if (userAccountBean == null) {
            userAccountBean = new userAccountBean();
        }
        return userAccountBean;
    }

    public vendorBean getCompanyBean() {
        if (companyBean == null) {
            companyBean = new vendorBean();
        }
        return companyBean;
    }

    public String getEMailKey() {
        return companyBean.getEmailKey();
    }

    @Override
    public String getRootJndi() {
        return jndiRoot;
    }

    @Override
    public String getUserJndi() {
        return getuserAccountBean().getJndi();
    }

    /**
     * @return the centerHtml
     */
    public String getCenterHtml() {
        return centerHtml.getPg();
    }

    

    public void setCenterHtml(eNumPageNav centerHtml) {
       
        if(isDebug()) System.err.println(
                "Client: "+centerHtml.getTab() + " " + centerHtml.getPg());
        this.centerHtml = centerHtml;
        foward();
    }

    public void foward() {
        RequestContext.getCurrentInstance().execute("PF('statusDialog').show()");
        iChkConCntr(true);
        FacesContextForward("/jlrWebApp/", FacesContext.getCurrentInstance());
        RequestContext.getCurrentInstance().execute("PF('statusDialog').hide()");
    }

    /**
     * @return the seek
     */
    public String getSeek() {
        return getJlrMgrBean().getSeek();
    }

    /**
     * @param seek the seek to set
     */
    public void setSeek(String seek) {
        getJlrMgrBean().setSeek(seek);
    }

    /**
     * @return the clientBean
     */
    public custBean getClientBean() {
        int i = this.getJlrMgrBean().getClientId();
        if (i == 0){
            return new custBean();
        } 
        custBean bean = (new custObj(this.getEMailKey())).getCustomerBean(i, this.getObj());
        if (bean == null) {
            return new custBean();
        }
        return bean;
    }

    public void setClientBean(custBean b, eNumPageNav url) {
        setDoneEditingClientENum(url);
        //doneEditingClientENum = url;
        this.getJlrMgrBean().setClientId(b.getCustId());
        this.getJlrMgrBean().setClientName(b.getDisplayFullName());
        
    }

    public void FacesContextForward(String url, FacesContext theFacesContext) {
        ExternalContext ec = theFacesContext.getExternalContext();
        try {
            ec.redirect(url);
        } catch (IOException ex) {

        } catch (Exception e) {

        }

    }

    public void clientOptions(custBean b) {
        setJlrDisp(b.getDisplayFullName());
        this.getJlrMgrBean().setClientId(b.getCustId());
        this.getJlrMgrBean().setClientName(b.getDisplayFullName());
        clientOptions();
    }

    

   

    public void roommdateEdit() {
        if (getRoommateId() <= 0) {

        }
        setCenterHtml(eNumPageNav.rm_roommate_view);

    }

    /**
     * @return the doneEditingClient
     */
    public eNumPageNav getDoneEditingClient() {
        return getDoneEditingClientENum();
    }

    /**
     * @return the sponsorId
     */
    public int getSponsorId() {
        return this.getSponsorBean().getSponsorId();
    }

    public void setVendorType(int type, String desc) {
        getJlrMgrBean().setVendorType(type);
        getJlrMgrBean().setVendorDescV2(desc);
        
    }
    public void setVendorBean(vendorBean bean){
        this.getJlrMgrBean().setVendorId(bean.getVendorId());
         this.getJlrMgrBean().setVendorName(bean.getVendorName());
       
    }
    public vendorBean  getVendorBean(){
        vendorBean vendorBean=null;
        if (getJlrMgrBean().getVendorId() == 0){
            vendorBean = mkVendorBean();
        } else {
            vendorBean = (new vendorObj(this.getEMailKey())).getVendorBean(getJlrMgrBean().getVendorId(),this.getObj());
            if (vendorBean == null) vendorBean = mkVendorBean();
        }
        return vendorBean;
    }
    private vendorBean mkVendorBean(){
       vendorBean vendorBean =   new vendorBean();
       vendorBean.setVendorType(getJlrMgrBean().getVendorType());
       return vendorBean;
    }
    public lookupBean getVendorType() {
        lookupBean b = new lookupBean();
        b.setDesc(getJlrMgrBean().getVendorDescV2());
        b.setLookupType(getJlrMgrBean().getVendorType());
        b.setLookupId(getJlrMgrBean().getVendorType());
        return b;
    }

    public Object accessBeanFromFacesContext(final String theBeanName, final FacesContext theFacesContext) {
        Object obj = null;
        try {
            obj = theFacesContext.getELContext().getELResolver().getValue(theFacesContext.getELContext(), null, theBeanName);

        } catch (Exception e) {

        }
        return obj;
    }

    public String getSessionID(FacesContext theFacesContext) {
        return ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getId();
    }

    public void setAccessBeanFromFacesContext(final String theBeanName, final FacesContext theFacesContext, Object obj) {

        try {
            theFacesContext.getELContext().getELResolver().setValue(theFacesContext.getELContext(), null, theBeanName, obj);

        } catch (Exception e) {

        }

    }

    /**
     * @return the vendorBean
     */
   

    /**
     * @return the roommateId
     */
    public int getRoommateId() {
        return roommateId;
    }

    /**
     * @param roommateId the roommateId to set
     */
    public void setRoommateId(int roommateId) {
        this.roommateId = roommateId;
    }

    /**
     * @return the navTabPtr
     */
    public int getNavTabPtr() {
        return this.centerHtml.getTab();
    }

    /**
     * @return the navTabXHtml
     */
    public String getNavTabXHtml() {
        eNumPageNavDisplay nav = this.centerHtml.getNav();
        if(nav == null || nav.getNavDisplay() == null){
            return eNumPageNavDisplay.cust_default.getNavDisplay();
            
        } else {
            return nav.getNavDisplay();
        }
        
    }

    

    

   

    /**
     * @return the centerBlockHtml
     */
    public String getCenterBlockHtml() {
        return centerHtml.getPg();
    }

    /**
     * @param centerBlockHtml the centerBlockHtml to set
     *  public void setCenterBlockHtml(eNumPageNav centerBlockHtml) {
        System.err.println("Client: "+centerHtml.getTab() + " " + centerHtml.getPg()+" (setCenterBlockHtml)");
        this.centerHtml = centerBlockHtml;
        
        foward();
    }
     */
   

    /**
     * @return the tabPtr
     */
    public int getTabPtr() {
        return tabPtr;
    }

    /**
     * @param tabPtr the tabPtr to set
     */
    public void setTabPtr(int tabPtr) {
        this.tabPtr = tabPtr;
    }

    /**
     * @return the blockId
     */
    public int getBlockId() {
        return blockId;
    }

    /**
     * @param blockId the blockId to set
     */
    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

    /**
     * @return the sponsorHotelId
     */
    public int getSponsorHotelId() {
        return sponsorHotelId;
    }

    /**
     * @param sponsorHotelId the sponsorHotelId to set
     */
    public void setSponsorHotelId(int sponsorHotelId) {
        this.sponsorHotelId = sponsorHotelId;
    }

    /**
     * @return the fltId
     */
    public int getFltId() {
        return fltId;
    }

    /**
     * @param fltId the fltId to set
     */
    public void setFltId(int fltId) {
        this.fltId = fltId;
    }

    /**
     * @return the sponsorBean
     */
    public sponsorBean getSponsorBean() {
        if(sponsorBean == null) {
            sponsorBean = getNewSponsorBean();
           
        }
        return sponsorBean;
    }
   
    /**
     * @return the jlrMgrClientPaymentBean
     */
    public jlrMgrClientPaymentBean getJlrMgrClientPaymentBean() {
        return getJlrMgrBean().getJlrMgrClientPaymentBean();
    }

    /**
     * @return the vendorDesc
     */
    public String getVendorDesc() {
        return getJlrMgrBean().getVendorDesc();
    }

    /**
     * @return the vendorId
     */
    public int getVendorId() {
        return vendorId;
    }
    public void setVendorId(int i){
        this.vendorId = i;
    }

    /**
     * @return the jlrMgrPtr
     */
    public int getJlrMgrPtr() {
        return jlrMgrPtr;
    }

    /**
     * @param jlrMgrPtr the jlrMgrPtr to set
     */
    public void setJlrMgrPtr(int jlrMgrPtr) {
        this.jlrMgrPtr = jlrMgrPtr;
    }

    /**
     * @return the debug
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * @param debug the debug to set
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    /**
     * @return the workFlwPtr
     */
    public int getWorkFlwPtr() {
        return workFlwPtr;
    }

    /**
     * @param workFlwPtr the workFlwPtr to set
     */
    public void setWorkFlwPtr(int workFlwPtr) {
        this.workFlwPtr = workFlwPtr;
    }

    /**
     * @return the eNumAppNav
     */
    public eNumAppNav geteNumAppNav() {
        return appNav;
    }

    /**
     * @return the siteId
     */
    public String getSiteId() {
        return siteId;
    }

    /**
     * @param siteId the siteId to set
     */
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    /**
     * @return the demo
     */
    public boolean isDemo() {
        return demo;
    }

    /**
     * @param demo the demo to set
     */
    public void setDemo(boolean demo) {
        this.demo = demo;
    }
     public jlrMgrEnum geteJlrMgrEnum() {
        return eJlrMgrEnum;
    }

    /**
     * @param eJlrMgrEnum the eJlrMgrEnum to set
     */
    public void seteJlrMgrEnum(jlrMgrEnum eJlrMgrEnum) {
        this.eJlrMgrEnum = eJlrMgrEnum;
    }
     /**
     * @return the doneEditingClientENum
     */
    public eNumPageNav getDoneEditingClientENum() {
        return doneEditingClientENum;
    }

    /**
     * @param doneEditingClientENum the doneEditingClientENum to set
     */
    public void setDoneEditingClientENum(eNumPageNav doneEditingClientENum) {
        this.doneEditingClientENum = doneEditingClientENum;
    }

    /**
     * @return the tabCust
     */
    public int getTabCust() {
        return tabCust;
    }

    /**
     * @param tabCust the tabCust to set
     */
    public void setTabCust(int tabCust) {
        this.tabCust = tabCust;
    }

}
