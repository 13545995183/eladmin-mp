package me.zhengjie.modules.system.rest;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.service.CellecBankInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qian Sheng
 * @date 2023-9-13
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "藏品用户账户接口")
@RequestMapping("/api/cellBankInfo")
public class CellecBankInfoController {
    @Autowired
    private CellecBankInfoService cellecBankInfoService;
}
