package me.zhengjie.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.zhengjie.modules.system.domain.CellOrderInfo;
import me.zhengjie.modules.system.mapper.CellOrderInfoMapper;
import me.zhengjie.modules.system.service.CellOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CellOrderInfoServiceImpl extends ServiceImpl<CellOrderInfoMapper,CellOrderInfo> implements CellOrderInfoService {
    @Autowired
    private CellOrderInfoMapper cellOrderInfoMapper;
    @Override
    public List<Map> queryAllInfo(String sqlInfo) {
        return cellOrderInfoMapper.queryAllInfo(sqlInfo);
    }

    @Override
    public List<Map> queryAllInfoByParams(Map paramsMap) {
        return cellOrderInfoMapper.queryAllInfoByParams(paramsMap);
    }
}
