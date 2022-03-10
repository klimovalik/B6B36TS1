package cz.cvut.fel.ts1.lab04;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MailHelperTest {

     String to = "user@fel.cvut.cz";
     String subject = "Greeting";
     String body = "Hello!";

     Mail mail;

     @BeforeEach
     public void setMailHelper() {
          MailHelper mh = new MailHelper();;
          mh.createAndSendMail(to, subject, body);
          mail = mh.getMail();
     }

     @Test
     public void setTo_toIsSet_returnsTo() {
          String expectedResult = to;
          String actualResult = mail.getTo();
          Assertions.assertEquals(expectedResult, actualResult);
     }

     @Test
     public void setSubject_subjectIsSet_returnsSubject() {
          String expectedResult = subject;
          String actualResult = mail.getSubject();
          Assertions.assertEquals(expectedResult, actualResult);
     }

     @Test
     public void setBody_bodyIsSet_returnsBody() {
          String expectedResult = body;
          String actualResult = mail.getBody();
          Assertions.assertEquals(expectedResult, actualResult);
     }
}
