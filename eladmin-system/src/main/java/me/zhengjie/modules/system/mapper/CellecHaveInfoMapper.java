package me.zhengjie.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.zhengjie.modules.system.domain.CellecHaveInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * qiansheng
 * 2023-9-20
 *
 * */
@Mapper
@CacheNamespace
public interface CellecHaveInfoMapper extends BaseMapper<CellecHaveInfo> {
    @Select("${sqlInfo}")
    @Options(timeout = 10*60*60*1000)
    List<Map> queryCollHaveInfoByUserId(@Param("sqlInfo") String sqlInfo);
}
