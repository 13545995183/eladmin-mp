package me.zhengjie.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.zhengjie.modules.system.domain.CellecHaveInfo;

import java.util.List;
import java.util.Map;

/**
 * qiansheng
 * 2023-9-20
 *
 * */
public interface CellecHaveInfoService extends IService<CellecHaveInfo> {
    List<Map>  queryCollHaveInfoByUserId(String sqlInfo);
}
