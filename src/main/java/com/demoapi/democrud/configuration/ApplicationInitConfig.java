package com.demoapi.democrud.configuration;

import com.demoapi.democrud.dto.request.PermissionRequest;
import com.demoapi.democrud.entity.Permission;
import com.demoapi.democrud.entity.User;
import com.demoapi.democrud.enums.Role;
import com.demoapi.democrud.repository.PermissionRepository;
import com.demoapi.democrud.repository.RoleRepository;
import com.demoapi.democrud.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @Bean
    @ConditionalOnProperty(prefix = "spring",
            value = "datasource.driverClassName",
            havingValue = "com.mysql.cj.jdbc.Driver"
    )
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository
            , PermissionRepository permissionRepository){
        return args -> {
            if(userRepository.findByUserName("admin").isEmpty()){

                Permission permission = Permission.builder()
                        .name("ALL_PERMISSION")
                        .description("full authority")
                        .build();
                permissionRepository.save(permission);

                var permissions = new HashSet<Permission>();
                permissions.add(permission);
                com.demoapi.democrud.entity.Role role = com.demoapi.democrud.entity.Role.builder()
                        .name("ADMIN")
                        .description("Admin role")
                        .permissions(permissions)
                        .build();
                roleRepository.save(role);

                var roles = new HashSet<com.demoapi.democrud.entity.Role>();
                roles.add(role);
                User user = User.builder()
                        .userName("admin")
                        .passWord(passwordEncoder.encode("admin"))
                        .roles(roles)
                        .build();
                userRepository.save(user);
                log.warn("admin user has been created with default password: admin, please change it");
            }
        };
    }

}
