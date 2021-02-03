package com.yukil.petcareauthserver.service;

import com.yukil.petcareauthserver.entity.AuthUser;
import com.yukil.petcareauthserver.entity.AuthUserInformation;
import com.yukil.petcareauthserver.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthUserInformationService implements UserDetailsService {

    private final AuthUserRepository authUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AuthUser> optionalAuthUser = authUserRepository.findByUsername(username);
        if(!optionalAuthUser.isPresent()){
            throw new UsernameNotFoundException(username);
        }
        return makeUser(optionalAuthUser.get());
    }

    private AuthUserInformation makeUser(AuthUser authUser) {
        AuthUserInformation authUserInformation = new AuthUserInformation();
        List<GrantedAuthority> collect = authUser.getAuthType().stream().map(auth -> new SimpleGrantedAuthority(auth.toString())).collect(Collectors.toList());
        authUserInformation.setUsername(authUser.getUsername());
        authUserInformation.setPassword(authUser.getPassword());
        authUserInformation.setAuthorityList(collect);
        return authUserInformation;
    }
}
