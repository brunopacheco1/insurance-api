package com.dev.bruno.insuranceapi.helper;

import com.dev.bruno.insuranceapi.domain.Module;
import com.dev.bruno.insuranceapi.domain.User;
import com.dev.bruno.insuranceapi.repository.ModuleRepository;
import com.dev.bruno.insuranceapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Profile("develop")
@Component
public class DummyDataCommandLineRunner implements CommandLineRunner {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private UserRepository userRepository;

    private final Module[] newModules = new Module[]{
            new Module("Bike", BigDecimal.ZERO, new BigDecimal("3000"), new BigDecimal("30")),
            new Module("Jewelry", new BigDecimal("500"), new BigDecimal("10000"), new BigDecimal("5")),
            new Module("Electronics", new BigDecimal("500"), new BigDecimal("6000"), new BigDecimal("35")),
            new Module("Bike", BigDecimal.ZERO, new BigDecimal("20000"), new BigDecimal("30"))
    };

    private final User[] newUsers = new User[]{
            new User("Test")
    };

    @Override
    public void run(String... args) {
        for (Module module : newModules) {
            moduleRepository.save(module);
        }
        for (User user : newUsers) {
            userRepository.save(user);
        }
    }
}
