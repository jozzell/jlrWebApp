/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.vScope;

import java.io.Serializable;

/**
 *
 * @author Lloyd
 */
public class jlrMgrClientPaymentBean   implements Serializable {
    private int typeId;
    private int categoryId;
    private int methodId;
    private int clientType;
    
    private int lookupId;
    private boolean client=true;
    private String typeDesc,typeRoot;
    
    //private String methodDesc;
    
   
    private boolean category=true;
    public jlrMgrClientPaymentBean(boolean client,int paymentType){
        this.clientType = paymentType;
        this.client = client;
    }

    public void setEditLookup(int id,boolean cat){
        this.setCategory(cat);
        setLookupId(id);
    }
    

    /**
     * @return the client
     */
    public boolean isClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(boolean client) {
        this.client = client;
    }

    /**
     * @return the typeDesc
     */
    public String getTypeDesc() {
        return typeDesc;
    }

    /**
     * @param typeDesc the typeDesc to set
     */
    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    /**
     * @return the typeRoot
     */
    public String getTypeRoot() {
        return typeRoot;
    }

    /**
     * @param typeRoot the typeRoot to set
     */
    public void setTypeRoot(String typeRoot) {
        this.typeRoot = typeRoot;
    }

    /**
     * @return the typeId
     */
    public int getTypeId() {
        return typeId;
    }

    /**
     * @param typeId the typeId to set
     */
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

   
    /**
     * @return the methodId
     */
    public int getMethodId() {
        return methodId;
    }

    /**
     * @param methodId the methodId to set
     */
    public void setMethodId(int methodId) {
        this.methodId = methodId;
    }

   

    /**
     * @return the category
     */
    public boolean isCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(boolean category) {
        this.category = category;
    }

    /**
     * @return the categoryId
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return the clientType
     */
    public int getClientType() {
        return clientType;
    }

    /**
     * @param clientType the clientType to set
     */
    public void setClientType(int clientType) {
        this.clientType = clientType;
    }

    /**
     * @return the lookupId
     */
    public int getLookupId() {
        return lookupId;
    }

    /**
     * @param lookupId the lookupId to set
     */
    public void setLookupId(int lookupId) {
        this.lookupId = lookupId;
    }
}
