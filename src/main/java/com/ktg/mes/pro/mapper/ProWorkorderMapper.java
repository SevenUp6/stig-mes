package com.ktg.mes.pro.mapper;

import java.util.List;
import com.ktg.mes.pro.domain.ProWorkorder;
import com.ktg.mes.pro.domain.ProWorkorderDashboard;
import com.ktg.mes.pro.domain.StatusDashboard;
import com.ktg.mes.pro.domain.UnqualifiedDashboard;

/**
 * 生产工单Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-05-09
 */
public interface ProWorkorderMapper 
{
    /**
     * 查询生产工单
     * 
     *   workorderId 生产工单主键
     * @return 生产工单
     */
    public ProWorkorder selectProWorkorderByWorkorderId(Long workorderId);

    /**
     * 查询生产工单列表
     * 
     *   proWorkorder 生产工单
     * @return 生产工单集合
     */
    public List<ProWorkorder> selectProWorkorderList(ProWorkorder proWorkorder);
    public List<ProWorkorderDashboard> selectDashboardList(ProWorkorder proWorkorder);
    public List<UnqualifiedDashboard> selectUnqualified(ProWorkorder proWorkorder);
    public List<UnqualifiedDashboard> selectQualified(ProWorkorder proWorkorder);

    public List<ProWorkorderDashboard> selectDashboardWorkorder(ProWorkorder proWorkorder);
    public List<StatusDashboard> selectStatuslist(ProWorkorder proWorkorder);

    public ProWorkorder checkWorkorderCodeUnique(ProWorkorder proWorkorder);

    /**
     * 新增生产工单
     * 
     *   proWorkorder 生产工单
     * @return 结果
     */
    public int insertProWorkorder(ProWorkorder proWorkorder);

    /**
     * 修改生产工单
     * 
     *   proWorkorder 生产工单
     * @return 结果
     */
    public int updateProWorkorder(ProWorkorder proWorkorder);

    /**
     * 删除生产工单
     * 
     *   workorderId 生产工单主键
     * @return 结果
     */
    public int deleteProWorkorderByWorkorderId(Long workorderId);

    /**
     * 批量删除生产工单
     * 
     *   workorderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProWorkorderByWorkorderIds(Long[] workorderIds);
}
