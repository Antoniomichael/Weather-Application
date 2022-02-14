package amg.technicalevaluation.kracekennedyemployeeapplication.model;

import com.sun.mail.smtp.SMTPTransport;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;

public final class Email {

    private  static LocalDate localDate = LocalDate.now();
    private static final String USERNAME = "antonioveteran@hotmail.com";
    private static final String PASSWORD = "Veronica";
    private static final String SMTP_SERVER = "smtp-mail.outlook.com";
    private static final String EMAIL_FROM = "antonioveteran@hotmail.com";
    private static final String EMAIL_TO_CC = "";

    private static final String EMAIL_SUBJECT_GENERALWORKERS = "Work Hours";
    private static final String EMAIL_SUBJECT_ITWORKERS = "Work Hours";
    private static  String ITMessageText = "Good Day, \n" +
            "Please note that for tomorrow's shift " + localDate.plusDays(1) + ", you are not expected to be on the road. \n Respectfully, \n\n Management";;

    private static  String GeneralWorkersText =  "Good Day, \n" +
            "Please note that for tomorrow's shift " + localDate.plusDays(1) + ",  you are not required to work the full 8 hour shift, " +
            "but instead you will be required to only work a 4 hour shift. \n Respectfully, \n\n Management";
    public Email(){
//


    }

    public static void EmailGeneralWorkers(String EMAIL_TO){

            Properties prop = System.getProperties();
            prop.put("mail.smtp.host", SMTP_SERVER); //optional, defined in SMTPTransport
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.port", "587"); // default port 25
            prop.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(prop, null);
            Message msg = new MimeMessage(session);

            try {
                msg.setFrom(new InternetAddress(EMAIL_FROM));
                msg.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(EMAIL_TO, false));

                msg.setRecipients(Message.RecipientType.CC,
                        InternetAddress.parse(EMAIL_TO_CC, false));

                msg.setSubject(EMAIL_SUBJECT_GENERALWORKERS);
                msg.setText(GeneralWorkersText);
                msg.setSentDate(new Date());

                SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

                t.connect(SMTP_SERVER, USERNAME, PASSWORD);
                t.sendMessage(msg, msg.getAllRecipients());
                System.out.println("Response: " + t.getLastServerResponse());
                t.close();

            } catch (MessagingException e) {
                e.printStackTrace();
            } catch(NullPointerException npe){
                System.out.println("Null message");
                npe.printStackTrace();
            }
    }

    public static void EmailITWorkers(String EMAIL_TO){

        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", SMTP_SERVER); //optional, defined in SMTPTransport
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "587"); // default port 25
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);

        try {

            // from
            msg.setFrom(new InternetAddress(EMAIL_FROM));

            // to
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(EMAIL_TO, false));

            // cc
            msg.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(EMAIL_TO_CC, false));

            msg.setSubject(EMAIL_SUBJECT_ITWORKERS);
            msg.setText(ITMessageText);
            msg.setSentDate(new Date());
            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

            t.connect(SMTP_SERVER, USERNAME, PASSWORD);
            t.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Response: " + t.getLastServerResponse());
            t.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch(NullPointerException npe){
            System.out.println("Null message");
            npe.printStackTrace();
        }
    }


}
