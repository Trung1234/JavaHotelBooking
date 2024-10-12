package com.example.hotel;

import com.example.hotel.services.EmailService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@EnableAspectJAutoProxy
@SpringBootApplication
@MapperScan("com.example.hotel.mapper")  // Adjust to your mapper package
public class HotelApiApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(HotelApiApplication.class, args);
		EmailService emailService = applicationContext
				.getBean(EmailService.class);
		emailService.sendMail(
				"trungnt.work.tech@gmail.com",
				"Hello ✔",
				"<b>Hello world?</b>"
		);
	}

}
