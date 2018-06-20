package cn.e3mall.content.service;

import java.util.List;

import pojo.EasyUITreeNode;
import Utils.E3Result;

public interface ContentCategoryService {

	List<EasyUITreeNode> getContentCatList(long parentId);
	E3Result addContentCategory(long parentId, String name);
}
