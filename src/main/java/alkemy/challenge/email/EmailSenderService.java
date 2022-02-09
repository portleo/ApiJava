/**
 *
 * @author Leonardo Portuguez
 * mail l.portuguez91@gmail.com
 * 
 */

package alkemy.challenge.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    
@Autowired
private JavaMailSender mailSender;
    
    public void sendSimpleEmail(String toEmail){
        SimpleMailMessage message= new SimpleMailMessage();
       message.setFrom("vargaszsebastian@gmail.com");
       message.setTo(toEmail);
       message.setText(" Usted se ha registrado en Diney Alkemy. ");
       message.setSubject("Bienvenido");
       
      mailSender.send(message);
    }
}
