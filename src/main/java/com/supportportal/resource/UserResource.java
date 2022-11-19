package com.supportportal.resource;

import com.supportportal.domain.HttpResponse;
import com.supportportal.domain.User;
import com.supportportal.domain.UserPrincipal;
import com.supportportal.exception.ExceptionHandling;
import com.supportportal.exception.domain.*;
import com.supportportal.service.UserService;
import com.supportportal.utility.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.supportportal.constant.FileConstant.*;
import static com.supportportal.constant.SecurityConstant.JWT_TOKEN_HEADER;
import static com.supportportal.constant.UserImplConstant.*;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

@RestController
@RequestMapping(path = {"/", "/user"})
public class UserResource extends ExceptionHandling {

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private JWTTokenProvider jwtTokenProvider;

    @Autowired
    public UserResource(AuthenticationManager authenticationManager, UserService userService, JWTTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;

    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        if (this.userService.getUsers().isEmpty()) {
            try {
                this.userService.addNewUser(FIRSTNAME, LASTNAME, USERNAME, EMAIL, CONTACT, AGENCY_ID, ROLE, IS_NON_LOCKED, IS_ACTIVE, PROFILE_IMAGE);
            } catch (UserNotFoundException e) {
                throw new RuntimeException(e);
            } catch (EmailExistException e) {
                throw new RuntimeException(e);
            } catch (UsernameExistException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            } catch (NotAnImageFileException e) {
                throw new RuntimeException(e);
            }
        }
        authenticate(user.getUsername(), user.getPassword());
        User loginUser = userService.findUserByUsername(user.getUsername());
        UserPrincipal userPrincipal = new UserPrincipal(loginUser);
        HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
        return new ResponseEntity<>(loginUser, jwtHeader, OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException, IOException, NotAnImageFileException {
        User newUser = userService.register(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail(), user.getContact(), user.getAgency());
        return new ResponseEntity<>(newUser, OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addNewUser(
            @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
            @RequestParam("username") String username, @RequestParam("email") String email,
            @RequestParam("contact") String contact, @RequestParam("agencyId") String agencyId,
            @RequestParam("role") String role, @RequestParam("isActive") String isActive,
            @RequestParam("isNonLocked") String isNonLocked, @RequestParam(value = "profileImage", required = false) MultipartFile profileImage
    ) throws UserNotFoundException, EmailExistException, MessagingException, IOException, UsernameExistException, NotAnImageFileException {

        System.out.println("-----" + firstName + "-----" + lastName + "-----" + username + "-----" + email + "-----" + role + "-----" + isActive + "-----" + contact + "-----" + agencyId + "-----" + isNonLocked + "-----" + profileImage);
        Long value = Long.valueOf(agencyId);
        User newUser = this.userService.addNewUser(firstName, lastName, username, email, contact, value, role,
                Boolean.parseBoolean(isNonLocked), Boolean.parseBoolean(isActive), profileImage);
        return new ResponseEntity<>(newUser, OK);
    }

    @PostMapping("/update-password")
    public ResponseEntity<HttpResponse> updatePassword(
            @RequestParam("username") String username,
            @RequestParam("currentPassword") String currentPassword,
            @RequestParam("newPassword") String newPassword) throws  PasswordNotMatch {
        authenticate(username,currentPassword);
        this.userService.updatePassword(username,currentPassword, newPassword);
        return response(OK, PASSWORD_UPDATED + newPassword);
    }

    @PostMapping("/update")
    public ResponseEntity<User> update(
            @RequestParam("currentUsername") String currentUsername, @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName, @RequestParam("username") String username,
            @RequestParam("email") String email, @RequestParam("role") String role, @RequestParam("isActive") String isActive,
            @RequestParam("contact") String contact, @RequestParam("agencyId") Long agencyId,
            @RequestParam("isNonLocked") String isNonLocked, @RequestParam(value = "profileImage", required = false) MultipartFile profileImage
    ) throws UserNotFoundException, EmailExistException, MessagingException, IOException, UsernameExistException, NotAnImageFileException {
        System.out.println(currentUsername + "-----" + firstName + "-----" + lastName + "-----" + username + "-----" + email + "-----" + role + "-----" + isActive + "-----" + contact + "-----" + agencyId + "-----" + isNonLocked + "-----");

        User updateUser = this.userService.updateUser(currentUsername, firstName, lastName, username, email, contact, agencyId, role,
                Boolean.parseBoolean(isNonLocked), Boolean.parseBoolean(isActive), profileImage);
        return new ResponseEntity<>(updateUser, OK);
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<User> getUser(@PathVariable("username") String username) {
        User user = this.userService.findUserByUsername(username);
        return new ResponseEntity<>(user, OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = this.userService.getUsers();
        return new ResponseEntity<>(users, OK);
    }

    @GetMapping("/reset-password/{email}")
    public ResponseEntity<HttpResponse> resetPassword(@PathVariable("email") String email) throws EmailNotFoundException, MessagingException {
        this.userService.resetPassword(email);
        return response(OK, EMAIL_SENT + email);
    }

    @DeleteMapping("/delete/{username}")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public ResponseEntity<HttpResponse> deleteUser(@PathVariable("username") String username) throws IOException {
        System.out.println(username);
        this.userService.deleteUser(username);
        return response(OK, USER_DELETED_SUCCESSFULLY);
    }

    @PostMapping("/update-profile-image")
    public ResponseEntity<User> updateProfileImage(@RequestParam("username") String username, @RequestParam(value = "profileImage") MultipartFile profileImage
    ) throws UserNotFoundException, EmailExistException, IOException, UsernameExistException, NotAnImageFileException {
        User updateUserProfileImage = this.userService.updateProfileImage(username, profileImage);
        return new ResponseEntity<>(updateUserProfileImage, OK);
    }

    @GetMapping(path = "/image/{username}/{fileName}", produces = IMAGE_JPEG_VALUE)
    public byte[] getProfileImage(@PathVariable("username") String username, @PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(USER_FOLDER + username + FORWARD_SLASH + fileName));
    }

    @GetMapping(path = "/image/profile/{username}", produces = IMAGE_JPEG_VALUE)
    public byte[] getTempProfileImage(@PathVariable("username") String username) throws IOException {
        URL url = new URL(TEMP_PROFILE_IMAGE_BASE_URL + username);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (InputStream inputStream = url.openStream()) {
            int bytesRead;
            byte[] chunk = new byte[BYTE_STREAM_SIZE];
            while ((bytesRead = inputStream.read(chunk)) > 0)
                byteArrayOutputStream.write(chunk, 0, bytesRead);
        }
        return byteArrayOutputStream.toByteArray();
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase()), httpStatus);
    }

    private HttpHeaders getJwtHeader(UserPrincipal user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(user));
        return headers;
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
