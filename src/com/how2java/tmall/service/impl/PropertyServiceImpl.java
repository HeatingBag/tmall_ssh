/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月30日 上午7:53:23
*/
package com.how2java.tmall.service.impl;

import org.springframework.stereotype.Service;

import com.how2java.tmall.service.PropertyService;

@Service
public class PropertyServiceImpl extends BaseServiceImpl implements PropertyService {

//	@Override
//	public List listByCategory(Category category) {
//		DetachedCriteria dc = DetachedCriteria.forClass(clazz);
//		dc.add(Restrictions.eq("category", category));
//		dc.addOrder(Order.desc("id"));
//		return findByCriteria(dc);
//	}
//
//	@Override
//	public List list(Page page, Category category) {
//		DetachedCriteria dc = DetachedCriteria.forClass(clazz);
//		dc.add(Restrictions.eq("category", category));
//		dc.addOrder(Order.desc("id"));
//		return findByCriteria(dc, page.getStart(), page.getCount());
//	}
//
//	@Override
//	public int total(Category category) {
//		String sqlFormat = "select count(*) from %s bean where bean.category = ?";
//		String hql = String.format(sqlFormat, clazz.getName());
//
//		List<Long> l = this.find(hql, category);
//		if (l.isEmpty())
//			return 0;
//		Long result = l.get(0);
//		return result.intValue();
//	}

}
