package me.zhengjie.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.zhengjie.modules.system.domain.CellUser;
import me.zhengjie.modules.system.mapper.CellUserMapper;
import me.zhengjie.modules.system.service.CellUserService;
import org.springframework.stereotype.Service;

@Service
public class CellUserServiceImpl extends ServiceImpl<CellUserMapper, CellUser> implements CellUserService {
}
