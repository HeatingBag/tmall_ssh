/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月30日 下午4:56:30
*/
package com.how2java.tmall.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.ProductImage;
import com.how2java.tmall.service.ProductImageService;

@Service
public class ProductImageServiceImpl extends BaseServiceImpl implements ProductImageService {

	/**
	 * 1. 查询出某个产品下的类型是type_single的图片集合
	 * 2. 如果这个集合不为空，那么取出其中的第一个图片，作为这个产品的图片：firstProdutImage.
	 * */
	@Override
	public void setFirstProductImage(Product product) {
		if (null != product.getFirstProductImage())
			return;
		List<ProductImage> pis = list("product", product, "type", ProductImageService.type_single);
		if (!pis.isEmpty())
			product.setFirstProductImage(pis.get(0));

	}

//	/*查询某个产品下的，某种类型的图片。*/	
//	@Override
//	public List<ProductImage> list(String key_product, Product product, String key_type, String type) {
//		DetachedCriteria dc = DetachedCriteria.forClass(clazz);
//		dc.add(Restrictions.eq(key_product, product));
//		dc.add(Restrictions.eq(key_type, type));
//		dc.addOrder(Order.desc("id"));
//		return this.findByCriteria(dc);
//	}

}
