/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月30日 下午9:05:33
*/
package com.how2java.tmall.action;

import org.apache.struts2.convention.annotation.Action;

import com.how2java.tmall.util.Page;

public class UserAction extends Action4Result {

	@Action("admin_user_list")
	public String list() {
		if (page == null)
			page = new Page();
		int total = userService.total();
		page.setTotal(total);
		users = userService.listByPage(page);
		return "listUser";

	}

}
