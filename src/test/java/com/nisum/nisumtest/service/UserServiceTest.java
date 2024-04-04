package com.nisum.nisumtest.service;

import com.nisum.nisumtest.exceptionhandler.custom.MailException;
import com.nisum.nisumtest.exceptionhandler.custom.UserMissingDataException;
import com.nisum.nisumtest.model.Phone;
import com.nisum.nisumtest.model.User;
import com.nisum.nisumtest.repository.PhoneRepository;
import com.nisum.nisumtest.repository.UserRepository;
import com.nisum.nisumtest.utils.JSONUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private final JWTService jwtService = mock(JWTService.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final PhoneRepository phoneRepository = mock(PhoneRepository.class);
    private UserService userService = new UserService(jwtService, userRepository, phoneRepository);

    @Test
    public void shouldBeASuccessfulCall() {
        User user = JSONUtils.readJson("/user-register-payload.json", User.class);

        when(userRepository.save(isA(User.class))).thenReturn(this.getUser());
        User savedUser = userService.saveUser(user);

        verify(userRepository, times(1)).save(isA(User.class));
        verify(phoneRepository, times(1)).save(isA(Phone.class));

        assertEquals(user.getEmail(), savedUser.getEmail());

    }

    @Test
    public void shouldBeMailException() {
        User user = JSONUtils.readJson("/user-fail-mail-register-payload.json", User.class);

        when(userRepository.save(isA(User.class))).thenReturn(this.getUser());
        MailException exception = assertThrows(
                MailException.class,
                () -> userService.saveUser(user));

        assertEquals(MailException.create("INVALID").getMessage(), exception.getMessage());

    }

    @Test
    public void shouldBeUserMissingDataException() {
        User user = null;

        when(userRepository.save(isA(User.class))).thenReturn(this.getUser());
        UserMissingDataException exception = assertThrows(
                UserMissingDataException.class,
                () -> userService.saveUser(user));

        assertEquals(UserMissingDataException.create("NULL").getMessage(), exception.getMessage());

    }

    private User getUser() {
        User user = new User();
        user.setUserId(UUID.randomUUID());
        user.setName("Juan Rodriguez");
        user.setPassword("hunter2");
        user.setEmail("juan2@rodriguez.org");
        user.setIsActive(Boolean.TRUE);
        user.setCreatedAt(new Date());
        user.setLoggedAt(new Date());
        user.setUpdatedAt(new Date());

        List<Phone> phoneList = new ArrayList<>();
        Phone phone = new Phone();
        phone.setNumber(Long.parseLong("1234567"));
        phone.setCitycode(1);
        phone.setCountrycode(57);
        phone.setPhoneId(UUID.randomUUID());
        phoneList.add(phone);

        user.setPhones(phoneList);

        return user;
    }

}
