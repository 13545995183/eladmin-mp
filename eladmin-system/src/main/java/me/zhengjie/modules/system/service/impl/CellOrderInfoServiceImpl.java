package me.zhengjie.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.zhengjie.modules.system.domain.CellOrderInfo;
import me.zhengjie.modules.system.mapper.CellOrderInfoMapper;
import me.zhengjie.modules.system.service.CellOrderInfoService;
import org.springframework.stereotype.Service;

@Service
public class CellOrderInfoServiceImpl extends ServiceImpl<CellOrderInfoMapper,CellOrderInfo> implements CellOrderInfoService {
}
