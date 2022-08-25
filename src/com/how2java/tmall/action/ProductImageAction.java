/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月30日 下午5:05:01
*/
package com.how2java.tmall.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;

import com.how2java.tmall.service.ProductImageService;
import com.how2java.tmall.util.ImageUtil;

public class ProductImageAction extends Action4Result {

	/**
	 * 1. 根据product和类型type_single获取productSingleImages集合
	 * 2. 根据product和类型type_detail 获取productDetailImages集合
	 * 3. 持久化(t2p)product，以便于在面包屑导航上显示产品信息和分类连接
	 * 4. 服务端跳转到listProductImage.jsp页面
	 * 5. 在listProductImage.jsp，使用c:forEach 遍历集合.
	 * */
	
	@Action("admin_productImage_list")
	public String list() {
		productSingleImages = productImageService.list("product", product, "type", ProductImageService.type_single);
		productDetailImages = productImageService.list("product", product, "type", ProductImageService.type_detail);
		t2p(product);
		return "listProductImage";
	}
	
	/**
	 * 增加产品图片分单个和详情两种，其区别在于增加时提交的type类型不一样。
	 * 这里就对单个的进行讲解，详情图片的处理同理。
	 * 首先， 在listProductImage.jsp准备一个form，提交到admin_productImage_add
	 * <form method="post" class="addFormSingle" action="admin_productImage_add" enctype="multipart/form-data">
	 * 接着在ProductImageAction的add()方法中进行处理:
	 * 1. 把productImage对象插入到数据库(productImageService.save(productImage))
	 * 2. 根据productImage的type字段值，确定文件存放的目录名称
	 * 3. 根据ServletActionContext.getServletContext().getRealPath(folder) 定位硬盘上的真正位置(在"E:\project\.metadata\..."中)
	 * 4. 根据插入到数据库之后，productImage的id，确定文件名称，并结合第2条的目录位置，确定文件的绝对路径
	 * 5. 因为ProductAction间接地继承了上传专用 Action4Upload类，所以提供了一个img对象以接受上传的文件，
	 * 不过这个img是一个临时文件，通过FileUtils.copyFile把这个临时文件复制到第4条描述的文件绝对路径
	 * 6. 通过ImageUtil确保图片的真正格式是jpg.(change2jpg())
	 * */

	@Action("admin_productImage_add")
	public String add() {
		productImageService.save(productImage);

		String folder = "img/";
		if (ProductImageService.type_single.equals(productImage.getType())) {
			folder += "productSingle";
		} else {
			folder += "productDetail";
		}

		File imageFolder = new File(ServletActionContext.getServletContext().getRealPath(folder));
		File file = new File(imageFolder, productImage.getId() + ".jpg");
		String fileName = file.getName();
		try {
			FileUtils.copyFile(img, file);
			BufferedImage img = ImageUtil.change2jpg(file);
			ImageIO.write(img, "jpg", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/**
		 * 7. 如果(if)图片的类型是type_single,借助ImageUtil.resizeImage把正常大小的图片，
		 * 改变大小之后，分别复制到productSingle_middle和productSingle_small目录下。
		 * 8. 最后，返回listProductImagePage，在Action4Result中，会导致客户端跳转到admin_productImage_list,并带上产品的id。
		 * @Result(name="listProductImagePage", type = "redirect", location="/admin_productImage_list?product.id=${productImage.product.id}")
		 * */


		if (ProductImageService.type_single.equals(productImage.getType())) {

			String imageFolder_small = ServletActionContext.getServletContext().getRealPath("img/productSingle_small");
			String imageFolder_middle = ServletActionContext.getServletContext()
					.getRealPath("img/productSingle_middle");

			File f_small = new File(imageFolder_small, fileName);
			File f_middle = new File(imageFolder_middle, fileName);

			f_small.getParentFile().mkdirs();
			f_middle.getParentFile().mkdirs();

			ImageUtil.resizeImage(file, 56, 56, f_small);
			ImageUtil.resizeImage(file, 217, 190, f_middle);
		}
		return "listProductImagePage";
	}
	
	
	/**
	 * 点击删除超链，进入ProductImageAction的delete方法
	 * 1. 通过t2p把productImage转换为持久化对象,删除按钮，只传递了productImage.id过来
	 * 删除结束之后，要跳转到productImage对应的产品下的图片查询界面，所以需要通过t2p()方法把其对应的产品信息查询出来。
	 * 2. 返回listProductImagePage，在Action4Result中，会导致客户端跳转到admin_productImage_list,并带上产品的id。
	 * */
	
	@Action("admin_productImage_delete")
	public String delete() {
		t2p(productImage);
		productService.delete(productImage);
		return "listProductImagePage";
	}
	

}
