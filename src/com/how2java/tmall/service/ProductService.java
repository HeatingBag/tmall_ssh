/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月30日 上午10:50:08
*/
package com.how2java.tmall.service;

import java.util.List;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Product;

public interface ProductService extends BaseService {

	public void fill(List<Category> categorys);

	public void fill(Category category);

	public void fillByRow(List<Category> categorys);

	public void setSaleAndReviewNumber(Product product);

	public void setSaleAndReviewNumber(List<Product> products);

	public List<Product> search(String keyword, int start, int count);

}
