/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月29日 下午4:54:09
*/
package com.how2java.tmall.action;

import java.io.File;

//Action4Upload这个类就专门用于处理图片上传，其他的事情一概不管,所以所有和img相关的都放这里
public class Action4Upload {

	protected File img;
	protected String imgFileName;
	protected String imgContentType;

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public String getImgContentType() {
		return imgContentType;
	}

	public void setImgContentType(String imgContentType) {
		this.imgContentType = imgContentType;
	}

}
