package cn.e3mall.content.service;

import java.util.List;

import Utils.E3Result;
import cn.e3mall.pojo.TbContent;

public interface ContentService {

	E3Result addContent(TbContent content);
	List<TbContent> getContentListByCid(long cid);
	// String tt();
}
