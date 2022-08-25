/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月30日 下午4:51:22
*/
package com.how2java.tmall.service;

import java.util.List;

import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.ProductImage;

public interface ProductImageService extends BaseService {
	
	/**
	 * 提供了两个常量，用于表示图片类型。
	 * */

	public static final String type_single = "type_single";
	public static final String type_detail = "type_detail";

//	public List<ProductImage> list(String key_product, Product product, String key_type, String type);

	public void setFirstProductImage(Product product);

}
