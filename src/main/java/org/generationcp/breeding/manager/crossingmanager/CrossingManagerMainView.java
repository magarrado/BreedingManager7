/**
 * 
 */
package org.generationcp.breeding.manager.crossingmanager;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.VerticalLayout;


/**
 * @author Mark Agarrado
 *
 */
public class CrossingManagerMainView extends VerticalLayout implements View{

    public static final String NAME = "crosses";

    @Override
    public void enter(ViewChangeEvent event) {
        Page.getCurrent().setTitle("CrossingManager");
        setSizeUndefined();
        
        CrossingManagerMain crossingManager = new CrossingManagerMain(this);
        
        this.addComponent(crossingManager);
    }

}
