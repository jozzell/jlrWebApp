/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.Navigation;

/**
 *
 * @author Lloyd
 */
public class varNav_Block implements varNav_Interface{
    private final int 
            clientEdit=0,
            client=1,
            vendorType=4,
            vendorEdit = 5,
            vendorBrw = 6
            ;

    /**
     * @return the clientEdit
     */
    
    @Override
    public int getClientEdit() {
        return clientEdit;
    }

    /**
     * @return the client
     */
    @Override
    public int getClient() {
        return client;
    }

    /**
     * @return the vendorType
     */
    @Override
    public int getVendorType() {
        return vendorType;
    }

    /**
     * @return the vendorEdit
     */
    @Override
    public int getVendorEdit() {
        return vendorEdit;
    }

    /**
     * @return the vendorBrw
     */
    @Override
    public int getVendorBrw() {
        return vendorBrw;
    }

    @Override
    public int getLookupList() {
        return 1;
    }

    @Override
    public int getLookupEdit() {
        return 1;
    }

    
}
