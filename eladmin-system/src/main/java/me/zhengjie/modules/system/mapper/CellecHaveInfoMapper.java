package me.zhengjie.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.zhengjie.modules.system.domain.CellecHaveInfo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * qiansheng
 * 2023-9-20
 *
 * */
@Mapper
@CacheNamespace
public interface CellecHaveInfoMapper extends BaseMapper<CellecHaveInfo> {
    CellecHaveInfo queryCollHaveInfoByUserId(@Param("userId") String userId);
}
