package com.supportportal;

import com.supportportal.domain.User;
import com.supportportal.enumeration.Role;
import com.supportportal.exception.domain.EmailExistException;
import com.supportportal.exception.domain.NotAnImageFileException;
import com.supportportal.exception.domain.UserNotFoundException;
import com.supportportal.exception.domain.UsernameExistException;
import com.supportportal.model.Agency;
import com.supportportal.model.City;
import com.supportportal.model.District;
import com.supportportal.model.Neighbourhood;
import com.supportportal.resource.UserResource;
import com.supportportal.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static com.supportportal.constant.FileConstant.USER_FOLDER;
import static com.supportportal.constant.UserImplConstant.*;
import static com.supportportal.enumeration.Role.ROLE_SUPER_ADMIN;

@SpringBootApplication
public class SupportportalApplication {
    private CityService cityService;
    private DistrictService districtService;
    private NeighbourhoodService neighbourhoodService;
    private AgencyService agencyService;
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(SupportportalApplication.class, args);
        new File(USER_FOLDER).mkdir();
    }

    @Autowired
    public SupportportalApplication(CityService cityService, DistrictService districtService, NeighbourhoodService neighbourhoodService, AgencyService agencyService) {
        this.cityService = cityService;
        this.districtService = districtService;
        this.neighbourhoodService = neighbourhoodService;
        this.agencyService = agencyService;
        City defaultCity = new City("Antalya");
        District defaultDistrict = new District("Merkez", defaultCity);
        Neighbourhood defaultNeighbourhood = new Neighbourhood("Merkez", defaultDistrict);
        Agency defaultAgency = new Agency("Merkez", defaultNeighbourhood);
        if (this.cityService.getAll().isEmpty())
            this.cityService.insert(defaultCity);
        if (this.districtService.getAll().isEmpty())
            this.districtService.insert(defaultDistrict);
        if (this.neighbourhoodService.getAll().isEmpty())
            this.neighbourhoodService.insert(defaultNeighbourhood);
        if (this.agencyService.getAll().isEmpty())
            this.agencyService.insert(defaultAgency);

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200","http://31.220.108.10:5000","http://31.220.108.10","http://talepm.com","**"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("*", "**"));
        corsConfiguration.setExposedHeaders(
                Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization", "Access-Control-Allow-Headers",
                        "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
