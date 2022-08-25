/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月30日 下午8:08:27
*/
package com.how2java.tmall.action;

import org.apache.struts2.convention.annotation.Action;

public class PropertyValueAction extends Action4Result {

	/**
	 * 1. 通过t2p()方法持久化product，因为面包屑导航里需要显示产品的名称和分类的连接。
	 * 2. 初始化属性值： propertyValueDAO.init(p)。 因为在第一次访问的时候，这些属性值是不存在的，需要进行初始化。
	 * 3. 根据产品，获取其对应的属性值集合。
	 * 4. 服务端跳转到editPropertyValue.jsp 上
	 * 5. 在editProductValue.jsp上，用c:forEach遍历出这些属性值
	 * <c:forEach items="${propertyValues}" var="propertyValue">
	 * */
	
	@Action("admin_propertyValue_edit")
	public String edit() {
		//初始化
		t2p(product);
		propertyValueService.init(product);
		propertyValues = propertyValueService.listByParent(product);
		return "editPropertyValue";
	}

	/**
	 * 修改功能采用的是使用post方式提交ajax的异步调用方式:
	 * 1. 监听输入框上的keyup事件(keyup())
	 * 2. 获取输入框里的值 var value = $(this).val();
	 * 3. 获取输入框上的自定义属性pvid，这就是当前PropertyValue对应的id
	 * 4. 把边框的颜色修改为黄色，表示正在修改的意思
	 * 5. 借助JQuery的ajax函数 $.post，把id和值，提交到admin_propertyValue_update
	 * 6. admin_propertyValue_update导致PropertyValueAction的update方法被调用
	 * $post();中的page, 对应var page = "admin_propertyValue_update";
	 * 6.1 通过 String value = propertyValue.getValue();获取页面传递过来的属性值
	 * 6.2 通过 t2p(propertyValue); 转换为持久对象
	 * 6.3 通过 propertyValue.setValue(value) 修改此值
	 * 6.4 通过 propertyValueService.update(propertyValue) 提交到数据库
	 * 6.5 服务端跳转到success.jsp，而success.jsp中，只有一个字符串 success
	 * 6.6 为什么这么繁琐，而不是直接调用propertyValueService.update(propertyValue)呢？ 
	 * 因为页面提交数据的时候，只提交了id和value,没有带product.id和property.id，假如直接propertyValueService.update(propertyValue)的话，这两个信息就丢失了。
	 * 7 浏览器判断如果返回值是"success",那么就把边框设置为绿色，表示修改成功，否则设置为红色，表示修改失败
	 * */
	
	@Action("admin_propertyValue_update")
	public String update() {
		String value = propertyValue.getValue();
		t2p(propertyValue);
		propertyValue.setValue(value);
		propertyValueService.update(propertyValue);
		return "success.jsp";
	}

}
