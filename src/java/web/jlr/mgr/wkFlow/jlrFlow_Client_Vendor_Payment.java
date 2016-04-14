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
public class jlrFlow_Client_Vendor_Payment  extends jlrFlow_ {
    private DefaultDiagramModel model;
    public DefaultDiagramModel genPayment(){
        if (model == null) payment();
        return model;
    }
    private void payment(){
        model = getModel();
        Element       Mgmt = new Element("Vendor Block Mgmt",     "30em", "1em"),
                Seek = new Element("Vendor Select",         "10em", "1em"),
                Itinerary = new Element("Payment History", "2em", "10em"),
                Options = new Element("Select Payment/Fee/Discount Type ", "2em", "20em"),
                Category = new Element("Payment Category (i.e. Deposit)", "2em", "30em"),
                Method = new Element("Payment Method (i.e. Cash)", "2em", "40em"),
                payment = new Element("Payment Amount ", "25em", "50em"),
                
                //maintClient1 = new Element("Client Maintenance (Add/Edit)", "40em", "10em"),
                maintCategoy = new Element("Category Maintenance (Add/Edit)", "40em", "30em"),
                maintMethod = new Element("Method Maintenance (Add/Edit)", "40em", "40em")
                
                
               
                
                
                
                
                
               ;
        Mgmt.setStyleClass("ui-diagram-success");
        maintCategoy.setStyleClass("ui-diagram-element-maint2");
        maintMethod.setStyleClass("ui-diagram-element-maint2");
        //maintClient1.setStyleClass("ui-diagram-element-maint2");
        
        Seek.setStyleClass("ui-diagram-fail");
        Options.setStyleClass("ui-diagram-success2");
        Itinerary.setStyleClass("ui-diagram-success2");
        
        Category.setStyleClass("ui-diagram-element2");
        Method.setStyleClass("ui-diagram-element2");
        payment.setStyleClass("ui-diagram-fail");
        
        maintCategoy.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
        
        maintMethod.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
        
        
        //maintClient1.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
        
        
        Mgmt.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
        
        Seek.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
         Seek.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
         
        
        Itinerary.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
        Itinerary.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
        
         Options.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
         Options.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
         //Options.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM_RIGHT));
        
         Category.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
         Category.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
         Category.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
         
         Method.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
         Method.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
         Method.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
         
         payment.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
         //payment.addEndPoint(new DotEndPoint(EndPointAnchor.TOP_RIGHT));
         

        
        
          //Select.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
        model.addElement(Seek);
        model.addElement(Itinerary);
        model.addElement(Options);
        model.addElement(Category);
        model.addElement(Method);
        model.addElement(payment);
       
        model.addElement(Mgmt);
        model.addElement(maintCategoy);
        model.addElement(maintMethod);
        //model.addElement(maintClient1);
        
        model.connect(createConnection(Mgmt.getEndPoints().get(0), Seek.getEndPoints().get(0),null));
        
        
        //model.connect(createConnection(Seek.getEndPoints().get(0), maintClient1.getEndPoints().get(0),null));
        model.connect(createConnection(Seek.getEndPoints().get(1), Itinerary.getEndPoints().get(0),null));
        
        model.connect(createConnection(Itinerary.getEndPoints().get(1), Options.getEndPoints().get(0), "Payment Selecded"));
        
        
        
        model.connect(createConnection(Options.getEndPoints().get(1), Category.getEndPoints().get(0), null));
        
        
        model.connect(createConnection(Category.getEndPoints().get(1), Method.getEndPoints().get(0), null));
        model.connect(createConnection(Category.getEndPoints().get(2), maintCategoy.getEndPoints().get(0),null));
        model.connect(createConnection(maintCategoy.getEndPoints().get(0), Category.getEndPoints().get(2),null));
        
        
        model.connect(createConnection(Method.getEndPoints().get(1), payment.getEndPoints().get(0), null));
        model.connect(createConnection(Options.getEndPoints().get(1), payment.getEndPoints().get(0), "$"));
         model.connect(createConnection(Seek.getEndPoints().get(1), payment.getEndPoints().get(0), "$"));
        
       
        
        model.connect(createConnection(Method.getEndPoints().get(2), maintMethod.getEndPoints().get(0),null));
        model.connect(createConnection(maintMethod.getEndPoints().get(0), Method.getEndPoints().get(2),null));
        
       
        
    }
}
