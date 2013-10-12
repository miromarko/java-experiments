package ro.springsoft.poc.mdb;

import ro.springsoft.poc.model.PocMessage;
import ro.springsoft.poc.persistence.DAOFactory;
import ro.springsoft.poc.persistence.PocMessageDAO;

import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.logging.Logger;

/**
 * @author Miroslav MARKO
 */
//Implementation of JEE MDB
@MessageDriven(mappedName = "pocqueue")
public class PocMessageListener implements MessageListener {

    private static final Logger LOG = Logger.getLogger(PocMessageListener.class.getName());

    private PocMessageDAO pocMessageDAO;

    public PocMessageListener() {
        pocMessageDAO = DAOFactory.getInstance().getPocMessageDAO();
    }


    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            PocMessage pm = PocMessage.parseMessage(text);
            pm.setTimestamp(textMessage.getJMSTimestamp());
            //insert or update date in DB
            boolean updated = pocMessageDAO.upsert(pm);
            LOG.info("**** Message: " + pm + " with timestamp: " + textMessage.getJMSTimestamp() + " has been updated/inserted in DB: " + updated);

        } catch (Exception e) {
            LOG.severe(e.toString());
        }
    }
}
