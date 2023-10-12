package me.zhengjie.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.zhengjie.modules.system.domain.CellecAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
 * qiansheng
 * 2023-9-18
 * */
public interface CellecAddressService extends IService<CellecAddress> {
    List<CellecAddress> listByUserId(@Param("userId") String userId);
}
