/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.application;

import java.util.Iterator;
import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

public class ShowcaseExceptionHandler extends ExceptionHandlerWrapper {

    private ExceptionHandler wrapped;

    public ShowcaseExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }

    @Override
    public void handle() throws FacesException {
        Iterable<ExceptionQueuedEvent> events = this.wrapped.getUnhandledExceptionQueuedEvents();
        for (Iterator<ExceptionQueuedEvent> it = events.iterator(); it.hasNext();) {
            ExceptionQueuedEvent event = it.next();
            ExceptionQueuedEventContext eqec = event.getContext();

            if (eqec.getException() instanceof ViewExpiredException) {
                FacesContext context = eqec.getContext();
                NavigationHandler navHandler = context.getApplication().getNavigationHandler();

                try {
                    navHandler.handleNavigation(context, null, "home?faces-redirect=true&expired=true");
                } finally {
                    it.remove();
                }
            }
        }

        this.wrapped.handle();
    }
}
