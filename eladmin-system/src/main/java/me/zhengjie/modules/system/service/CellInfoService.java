package me.zhengjie.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.zhengjie.modules.system.domain.CellInfo;

import java.util.List;
import java.util.Map;

public interface CellInfoService extends IService<CellInfo> {
    List<Map> queryList();
}
