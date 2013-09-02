/**
 * 
 */
package org.generationcp.breeding.manager.application;

import org.generationcp.breeding.manager.crosses.NurseryTemplateMain;
import org.generationcp.breeding.manager.crossingmanager.CrossingManagerMain;
import org.generationcp.breeding.manager.listimport.GermplasmImportMain;
import org.generationcp.commons.exceptions.InternationalizableException;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;


/**
 * @author Mark Agarrado
 *
 */
public class BreedingManagerMainView extends VerticalLayout implements View{
    
    private static final long serialVersionUID = 3590910649413688977L;

    public static final String NAME = "";
    
    private VerticalLayout rootLayoutForImportGermplasmList;
    private VerticalLayout rootLayoutForCrossingManager;
    private VerticalLayout rootLayoutForNurseryTemplate;

    @Override
    public void enter(ViewChangeEvent event) {
        this.rootLayoutForImportGermplasmList = new VerticalLayout();
        rootLayoutForImportGermplasmList.setSizeFull();

        this.rootLayoutForCrossingManager = new VerticalLayout();
        rootLayoutForCrossingManager.setSizeFull();
        
        this.rootLayoutForNurseryTemplate = new VerticalLayout();
        rootLayoutForNurseryTemplate.setSizeFull();
        
        //window = new Window(messageSource.getMessage(Message.MAIN_WINDOW_CAPTION)); // "Breeding Manager"
        //Page.getCurrent().setTitle(messageSource.getMessage(Message.MAIN_WINDOW_CAPTION));
        Page.getCurrent().setTitle("BreedingManager");
        //window.setSizeUndefined();
        setSizeUndefined();

        TabSheet tabSheet = new TabSheet();
        
        VerticalLayout layouts[] = new VerticalLayout[3];
        layouts[0] = this.rootLayoutForImportGermplasmList;
        layouts[1] = this.rootLayoutForCrossingManager;
        layouts[2] = this.rootLayoutForNurseryTemplate;
        
        WelcomeTab welcomeTab = new WelcomeTab(tabSheet, layouts);
        //FIXME: uncomment once messagesource done
        /*tabSheet.addTab(welcomeTab, messageSource.getMessage(Message.WELCOME_TAB_LABEL)); // "Welcome"
        tabSheet.addTab(rootLayoutForImportGermplasmList, messageSource.getMessage(Message.IMPORT_GERMPLASM_LIST_TAB_LABEL)); // "Import Germlasm List"
        tabSheet.addTab(rootLayoutForCrossingManager, messageSource.getMessage(Message.CROSSING_MANAGER_LABEL)); // "Crossing Manager"
        tabSheet.addTab(rootLayoutForNurseryTemplate, messageSource.getMessage(Message.NURSERY_TEMPLATE_CAPTION_LABEL)); // "Nursery Template"*/
        tabSheet.addTab(welcomeTab, "Welcome"); // "Welcome"
        tabSheet.addTab(rootLayoutForImportGermplasmList, "Import Germlasm List"); // "Import Germlasm List"
        tabSheet.addTab(rootLayoutForCrossingManager, "Crossing Manager"); // "Crossing Manager"
        tabSheet.addTab(rootLayoutForNurseryTemplate, "Nursery Template"); // "Nursery Template"
        tabSheet.addListener(new MainApplicationSelectedTabChangeListener(this));
        
        this.addComponent(tabSheet);
    }
    
    public void tabSheetSelectedTabChangeAction(TabSheet source) throws InternationalizableException {

        if (source.getSelectedTab() == this.rootLayoutForImportGermplasmList) {
            if (this.rootLayoutForImportGermplasmList.getComponentCount() == 0) {
                rootLayoutForImportGermplasmList.addComponent(new GermplasmImportMain(this));
                rootLayoutForCrossingManager.addStyleName("addSpacing");
            } 
        }
        else if (source.getSelectedTab() == this.rootLayoutForCrossingManager) {
            if (this.rootLayoutForCrossingManager.getComponentCount() == 0) {
                rootLayoutForCrossingManager.addComponent(new CrossingManagerMain(rootLayoutForCrossingManager));
                rootLayoutForCrossingManager.addStyleName("addSpacing");
            }
        }
        else if (source.getSelectedTab() == this.rootLayoutForNurseryTemplate) {
            if (this.rootLayoutForNurseryTemplate.getComponentCount() == 0) {
                rootLayoutForNurseryTemplate.addComponent(new NurseryTemplateMain());
                rootLayoutForNurseryTemplate.addStyleName("addSpacing");
            }
        }
    }

}
