import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import java.io.File;
import java.util.List;

public class Mailer {

    public void createAndSendMail(final String msg, final List<File> attachments) throws EmailException {

        final MultiPartEmail email = new MultiPartEmail();
        email.setHostName("mail.gmx.net");
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator("alex.gempp@gmx.de", "Drei5und40"));
        email.setSSLOnConnect(true);
        email.setFrom("alex.gempp@gmx.de");
        email.setSubject("Die Ergebnisse sind da");
        email.setMsg(msg);
        email.addTo("alexander.gempp@gmail.com");

        for (File f: attachments) {
            email.attach(f);
        }

        email.send();

        System.out.println("eMail sent.");
    }
}
