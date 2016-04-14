/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.jlrMgrPDF_Make;

import java.io.Serializable;
import web.jlr.mgr.jlrMgr;

/**
 *
 * @author lmeans
 */
public interface jlrMgrPDF_Interface  {
    public jlrMgr getJlrMgr();
    public String getUnixRoot();
    public String getFileNamePath();
    public void setPdf(String str);
}
