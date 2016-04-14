/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.wkFlow;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

/**
 *
 * @author Lloyd
 */
@ManagedBean
@RequestScoped
public class jlrFlow_Client_Block   extends jlrFlow_ {
    private DefaultDiagramModel model;
    public DefaultDiagramModel genModel(){
        if (model == null) block();
        return model;
    }
    private void block(){
        model = getModel();
        Element       //Mgmt = new Element("Client Mgmt",     "50em", "0em"),
                //seek = new Element("Client Search",     "30em", "0em"),
                Itinerary = new Element("Reserved Block Mgmt",         "10em", "0em"),
                currBooking = new Element("Available Reserved Block", "2em", "10em"),
                vendorType = new Element("Vendor Type (ie Hotel/Airline)", "2em", "20em"),
                vendorList = new Element("Vendor List", "2em", "30em"),
                resBlock = new Element("Booking Type (ie Suite/Coach) Reserved Block", "2em", "40em"),
                detail = new Element("Booking Detail", "25em", "50em"),
                
                flt = new Element("Route (ie LAX to JFK)",     "40em", "30em"),
                
                //maintClient1 = new Element("Client Maintenance (Add/Edit)", "40em", "10em"),
                maintVendor = new Element("Vendor Maintenance (Add/Edit)", "40em", "20em"),
                maintType = new Element("Type Maintenance (Add/Edit)", "40em", "40em");
                
                
               
                
                
                
                
                
        flt.setStyleClass("ui-diagram-element-maint2");       ;
        //Mgmt.setStyleClass("ui-diagram-fail");
        //seek.setStyleClass("ui-diagram-success");
        maintVendor.setStyleClass("ui-diagram-element-maint2");
        maintType.setStyleClass("ui-diagram-element-maint2");
        //maintClient1.setStyleClass("ui-diagram-element-maint2");
        
        Itinerary.setStyleClass("ui-diagram-fail");
        vendorType.setStyleClass("ui-diagram-success2");
        currBooking.setStyleClass("ui-diagram-success2");
        
        vendorList.setStyleClass("ui-diagram-element2");
        resBlock.setStyleClass("ui-diagram-element2");
        detail.setStyleClass("ui-diagram-fail");
        
        flt.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
        flt.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
        
        //Mgmt.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
        maintVendor.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM_LEFT));
        
        maintType.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
        
        
        //maintClient1.addEndPoint(new DotEndPoint(EndPointAnchor.TOP_LEFT));
        
        
        //seek.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
        //seek.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
        //seek.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
        
         Itinerary.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
         Itinerary.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
        
        currBooking.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
        currBooking.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
        
         vendorType.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
         vendorType.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
         //Options.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM_RIGHT));
        
         vendorList.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
         vendorList.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
         vendorList.addEndPoint(new DotEndPoint(EndPointAnchor.TOP_RIGHT));
         vendorList.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
         
         resBlock.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
         resBlock.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
         resBlock.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
         
         detail.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
         //payment.addEndPoint(new DotEndPoint(EndPointAnchor.TOP_RIGHT));
         

        
        
          //Select.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
         model.addElement(flt);
         //model.addElement(Mgmt);
        model.addElement(Itinerary);
        model.addElement(currBooking);
        model.addElement(vendorType);
        model.addElement(vendorList);
        model.addElement(resBlock);
        model.addElement(detail);
       
        //model.addElement(seek);
        model.addElement(maintVendor);
        model.addElement(maintType);
        //model.addElement(maintClient1);
        
         model.connect(createConnection(vendorList.getEndPoints().get(3), flt.getEndPoints().get(0),null));
         
        //model.connect(createConnection(Mgmt.getEndPoints().get(0), seek.getEndPoints().get(2),null));
        //model.connect(createConnection(seek.getEndPoints().get(0), Itinerary.getEndPoints().get(1),null));
        
        
        //model.connect(createConnection(seek.getEndPoints().get(1), maintClient1.getEndPoints().get(0),null));
        //model.connect(createConnection(maintClient1.getEndPoints().get(0), seek.getEndPoints().get(1), null));
        
        model.connect(createConnection(Itinerary.getEndPoints().get(0), currBooking.getEndPoints().get(0),"Booking"));
        
        model.connect(createConnection(currBooking.getEndPoints().get(1), vendorType.getEndPoints().get(0), "Add"));
        
        
        
        model.connect(createConnection(vendorType.getEndPoints().get(1), vendorList.getEndPoints().get(0), null));
        
        
        model.connect(createConnection(vendorList.getEndPoints().get(1), resBlock.getEndPoints().get(0), null));
        model.connect(createConnection(flt.getEndPoints().get(1), resBlock.getEndPoints().get(0), null));
        
        model.connect(createConnection(vendorList.getEndPoints().get(2), maintVendor.getEndPoints().get(0),null));
        model.connect(createConnection(maintVendor.getEndPoints().get(0), vendorList.getEndPoints().get(2),null));
        
        
        model.connect(createConnection(resBlock.getEndPoints().get(1), detail.getEndPoints().get(0), null));
        //model.connect(createConnection(vendorType.getEndPoints().get(1), detail.getEndPoints().get(0), "$"));
         model.connect(createConnection(currBooking.getEndPoints().get(1), detail.getEndPoints().get(0), "Edit"));
        
       
        
        model.connect(createConnection(resBlock.getEndPoints().get(2), maintType.getEndPoints().get(0),null));
        model.connect(createConnection(maintType.getEndPoints().get(0), resBlock.getEndPoints().get(2),null));
        
       
        
    }
}
