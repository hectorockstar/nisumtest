package com.nisum.nisumtest.service;

import com.nisum.nisumtest.constant.NisumConstants;
import com.nisum.nisumtest.exceptionhandler.custom.MailException;
import com.nisum.nisumtest.exceptionhandler.custom.UserMissingDataException;
import com.nisum.nisumtest.model.Phone;
import com.nisum.nisumtest.model.User;
import com.nisum.nisumtest.repository.PhoneRepository;
import com.nisum.nisumtest.repository.UserRepository;
import com.nisum.nisumtest.utils.Utils;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;

    @Autowired
    public UserService(JWTService jwtService, UserRepository userRepository, PhoneRepository phoneRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.phoneRepository = phoneRepository;
    }

    @SneakyThrows
    public User saveUser(User user) {
        if(user != null) {
            this.validateUserEmail(user.getEmail());
            String token = jwtService.generateToken(user);
            user.setToken(token);

            User userMetadata = User.getUserBuilder(user);
            this.savePhones(userMetadata.getPhones());
            return userRepository.save(userMetadata);
        } else {
            throw UserMissingDataException.create("NULL");
        }
    }

    public void savePhones(List<Phone> phones){
        logger.info(phones.toString());
        for(Phone phone : phones) {
            phoneRepository.save(phone);
        }
    }

    @SneakyThrows
    public List<User> getUserByEmail(String email) {
        Optional<User> usersByEmail = userRepository.findByEmail(email);
        return List.copyOf(usersByEmail.stream().toList());
    }

    @SneakyThrows
    private void validateUserEmail(String email) {
        if(email != null
                && !email.isBlank()
                && !Utils.validatorRegexMatcher(NisumConstants.REGEX_EMAIL_VALIDATION, email)) {
            throw MailException.create("INVALID");
        }

        if(!this.getUserByEmail(email).isEmpty()) {
            throw MailException.create("EXISTS");
        }
    }
}
