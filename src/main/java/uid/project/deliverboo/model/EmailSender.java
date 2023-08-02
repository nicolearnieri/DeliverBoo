package uid.project.deliverboo.model;
import org.simplejavamail.MailException;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.api.mailer.config.TransportStrategy;

public class EmailSender {
    private static final String username = "deliverboo@libero.it";
    private static final String password = "progettoUID1!";

    public static void sendEmail(String recipient, String subject, String body) {
        // Creazione dell'oggetto Email utilizzando Simple Java Mail
        Email email = EmailBuilder.startingBlank()
                .from(username) // In Gmail, l'indirizzo e-mail del mittente deve corrispondere al nome utente (username).
                .to(recipient)
                .withSubject(subject)
                .withPlainText(body)
                .buildEmail();

        // Configurazione del Mailer per inviare l'e-mail utilizzando il server SMTP di Gmail
        Mailer mailer = MailerBuilder.withSMTPServer("smtp.libero.it", 587, username, password)
                .withTransportStrategy(TransportStrategy.SMTP_TLS) // Usa TLS per la crittografia
                .buildMailer();

        // Invio dell'e-mail
        mailer.sendMail(email);

        System.out.println("Email inviata con successo a " + recipient);
    }
}
