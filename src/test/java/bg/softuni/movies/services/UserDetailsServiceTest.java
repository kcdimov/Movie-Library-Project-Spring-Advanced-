package bg.softuni.movies.services;


import bg.softuni.movies.models.entity.UserEntity;
import bg.softuni.movies.models.entity.UserRoleEntity;
import bg.softuni.movies.models.enums.UserRoleEnum;
import bg.softuni.movies.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceTest {

    @Mock
    private UserRepository mockUserRepo;

    private AppUserDetailsService toTest;

    @BeforeEach
    void setUp() {
        toTest = new AppUserDetailsService(mockUserRepo);
    }

    @Test
    void testLoadUserByUsername_UserExists() {
        UserEntity testUserEntity =
                new UserEntity().
                        setUsername("peter").
                        setEmail("peter@abv.bg").
                        setPassword("1234").
                        setFirstName("Peter").
                        setLastName("McGrane").
                        setUserRoles(
                                List.of(
                                        new UserRoleEntity().setUserRole(UserRoleEnum.ADMIN),
                                        new UserRoleEntity().setUserRole(UserRoleEnum.USER)
                                )
                        );

        when(mockUserRepo.findByUsername(testUserEntity.getUsername())).
                thenReturn(Optional.of(testUserEntity));

        UserDetails userDetails =
                toTest.loadUserByUsername(testUserEntity.getUsername());

        Assertions.assertEquals(testUserEntity.getUsername(),
                userDetails.getUsername());
       Assertions.assertEquals(testUserEntity.getPassword(), userDetails.getPassword());


        var authorities = userDetails.getAuthorities();

        Assertions.assertEquals(2, authorities.size());

        var authoritiesIter = authorities.iterator();

        Assertions.assertEquals("ROLE_" + UserRoleEnum.ADMIN.name(),
                authoritiesIter.next().getAuthority());
        Assertions.assertEquals("ROLE_" + UserRoleEnum.USER.name(),
                authoritiesIter.next().getAuthority());
    }

    @Test
    void testLoadUserByUsername_UserDoesNotExist() {
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername("non-existant@example.com")
        );
    }
}
