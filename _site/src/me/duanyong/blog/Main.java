package me.duanyong.blog;

import me.duanyong.handswork.profiling.Profiler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.TimeZone;

/**
 * Created by duanyong on 9/12/16.
 */
public class Main implements ApplicationContextAware {
    private static final Logger log = LogManager.getLogger();

    private static String appContextPath;
    private static Server server;
    private static ApplicationContext applicationContext;


    public void start() throws Exception {
        Profiler.start("Jetty started");
        appContextPath = Class.class.getClass().getResource("/").getPath();
        server = new Server();

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(18000);
//        connector.setReuseAddress(false);

        WebAppContext webAppContext = new WebAppContext(server, appContextPath, "/");
        webAppContext.setDescriptor(appContextPath + "WEB-INF/web.xml");
        webAppContext.setResourceBase(appContextPath + "WEB-ROOT");
        webAppContext.setConfigurationDiscovered(true);
        webAppContext.setParentLoaderPriority(true);

        server.setStopAtShutdown(true);
        server.setStopTimeout(3000L);
        server.setConnectors(new Connector[] { connector });
        server.setHandler(webAppContext);

        try {
            server.stop();
            server.start();


        } catch (Exception e) {
            //发生异常，停止服务
            commandStopJetty();
            log.error("Jetty was stopped, case by:" + e.getMessage());

        } finally {
            Profiler.finish();
        }

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        System.setProperty("user.timezone", "UTC");

        log.info("Set timezone: " + TimeZone.getDefault());

        // 合并主线程
        server.join();
    }

    // 特殊指令，stopJetty
    public static void commandStopJetty() {
        new Thread() {
            @Override
            public void run() {
                try {
                    server.stop();
                } catch (Exception e) {
                } finally {
                    log.info("Jetty is stopped: " + server.isStopped());
                }
            }
        }.start();
    }



    /**
         * Created by duanyong on 9/12/16.
         */
    public static void main(String[] args) throws Throwable {
        new Main().start();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Main.applicationContext = applicationContext;
    }
}
