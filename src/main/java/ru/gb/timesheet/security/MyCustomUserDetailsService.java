package ru.gb.timesheet.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.gb.timesheet.model.User;
import ru.gb.timesheet.repository.RoleRepository;
import ru.gb.timesheet.repository.UserRepository;
import ru.gb.timesheet.repository.UserRoleRepository;
//import ru.gb.timesheet.repository.UserRoleRepository;
//import ru.gb.timesheet.repository.UserRoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MyCustomUserDetailsService implements UserDetailsService {

    //{SpringSecurity}
    // ... @UserDetailsService userDetailsService
    // User[admin] --login--> [UserDetails userDetailsService.loadUserByUsername(login)]
    // UserDetails -> SecurityContext


    // в нашем случае юзеры храняться в БД в таблице UserRepository
    //Строго говоря, в этой реализации UserDetailsService можно загружать данные о пользоваеле из любого источника:
    //внешний auth-service, ldap-сервер ...
    //private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    //private final UserRoleRepository userRoleRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

        List<SimpleGrantedAuthority> userRoles = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                userRoles
        );

    }
}