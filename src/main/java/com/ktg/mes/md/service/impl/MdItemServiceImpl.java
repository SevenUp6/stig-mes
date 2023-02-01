package com.ktg.mes.md.service.impl;

import com.ktg.common.core.domain.entity.ItemType;
import com.ktg.common.exception.ServiceException;
import com.ktg.common.utils.bean.BeanValidators;
import com.ktg.mes.md.domain.MdItemDashboardVo;
import com.ktg.mes.md.domain.MdProductBom;
import com.ktg.mes.md.mapper.ItemTypeMapper;
import com.ktg.mes.md.service.IMdItemService;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.md.domain.MdItem;
import com.ktg.mes.md.mapper.MdItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.List;

@Service
public class MdItemServiceImpl implements IMdItemService {

    private static final Logger log = LoggerFactory.getLogger(MdItemServiceImpl.class);
    @Autowired
    private MdItemMapper mdItemMapper;
    @Autowired
    private ItemTypeMapper itemTypeMapper;
    @Autowired
    protected Validator validator;
    @Autowired
    private MdProductBomServiceImpl mdProductBomServiceImpl;

    @Override
    public List<MdItem> selectMdItemList(MdItem mdItem) {
        return mdItemMapper.selectMdItemList(mdItem);
    }

    public List<MdItemDashboardVo> selectMdItemScreen(MdItemDashboardVo mdItem) {
        return mdItemMapper.selectMdItemScreen(mdItem);
    }

    @Override
    public List<MdItem> selectMdItemAll() {
        return mdItemMapper.selectMdItemAll();
    }


    @Override
    public MdItem selectMdItemById(Long itemId) {
        return mdItemMapper.selectMdItemById(itemId);
    }

    @Override
    public String checkItemCodeUnique(MdItem mdItem) {
        MdItem item = mdItemMapper.checkItemCodeUnique(mdItem);
        Long itemId = mdItem.getItemId() == null? -1L:mdItem.getItemId();
        if(StringUtils.isNotNull(item) && item.getItemId().longValue() != itemId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }else{
            return UserConstants.UNIQUE;
        }
    }

    @Override
    public String checkItemNameUnique(MdItem mdItem) {
        MdItem item = mdItemMapper.checkItemNameUnique(mdItem);
        Long itemId = mdItem.getItemId() == null? -1L:mdItem.getItemId();
        if(StringUtils.isNotNull(item) && item.getItemId().longValue() != itemId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }else{
            return UserConstants.UNIQUE;
        }
    }

    @Override
    public int insertMdItem(MdItem mdItem) {
        return mdItemMapper.insertMdItem(mdItem);
    }

    @Override
    public int updateMdItem(MdItem mdItem) {
        return mdItemMapper.updateMdItem(mdItem);
    }

    @Override
    public int deleteByItemIds(Long[] itemIds) {
        return mdItemMapper.deleteMdItemByIds(itemIds);
    }

    @Override
    public int deleteByItemId(Long itemId) {
        return mdItemMapper.deleteMdItemById(itemId);
    }

    /**
     * 导入物料数据
     *
     * @param itemList 物料数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    @Override
    public String importMdItem(List<MdItem> itemList, Boolean isUpdateSupport, String operName)
    {
        if (StringUtils.isNull(itemList) || itemList.size() == 0)
        {
            throw new ServiceException("导入物料数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        Long  itemId = null;
        Long  itemIdParent = null;
        Long  itemTypeParent = null;
        Long  itemTypeId = null;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (MdItem mdItem : itemList)
        {
            try
            {
                // 验证是否存在这个用户
                MdItem u = mdItemMapper.selectMdItemById(mdItem.getItemId());
                if (StringUtils.isNull(u))
                {
                    MdProductBom mdProductBom = new MdProductBom();
                    MdProductBom mdProductBomParent = new MdProductBom();

                    if("+".equals(mdItem.getItemTypeCode()) || "".equals(mdItem.getItemTypeCode())){ //是节点

                        ItemType itemType = new ItemType();
                        itemType.setItemTypeCode(mdItem.getItemCode());
                        itemType.setItemTypeName(mdItem.getItemName());
                        if("".equals(mdItem.getItemTypeCode())){//当级别为空时就是最高级的产品，此时父级为“光华科技产品分类”
                            itemTypeParent=200L;
                        }else if(successNum==1||failureNum==1){ //失败或者成功一次，说明是第二行数据，第二行数据都是最高级产品的子集
                            itemTypeParent=itemTypeId;  //不是最高级的时候，取上一次的ID
                        }
                        itemType.setParentTypeId(itemTypeParent);
                        itemType.setAncestors("0"+","+itemTypeParent);
                        itemTypeMapper.insertItemType(itemType);
                        itemTypeId=itemType.getItemTypeId();
                    }
                    //先保存type，获取到typeid再保存item
                    mdItem.setItemTypeId(itemTypeId);
                    BeanValidators.validateWithException(validator, mdItem);

                    mdItemMapper.insertMdItem(mdItem);
                    itemId=mdItem.getItemId();

                    if(StringUtils.EMPTY.equals(mdItem.getItemTypeCode())){ //是母物料
                        itemIdParent=mdItem.getItemId();
                    }
                    //母物料
                    mdProductBomParent.setItemId(itemIdParent);
                    mdProductBomParent.setBomItemId(mdItem.getItemId());
                    mdProductBomParent.setBomItemName(mdItem.getItemName());
                    mdProductBomParent.setBomItemCode(mdItem.getItemCode());
                    mdProductBomParent.setBomItemSpec(mdItem.getSpecification());
                    mdProductBomParent.setUnitOfMeasure(mdItem.getUnitOfMeasure());
                    //普通物料
                    mdProductBom.setItemId(null==itemId?itemIdParent:itemId);
                    mdProductBom.setBomItemId(mdItem.getItemId());
                    mdProductBom.setBomItemName(mdItem.getItemName());
                    mdProductBom.setBomItemCode(mdItem.getItemCode());
                    mdProductBom.setBomItemSpec(mdItem.getSpecification());
                    mdProductBom.setUnitOfMeasure(mdItem.getUnitOfMeasure());
                    if(StringUtils.isNotEmpty(mdItem.getAttr2())){
                        mdProductBom.setQuantity(new BigDecimal(mdItem.getAttr2()));
                    }
                    mdProductBomServiceImpl.insertMdProductBom(mdProductBom);
                    mdProductBomServiceImpl.insertMdProductBom(mdProductBomParent);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "物料名称 " + mdItem.getItemName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    BeanValidators.validateWithException(validator, mdItem);
                    mdItem.setUpdateBy(operName);
                    mdItemMapper.updateMdItem(mdItem);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、物料名称 " + mdItem.getItemName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、物料名称 " + mdItem.getItemName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、物料名称 " + mdItem.getItemName() +" 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    public static void main(String[] args) {
        System.out.println(new BigDecimal("0"));
    }
}
