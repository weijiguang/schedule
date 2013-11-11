/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.test;

import org.eclipse.jetty.server.Server;

/**
 *
 * @author Weir
 */
public class RunJetty {

    public static int port = 8088;
    public static String contextPath = "/";
    private Server server = JettyFactory.getServer(port, contextPath);

    public void run() throws Exception {
        server.start();
    }
    
    public void stop() throws Exception {
        server.stop();
        server.join();
    }

    public static void main(String[] args) throws Exception {
        RunJetty jetty = new RunJetty();
        jetty.run();
    }
}
