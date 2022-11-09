package com.supportportal.service.impl;

import com.supportportal.domain.User;
import com.supportportal.domain.UserPrincipal;
import com.supportportal.enumeration.Role;
import com.supportportal.exception.domain.*;
import com.supportportal.model.Agency;
import com.supportportal.model.City;
import com.supportportal.model.District;
import com.supportportal.model.Neighbourhood;
import com.supportportal.repository.UserRepository;
import com.supportportal.service.AgencyService;
import com.supportportal.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.supportportal.constant.FileConstant.*;
import static com.supportportal.constant.UserImplConstant.*;
import static com.supportportal.enumeration.Role.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.countMatches;
import static org.springframework.http.MediaType.*;

@Service
@Transactional
@Qualifier("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private LoginAttemptService loginAttemptService;
    private EmailService emailService;
    private AgencyService agencyService;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, LoginAttemptService loginAttemptService, EmailService emailService, AgencyService agencyService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loginAttemptService = loginAttemptService;
        this.emailService = emailService;
        this.agencyService = agencyService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            LOGGER.error(NO_USER_FOUND_BY_USERNAME + username);
            throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username);
        } else {
            validateLoginAttempt(user);
            user.setLastLoginDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            userRepository.save(user);
            UserPrincipal userPrincipal = new UserPrincipal(user);
            LOGGER.info(FOUND_USER_BY_USERNAME + username);
            return userPrincipal;
        }
    }

    @Override
    public User register(String firstName, String lastName, String username, String email, String contact, Agency agency) throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException, IOException, NotAnImageFileException {
        Agency defaultAgency = this.agencyService.getById(1L);
        return configUser(EMPTY, firstName, lastName, username, email, null, defaultAgency.getId(), ROLE_USER.name(), true, true, null);
    }

    @Override
    public User addNewUser(String firstName, String lastName, String username, String email, String contact, Long agencyId, String role, boolean isNonLocked, boolean istActive, MultipartFile profileImage) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException, MessagingException, NotAnImageFileException {
        return configUser(EMPTY, firstName, lastName, username, email, contact, agencyId, role, isNonLocked, istActive, profileImage);
    }

    @Override
    public User updateUser(String currentUsername, String newFirstName, String newLastName, String newUsername, String newEmail, String contact, Long agencyId, String role, boolean isNonLocked, boolean istActive, MultipartFile profileImage) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException, MessagingException, NotAnImageFileException {
        return configUser(currentUsername, newFirstName, newLastName, newUsername, newEmail, contact, agencyId, role, isNonLocked, istActive, profileImage);
    }

    private User configUser(String currentUsername, String firstName, String lastName, String username, String email, String contact, Long agencyId, String role, boolean isNonLocked, boolean istActive, MultipartFile profileImage) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException, MessagingException, NotAnImageFileException {
        User currentUser = validateNewUsernameAndEmail(currentUsername, username, email);
        if (currentUsername == EMPTY) {
            currentUser = new User();
            String password = generatePassword();
            currentUser.setUserId(generateUserId());
            currentUser.setJoinDate(new Date());
            currentUser.setPassword(encodePassword(password));
            currentUser.setProfileImageUrl(getTemporaryProfileImageUrl(username));
            this.emailService.sendNewPasswordEmail(firstName, password, email);
        }
        currentUser.setFirstName(firstName);
        currentUser.setLastName(lastName);
        currentUser.setUsername(username);
        currentUser.setEmail(email);
        currentUser.setActive(istActive);
        currentUser.setNotLocked(isNonLocked);
        currentUser.setRole(getRoleEnumName(role).name());
        currentUser.setAuthorities(getRoleEnumName(role).getAuthorities());
        Agency foundAgency = this.agencyService.getById(agencyId);
        currentUser.setAgency(foundAgency);
        currentUser.setContact(contact);
        this.userRepository.save(currentUser);
        saveProfileImage(currentUser, profileImage);
        return currentUser;
    }


    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }


    @Override
    public void deleteUser(String username) throws IOException {
        User user = this.userRepository.findUserByUsername(username);
        Path userFolder = Paths.get(USER_FOLDER + user.getUsername()).toAbsolutePath().normalize();
        FileUtils.deleteDirectory(new File(userFolder.toString()));
        this.userRepository.deleteById(user.getId());
    }

    @Override
    public void resetPassword(String email) throws MessagingException, EmailNotFoundException {
        User user = this.userRepository.findUserByEmail(email);
        if (user == null)
            throw new EmailNotFoundException(NO_USER_FOUND_BY_EMAIL + email);
        String password = generatePassword();
        user.setPassword(encodePassword(password));
        this.userRepository.save(user);
        emailService.sendNewPasswordEmail(user.getFirstName(), password, user.getEmail());
    }

    @Override
    public void updatePassword(String username,String currentPassword, String newPassword) throws  PasswordNotMatch {
        User user = this.userRepository.findUserByUsername(username);
        if(user==null)
            throw  new PasswordNotMatch(PASSWORD_NOT_MATCH);
        user.setPassword(encodePassword(newPassword));
        this.userRepository.save(user);
    }

    @Override
    public User updateProfileImage(String username, MultipartFile profileImage) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException, NotAnImageFileException {
        User user = validateNewUsernameAndEmail(username, null, null);
        saveProfileImage(user, profileImage);
        return user;
    }

    private void saveProfileImage(User user, MultipartFile profileImage) throws IOException, NotAnImageFileException {
        if (profileImage != null) {
            if (!Arrays.asList(MediaType.IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE, IMAGE_GIF_VALUE).contains((profileImage.getContentType())))
                throw new NotAnImageFileException(profileImage.getOriginalFilename() + IS_NOT_AN_IMAGE_FILE_PLEASE_UPLOAD_AN_IMAGE);
            Path userFolder = Paths.get(USER_FOLDER + user.getUsername()).toAbsolutePath().normalize();
            if (!Files.exists(userFolder)) {
                Files.createDirectories(userFolder);
                LOGGER.info(DIRECTORY_CREATED + userFolder);
            }
            Files.deleteIfExists(Paths.get(userFolder + user.getUsername() + DOT + JPG_EXTENSION));
            Files.copy(profileImage.getInputStream(), userFolder.resolve(user.getUsername() + DOT + JPG_EXTENSION), REPLACE_EXISTING);
            user.setProfileImageUrl(setProfileImageUrl(user.getUsername()));
            this.userRepository.save(user);
            LOGGER.info(FILE_SAVED_IN_FILE_SYSTEM + profileImage.getOriginalFilename());
        }
    }

    private String setProfileImageUrl(String username) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(USER_IMAGE_PATH + username + FORWARD_SLASH + username + DOT + JPG_EXTENSION).toUriString();
    }

    private Role getRoleEnumName(String role) {
        return Role.valueOf(role.toUpperCase());
    }

    private void validateLoginAttempt(User user) {
        if (user.isNotLocked())
            if (this.loginAttemptService.hasExceededMaxAttempts(user.getUsername()))
                user.setNotLocked(false);
            else
                user.setNotLocked(true);
        else
            this.loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
    }

    private String getTemporaryProfileImageUrl(String username) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(DEFAULT_USER_IMAGE_PATH + username).toUriString();
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    private String generateUserId() {
        return RandomStringUtils.randomNumeric(10);
    }

    private User validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail) throws UserNotFoundException, UsernameExistException, EmailExistException {
        User userByNewUsername = findUserByUsername(newUsername);
        User userByNewEmail = findUserByEmail(newEmail);
        if (StringUtils.isNotBlank(currentUsername)) {
            User currentUser = findUserByUsername(currentUsername);
            if (currentUser == null) {
                throw new UserNotFoundException(NO_USER_FOUND_BY_USERNAME + currentUsername);
            }
            if (userByNewUsername != null && !currentUser.getId().equals(userByNewUsername.getId())) {
                throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
            }
            if (userByNewEmail != null && !currentUser.getId().equals(userByNewEmail.getId())) {
                throw new EmailExistException(EMAIL_ALREADY_EXISTS);
            }
            return currentUser;
        } else {
            if (userByNewUsername != null) {
                throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
            }
            if (userByNewEmail != null) {
                throw new EmailExistException(EMAIL_ALREADY_EXISTS);
            }
            return null;
        }
    }

}
