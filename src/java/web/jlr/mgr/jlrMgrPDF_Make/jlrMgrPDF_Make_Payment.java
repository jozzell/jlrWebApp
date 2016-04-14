/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.jlrMgrPDF_Make;


import com.itextpdf.text.DocumentException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._beans.custBean;
import jlRoomsCommon._beans.rptBeanColumesAmtList;
import jlRoomsCommon.bookingTable;
import jlRoomsV3.rpt.payment.feeV3_Obj_;
import jlRoomsV3.rpt.payment.paymentV3_Obj_Rpt;
import jlRoomsV3.rpt.payment.paymentV3_Pdf;
import org.primefaces.context.RequestContext;
import web.jlr.mgr.eNum.eNumPageNav;
import web.jlr.mgr.jlrMgrPDF;

/**
 *
 * @author lmeans
 */
public class jlrMgrPDF_Make_Payment implements Serializable {
    private custBean custBean=null;
    public jlrMgrPDF_Make_Payment(jlrMgrPDF_Interface i) {
        iface = i;
    }
    jlrMgrPDF_Interface iface;

    public void genVendorPaymentAll() {
        RequestContext.getCurrentInstance().execute("PF('statusDialog').show()");
        List<rptBeanColumesAmtList> list
                = (new paymentV3_Obj_Rpt()).getVendorPaymentAll(
                        iface.getJlrMgr().getSponsorId(),
                        iface.getJlrMgr().getEMailKey(),
                        iface.getJlrMgr().getObj());
        genPdf(
                iface.getJlrMgr().getSponsorBean().getSponsorDesc() + " Payment Report (Vendors)",
                true,
                list,
                eNumPageNav.RptVendorPayment_All_PDF);
    }

    public void genVendorPayment() {
        RequestContext.getCurrentInstance().execute("PF('statusDialog').show()");
        paymentV3_Obj_Rpt rpt = new paymentV3_Obj_Rpt();
        rpt.genVendorPayment(
                iface.getJlrMgr().getVendorId(),
                iface.getJlrMgr().getSponsorId(),
                iface.getJlrMgr().getEMailKey(),
                iface.getJlrMgr().getObj());
        rptBeanColumesAmtList amt = new rptBeanColumesAmtList();
        amt.setRptList(rpt.getRptList());
        amt.setHeader(rpt.getWho());
        amt.setAmt(rpt.getTotal());
        genPdf(
                iface.getJlrMgr().getVendorDesc() + " Payment Report",
                true,
                amt,
                eNumPageNav.RptVendorPayment_PDF);
    }

   

    public void genPaymentPdfRptAll() {
        RequestContext.getCurrentInstance().execute("PF('statusDialog').show()");
        List<rptBeanColumesAmtList> list = (new paymentV3_Obj_Rpt()).getCustPaymentAll(
                iface.getJlrMgr().getSponsorId(),
                iface.getJlrMgr().getEMailKey(),
                iface.getJlrMgr().getObj());
        genPdf(
                iface.getJlrMgr().getSponsorBean().getSponsorDesc() + " Payment Report (Client)",
                true,
                list,
                eNumPageNav.RptCustomerPayment_All_PDF);

    }

    public void genPaymentPdfRpt() {
        RequestContext.getCurrentInstance().execute("PF('statusDialog').show()");
        this.custBean = iface.getJlrMgr().getClientBean();
        paymentV3_Obj_Rpt rpt = new paymentV3_Obj_Rpt();
        rpt.genCustPayment(
                custBean.getCustId(),
                iface.getJlrMgr().getSponsorId(),
                iface.getJlrMgr().getEMailKey(),
                iface.getJlrMgr().getObj());
        rptBeanColumesAmtList amt = new rptBeanColumesAmtList();
        amt.setRptList(rpt.getRptList());
        amt.setHeader(rpt.getWho());
        amt.setAmt(rpt.getTotal());
        genPdf(
                custBean.getDisplayFullName() + " Payment Report for " + iface.getJlrMgr().getSponsorBean().getSponsorDesc(),
                true,
                amt,
                eNumPageNav.customer_custPg02_pdf);
    }

    private void genPdf(String header, boolean landscape, rptBeanColumesAmtList list, eNumPageNav eNum) {
        List<rptBeanColumesAmtList> x = new ArrayList<rptBeanColumesAmtList>();
        x.add(list);
        genPdf(header, landscape, x, eNum);
    }

    private void genPdf(String header, boolean landscape, List<rptBeanColumesAmtList> list, eNumPageNav eNum) {

        bookingTable bookingTable = new bookingTable(iface.getJlrMgr().getCompanyBean(),custBean,iface.getJlrMgr().getSponsorBean());
        bookingTable.openWithFile(
                header,
                iface.getFileNamePath(),
                iface.getUnixRoot(),
                landscape);
        try {
            (new paymentV3_Pdf()).getPayment(list, bookingTable.getDocument());
        } catch (DocumentException ex) {
            Logger.getLogger(jlrMgrPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        bookingTable.close();
        iface.setPdf(bookingTable.getFileName());
        iface.getJlrMgr().setCenterHtml(eNum);
    }
}
