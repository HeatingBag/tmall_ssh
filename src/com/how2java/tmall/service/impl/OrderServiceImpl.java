/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月30日 下午9:41:31
*/
package com.how2java.tmall.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.how2java.tmall.pojo.Order;
import com.how2java.tmall.pojo.OrderItem;
import com.how2java.tmall.pojo.User;
import com.how2java.tmall.service.OrderItemService;
import com.how2java.tmall.service.OrderService;

@Service
public class OrderServiceImpl extends BaseServiceImpl implements OrderService {

	@Autowired
	OrderItemService orderItemService;

	/**
	 * 因为插入订单和修改订单项应该是要么都成功，要么都失败，所以在createOrder前加上了事务注解：@Transactional
	 * 1. 把订单插入到数据库中
	 * 2. 为每个OrderItem设置其订单
	 * 3. 累计金额并返回
	 * */

	@Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
	public float createOrder(Order order, List<OrderItem> ois) {
		save(order);
		float total = 0;
		for (OrderItem oi : ois) {
			oi.setOrder(order);
			orderItemService.update(oi);
			total += oi.getProduct().getPromotePrice() * oi.getNumber();
		}
		return total;
	}
	
	/**
	 * OrderServiceImpl新增listByUserWithoutDelete的实现
	 * 1. 创建DetachedCriteria 对象
	 * 2. 条件加上user
	 * 3. 条件加上 status不等于delete
	 * 4. 返回查询结果
	 * */

	@Override
	public List<Order> listByUserWithoutDelete(User user) {
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);
		dc.add(Restrictions.eq("user", user));
		dc.add(Restrictions.ne("status", OrderService.delete));
		return findByCriteria(dc);
	}

}
