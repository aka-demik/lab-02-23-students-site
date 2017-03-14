package com.aka.services;

import com.aka.dao.exceptions.PersistentException;
import com.aka.models.SuperUser;
import com.aka.services.interfaces.SuperUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {
    private SuperUserService superUserService;

    @Autowired
    public void setSuperUserService(SuperUserService superUserService) {
        this.superUserService = superUserService;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            SuperUser superUser = superUserService.getByUserName(username);

            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            if ("root".equals(username))
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

            return new User(username,
                    superUser.getUserPassword(),
                    authorities);
        } catch (PersistentException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
}
