/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr;

import web.jlr.mgr.Navigation.varNav_BookingQuick;
import web.jlr.mgr.Navigation.varNav_Interface;
import web.jlr.mgr.Navigation.varNav_Roommate;
import web.jlr.mgr.Navigation.varNav_SponsorHotel;
import web.jlr.mgr.Navigation.varNav_Block;

/**
 *
 * @author Lloyd
 */
public enum jlrMgrEnum {
    roommate(0),
    reservation(1),
    blockMaint(2),
    sponsorHotelMaint(3);
    private final int appType;
    private varNav_Interface iface;
    public static final int
            sponsorHotel=3,
            block = 2,
            roommate_value=0,
            reservation_id=1;
    private jlrMgrEnum(int appType){
        this.appType = appType;
        switch (appType) {
            case 3:
                iface = new varNav_SponsorHotel();
                break;
            case 2:
                iface = new varNav_Block();
                break;
            case 1:
                 iface = new varNav_BookingQuick();
                break;
            default:
                iface = new varNav_Roommate();
                break;
        }
    }
    public int getFlightInfo(){
        return 0;
    }
    public int getClientBookingEdit(){
        return 4;
    }
    public int getClientBookingList(){
        return 2;
    }
    public int getAppType() {
        return appType;
    }
    public int getEventActivity() {
        return 2;
    }

    public int getRoommdate() {
        return 7;
    }

    public int getRoommateBrw() {
        return 3;
    }
     public int getLookupList(){
         return iface.getLookupList();
     }
    public int getLookupEdit(){
        return iface.getLookupEdit();
    }
    public int getVendorBrw() {
        return iface.getVendorBrw();
    }

    public int getVendorType() {
        return iface.getVendorType();
    }

    public int getVendorEdit() {
        return iface.getVendorEdit();
    }

    public int getClientBrw() {
        return iface.getClient();
    }

    public int getClientEdit() {
        return iface.getClientEdit();
    }
}
