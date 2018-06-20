package cn.e3mall.service;

import Utils.E3Result;
import pojo.EasyUIgridResult;
import cn.e3mall.pojo.TbItem;

public interface ItemService {
 TbItem getItemById(long itemId); 
 EasyUIgridResult getItemList(int page,int rows);
 E3Result addItem(TbItem item, String desc);
 //利用工具类实现返回数据以及商品描述（分别在数据库的两表中）。
 //商品的添加需要一个pojo封装类，因为该商品的添加需要2个数据库，所以额外还需要一个desc来表述添加到数据库的商品的描述.
}
