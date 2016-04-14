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
 * @author lmeans
 */
@ManagedBean
@RequestScoped
public class jlrFlow_Client_Roommate extends jlrFlow_ {
private DefaultDiagramModel model;
    public DefaultDiagramModel getRoommate(){
        if (model == null)genRoommate();
        return model;
    }
    
    private void genRoommate() {
        model = getModel();
        Element Mgmt = new Element("Client Mgmt", "40em", "2em"),
                maintV = new Element("Vendor Maintenance", "40em", "40em"),
                maintC = new Element("Client Maintenance", "40em", "50em"),
                
                maintC2 = new Element("Client Maintenance", "40em", "10em"),
                
                Seek = new Element("Client Search", "10em", "2em"),
                
                Rpt = new Element("Show Itinerary", "10em", "10em"),
                List = new Element("Display Current Roommate", "2em", "20em"),
                Book = new Element("Vendor Type Selection", "2em", "30em"),
                Vendor = new Element("Vendor Selection", "2em", "40em"),
                Select = new Element("Select Roommates", "15em", "50em");
        Mgmt.setStyleClass("ui-diagram-success");
        maintV.setStyleClass("ui-diagram-element-maint2");
        maintC.setStyleClass("ui-diagram-element-maint2");
        maintC2.setStyleClass("ui-diagram-element-maint2");
        
        Seek.setStyleClass("ui-diagram-fail");
        Rpt.setStyleClass("ui-diagram-success");
        
        List.setStyleClass("ui-diagram-element2");
        Book.setStyleClass("ui-diagram-element2");
        Vendor.setStyleClass("ui-diagram-element2");
        Select.setStyleClass("ui-diagram-fail");
        maintV.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
        ;
        maintC.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
        
        
        maintC2.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
        
        
        Mgmt.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
        
         Seek.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
        Seek.addEndPoint(new DotEndPoint(EndPointAnchor.TOP_RIGHT));
        Seek.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM_RIGHT));
        
         Rpt.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
         Rpt.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
        
         List.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
         List.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
         List.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
         
          Book.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
         Book.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
         
         Vendor.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
         Vendor.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
         Vendor.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
        
          Select.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
          Select.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
           Select.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
          //Select.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
        model.addElement(Seek);
        model.addElement(Rpt);
        model.addElement(List);
        model.addElement(Book);
        model.addElement(Vendor);
        model.addElement(Select);
        model.addElement(Mgmt);
        model.addElement(maintV);
        model.addElement(maintC);
        model.addElement(maintC2);
        
        model.connect(createConnection(Seek.getEndPoints().get(2), maintC2.getEndPoints().get(0),null));
        model.connect(createConnection(maintC2.getEndPoints().get(0), Seek.getEndPoints().get(2), null));
        
        model.connect(createConnection(Seek.getEndPoints().get(0), Rpt.getEndPoints().get(0),null));
        model.connect(createConnection(Mgmt.getEndPoints().get(0), Seek.getEndPoints().get(1), null));
        
        model.connect(createConnection(Rpt.getEndPoints().get(1), List.getEndPoints().get(0), "Click Roommate Button"));
        model.connect(createConnection(List.getEndPoints().get(2), Select.getEndPoints().get(1), "Edit"));
        
        model.connect(createConnection(List.getEndPoints().get(1), Book.getEndPoints().get(0), "Add"));
        
        model.connect(createConnection(Book.getEndPoints().get(1), Vendor.getEndPoints().get(0), null));
        model.connect(createConnection(Vendor.getEndPoints().get(1), Select.getEndPoints().get(0),null));
        
        model.connect(createConnection(Vendor.getEndPoints().get(2), maintV.getEndPoints().get(0),null));
        model.connect(createConnection(maintV.getEndPoints().get(0), Vendor.getEndPoints().get(2),null));
        
        
        model.connect(createConnection(Select.getEndPoints().get(2), maintC.getEndPoints().get(0),null));
        model.connect(createConnection(maintC.getEndPoints().get(0), Select.getEndPoints().get(2),null));
        
        
    }
}
