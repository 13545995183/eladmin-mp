package me.zhengjie.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.zhengjie.modules.system.domain.CellInfo;
import me.zhengjie.modules.system.mapper.CellInfoMapper;
import me.zhengjie.modules.system.service.CellInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CellInfoServiceImpl extends ServiceImpl<CellInfoMapper, CellInfo> implements CellInfoService {
    @Autowired
    private CellInfoMapper cellInfoMapper;
    @Override
    public List<Map> queryList() {
        return cellInfoMapper.queryList();
    }
}
