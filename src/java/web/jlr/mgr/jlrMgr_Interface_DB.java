/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr;

import cv.bisc.db.dbMgr;
import cv.bisc.db.dbMgrEnumType;

/**
 *
 * @author lmeans
 */
public abstract class jlrMgr_Interface_DB {
    private dbMgr dbMgr=null,dbRoot=null;
    public void switchDB(){
        dbMgr = new dbMgr(getUserJndi(), dbMgrEnumType.typeMySql.getType());
    }
    public dbMgr getObj() {
        if (dbMgr == null) {
           
            dbMgr = new dbMgr(getUserJndi(), dbMgrEnumType.typeMySql.getType());
            
        }
        
        return dbMgr;
    }
    public dbMgr getObjRoot() {
        if (dbRoot == null) {
            dbRoot = new dbMgr(getRootJndi(), dbMgrEnumType.typeMySql.getType());
        }
        return dbRoot;
    }
    public abstract String getRootJndi();
    public abstract String getUserJndi();
}
