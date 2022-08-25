/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月30日 下午9:49:41
*/
package com.how2java.tmall.action;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;

import com.how2java.tmall.service.OrderService;
import com.how2java.tmall.util.Page;

public class OrderAction extends Action4Result {
	
	
	/**
	 * admin_order_list 导致OrderAction.list()方法被调用
	 * 1. 分页查询订单信息
	 * 2. 借助orderItemService.fill()方法为这些订单填充上orderItems信息
	 * 3. 服务端跳转到admin/listOrder.jsp页面
	 * 4. 在listOrder.jsp借助c:forEach把订单集合遍历出来<c:forEach items="${orders}" var="o">
	 * 5. 遍历订单的时候，再把当前订单的orderItem订单项集合遍历出来
	 * */

	@Action("admin_order_list")
	public String list() {
		if (page == null)
			page = new Page();
		int total = orderService.total();
		page.setTotal(total);
		orders = orderService.listByPage(page);
		orderItemService.fill(orders);
		return "listOrder";
	}
	
	/**
	 * 1. 发货按钮链接跳转到admin_order_delivery
	 * 2. OrderAction.delivery()方法被调用
	 * 2.1 通过t2p把order对象转换为持久化对象
	 * 2.2 修改发货时间，设置发货状态
	 * 2.3 更新到数据库
	 * 2.4 客户端跳转到admin_order_list页面
	 * */
	
	@Action("admin_order_delivery")
	public String delivery() {
		t2p(order);
		order.setDeliveryDate(new Date());
		order.setStatus(OrderService.waitConfirm);
		orderService.update(order);
		return "listOrderPage";
	}

}
