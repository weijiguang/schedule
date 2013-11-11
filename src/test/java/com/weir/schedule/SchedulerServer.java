/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 *
 * @author Weir
 */
public class SchedulerServer {

    public static final int PORT = 8088;
    public static final String CONTEXT = "/";
    private static final String DEFAULT_WEBAPP_PATH = "src/main/webapp";

    public static void main(String[] args) throws Exception {
        Server server = SchedulerServer.newServer(PORT, CONTEXT);
        server.start();

        //wait for close
//        System.in.read();
//        server.stop();
//        server.join();
//        System.out.println("Server stopped");
    }

    /**
     * 创建用于开发运行调试的Jetty Server, 以src/main/webapp为Web应用目录.
     */
    public static Server newServer(int port, String contextPath) {
        Server server = new Server();
        //设置在JVM退出时关闭Jetty的钩子。
        server.setStopAtShutdown(true);

        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(port);
        //解决Windows下重复启动Jetty居然不报端口冲突的问题.
        connector.setReuseAddress(false);
        server.setConnectors(new Connector[]{connector});

        WebAppContext webContext = new WebAppContext(DEFAULT_WEBAPP_PATH, contextPath);
        //设置使用当前线程的ClassLoder以解决JSP不能正确扫描taglib的tld文件的问题。
        webContext.setClassLoader(Thread.currentThread().getContextClassLoader());
        server.setHandler(webContext);

        return server;
    }
}
