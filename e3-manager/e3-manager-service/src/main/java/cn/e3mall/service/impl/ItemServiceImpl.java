package cn.e3mall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.container.page.PageHandler;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import Utils.E3Result;
import Utils.IDUtils;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pagehelper.pagehelper;
import pojo.EasyUIgridResult;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.pojo.TbItemExample.Criteria;
import cn.e3mall.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {
 @Autowired
 private TbItemMapper itemMapper;
 @Autowired 
 private TbItemDescMapper itemDescMapper;
	@Override
	public TbItem getItemById(long itemId) {
		//根据逐渐查询
		//TbItem selectByPrimaryKey = itemMapper.selectByPrimaryKey(itemId);
		//根据条件查询
		TbItemExample example = new TbItemExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		if(list!=null&&list.size()>0)
		{
		return list.get(0);
		}
		return null;
	}
	@Override
	public EasyUIgridResult getItemList(int page, int rows) {
		PageHelper.startPage(page, rows);//客户端传到web传到这的page（第几页ex：1）rows（显示多少条数据ex:30）
		TbItemExample example = new TbItemExample();
		List<TbItem> selectByExample = itemMapper.selectByExample(example);//查询所有
		EasyUIgridResult result = new EasyUIgridResult();
		result.setRows(selectByExample);//将查询到的所有结果封装到pojo中
		PageInfo<TbItem> pageinfo = new PageInfo<>(selectByExample);//通过mysql数据自行计算可算出每一个页显示多少条记录
		result.setTotal(pageinfo.getTotal());	
		return result;
	}
	@Override
	public E3Result addItem(TbItem item, String desc) {
		//生成商品id,因为数据库中的id不是主见自增长，所以要每次添加商品时自动生成一个ID。
		long itemId = IDUtils.genItemId();
		//补全item的属性
		item.setId(itemId);
		//1-正常，2-下架，3-删除
		item.setStatus((byte)1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		//向商品表插入数据
		itemMapper.insert(item);
		//创建一个商品描述表对应的pojo对象。
		TbItemDesc itemDesc = new TbItemDesc();
		//补全属性
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		//向商品描述表插入数据
		itemDescMapper.insert(itemDesc);
		//返回成功
		return E3Result.ok();
		}

}
