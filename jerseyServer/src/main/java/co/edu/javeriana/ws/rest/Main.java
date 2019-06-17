package co.edu.javeriana.ws.rest;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import org.glassfish.grizzly.ssl.SSLContextConfigurator;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;

/**
 * Main class.
 *
 */
public class Main {

    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "https://localhost:8443/WSGuardian/api/";
    private static final String KEYSTORE_LOC = "./server.keystore";
    private static final String KEYSTORE_PASS = "javeriana";

    private static final String TRUSTSTORE_LOC = "./server.truststore";
    private static final String TRUSTSTORE_PASS = "javeriana";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
     * application.
     *
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {

        final ResourceConfig rc = new ResourceConfig().packages("co.edu.javeriana.ws.rest");
        SSLContextConfigurator sslCon = new SSLContextConfigurator();

        sslCon.setKeyStoreFile(KEYSTORE_LOC);
        sslCon.setKeyStorePass(KEYSTORE_PASS);

        sslCon.setTrustStoreFile(TRUSTSTORE_LOC);
        sslCon.setTrustStorePass(TRUSTSTORE_PASS);

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc, true, new SSLEngineConfigurator(sslCon).setClientMode(false).setNeedClientAuth(false));

        // create a resource config that scans for JAX-RS resources and providers
        // in co.edu.javeriana.ws.rest package
        // final ResourceConfig rc = new ResourceConfig().packages("co.edu.javeriana.ws.rest");
        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        // return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }
}
