package com.example.hotel.aop;

import com.example.hotel.dto.BaseDto;
import com.example.hotel.entity.User;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import com.example.hotel.dto.SignUpDto;

@Aspect
@Component
public class AuditAspect {

    @Before("execution(* com.example.hotel.services.*.create*(..))")
    public void setInsertDateBeforeCreate(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length > 0 && args[0] instanceof BaseDto) {
            BaseDto baseDtoDto = (BaseDto) args[0];
            baseDtoDto.setInsertDate(LocalDateTime.now());
            baseDtoDto.setUpdateDate(LocalDateTime.now());
            System.out.println("Insert date set before  creation: " + baseDtoDto.getInsertDate());
        }
    }
}