package com.site.autosite.account;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements UserDetailsService {

    private final AcccountRepository acccountRepository;

    public AccountServiceImpl(AcccountRepository acccountRepository) {
        this.acccountRepository = acccountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return acccountRepository.findByLogin(login).orElseThrow(() ->
            new UsernameNotFoundException("Account not found with login: " + login));
    }
}
