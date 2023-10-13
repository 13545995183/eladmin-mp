package me.zhengjie.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.zhengjie.modules.system.domain.CellecHaveInfo;
import me.zhengjie.modules.system.mapper.CellecHaveInfoMapper;
import me.zhengjie.modules.system.service.CellecHaveInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * qiansheng
 * 2023-9-20
 *
 * */
@Service
public class CellecHaveInfoServiceImpl extends ServiceImpl<CellecHaveInfoMapper,CellecHaveInfo> implements CellecHaveInfoService {
    @Override
    public CellecHaveInfo queryCollHaveInfoByUserId(String userId) {
        return this.baseMapper.queryCollHaveInfoByUserId(userId);
    }
}
