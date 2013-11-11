/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.application;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 *
 * @author Weir
 */
public class ShowcaseExceptionHandlerFactory extends ExceptionHandlerFactory {

    private ExceptionHandlerFactory base;

    public ShowcaseExceptionHandlerFactory(ExceptionHandlerFactory base) {
        this.base = base;
    }

    @Override
    public ExceptionHandler getExceptionHandler() {
        return new ShowcaseExceptionHandler(base.getExceptionHandler());
    }
}