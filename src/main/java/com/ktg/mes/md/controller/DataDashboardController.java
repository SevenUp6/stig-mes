package com.ktg.mes.md.controller;

import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.core.page.TableDataInfo;
import com.ktg.mes.md.domain.MdItemDashboardVo;
import com.ktg.mes.md.service.IMdItemService;
import com.ktg.mes.pro.domain.ProWorkorder;
import com.ktg.mes.pro.domain.ProWorkorderDashboard;
import com.ktg.mes.pro.domain.StatusDashboard;
import com.ktg.mes.pro.domain.UnqualifiedDashboard;
import com.ktg.mes.pro.service.IProWorkorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mes/md/dashboard")
public class DataDashboardController  extends BaseController {
    @Autowired
    private IMdItemService mdItemService;
    @Autowired
    private IProWorkorderService proWorkorderService;
    /**
     * bom看板
     * @param mdItem
     * @return
     */
    @GetMapping("/bom")
    public TableDataInfo bomlist(MdItemDashboardVo mdItem){
        List<MdItemDashboardVo> list = mdItemService.selectMdItemScreen(mdItem);
        return getDataTable(list);
    }

    /**
     * 产品完成进度看板
     * @param
     * @return
     */
    @GetMapping("/productlist")
    public AjaxResult productlist(ProWorkorder proWorkorder)
    {
        List<ProWorkorderDashboard> list = proWorkorderService.selectDashboardList(proWorkorder);
        return AjaxResult.success(list);
    }
    /**
     * 不良品看板
     * @param
     * @return
     */
    @GetMapping("/unqualified")
    public AjaxResult unqualifiedlist(ProWorkorder proWorkorder)
    {
        List<UnqualifiedDashboard> list = proWorkorderService.selectUnqualified(proWorkorder);
        return AjaxResult.success(list);
    }
    /**
     * 工单直通率
     * @param
     * @return
     */
    @GetMapping("/qualified")
    public AjaxResult qualified(ProWorkorder proWorkorder)
    {
        List<UnqualifiedDashboard> list = proWorkorderService.selectQualified(proWorkorder);
        return AjaxResult.success(list);
    }
    /**
     * 生产进度看板
     * @param
     * @return
     */
    @GetMapping("/workorderlist")
    public AjaxResult workorderlist(ProWorkorder proWorkorder)
    {
        List<ProWorkorderDashboard> list = proWorkorderService.selectDashboardWorkorder(proWorkorder);
        return AjaxResult.success(list);
    }
    /**
     * 完成进度看板
     * @param
     * @return
     */
    @GetMapping("/statuslist")
    public AjaxResult Statuslist(ProWorkorder proWorkorder)
    {
        List<StatusDashboard> list = proWorkorderService.selectStatuslist(proWorkorder);
        return AjaxResult.success(list);
    }
}
