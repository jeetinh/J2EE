package phattrienungdungvoi2ee.bai4_qlsp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import phattrienungdungvoi2ee.bai4_qlsp.Model.User;
import phattrienungdungvoi2ee.bai4_qlsp.Repository.UserRepository;

import java.util.Collections;

public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User account = accountRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Could not find user"
                        ));

        return new org.springframework.security.core.userdetails.User(
                account.getUsername(),
                account.getPassword(),
                Collections.singleton(
                        new SimpleGrantedAuthority(
                                "ROLE_" + account.getRole()
                        )
                )
        );
    }
}