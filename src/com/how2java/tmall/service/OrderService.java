/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月30日 下午9:36:55
*/
package com.how2java.tmall.service;

import java.util.List;

import com.how2java.tmall.pojo.Order;
import com.how2java.tmall.pojo.OrderItem;
import com.how2java.tmall.pojo.User;

public interface OrderService extends BaseService {

	public static final String waitPay = "waitPay";
	public static final String waitDelivery = "waitDelivery";
	public static final String waitConfirm = "waitConfirm";
	public static final String waitReview = "waitReview";
	public static final String finish = "finish";
	public static final String delete = "delete";

	public float createOrder(Order order, List<OrderItem> ois);
	
	public List<Order> listByUserWithoutDelete(User user);

}
