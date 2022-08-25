/**
* @author 作者 E-mail:
* @version 创建时间：2022年6月1日 下午4:01:51
*/
package com.how2java.tmall.comparator;

import java.util.Comparator;

import com.how2java.tmall.pojo.Product;

public class ProductPriceComparator implements Comparator<Product> {

	@Override
	public int compare(Product p1, Product p2) {
		return (int) (p1.getPromotePrice() - p2.getPromotePrice());
	}

}
