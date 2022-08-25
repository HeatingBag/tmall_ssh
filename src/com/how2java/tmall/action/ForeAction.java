/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月31日 上午8:43:45
*/
package com.how2java.tmall.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.xwork.math.RandomUtils;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.web.util.HtmlUtils;

import com.how2java.tmall.comparator.ProductAllComparator;
import com.how2java.tmall.comparator.ProductDateComparator;
import com.how2java.tmall.comparator.ProductPriceComparator;
import com.how2java.tmall.comparator.ProductReviewComparator;
import com.how2java.tmall.comparator.ProductSaleCountComparator;
import com.how2java.tmall.pojo.OrderItem;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.User;
import com.how2java.tmall.service.OrderService;
import com.how2java.tmall.service.ProductImageService;
import com.opensymphony.xwork2.ActionContext;

public class ForeAction extends Action4Result {
	
	/**
	 * 在评价产品页面点击提交评价，就把数据提交到了/foredoreview路径，导致ForeAction.doreview方法被调用
	 * 1. ForeAction.doreview()
	 * 1.1 让order引用指向持久化对象
	 * 1.2 让product引用指向持久化对象
	 * 1.3 修改订单对象状态
	 * 1.4 更新订单对象到数据库
	 * 1.5 获取参数content (评价信息)
	 * 1.6 对评价信息进行转义，道理同ForeAction.register()
	 * 1.7 从session中获取当前用户
	 * 1.8 创建评价对象review
	 * 1.9 为评价对象review设置 评价信息，产品，时间，用户
	 * 1.10 增加到数据库
	 * 1.11.客户端跳转到/forereview： 评价产品页面，并带上参数showonly=true
	 * */
	
	@Action("foredoreview")
	public String doreview() {
		t2p(order);
		t2p(product);
		
		order.setStatus(OrderService.finish);
		
		String content = review.getContent();
		content = HtmlUtils.htmlEscape(content);
//		review.setContent(content);
		
		User user  = (User) ActionContext.getContext().getSession().get("user");
		review.setContent(content);
		review.setProduct(product);
		review.setCreateDate(new Date());
		review.setUser(user);
		
		 reviewService.saveReviewAndUpdateOrderStatus(review,order);
		 
		 showonly = true;
		 return "reviewPage";
	}
		
	/**
	 * 通过点击评价按钮，来到路径/forereview，导致ForeAction.review()方法被调用
	 * 1. ForeAction.review()
	 * 1.1 让order引用指向持久化对象
	 * 1.2 为订单对象填充订单项
	 * 1.3 获取第一个订单项对应的产品,因为在评价页面需要显示一个产品图片，那么就使用这第一个产品的图片了
	 * 1.4 获取这个产品的评价集合
	 * 1.5 为产品设置评价数量和销量
	 * 1.6 服务端跳转到 review.jsp
	 * */
	
	@Action("forereview")
	public String review() {
		t2p(order);
		orderItemService.fill(order);
		product = order.getOrderItems().get(0).getProduct();
		reviews = reviewService.listByParent(product);
		productService.setSaleAndReviewNumber(product);
		return "review.jsp";
	}

	@Action("foredeleteOrder")
	public String deleteOrder() {
		t2p(order);
		order.setStatus(OrderService.delete);
		orderService.update(order);
		return "success.jsp";
	}
	
	/**
	 * 1.1 order指向持久化对象
	 * 1.2 修改对象o的状态为等待评价，修改其确认支付时间
	 * 1.3 更新到数据库
	 * 1.4 服务端跳转到orderConfirmed.jsp页面
	 * */

	@Action("foreorderConfirmed")
	public String orderConfirmed() {
		t2p(order);
		order.setStatus(OrderService.waitReview);
		order.setConfirmDate(new Date());
		orderService.update(order);
		return "orderConfirmed.jsp";

	}
	
	/**
	 * 1. 点击确认收货后，访问地址/foreconfirmPay
	 * 2. ForeAction.confirmPay()方法被调用
	 * 2.1 order指向持久化对象
	 * 2.2 为订单对象填充订单项
	 * 2.3 服务端跳转到 confirmPay.jsp
	 * */

	@Action("foreconfirmPay")
	public String confirmPay() {
		t2p(order);
		orderItemService.fill(order);
		return "confirmPay.jsp";
	}

	/**
	 * /forebought导致ForeAction.bought()方法被调用
	 * 1. 通过session获取用户user
	 * 2. 查询user所有的状态不是"delete" 的订单集合os
	 * 3. 为这些订单填充订单项
	 * 4. 服务端跳转到bought.jsp
	 * */

	@Action("forebought")
	public String bought() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		orders = orderService.listByUserWithoutDelete(user);
		orderItemService.fill(orders);
		return "bought.jsp";
	}

	/**
	 * 1.1 让order引用指向持久化对象
	 * 1.2 修改订单对象的状态和支付时间
	 * 1.3 更新这个订单对象到数据库
	 * 1.4 服务端跳转到payed.jsp
	 * 1.5 把这个订单对象放在request的属性"o"上
	 * */

	@Action("forepayed")
	public String payed() {
		t2p(order);
		order.setStatus(OrderService.waitDelivery);
		order.setPayDate(new Date());
		orderService.update(order);
		return "payed.jsp";
	}

	@Action("forealipay")
	public String forealipay() {
		return "alipay.jsp";
	}

	/**
	 * 提交订单访问路径 /forecreateOrder, 导致ForeAction.createOrder 方法被调用
	 * 1. 从session获取订单项集合. ( 在结算功能的ForeAction.buy() 订单项集合被放到了session中 )
	 * 2. 如果订单项集合是空，则跳转到登陆页面
	 * 3. 从session中获取user对象
	 * 4. 根据当前时间加上一个4位随机数生成订单号
	 * 4. 根据上述参数，创建订单对象
	 * 5. 把订单状态设置为等待支付
	 * 6. 调用OrderServiceImpl.createOrder() 把订单插入到数据库，并设置每个订单项的Order属性，更新到数据库
	 * 7. 获取本次订单的总金额
	 * 8. 客户端跳转到确认支付页forealipay
	 * */

	@Action("forecreateOrder")
	public String createOrder() {
		List<OrderItem> ois = (List<OrderItem>) ActionContext.getContext().getSession().get("orderItems");
		if (ois.isEmpty())
			return "login.jsp";

		User user = (User) ActionContext.getContext().getSession().get("user");
		String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + RandomUtils.nextInt(10000);

		order.setOrderCode(orderCode);
		order.setCreateDate(new Date());
		order.setUser(user);
		order.setStatus(OrderService.waitPay);

		total = orderService.createOrder(order, ois);
		return "alipayPage";

	}

	/**
	 * 点击删除按钮后，根据 cartPage.jsp 中的js代码，会通过Ajax访问/foredeleteOrderItem路径，导致ForeAction.deleteOrderItem方法被调用
	 * var page = "foredeleteOrderItem";
	 * 1. 删除OrderItem数据
	 * 2. 返回字符串"success"
	 * */

	@Action("foredeleteOrderItem")
	public String deleteOrderItem() {
		orderItemService.delete(orderItem);
		return "success.jsp";
	}

	/**
	 * var page = "forechangeOrderItem";导致ForeAction.changeOrderItem()方法被调用
	 * 1. 获取当前用户
	 * 2. 遍历出用户当前所有的未生成订单的OrderItem
	 * 3. 根据product.id找到匹配的OrderItem，并修改数量后更新到数据库
	 * 4. 返回字符串"success"
	 * */

	@Action("forechangeOrderItem")
	public String changeOrderItem() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		List<OrderItem> ois = orderItemService.list("user", user, "order", null);
		for (OrderItem oi : ois) {
			if (oi.getProduct().getId() == product.getId()) {
				oi.setNumber(num);
				orderItemService.update(oi);
				break;
			}
		}
		return "success.jsp";
	}

	/**
	 * 访问地址/forecart导致ForeAction.cart()方法被调用
	 * 1. 通过session获取当前用户:所以一定要登录才访问，否则拿不到用户对象
	 * 2. 获取未这个用户关联的订单项集合 orderItems
	 * 3. 服务端跳转到cart.jsp
	 * */

	@Action("forecart")
	public String cart() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		orderItems = orderItemService.list("user", user, "order", null);
		for (OrderItem orderItem : orderItems)
			productImageService.setFirstProductImage(orderItem.getProduct());
		return "cart.jsp";
	}

	/**
	 * 与 ForeAction.buyone() 客户端跳转到结算页面不同的是， 最后返回字符串"success"，表示添加成功
	 * 
	 * */

	@Action("foreaddCart")
	public String addCart() {

		User user = (User) ActionContext.getContext().getSession().get("user");
		boolean found = false;
		List<OrderItem> ois = orderItemService.list("user", user, "order", null);
		for (OrderItem oi : ois) {
			if (oi.getProduct().getId() == product.getId()) {
				oi.setNumber(oi.getNumber() + num);
				orderItemService.update(oi);
				found = true;
				break;
			}
		}
		if (!found) {
			OrderItem oi = new OrderItem();
			oi.setUser(user);
			oi.setNumber(num);
			oi.setProduct(product);
			orderItemService.save(oi);
		}
		return "success.jsp";
	}

	/**
	 * 点立即购买最后，客户端跳转到结算路径： "forebuy?oiid="+oiid;导致ForeAction.buy()方法被调用
	 * @Result(name = "buyPage", type = "redirect", location = "forebuy?oiids=${oiid}"),
	 * 1. 获取参数：数组 oiid
	 * 注：为什么这里要用数组的方式试图获取多个oiid，而不是仅仅获取一个oiid?
	 * 因为根据购物流程环节与表关系，结算页面还需要显示在购物车里选中的多条OrderItem数据，
	 * 所以为了兼容从购物车页面跳转过来的需求，要用一个int 数组来接受参数。
	 * 2. 让orderItems 指向一个新的ArrayList
	 * 3. 根据前面步骤获取的oiids，从数据库中取出OrderItem对象，并放入orderItems 集合中
	 * 4. 累计这些ois的价格总数，赋值在total上
	 * 5. 把订单项集合放在session的属性 "orderItems" 上，为什么要用session? 因为后续生成订单的时候，还会用到它。
	 * 6. 服务端跳转到buy.jsp
	 * */

	@Action("forebuy")
	public String buy() {
		orderItems = new ArrayList<>();
		for (int oiid : oiids) {
			OrderItem oi = (OrderItem) orderItemService.get(oiid);
			total += oi.getProduct().getPromotePrice() * oi.getNumber();
			orderItems.add(oi);
			productImageService.setFirstProductImage(oi.getProduct());
		}

		ActionContext.getContext().getSession().put("orderItems", orderItems);
		return "buy.jsp";
	}

	/**
	 *  新增订单项要考虑两个情况:
	 * a. 如果已经存在这个产品对应的OrderItem，并且还没有生成订单，即还在购物车中。 那么就应该在对应的OrderItem基础上，调整数量
	 * a.1 基于用户对象user，查询没有生成订单的订单项集合
	 * a.2 遍历这个集合
	 * a.3 如果产品是一样的话，就进行数量追加
	 * a.4 获取这个订单项的 id
	 * b. 如果不存在对应的OrderItem,那么就新增一个订单项OrderItem
	 * b.1 生成新的订单项
	 * b.2 设置数量，用户和产品
	 * b.3 插入到数据库
	 * b.4 获取这个订单项的 id
	 * */

	@Action("forebuyone")
	public String buyone() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		boolean found = false;
		List<OrderItem> ois = orderItemService.list("user", user, "order", null);
		for (OrderItem oi : ois) {
			if (oi.getProduct().getId() == product.getId()) {
				oi.setNumber(oi.getNumber() + num);
				orderItemService.update(oi);
				found = true;
				oiid = oi.getId();
				break;
			}
		}
		if (!found) {
			OrderItem oi = new OrderItem();
			oi.setUser(user);
			oi.setNumber(num);
			oi.setProduct(product);
			orderItemService.save(oi);
			oiid = oi.getId();
		}
		return "buyPage";
	}

	/**
	 * 通过search.jsp或者simpleSearch.jsp提交数据到路径 /foresearch， 导致ForeAction.search()方法被调用
	 *  <form action="foresearch" method="post" >
	 *  1. 获取参数keyword
	 *  2. 根据keyword进行模糊查询，获取满足条件的前20个产品
	 *  3. 为这些产品设置销量和评价数量
	 *  4. 服务端跳转到 searchResult.jsp 页面
	 * */

	@Action("foresearch")
	public String search() {
		products = productService.search(keyword, 0, 20);
		productService.setSaleAndReviewNumber(products);
		for (Product product : products)
			productImageService.setFirstProductImage(product);

		return "searchResult.jsp";
	}

	/**
	 * 1. category指向持久化对象
	 * 2. 为category填充产品
	 * 3. 为产品填充销量和评价数据
	 * 4. 获取参数sort
	 * 4.1 如果sort==null，即不排序
	 * 4.2 如果sort!=null，则根据sort的值，从5个Comparator比较器中选择一个对应的排序器进行排序
	 * 5. 服务端跳转到 category.jsp
	 * */

	@Action("forecategory")
	public String category() {
		t2p(category);
		productService.fill(category);
		productService.setSaleAndReviewNumber(category.getProducts());

		if (null != sort) {
			switch (sort) {
			case "review":
				Collections.sort(category.getProducts(), new ProductReviewComparator());
				break;
			case "date":
				Collections.sort(category.getProducts(), new ProductDateComparator());
				break;
			case "saleCount":
				Collections.sort(category.getProducts(), new ProductSaleCountComparator());
				break;
			case "price":
				Collections.sort(category.getProducts(), new ProductPriceComparator());
				break;
			case "all":
				Collections.sort(category.getProducts(), new ProductAllComparator());
				break;
			}
		}
		return "category.jsp";

	}

	/**
	 * var page = "foreloginAjax";
	 * 点击了登录按钮之后，访问路径/foreloginAjax,导致ForeAction.loginAjax()方法被调用
	 * 1. 通过HtmlUtils.htmlEscape将账号转义，因为数据库里保存的是转义过后的
	 * 2. 通过账号密码获取User对象
	 * 2.1 如果User对象为空，那么就返回"fail"字符串。(fail.jsp)
	 * 2.2 如果User对象不为空，那么就把User对象放在session中，并返回"success" 字符串
	 * */

	@Action("foreloginAjax")
	public String loginAjax() {

		user.setName(HtmlUtils.htmlEscape(user.getName()));
		User user_session = userService.get(user.getName(), user.getPassword());

		if (null == user_session)
			return "fail.jsp";

		ActionContext.getContext().getSession().put("user", user_session);
		return "success.jsp";

	}

	/**
	 * $(".addCartLink").click(function(){var page = "forecheckLogin";
	 *   以及$(".buyLink").click(function(){ var page = "forecheckLogin";
	 *   会导致ForeAction.checkLogin()方法被调用。
	 *   获取session中的"user"对象:
	 *   如果不为空，即表示已经登录，服务端跳转到 success.jsp
	 *   如果不空，即表示未经登录，服务端跳转到 fail.jsp
	 * */

	@Action("forecheckLogin")
	public String checkLogin() {
		User u = (User) ActionContext.getContext().getSession().get("user");
		if (null == u)
			return "fail.jsp";
		else
			return "success.jsp";
	}

	/**
	 * 1. 把product 指向持久化对象
	 * 2. 设置首张图片
	 * 3. 设置单个和详情图片集合
	 * 4. 获取本产品的属性值集合
	 * 5. 获取本产品的评价集合
	 * 6. 设置销售数量和评价数量
	 * 7. 服务端跳转到 product.jsp
	 * */

	@Action("foreproduct")
	public String product() {
		t2p(product);

		productImageService.setFirstProductImage(product);
		productSingleImages = productImageService.list("product", product, "type", ProductImageService.type_single);
		productDetailImages = productImageService.list("product", product, "type", ProductImageService.type_detail);
		product.setProductSingleImages(productSingleImages);
		product.setProductDetailImages(productDetailImages);

		propertyValues = propertyValueService.listByParent(product);

		reviews = reviewService.listByParent(product);

		productService.setSaleAndReviewNumber(product);

		return "product.jsp";
	}

	@Action("forelogout")
	public String logout() {
		ActionContext.getContext().getSession().remove("user");
		return "homePage";
	}

	/**
	 * login.jsp的form提交数据到路径 forelogin,导致ForeAction.login()方法被调用
	 * <form class="loginForm" action="forelogin" method="post">
	 * 1. 把账号通过HtmlUtils.htmlEscape进行转义
	 * 2. 根据账号和密码获取User对象
	 * 2.1 如果对象为空，则服务端跳转回login.jsp，也带上错误信息，并且使用login.jsp中的办法显示错误信息
	 * 2.2 如果对象存在，则把对象保存在session中，并客户端跳转到首页"forehome"
	 * */

	@Action("forelogin")
	public String login() {
		user.setName(HtmlUtils.htmlEscape(user.getName()));
		User user_session = userService.get(user.getName(), user.getPassword());
		if (null == user_session) {
			msg = "账号密码错误";
			return "login.jsp";
		}
		ActionContext.getContext().getSession().put("user", user_session);
		return "homePage";
	}

	/**
	 * registerPage.jsp 的form提交数据到路径 foreregister,导致ForeAction.register()方法被调用
	 * <form method="post" action="foreregister" class="registerForm">
	 * 1. 通过HtmlUtils.htmlEscape();把账号里的特殊符号进行转义
	 * 2. 判断用户名是否存在
	 * 2.1 如果已经存在，就服务端跳转到reigster.jsp，并且带上错误提示信息
	 * 2.2 如果不存在，则加入到数据库中，并客户端跳转到registerSuccess.jsp页面
	 * 注：为什么要用 HtmlUtils.htmlEscape？
	 * 因为有些在恶意注册的时候，会使用诸如 <script>alert('papapa')</script> 这样的名称，会导致网页打开就弹出一个对话框。 那么在转义之后，就没有这个问题了。
	 * */

	@Action("foreregister")
	public String register() {

		user.setName(HtmlUtils.htmlEscape(user.getName()));
		boolean exist = userService.isExist(user.getName());

		if (exist) {
			msg = "用户名已经被使用，不能使用";
			setUser(null);
			return "register.jsp";
		}
		userService.save(user);
		return "registerSuccessPage";
	}

	/**
	 * home()方法映射首页访问路径 "forehome".
	 * 1. 查询所有分类
	 * 2. 为这些分类填充产品集合
	 * 3. 为这些分类填充推荐产品集合
	 * 4. 服务端跳转到home.jsp
	 * */

	@Action("forehome")
	public String home() {
		categorys = categoryService.list();
		productService.fill(categorys);
		productService.fillByRow(categorys);
		return "home.jsp";
	}

}
