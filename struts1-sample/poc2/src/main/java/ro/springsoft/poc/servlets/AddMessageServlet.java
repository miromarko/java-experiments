package ro.springsoft.poc.servlets;

import ro.springsoft.poc.model.PocMessage;

import javax.annotation.Resource;
import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet for inserting one message into the Queue
 *
 * @author Miroslav MARKO
 */
public class AddMessageServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(AddMessageServlet.class.getName());

    @Resource
    private ConnectionFactory connectionFactory;
    @Resource(name = "pocqueue")
    private Queue pocqueue;

    /**
     * * Processes requests for both HTTP
     * <p/>
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Connection connection = null;

        try {
            connection = connectionFactory.createConnection();
            connection.start();

            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create a MessageProducer from the Session to the Topic or Queue
            MessageProducer producer = session.createProducer(pocqueue);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            // Create a message
            PocMessage pocMessage = new PocMessage();
            pocMessage.setAccount("222-2222-222");
            Random r = new Random();
            pocMessage.setBallance(r.nextInt(1000));
            TextMessage message = session.createTextMessage(pocMessage.toString());

            // Tell the producer to send the message
            producer.send(message);
            //prevent activemq exceptions for fast JMS calls
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                LOG.info(ex.getMessage());
            }
            producer.close();
            session.close();
            connection.close();

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddMessageServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Message: " + message.getText() + "was send successfully to pocqueue!</h1>");
            out.println("</body>");
            out.println("</html>");
        } catch (JMSException ex) {
            Logger.getLogger(AddMessageServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (JMSException e) {
                    Logger.getLogger(AddMessageServlet.class.getName()).log(Level.SEVERE, null, e);
                }
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet for insert one message in Queue";
    }// </editor-fold>
}
