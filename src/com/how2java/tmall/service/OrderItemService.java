/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月30日 下午9:42:17
*/
package com.how2java.tmall.service;

import java.util.List;

import com.how2java.tmall.pojo.Order;

public interface OrderItemService extends BaseService {

	public void fill(List<Order> orders);

	public void fill(Order order);

}
