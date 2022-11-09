package com.supportportal.service;

import com.supportportal.domain.User;
import com.supportportal.exception.domain.*;
import com.supportportal.model.Agency;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface UserService {

    User register(String firstName, String lastName, String username, String email,String contact, Agency agency) throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException, IOException, NotAnImageFileException;

    List<User> getUsers();

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User addNewUser(String firstName, String lastName, String username,
                    String email, String contact, Long agencyId, String role, boolean isNonLocked,
                    boolean istActive, MultipartFile profileImage) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException, MessagingException, NotAnImageFileException;

    User updateUser(String currentUsername,String newFirstName, String newLastName, String newUsername,
                       String newEmail, String contact, Long agencyId, String newRole, boolean isNonLocked,
                       boolean istActive, MultipartFile profileImage) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException, MessagingException, NotAnImageFileException;
    void deleteUser(String username) throws IOException;
    void resetPassword(String email) throws MessagingException, EmailNotFoundException;
    void updatePassword(String username,String currentPassword,String newPassword) throws  PasswordNotMatch;
    User updateProfileImage(String username,MultipartFile profileImage) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException, NotAnImageFileException;
}
