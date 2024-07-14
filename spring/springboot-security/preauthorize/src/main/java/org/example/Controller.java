package org.example;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    @GetMapping("/hi")
    @PreAuthorize("hasAnyAuthority('admins')")
    public String hi() {
        return "hi";
    }

    @GetMapping("/hi1")
    @PreAuthorize("hasAnyAuthority('admins')")
    // only return filter result
    @PostFilter("filterObject.username == 'a1'")
    public List<User> hi1() {
        List<User> us = new ArrayList<>();
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("admins");
        us.add(new User("a1", "bb", auths));
        us.add(new User("b2", "bb", auths));
        return us;
    }

}