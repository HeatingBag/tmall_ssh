/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月25日 下午4:01:06
*/
package com.how2java.tmall.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.util.ImageUtil;
import com.how2java.tmall.util.Page;

/**
 * @Namespace("/"):
 * 表示访问路径，如果是@Namespace("/test")，那么访问的时候，就需要写成http://127.0.0.1:8080/struts/test/showProduct
 * @ParentPackage("basicstruts"):
 * 与配置文件中的struts.xml中的“package name="basicstruts"”相对于，表示使用默认的一套拦截器
 * result 是 action 节点的子节点， result 代表 action 方法执行后, 可能去的一个目的地，一个 action 节点可以配置多个 result 子节点.
 * name：相应<result>中的name属性;location：相应<result></result>间的地址
 * 此处也是就 <result name="listCategory">/admin/listCategory.jsp</result>
 * 和下面@Action注释的public String list() 的return "listCategory"相对应。返回listCategory会跳转到listCategory.jsp
 * 
 * */

/**
 * 代码重构:
 * CategoryAction 通过继承功能性的父类，将非控制器功能规划到了父类里，
 * CategoryAction 本身只需要专注于扮演控制器(Controller)本身就行了，不会再出现其他看上去纷繁复杂的其他手段代码
 * 重构思路:
 * CategoryAction存在的问题是一个类需要做太多的事情，显的繁杂，影响阅读和维护。 那么重构思路就是把不同的事情，放在专门的类进行处理。
 * CategoryAction 继承Action4Result,于是就通过继承提供了各种相关的功能,CategoryAction 本身只需要专注于扮演控制器(Controller)本身就行了。
 * */

public class CategoryAction extends Action4Result {

	/**
	 * 此处的@Action("admin_category_list")意思也是，网址访问"admin_category_list"会自动调用List()方法
	 * 服务端跳转到listCategory.jsp
	 * 而list()方法通过categoryService获取到所有的分类对象，放在categorys属性中。
	 * 同时提供了 getCategorys() 方法，用于向listCategory.jsp页面传递数据;此处的的categorys对应
	 * <c:forEach items="${categorys}" var="c">中的categorys；
	 * */

	@Action("admin_category_list")
	public String list() {

		/**
		 * 1. 如果没有传递分页参数进来，那么就new一个分页对象，相当于查询第一页
		 * 2. 获取总数
		 * 3. 给分页对象page设置总数
		 * 4. 根据page对象查询当前分页的分类集合数据。
		 * */
		if (page == null)
			page = new Page();
		int total = categoryService.total();
		page.setTotal(total);

		categorys = categoryService.listByPage(page);
		return "listCategory";
	}

	@Action("admin_category_add")
	public String add() {

		/**
		 *  把category对象保存到数据库，然后获取图片的相对路径RealPath
		 *  图片会被保存到本地E:\project\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\tmall_ssh\img\category中
		 *  根据category对象保存到数据库之后所获取的id， 计算其对应的图片文件的名称: id.jpg
		 *  try:
		 *  然后把 Struts 接受到的浏览器上传的临时文件，复制到copyFile() id.jpg。
		 *  这里的img就是指的上传的图片，和listCategory.jsp中的 <input id="categoryPic" type="file" name="img" />相对应，两个img是一样的
		 *  借助ImageUtil把这个文件的格式转换为真正的JPG格式，然后写进去
		 *  
		 *  通过listCategoryPage，客户端跳转到admin_category_list 路径
		 * */

		categoryService.save(category);
		File imageFolder = new File(ServletActionContext.getServletContext().getRealPath("img/category"));
		File file = new File(imageFolder, category.getId() + ".jpg");
		try {
			FileUtils.copyFile(img, file);
			BufferedImage img = ImageUtil.change2jpg(file);
			ImageIO.write(img, "jpg", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "listCategoryPage";
	}

	@Action("admin_category_delete")
	public String delete() {

		/**
		 * 通过categoryService删除Category对象
		 * 客户端跳转到listCategoryPage
		 * */
		categoryService.delete(category);
		return "listCategoryPage";
	}

	@Action("admin_category_edit")
	public String edit() {

		/**
		 * 首先获取id
		 * 根据id获取Category对象,浏览器提交数据的时候，只提交了id,而编辑页面需要显示的是这个分类的名称，需要根据id到数据库中把相应的信息查询出来。
		 * 服务端跳转到editCategory.jsp页面
		 * 重构后:因为BaseService接口中方法声明的改变,由原本需要制定class对象，变为只需要给出id即可。
		 * */
//		int id = category.getId();
//		category = categoryService.get(Category.class, id);
//		category = (Category) categoryService.get(id);

		t2p(category);
		return "editCategory";
	}

	@Action("admin_category_update")
	public String update() {

		/**
		 * 更新category对象
		 * 当发现上传的文件的时候，进行文件更新操作
		 * CategoryAction.update() 方法和CategoryAction.add() 方法的处理很类似，有所不同之处在于增加操作一定会提交图片，而修改不一定提交图片。
		 * */
		categoryService.update(category);
		if (null != img) {
			File imageFolder = new File(ServletActionContext.getServletContext().getRealPath("img/category"));
			File file = new File(imageFolder, category.getId() + ".jpg");
			try {
				FileUtils.copyFile(img, file);
				BufferedImage img = ImageUtil.change2jpg(file);
				ImageIO.write(img, "jpg", file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "listCategoryPage";
	}

}
