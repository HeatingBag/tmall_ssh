/**
* @author 作者 E-mail:
* @version 创建时间：2022年6月1日 上午9:06:09
*/
package com.how2java.tmall.service;

import com.how2java.tmall.pojo.Order;
import com.how2java.tmall.pojo.Review;

public interface ReviewService extends BaseService{
	
	void saveReviewAndUpdateOrderStatus(Review review, Order order);

}
