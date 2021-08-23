package edu.pucmm.eict.util;

import java.util.Base64;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

public class EmailFallEvent {

    private String host;
    private int port;
    private boolean debug;

    private String username;
    private String password;

    private String senderEmail;

    public EmailFallEvent() {

        host 	= "74.125.133.109";
        port	= 587;
        debug	= true;

        username = "proyectosilladeruedasitt@gmail.com";
        password = "wheelchair1408";

        senderEmail = "proyectosilladeruedasitt@gmail.com";
    }

    public EmailFallEvent( String host, int port, String username, String password, String senderEmail ) {

        this.host 	= host;
        this.port	= port;
        this.debug	= true;

        this.username = username;
        this.password = password;

        this.senderEmail = senderEmail;
    }

    public void sendMail( String to, String subject,String content,String base64Image,double latitude,double longitude) {

        // Set Properties
        Properties props = new Properties();

        props.put( "mail.smtp.auth", "true" );
        props.put( "mail.smtp.host", host );
        props.put( "mail.smtp.port", port );
        props.put( "mail.smtp.starttls.enable", "true" );
        props.put( "mail.debug", debug );
        props.put( "mail.smtp.socketFactory.port", port );
        //props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put( "mail.smtp.socketFactory.fallback", "false" );
        props.put( "mail.smtp.ssl.trust", host );

        // Create the Session Object
        Session session = Session.getDefaultInstance(
                props,
                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {

                        return new PasswordAuthentication( username, password );
                    }
                }
        );
        try {
            MimeMessage message = new MimeMessage( session );
            message.setSubject(subject);
            message.setFrom(new InternetAddress(this.senderEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject( subject );

            String bodyHtml = "<h4>Información del usuario de la silla de ruedas:</h4>";
            bodyHtml += "<h4>"+content+"</h4>";
            bodyHtml += "\n";
            bodyHtml += "<h4>Imagen tomada por el sistema al detectar el evento:<h4>";
            bodyHtml += "\n";
            bodyHtml += "<img src=\"cid:qrImage\" alt=\"qr code\">";
            bodyHtml += "\n";
            bodyHtml += "<h4>Para ver la ubicación acceda al siguiente enlace:<h4>";
            bodyHtml += "\n";
            bodyHtml += "<h4>https://www.google.com/maps/search/?api=1&query="+latitude+","+longitude+"<h4>";


            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent(subject, "text/plain; charset=UTF-8");

            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(bodyHtml, "text/html; charset=UTF-8");

            Multipart multiPart = new MimeMultipart("alternative");

            // create a new imagePart and add it to multipart so that the image is inline attached in the email
            byte[] rawImage = Base64.getDecoder().decode(base64Image);
            BodyPart imagePart = new MimeBodyPart();
            ByteArrayDataSource imageDataSource = new ByteArrayDataSource(rawImage,"image/png");

            imagePart.setDataHandler(new DataHandler(imageDataSource));
            imagePart.setHeader("Content-ID", "<qrImage>");
            imagePart.setFileName("someFileName.png");

            multiPart.addBodyPart(imagePart);
            multiPart.addBodyPart(textPart);
            multiPart.addBodyPart(htmlPart);

            message.setContent(multiPart);

            Transport.send(message);
        } catch (MessagingException exception) {
            //log error.
            throw new RuntimeException( exception );
        }
    }

}
