package me.zhengjie.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.zhengjie.modules.system.domain.CellecAddress;
import me.zhengjie.modules.system.mapper.CellecAddressMapper;
import me.zhengjie.modules.system.service.CellecAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * qiansheng
 * 2023-9-18
 * */
@Service
public class CellecAddressServiceImpl extends ServiceImpl<CellecAddressMapper,CellecAddress> implements CellecAddressService {
    @Autowired
    private CellecAddressMapper cellecAddressMapper;
    @Override
    public List<CellecAddress> listByUserId(String userId) {
        return cellecAddressMapper.listByUserId(userId);
    }
}
