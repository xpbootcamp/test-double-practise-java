package com.tw.practise;

import com.tw.practise.repository.UserRepository;
import com.tw.practise.service.AuditService;
import com.tw.practise.service.SMSClient;
import com.tw.practise.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {
    @Mock
    private AuditService auditService;
    @Mock
    private SMSClient smsClient;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;


    @Test
    public void should_request_register_service_success_when_user_register_given_valid_info() {
        String userName = "lisa";
        String password = "lisa123";
        String telephone = "13829763456";
        when(userRepository.getUserBy(userName)).thenReturn(null);
        userService.register(userName, password, telephone);

        verify(auditService, times(1)).log(userName, password, telephone, getCurrentDate());
        verify(userRepository, times(1)).save(userName, password, telephone);
        verify(smsClient, times(1)).send(telephone, "lisa ，您已注册成功，感谢您的使用！");
    }

    private String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
    }

}
