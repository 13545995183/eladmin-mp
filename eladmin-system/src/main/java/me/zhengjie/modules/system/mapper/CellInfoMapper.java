package me.zhengjie.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.zhengjie.modules.system.domain.CellInfo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
@CacheNamespace
public interface CellInfoMapper extends BaseMapper<CellInfo> {
    @Select("select name,title,status,create_time as createTime,images,content,qrcode,sell_price as sellPrice,  " +
            "current_price as currentPrice,issue,stock,user_type as  userType,sales,small_image as smallImage  " +
            "from collections_info ")
    List<Map> queryList();
}
