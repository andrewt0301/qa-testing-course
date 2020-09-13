package ru.hse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

class TestServer {

  private static Server server;

  private static int maxThreads = 100;
  private static int minThreads = 10;
  private static int idleTimeout = 120;

  public static void main(String[] args) throws Exception {

    QueuedThreadPool threadPool = new QueuedThreadPool(maxThreads, minThreads, idleTimeout);

    server = new Server(threadPool);
    ServerConnector connector = new ServerConnector(server);
    connector.setPort(8090);
    server.setConnectors(new Connector[] { connector });

    ServletHandler servletHandler = new ServletHandler();
    server.setHandler(servletHandler);

    servletHandler.addServletWithMapping(TestServlet.class, "/test");
    servletHandler.addServletWithMapping(StopServlet.class, "/stop");

    server.start();
  }

  public static class TestServlet extends HttpServlet {

    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {

      String sleep = request.getParameter("sleep");
      if (sleep != null) {
        try {
          System.out.println(sleep);
          Thread.sleep(Long.parseLong(sleep));
        }
        catch (Exception e) {
          // Nothing
        }
      }

      response.setContentType("application/json");
      response.setStatus(HttpServletResponse.SC_OK);
      response.getWriter().println("{ \"status\": \"ok\"}");
    }

  }

  public static class StopServlet extends HttpServlet {

    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {

      try {
        server.stop();

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
      }
      catch (Exception ex) {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().println("{ \"error\": \"" + ex.getMessage() + "\"}");
      }
    }

  }

}
