/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Weir
 */
@Named
@ViewScoped
public class LoginController {

    private final static Logger log = LoggerFactory.getLogger(LoginController.class);
    String username;
    String password;
    boolean rememberMe = false;

    public String authenticate() {

        // Example using most common scenario of username/password pair:
        UsernamePasswordToken token = new UsernamePasswordToken(username,
                password);

        // "Remember Me" built-in:
        token.setRememberMe(rememberMe);

        Subject currentUser = SecurityUtils.getSubject();

        log.info("Submitting login with username of " + username
                + " and password of " + password);

        try {
            currentUser.login(token);
        } catch (AuthenticationException e) {
            // Could catch a subclass of AuthenticationException if you like
            log.warn(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage("Login Failed: " + e.getMessage(), e
                    .toString()));
            return "/pages/login";
        }
        return "/pages/job/list?faces-redirect=true";
    }

    public String logout() {

        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.logout();
        } catch (Exception e) {
            log.warn(e.toString());
        }
        return "/pages/login";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
