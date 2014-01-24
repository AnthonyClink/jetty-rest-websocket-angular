package no.mesan.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.sun.jersey.spi.container.servlet.ServletContainer;

public class JettyStarter {
    public static void main( final String[] args ) throws Exception {
        final Server server = new Server(8080);
        final ServletContextHandler servletContextHandler = new ServletContextHandler(server, "/");
        servletContextHandler.addServlet(getJerseyServlet(), "/*");
        servletContextHandler.setHandler(getResourceHandler());
        server.start();
        server.join();
    }

    private static ServletHolder getJerseyServlet() {
        final ServletHolder servletHolder = new ServletHolder(ServletContainer.class);
        servletHolder.setInitParameter("com.sun.jersey.config.property.packages", "no.mesan.rest");
        return servletHolder;
    }

    private static ResourceHandler getResourceHandler() {
        final ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("src/main/webapp/");
        resourceHandler.setWelcomeFiles(new String[] {"index.html"});
        return resourceHandler;
    }
}
