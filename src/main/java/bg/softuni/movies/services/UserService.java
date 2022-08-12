package bg.softuni.movies.services;

import bg.softuni.movies.models.bindings.UserRegisterBM;
import bg.softuni.movies.models.entity.UserEntity;
import bg.softuni.movies.models.entity.UserRoleEntity;
import bg.softuni.movies.models.enums.UserRoleEnum;
import bg.softuni.movies.models.service.UserServiceModel;
import bg.softuni.movies.repository.UserRepository;
import bg.softuni.movies.repository.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService appUserDetailsService;
    private final ModelMapper modelMapper;


    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository,
                       PasswordEncoder passwordEncoder, UserDetailsService appUserDetailsService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.appUserDetailsService = appUserDetailsService;
        this.modelMapper = modelMapper;
    }

    public void init() {
        if (userRepository.count() == 0 && userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setUserRole(UserRoleEnum.ADMIN);
            UserRoleEntity moderatorRole = new UserRoleEntity().setUserRole(UserRoleEnum.MODERATOR);
            UserRoleEntity userRole = new UserRoleEntity().setUserRole(UserRoleEnum.USER);

            adminRole = userRoleRepository.save(adminRole);
            moderatorRole = userRoleRepository.save(moderatorRole);
            userRole = userRoleRepository.save(userRole);

            initAdmin(List.of(adminRole, userRole, moderatorRole));
            initModerator(List.of(moderatorRole));
            initUser(List.of(userRole));
        }
    }

    private void initAdmin(List<UserRoleEntity> roles) {

        UserEntity admin = new UserEntity();
        admin.setUserRoles(roles);
        admin.setUsername("admin");
        admin.setFirstName("Keanu");
        admin.setLastName("Reeves");
        admin.setEmail("admin@admin.com");
        admin.setPassword(passwordEncoder.encode("123"));

        userRepository.save(admin);
    }

    private void initModerator(List<UserRoleEntity> roles) {
        UserEntity moderator = new UserEntity().
                setUserRoles(roles).
                setFirstName("Moderator").setLastName("Moderator").
                setUsername("moderator").setEmail("moderator@example.com").
                setPassword(passwordEncoder.encode("123"));

        userRepository.save(moderator);
    }

    private void initUser(List<UserRoleEntity> roles) {


        UserEntity user = new UserEntity().
                setUserRoles(roles).
                setUsername("peter").
                setFirstName("Peter").
                setLastName("McGrane").
                setEmail("peter@abv.bg").
                setPassword(passwordEncoder.encode("1234"));

        userRepository.save(user);
    }

    public void registerAndLogin(UserRegisterBM userRegisterBM) {


        UserEntity newUser =
                new UserEntity().
                        setUsername(userRegisterBM.getUsername()).
                        setEmail(userRegisterBM.getEmail()).
                        setFirstName(userRegisterBM.getFirstName()).
                        setLastName(userRegisterBM.getLastName()).
                        setUserRoles(List.of(new UserRoleEntity().setUserRole(UserRoleEnum.USER))).
                        setPassword(passwordEncoder.encode(userRegisterBM.getPassword()));


        userRepository.save(newUser);

        UserDetails userDetails =
                appUserDetailsService.loadUserByUsername(newUser.getUsername());

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.
                getContext().
                setAuthentication(auth);
    }

    public UserServiceModel findByUsername(String username) {
        return this.modelMapper.map(this.userRepository.findByUsername(username), UserServiceModel.class);
    }
}
