/**
 * 
 */
package org.generationcp.breeding.manager.application;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.generationcp.commons.hibernate.util.HttpRequestAwareUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.xpoft.vaadin.SpringApplicationContext;
import ru.xpoft.vaadin.SpringVaadinServlet;

/**
 * @author Mark Agarrado
 *
 */
public class IbwsSpringVaadinServlet extends SpringVaadinServlet {
	
	private final static Logger LOG = LoggerFactory.getLogger(IbwsSpringVaadinServlet.class);

	private static final long serialVersionUID = -7095906197886688425L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        onRequestStart(request, response);
        super.service(request, response);
        onRequestEnd(request, response);
        
	}
	
	private void onRequestStart(HttpServletRequest request, HttpServletResponse response) {
		LOG.trace("Request started " + request.getRequestURI() + "?" + request.getQueryString());
        
        synchronized (this) {
            HttpRequestAwareUtil.onRequestStart(SpringApplicationContext.getApplicationContext(), request, response);
        }
	}
	
	private void onRequestEnd(HttpServletRequest request, HttpServletResponse response) {
		LOG.trace("Request ended " + request.getRequestURI() + "?" + request.getQueryString());
        
        synchronized (this) {
            HttpRequestAwareUtil.onRequestEnd(SpringApplicationContext.getApplicationContext(), request, response);
        }
	}
}
