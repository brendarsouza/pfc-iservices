/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Model.Usuario;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author brenda
 */
public class EmailJava {

    Email email;
    Usuario usuario;

    public EmailJava() {
        email = new SimpleEmail();
        configure();
    }

    private void configure() {
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(587);
        email.setDebug(true);
        email.setAuthentication("iservices.pfc@gmail.com", "iservices0327");
        email.setSSLOnConnect(true);
    }

    public void enviarEmail(String from, String subject, String msg, String to) {
        try {

            email.setFrom(from);
            email.setSubject(subject);
            email.setMsg(msg);
            email.addTo(to);
            email.addReplyTo(from);
            email.send();
        } catch (EmailException ex) {
            Logger.getLogger(EmailJava.class.getName()).log(Level.SEVERE, null, ex);
        }

 
    }

}
