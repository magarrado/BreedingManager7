package org.generationcp.breeding.manager.application;

import org.generationcp.breeding.manager.crossingmanager.CrossingManagerMainView;
import org.generationcp.commons.vaadin.actions.UpdateComponentLabelsAction;
import org.generationcp.commons.vaadin.spring.SimpleResourceBundleMessageSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@Theme("gcp-default")
@Component("breedingManagerApplication")
@Scope("session")
public class BreedingManagerApplication extends UI implements ApplicationContextAware {
    private final static Logger LOG = LoggerFactory.getLogger(BreedingManagerApplication.class);

    private static final long serialVersionUID = 1L;
    
    //public static final String GERMPLASM_IMPORT_WINDOW_NAME = "germplasm-import";
    //public static final String CROSSING_MANAGER_WINDOW_NAME = "crosses";
    //public static final String NURSERY_TEMPLATE_WINDOW_NAME = "nursery-template";
    
    @Autowired
    private SimpleResourceBundleMessageSource messageSource;
    
    private UpdateComponentLabelsAction messageSourceListener;

    private ApplicationContext applicationContext;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    
    public void setMessageSource(SimpleResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    //public void initSpringApplication(ConfigurableWebApplicationContext arg0) {
    protected void init(VaadinRequest request) {
        
        //FIXME: uncomment once resolved
        /*messageSourceListener = new UpdateComponentLabelsAction(this);
        messageSource.addListener(messageSourceListener);*/
        
        // Create Navigator, use the UI content layout to display the views
        Navigator navigator = new Navigator(this, this);

        // Add some Views
        navigator.addView(BreedingManagerMainView.NAME, new BreedingManagerMainView()); // no fragment
        navigator.addView(CrossingManagerMainView.NAME, new CrossingManagerMainView());
        
        // Override the existing error handler that shows the stack trace
        // FIXME
        //setErrorHandler(this);
    }

    //FIXME: implement navigation
    /*@Override
    public Window getWindow(String name) {
        // dynamically create other application-level windows which is associated with specific URLs
        // these windows are the jumping on points to parts of the application
        if(super.getWindow(name) == null){
            if(name.equals(GERMPLASM_IMPORT_WINDOW_NAME)){
                Window germplasmImportWindow = new Window(messageSource.getMessage(Message.IMPORT_GERMPLASM_LIST_TAB_LABEL));
                germplasmImportWindow.setName(GERMPLASM_IMPORT_WINDOW_NAME);
                germplasmImportWindow.setSizeUndefined();
                germplasmImportWindow.addComponent(new GermplasmImportMain());
                this.addWindow(germplasmImportWindow);
                return germplasmImportWindow;
            } else if(name.equals(CROSSING_MANAGER_WINDOW_NAME)){
                Window crossingManagerWindow = new Window(messageSource.getMessage(Message.CROSSING_MANAGER_TAB_LABEL));
                crossingManagerWindow.setName(CROSSING_MANAGER_WINDOW_NAME);
                crossingManagerWindow.setSizeUndefined();
                crossingManagerWindow.addComponent(new CrossingManagerMain(crossingManagerWindow));
                this.addWindow(crossingManagerWindow);
                return crossingManagerWindow;
            } else if(name.equals(NURSERY_TEMPLATE_WINDOW_NAME)){
                Window nurseryTemplateWindow = new Window(messageSource.getMessage(Message.NURSERY_TEMPLATE_TAB_LABEL));
                nurseryTemplateWindow.setName(NURSERY_TEMPLATE_WINDOW_NAME);
                nurseryTemplateWindow.setSizeUndefined();
                nurseryTemplateWindow.addComponent(new NurseryTemplateMain());
                this.addWindow(nurseryTemplateWindow);
                return nurseryTemplateWindow;
            }
        }
        
        return super.getWindow(name);
    }*/


    /** 
     * Override terminalError() to handle terminal errors, to avoid showing the stack trace in the application 
     */
    //FIXME: Check after migrate to Vaadin 7 
    /*@Override
    public void terminalError(Terminal.ErrorEvent event) {
        LOG.error("An unchecked exception occurred: ", event.getThrowable());
        event.getThrowable().printStackTrace();
        // Some custom behaviour.
        if (getMainWindow() != null) {
            MessageNotifier.showError(messageSource.getMessage(Message.ERROR_INTERNAL),  // TESTED
                    messageSource.getMessage(Message.ERROR_PLEASE_CONTACT_ADMINISTRATOR)
                            + (event.getThrowable().getLocalizedMessage() == null ? "" : "</br>"
                                    + event.getThrowable().getLocalizedMessage()));
        }
    }*/

    @Override
    public void close() {
        super.close();

        // implement this when we need to do something on session timeout
        messageSource.removeListener(messageSourceListener);

        LOG.debug("Application closed");
    }
    
    //FIXME: Uncomment when integrated with Spring
    /*public static BreedingManagerApplication get() {
        return get(BreedingManagerApplication.class);
    }   

    @Override
    protected void doOnRequestStart(HttpServletRequest request, HttpServletResponse response) {
        super.doOnRequestStart(request, response);
        
        LOG.trace("Request started " + request.getRequestURI() + "?" + request.getQueryString());
        
        synchronized (this) {
            HttpRequestAwareUtil.onRequestEnd(applicationContext, request, response);
        }
    }
    
    @Override
    protected void doOnRequestEnd(HttpServletRequest request, HttpServletResponse response) {
        super.doOnRequestEnd(request, response);
        
        LOG.trace("Request ended " + request.getRequestURI() + "?" + request.getQueryString());
        
        synchronized (this) {
            HttpRequestAwareUtil.onRequestEnd(applicationContext, request, response);
        }
    }*/

}
