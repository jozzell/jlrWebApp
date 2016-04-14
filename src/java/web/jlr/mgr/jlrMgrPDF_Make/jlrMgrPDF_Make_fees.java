/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.jlrMgrPDF_Make;


import com.itextpdf.text.DocumentException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._beans.custBean;
import jlRoomsCommon._beans.rptBeanColumesAmtList;
import jlRoomsCommon.bookingTable;
import jlRoomsV3.rpt.payment.feeV3_PDF;
import jlRoomsV3.rpt.payment.feeV3_Obj_;
import jlRoomsV3.rpt.payment.paymentV3_Pdf;
import org.primefaces.context.RequestContext;
import web.jlr.mgr.eNum.eNumPageNav;
import web.jlr.mgr.jlrMgrPDF;

/**
 *
 * @author lmeans
 */
public class jlrMgrPDF_Make_fees implements Serializable{
    private custBean custBean=null;
     public jlrMgrPDF_Make_fees(jlrMgrPDF_Interface i) {
        iface = i;
    }
    jlrMgrPDF_Interface iface;
    
     public void genPaymentPdf_FeeAll() {
         RequestContext.getCurrentInstance().execute("PF('statusDialog').show()");
        List<rptBeanColumesAmtList> list = (new feeV3_Obj_()).getFee_All(
                iface.getJlrMgr().getSponsorId(),
                iface.getJlrMgr().getEMailKey(),
                iface.getJlrMgr().getObj());
        genPdf(
                iface.getJlrMgr().getSponsorBean().getSponsorDesc() + " Payment Fee Report (Client)",
                true,
                list,
                eNumPageNav.Rpt_Fees_ALL_PDF);
    }
      private void genPdf(String header, boolean landscape, List<rptBeanColumesAmtList> list, eNumPageNav eNum) {

        try {
            bookingTable bookingTable = new bookingTable(iface.getJlrMgr().getCompanyBean(),custBean,iface.getJlrMgr().getSponsorBean());
            bookingTable.openWithFile(
                    header,
                    iface.getFileNamePath(),
                    iface.getUnixRoot(),
                    landscape);
            (new feeV3_PDF()).getPayment(list, bookingTable.getDocument());
            bookingTable.close();
            iface.setPdf(bookingTable.getFileName());
            iface.getJlrMgr().setCenterHtml(eNum);
        } catch (DocumentException ex) {
            Logger.getLogger(jlrMgrPDF_Make_fees.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
