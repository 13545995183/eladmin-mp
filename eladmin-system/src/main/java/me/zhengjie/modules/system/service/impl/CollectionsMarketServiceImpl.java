package me.zhengjie.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.zhengjie.modules.system.domain.CollectionsMarket;
import me.zhengjie.modules.system.mapper.CollectionsMarketMapper;
import me.zhengjie.modules.system.service.CollectionsMarketService;
import org.springframework.stereotype.Service;

@Service
public class CollectionsMarketServiceImpl extends ServiceImpl<CollectionsMarketMapper,CollectionsMarket>  implements CollectionsMarketService {
}
