/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月30日 上午10:54:53
*/
package com.how2java.tmall.action;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;

import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.util.Page;

public class ProductAction extends Action4Result {

	/**
	 * 1. 判断是否有分页对象，如果没有创建一个新的Page对象。新的Page对象即代表第一个，本页有5条数据。
	 * 2. 判断当前分类下一共有多少条数据
	 * 3. 为分页对象设置总数
	 * 4. 为分页对象设置参数
	 * 5. 根据分页对象和分类对象获取产品集合
	 * 6. 让category引用从指向瞬时对象，变为指向持久对象。因为在面包屑导航需要显示分类的名称。
	 * 7. 在listProduct.jsp页面上使用c:forEach 遍历products集合，并显示
	 * */

	@Action("admin_product_list")
	public String list() {
		if (page == null)
			page = new Page();
		int total = productService.total(category);
		page.setTotal(total);
		page.setParam("&category.id=" + category.getId());
		products = productService.list(page, category);

		/*为每个产品设置firstProdutImage用于显示*/	
		for (Product product : products) {
			productImageService.setFirstProductImage(product);
		}

		t2p(category);
		return "listProduct";
	}

	/**
	 * 1. 在listProduct.jsp页面提交数据的时候，除了提交产品名称，小标题，原价格，优惠价格，还会提交product.category.id
	 * 2. 为产品设置创建时间
	 * 3. 通过productService将Product对象插入到数据库
	 * 4. 返回listProductPage，客户端跳转到admin_product_list,并带上分类的id。
	 * @Result(name="listProductPage", type = "redirect", location="/admin_product_list?category.id=${product.category.id}"),
	 * */

	@Action("admin_product_add")
	public String add() {
		product.setCreateDate(new Date());
		productService.save(product);
		return "listProductPage";
	}

	/**
	 * 1. 在ProductAction的delete方法中通过t2p把product转换为持久化对象
	 * 在查询功能讲解里的删除按钮，只传递了product.id过来，但是删除结束之后，要跳转到Product对应的分类下的产品查询界面，所以需要通过t2p()方法把其对应的分类信息查询出来。
	 * 2. 借助ProductService删除这个对象
	 * 3. 返回listProductPage，客户端跳转到admin_product_list,并带上分类的id。
	 * */

	@Action("admin_product_delete")
	public String delete() {
		t2p(product);
		productService.delete(product);
		return "listProductPage";
	}

	/**
	 * 1. 在ProductAction的edit方法中，通过t2p()方法，让Product引用从指向瞬时对象，改为指向持久化对象。
	 * 瞬时对象是Struts注入进来的，只有id，没有其他字段,持久化对象就和数据库中的同步了，不仅有id,也有name和其他字段.
	 * 2. 服务端跳转到editProduct.jsp
	 * 3. 在editProduct.jsp中显示产品名称等信息
	 * 4. 在editProduct.jsp中隐式提供product.id和product.category.id(两个type="hidden")
	 * */

	@Action("admin_product_edit")
	public String edit() {
		t2p(product);
		return "editProduct";
	}

	/**
	 * 1. 首先根据浏览器传递过来的id，到数据库中把对象查询出来。 
	 * 查询出来的目的是获取创建时间，因为这个创建时间并没有从页面上提交过来，如果不处理，那么日期信息就丢失了。
	 * 2. 将日期信息设置在product对象上
	 * 3. 通过ProductService更新product对象
	 * 4. 返回listProductPage，客户端跳转到admin_product_list,并带上分类的id。
	 * */

	@Action("admin_product_update")
	public String update() {
		Product productFromDB = (Product) productService.get(product.getId());
		product.setCreateDate(productFromDB.getCreateDate());

		productService.update(product);
		return "listProductPage";
	}

}
