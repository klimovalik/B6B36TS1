package cz.cvut.fel.ts1.lab05.refactoring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

public class MockMailHelperTest {
    DBManager mockedDBManager = Mockito.mock(DBManager.class);
    MailHelper mailHelper = new MailHelper(mockedDBManager);

    @Test
    public void negativeVerifySendMailTwice() {
        // mailHelper.sendMail(Mockito.anyInt());
        Mockito.verify(mockedDBManager, times(2)).findMail(Mockito.anyInt());
    }

    @Test
    public void positiveVerifySendMailOnce() {
        mailHelper.sendMail(Mockito.anyInt());
        Mockito.verify(mockedDBManager).findMail(Mockito.anyInt());
    }

    @Test
    public void mockTest() {
        Mail mail = getMail();
        int mailID = 1;

        Mockito.when(mockedDBManager.findMail(mailID)).thenReturn(mail);
        mailHelper.sendMail(mailID);
        // check that findMail was called 1 time
        Mockito.verify(mockedDBManager).findMail(mailID);
    }

    @Test
    public void CheckMailReturnsCorrectTo() {
        int mailID = Mockito.anyInt();
        Mockito.when(mockedDBManager.findMail(mailID)).thenReturn(getMail());
        mailHelper.sendMail(mailID);
        Assertions.assertEquals("TO", mailHelper.getMail().getTo());
    }

    @Test
    public void CheckMailReturnsCorrectBody() {
        int mailID = Mockito.anyInt();
        Mockito.when(mockedDBManager.findMail(mailID)).thenReturn(getMail());
        mailHelper.sendMail(mailID);
        Assertions.assertEquals("BODY", mailHelper.getMail().getBody());
    }

    private Mail getMail() {
        Mail mail = new Mail();
        mail.setTo("TO");
        mail.setSubject("SUBJECT");
        mail.setBody("BODY");
        return mail;
    }
}
