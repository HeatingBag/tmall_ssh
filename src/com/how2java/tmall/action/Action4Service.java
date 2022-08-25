/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月29日 下午4:58:21
*/
package com.how2java.tmall.action;

import java.lang.reflect.Method;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.service.OrderItemService;
import com.how2java.tmall.service.OrderService;
import com.how2java.tmall.service.ProductImageService;
import com.how2java.tmall.service.ProductService;
import com.how2java.tmall.service.PropertyService;
import com.how2java.tmall.service.PropertyValueService;
import com.how2java.tmall.service.ReviewService;
import com.how2java.tmall.service.UserService;

/**
 * Action4Service提供服务的注入
 * */
public class Action4Service extends Action4Pojo {

	/**
	 * 自动装配（注入）categoryService对象，用于从数据库获取所有分类对象的集合。
	 * */
	@Autowired
	CategoryService categoryService;

	@Autowired
	PropertyService propertyService;

	@Autowired
	ProductService productService;

	@Autowired
	ProductImageService productImageService;

	@Autowired
	PropertyValueService propertyValueService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderItemService orderItemService;
	
	@Autowired
	ReviewService reviewService;

	/**
	 * 在Action4Service新加一个方法t2p()，专门用于重构: 需要根据浏览器传递过来的分类id,去获取一个分类对象。这个行为
	 * 核心思路为:transient to persistent(瞬时对象转换为持久对象)
	 * 通过getClass()获取瞬时对象的类对象
	 * 接着通过反射，调用invoke()这个瞬时对象的getId方法获取id
	 * 然后根据id，获取持久化对象persistentBean
	 * Action4Service继承了Action4Pojo,所以就提供了改持久对象对应的setXXX方法,在这个例子里就是setCategory方法，通过反射，把该持久对象设置在category引用上
	 * 最后结果就导致父类Action4Pojo中声明的category本身是指向瞬时对象的，现在指向了持久对象。
	 * */

	public void t2p(Object o) {
		try {
			Class clazz = o.getClass();
			int id = (Integer) clazz.getMethod("getId").invoke(o);
			Object persistentBean = categoryService.get(clazz, id);

			String beanName = clazz.getSimpleName();
			Method setMethod = getClass().getMethod("set" + WordUtils.capitalize(beanName), clazz);
			setMethod.invoke(this, persistentBean);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
