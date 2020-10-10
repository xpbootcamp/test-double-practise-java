package com.tw.practise.service;

import com.tw.practise.Exception.UserConflictException;
import com.tw.practise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final AuditService auditService;
    @Autowired
    private final SMSClient smsClient;

    public UserService(UserRepository userRepository, AuditService auditService, SMSClient smsClient) {
        this.userRepository = userRepository;
        this.auditService = auditService;
        this.smsClient = smsClient;
    }

    public long register(String userName, String password, String telephone) {
        if (userRepository.getUserBy(userName) != null) {
            throw new UserConflictException();
        }
        long userId = userRepository.save(userName, password, telephone);
        auditService.log(userName, password, telephone, getCurrentDate());
        smsClient.send(telephone, String.format("%s ，您已注册成功，感谢您的使用！", userName));
        return userId;
    }

    private String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
    }

}
