package com.secil.service;

import com.secil.dto.ForgotPasswordMailResponseDto;
import com.secil.rabbitmq.model.RecipeMailModel;
import com.secil.rabbitmq.model.RegisterMailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
@Service
@RequiredArgsConstructor
public class MailSenderService {
    private final JavaMailSender javaMailSender;
    public void sendMail(RegisterMailModel registerMailModel){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("${spring.mail.username}");
        mailMessage.setTo(registerMailModel.getEmail());
        mailMessage.setSubject("Hesap Aktivasyonu");

        String emailContent = "Merhaba " + registerMailModel.getUsername() + ",\n\n" +
                "Kayıt olduğunuz için teşekkür ederiz. Hesabınızı etkinleştirmek için aşağıdaki aktivasyon kodunu kullanabilirsiniz:\n\n" +
                "Aktivasyon Kodu: " + registerMailModel.getActivationCode() + "\n\n" +
                "Eğer bu kayıt talebi sizin tarafınızdan yapılmadıysa, bu e-postayı görmezden gelebilirsiniz.\n\n" +
                "Saygılarımızla";

        mailMessage.setText(emailContent);

        javaMailSender.send(mailMessage);
    }



    public void sendRecipeMail(RecipeMailModel recipeMailModel){
        System.out.println(recipeMailModel);
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom("${spring.mail.username}");
        mailMessage.setTo(recipeMailModel.getEmail());
        mailMessage.setSubject("FAVORİ YEMEĞİNİZİN KATEGORİSİNDE YENİ YEMEK EKLENDİ");
        mailMessage.setText("Sevgili kullanıcı "+recipeMailModel.getUsername() + " daha önce favorilere eklediğiniz yemekle aynı kategoride yer alan \n" +
                recipeMailModel.getRecipeName()+" isimli yemek yeni eklendi.");
        javaMailSender.send(mailMessage);
    }

    public Boolean sendMailForgotPassword(ForgotPasswordMailResponseDto dto){
        try{
            SimpleMailMessage mailMessage=new SimpleMailMessage();
            mailMessage.setFrom("${spring.mail.username}");
            mailMessage.setTo(dto.getEmail());
            mailMessage.setSubject("SIFRE SIFIRLAMA E-POSTASI");
            mailMessage.setText("Yeni şifreniz : " + dto.getPassword() +
                    "\nGiriş yaptıktan sonra güvenlik nedeniyle şifrenizi değiştiriniz.");
            javaMailSender.send(mailMessage);
        }catch (Exception e){
            e.getMessage();
        }
        return true;

    }
}
