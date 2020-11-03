package org.kee.vue.server.vhr.service.impl;

import org.kee.vue.server.vhr.mapper.HrMapper;
import org.kee.vue.server.vhr.model.Hr;
import org.kee.vue.server.vhr.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author： 羽子少年
 * @Date： 2020/6/10 23:40
 */
@Service
public class HrServiceImpl implements HrService {

    @Autowired
    private HrMapper hrMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadUserByUsername(username);
        if (null == hr) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        return hr;
    }
}
