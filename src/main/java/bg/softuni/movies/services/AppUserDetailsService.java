package bg.softuni.movies.services;


import bg.softuni.movies.models.entity.UserEntity;
import bg.softuni.movies.models.entity.UserRoleEntity;
import bg.softuni.movies.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .map(this::map)
                .orElseThrow(()-> new UsernameNotFoundException("User with this name " + username + " not found!"));
    }

    private UserDetails map (UserEntity userEntity) {
        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(userEntity.getUserRoles()
                .stream().map(this::mapAuthority)
                .toList())
                .build();
    }

    private GrantedAuthority mapAuthority (UserRoleEntity userRole) {
            return new SimpleGrantedAuthority("ROLE_" +
                    userRole.getUserRole().name());
    }
}
