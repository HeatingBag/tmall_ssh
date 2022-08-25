/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月30日 上午10:50:42
*/
package com.how2java.tmall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.service.OrderItemService;
import com.how2java.tmall.service.ProductImageService;
import com.how2java.tmall.service.ProductService;
import com.how2java.tmall.service.ReviewService;

@Service
public class ProductServiceImpl extends BaseServiceImpl implements ProductService {

	@Autowired
	ProductImageService productImageService;

	@Autowired
	OrderItemService orderItemService;

	@Autowired
	ReviewService reviewService;

	/**
	 * 模糊查询
	 * */
	
	@Override
	public List<Product> search(String keyword, int start, int count) {
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);
		dc.add(Restrictions.like("name", "%" + keyword + "%"));
		return findByCriteria(dc, start, count);
	}

	/**
	 * 第一个方法是对每个产品进行销售总数统计和所属页面下评论数统计
	 * 第二个方式则是对一个产品集合里的所有产品进行第一个方法的操作
	 * */

	@Override
	public void setSaleAndReviewNumber(Product product) {
		int saleCount = orderItemService.total(product);
		product.setSaleCount(saleCount);
		int reviewCount = reviewService.total(product);
		product.setReviewCount(reviewCount);

	}

	@Override
	public void setSaleAndReviewNumber(List<Product> products) {
		for (Product product : products) {
			setSaleAndReviewNumber(product);
		}

	}

	@Override
	public void fill(List<Category> categorys) {
		for (Category category : categorys) {
			fill(category);
		}

	}

	/**
	 * List < E > subList​(int fromIndex, int toIndex)
	 * 返回此列表在指定的 fromIndex、包含的和toIndex排除的部分之间的视图。
	 * fromIndex- subList 的低端点（包括）,toIndex- 子列表的高端（不包括）
	 * */

	@Override
	public void fillByRow(List<Category> categorys) {
		int productNumberEachRow = 8;
		for (Category category : categorys) {
			List<Product> products = category.getProducts();
			List<List<Product>> productsByRow = new ArrayList<>();
			for (int i = 0; i < products.size(); i += productNumberEachRow) {
				int size = i + productNumberEachRow;
				size = size > products.size() ? products.size() : size;
				List<Product> productsOfEachRow = products.subList(i, size);
				productsByRow.add(productsOfEachRow);
			}
			category.setProductsByRow(productsByRow);
		}

	}

	@Override
	public void fill(Category category) {

		List<Product> products = listByParent(category);

		for (Product product : products)
			productImageService.setFirstProductImage(product);

		category.setProducts(products);

	}

}
