package no.mesan.jetty;

import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.security.Constraint;
import org.eclipse.jetty.util.security.Credential;

import com.sun.jersey.spi.container.servlet.ServletContainer;

public class JettyStarter {
    public static void main( final String[] args ) throws Exception {
        final Server server = new Server(8080);
        final ServletContextHandler servletContextHandler = new ServletContextHandler(server, "/");
        servletContextHandler.addServlet(getJerseyServlet(), "/*");
        servletContextHandler.setHandler(getSecurityHandler());
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
        resourceHandler.setWelcomeFiles(new String[] { "index.html" });
        return resourceHandler;
    }

    private static ConstraintMapping getConstraintMapping() {
        final Constraint constraint = new Constraint();
        constraint.setName(Constraint.__FORM_AUTH);
        constraint.setRoles(new String[] {"admin"});
        constraint.setAuthenticate(true);

        final ConstraintMapping constraintMapping = new ConstraintMapping();
        constraintMapping.setConstraint(constraint);
        constraintMapping.setPathSpec("/*");

        return constraintMapping;
    }

    private static ConstraintSecurityHandler getSecurityHandler() {
        final HashLoginService loginService = new HashLoginService();
        loginService.putUser("admin", Credential.getCredential("test"), new String[] {"admin"});

        final ConstraintSecurityHandler securityHandler = new ConstraintSecurityHandler();
        securityHandler.setAuthenticator(new BasicAuthenticator());
        securityHandler.addConstraintMapping(getConstraintMapping());
        securityHandler.setLoginService(loginService);

        return securityHandler;
    }
}
