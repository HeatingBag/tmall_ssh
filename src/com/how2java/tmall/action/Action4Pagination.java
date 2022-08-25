/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月29日 下午4:55:48
*/
package com.how2java.tmall.action;

import com.how2java.tmall.util.Page;

//Action4Pagination 专门用于处理分页，并且继承上传专用 Action4Upload，这样就能又处理分页，又处理上传
public class Action4Pagination extends Action4Upload {

	protected Page page;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
