package me.zhengjie.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.zhengjie.modules.system.domain.CellInfo;
import me.zhengjie.modules.system.mapper.CellInfoMapper;
import me.zhengjie.modules.system.service.CellInfoService;
import org.springframework.stereotype.Service;

@Service
public class CellInfoServiceImpl extends ServiceImpl<CellInfoMapper, CellInfo> implements CellInfoService {
}
