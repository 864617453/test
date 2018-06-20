package cn.e3mall.pagehelper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemExample;

public class pagehelper {
    @Test    
	public void test()throws Exception
	{  //扫描包已经自动臊面了该结果过所以要有bean对象，直接加载调用其bean对象&方法既可以查询数据库.
    	ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("classpath:Spring/applicationContext-Dao.xml");
                                  TbItemMapper item = c.getBean(TbItemMapper.class);
                                  PageHelper.startPage(1, 10);
                                 TbItemExample example = new TbItemExample();
                                  List<TbItem> s = item.selectByExample(example);
                             PageInfo<TbItem> p = new PageInfo<TbItem>(s);
                           System.out.println(p.getFirstPage());
                           System.out.println(p.getTotal());
 	 }
    
	
}
