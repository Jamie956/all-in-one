package org.example;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 用户名查询数据库
        QueryWrapper<Users> qw = new QueryWrapper<>();
        qw.eq("username", username);
        Users users = userMapper.selectOne(qw);
        // 找不到用户认证失败
        if (users == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        return new User(users.getUsername(), encoder.encode(users.getPassword()),
                AuthorityUtils.commaSeparatedStringToAuthorityList("role"));
    }
}
