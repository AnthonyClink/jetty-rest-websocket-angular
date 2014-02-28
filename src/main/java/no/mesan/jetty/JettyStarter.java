package no.mesan.jetty;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.websocket.DeploymentException;

import no.mesan.websocket.WebsocketTest;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.eclipse.jetty.websocket.jsr356.server.ServerContainer;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

/**
 * @author Knut Esten Melandsø Nekså
 */
public class JettyStarter {
    public static void main( final String[] args ) throws Exception {
        final Server server = new Server(8000);

        final ServletContextHandler webSocketServletContext = getWebSocketContext();
        final ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.setHandlers(new Handler[]{
                webSocketServletContext,
                getRestServletContext(),
                getAngularContext()
        });

        server.setHandler(contexts);
        initializeWebSocketServlet(webSocketServletContext);

        server.start();
        server.join();
    }

    private static void initializeWebSocketServlet(final ServletContextHandler webSocketServletContext)
            throws DeploymentException {
        final ServerContainer webSocketContainer =
                WebSocketServerContainerInitializer.configureContext(webSocketServletContext);
        webSocketContainer.addEndpoint(WebsocketTest.class);
    }

    private static ServletContextHandler getWebSocketContext() {
        final ServletContextHandler webSocketServletContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
        webSocketServletContext.setContextPath("/ws");
        return webSocketServletContext;
    }

    private static ContextHandler getAngularContext() {
        final ContextHandler vaktContext = new ContextHandler();
        final ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("src/main/webapp/bin");
        vaktContext.setHandler(resourceHandler);
        vaktContext.setContextPath("/");
        return vaktContext;
    }

    private static ServletContextHandler getRestServletContext() {
        final ServletContextHandler restServletContext = new ServletContextHandler();
        restServletContext.setContextPath("/rest");
        restServletContext.addServlet(getResteasyServlet(), "/*");
        final FilterHolder filterHolder = new FilterHolder();
        filterHolder.setFilter(new CrossOriginFilter());
        restServletContext.addFilter(CrossOriginFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        return restServletContext;
    }

    private static ServletHolder getResteasyServlet() {
        final ServletHolder servletHolder = new ServletHolder(HttpServletDispatcher.class);
        servletHolder.setInitParameter("resteasy.resources", "no.mesan.rest.HelloWorld");
        return servletHolder;
    }

    private static ResourceHandler getResourceHandler(final String systemResourcePath) {
        final ResourceHandler resourceHandler = new ResourceHandler();

        final String resourceBase = ClassLoader.getSystemResource(systemResourcePath).toExternalForm();
        resourceHandler.setResourceBase(resourceBase);

        resourceHandler.setWelcomeFiles(new String[] {"index.html"});
        return resourceHandler;
    }
}
