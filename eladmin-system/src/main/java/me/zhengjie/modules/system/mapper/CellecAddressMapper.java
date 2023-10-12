package me.zhengjie.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.zhengjie.modules.system.domain.CellecAddress;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/*
* qiansheng
* 2023-9-18
* */
@Mapper
@CacheNamespace
public interface CellecAddressMapper extends BaseMapper<CellecAddress> {
    @Select("select * from collec_address where user_id='${param}'")
    List<CellecAddress> listByUserId(@Param("param") String userId);
}
