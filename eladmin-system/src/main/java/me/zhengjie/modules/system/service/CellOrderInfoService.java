package me.zhengjie.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.zhengjie.modules.system.domain.CellOrderInfo;

import java.util.List;
import java.util.Map;

public interface CellOrderInfoService extends IService<CellOrderInfo> {
    List<Map> queryAllInfo(String sqlInfo);

    List<Map> queryAllInfoByParams(Map paramsMap);
}
