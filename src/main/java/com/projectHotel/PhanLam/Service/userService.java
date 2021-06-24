package com.projectHotel.PhanLam.Service;

import java.util.*;

import com.projectHotel.PhanLam.entity.Account;
import com.projectHotel.PhanLam.entity.Role;
import com.projectHotel.PhanLam.repository.IAccount;
import com.projectHotel.PhanLam.repository.IuserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class userService implements UserDetailsService {
    @Autowired
    private IAccount accRepository;
    @Autowired
    private IuserRole roleRepository;

    //    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Account acc = accRepository.findByUserName(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Name " + username + " not found"));
//        return new org.springframework.security.core.userdetails.User(acc.getUserName(), acc.getPassword(),
//                getAuthorities(acc));
//    }
//
//    private static Collection<? extends GrantedAuthority> getAuthorities(Account acc) {
//        String[] userRoles = acc.getRole().stream().map((role) -> role.getRole()).toArray(String[]::new);
//        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
//        return authorities;
//    }
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Account user = this.accRepository.findByUserName(userName);

        if (user == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        System.out.println("Found User: " + user);
        List<Role> roles = user.getRole();
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        for (Role role : roles) {
            GrantedAuthority authority = new SimpleGrantedAuthority(role.getRole());
            grantList.add(authority);
        }
        UserDetails userDetails = new User(user.getUserName(), user.getPassword(), grantList);
        return userDetails;
    }

}

