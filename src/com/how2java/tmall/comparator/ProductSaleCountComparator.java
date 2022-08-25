/**
* @author 作者 E-mail:
* @version 创建时间：2022年6月1日 下午4:02:07
*/
package com.how2java.tmall.comparator;

import java.util.Comparator;

import com.how2java.tmall.pojo.Product;

public class ProductSaleCountComparator implements Comparator<Product> {

	@Override
	public int compare(Product p1, Product p2) {
		return p2.getSaleCount() - p1.getSaleCount();
	}

}
