package me.zhengjie.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.zhengjie.modules.system.domain.CellOrderInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
@CacheNamespace
public interface CellOrderInfoMapper extends BaseMapper<CellOrderInfo> {
    @Select("${params}")
    @Options(useCache = true)
    List<Map> queryAllInfo(@Param("params")String sqlInfo);
    @Options(useCache = true)
    List<Map> queryAllInfoByParams(@Param("params") Map paramsMap);
}
