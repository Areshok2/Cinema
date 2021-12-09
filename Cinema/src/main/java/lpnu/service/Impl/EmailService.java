//package lpnu.service.Impl;
//
//import lpnu.Application;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.event.EventListener;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmailService {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    public void sendSimpleEmail(final String toEmail,
//                                final String body,
//                                final String subject) {
//        final SimpleMailMessage message = new SimpleMailMessage();
//
//        message.setFrom("testlpnulab4@gmail.com");
//        message.setTo(toEmail);
//        message.setText(body);
//        message.setSubject(subject);
//
//        mailSender.send(message);
//        System.out.println("Mail Send...");
//    }
//////////////////////////////////////////////////
//
//    @Autowired
//    private EmailSenderService service;
//
//    @EventListener(Application.class)
//        public void triggerMail() {
//        service.sendSimpleEmail("yrameda0404@gmail.com",
//                "Email body...",
//                "Email subject...")
//    }
//
//}
