package no.mesan.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.sun.jersey.spi.container.servlet.ServletContainer;

public class JettyStarter {
    public static void main( final String[] args ) throws Exception {
        final ServletHolder servletHolder = new ServletHolder(ServletContainer.class);
        servletHolder.setInitParameter("com.sun.jersey.config.property.packages", "no.mesan.rest");

        final Server server = new Server(8080);
        final ServletContextHandler handler = new ServletContextHandler(server, "/");
        handler.addServlet(servletHolder, "/*");
        server.start();
        server.join();
    }
}
