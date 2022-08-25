/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月30日 上午8:04:52
*/
package com.how2java.tmall.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.how2java.tmall.util.Page;

public class PropertyAction extends Action4Result {

	/**
	 * 1. 判断是否有分页对象，如果没有创建一个新的Page对象。新的Page对象即代表第一个，本页有5条数据。
	 * 2. 判断当前分类下一共有多少条数据
	 * 3. 为分页对象设置总数
	 * 4. 为分页对象设置参数，page.setParam("&category.id="+category.getId());对应在adminPage.jsp页面中通过${page.param}取出的这个参数
	 * 5. 根据分页对象和分类对象获取属性集合
	 * 6. 让category引用从指向瞬时对象，瞬时对象只有ID是不行的，要查询名称，所以变为指向持久对象，在后面步骤的其他-面包屑导航需要显示分类的名称。
	 * 7. 在listProperty.jsp页面上使用c:forEach 遍历propertys集合，并显示
	 * */
	
	@Action("admin_property_list")
	public String list() {
		if (page==null)
			page = new Page();

		int total = propertyService.total(category);
		page.setTotal(total);
		page.setParam("&category.id="+category.getId());
		propertys = propertyService.list(page,category);
		t2p(category);
		return "listProperty";
	}

	/**
	 * 1. 在listProperty.jsp页面提交数据的时候，除了提交属性名称，还会提交property.category.id
	 * 2. 通过propertyService将Property对象插入到数据库
	 * 3. 返回listPropertyPage，在Action4Result中，会导致客户端跳转到admin_property_list,并带上分类的id(category.id=${property.category.id})。
	 * */
	
	@Action("admin_property_add")
	public String add() {
		propertyService.save(property);
		return "listPropertyPage";
	}
	
	/**
	 * 1. 在PropertyAction的delete方法中通过t2p把property转换为持久化对象，
	 * 在查询功能讲解里的删除按钮，只传递了property.id过来，但是删除结束之后，要跳转到property对应的分类下的属性查询界面，所以需要通过t2p()方法把其对应的分类信息查询出来。
	 * 2. 借助propertyService删除这个对象
	 * 3. 返回listPropertyPage
	 * */

	@Action("admin_property_delete")
	public String delete() {
		t2p(property);
		propertyService.delete(property);
		return "listPropertyPage";
	}

	/**
	 * 1. 在PropertyAction的edit方法中，通过t2p()方法，让property引用从指向瞬时对象，改为指向持久化对象。
	 * 瞬时对象是Struts注入进来的，只有id，没有name,持久化对象就和数据库中的同步了，不仅有id,也有name.
	 * 2. 服务端跳转到editProperty.jsp
	 * 3. 在editProperty.jsp中显示属性名称value="${property.name}
	 * 4. 在editProperty.jsp中隐式提供property.id和property.category.id
	 * */
	
	@Action("admin_property_edit")
	public String edit() {
		t2p(property);
		return "editProperty";
	}

	/**
	 * 1. 在PropertyAction的update方法中通过propertyService更新property对象
	 * 2. 返回listPropertyPage，在Action4Result中，会导致客户端跳转到admin_property_list,并带上分类的id。
	 * @Result(name="listPropertyPage", type = "redirect", location="/admin_property_list?category.id=${property.category.id}")
	 * */
	
	@Action("admin_property_update")
	public String update() {
		propertyService.update(property);
		return "listPropertyPage";
	}
}
