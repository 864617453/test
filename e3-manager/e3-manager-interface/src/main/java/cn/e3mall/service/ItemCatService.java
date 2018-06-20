package cn.e3mall.service;

import java.util.List;

import pojo.EasyUITreeNode;

public interface ItemCatService {
List<EasyUITreeNode> getItemCatlist(long parentId);
}
