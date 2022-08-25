/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月29日 下午4:56:35
*/
package com.how2java.tmall.action;

import java.util.List;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Order;
import com.how2java.tmall.pojo.OrderItem;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.ProductImage;
import com.how2java.tmall.pojo.Property;
import com.how2java.tmall.pojo.PropertyValue;
import com.how2java.tmall.pojo.Review;
import com.how2java.tmall.pojo.User;

/**
 * Action4Pojo用于提供实体对象以及实体对象集合的setter和getter.
 * setter用于接收注入
 * getter用于提供数据到JSP(VIEW)上
 * */
public class Action4Pojo extends Action4Pagination {

	protected Category category;

	protected Property property;

	protected Product product;

	protected ProductImage productImage;

	protected PropertyValue propertyValue;

	protected User user;

	protected Order order;

	protected Review review;

	protected OrderItem orderItem;

	protected List<Category> categorys;

	protected List<Property> propertys;

	protected List<Product> products;

	protected List<ProductImage> productSingleImages;

	protected List<ProductImage> productDetailImages;

	protected List<PropertyValue> propertyValues;

	protected List<User> users;

	protected List<Order> orders;

	protected List<Review> reviews;

	protected List<OrderItem> orderItems;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Category> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<Category> categorys) {
		this.categorys = categorys;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public List<Property> getPropertys() {
		return propertys;
	}

	public void setPropertys(List<Property> propertys) {
		this.propertys = propertys;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public ProductImage getProductImage() {
		return productImage;
	}

	public void setProductImage(ProductImage productImage) {
		this.productImage = productImage;
	}

	public List<ProductImage> getProductSingleImages() {
		return productSingleImages;
	}

	public void setProductSingleImages(List<ProductImage> productSingleImages) {
		this.productSingleImages = productSingleImages;
	}

	public List<ProductImage> getProductDetailImages() {
		return productDetailImages;
	}

	public void setProductDetailImages(List<ProductImage> productDetailImages) {
		this.productDetailImages = productDetailImages;
	}

	public PropertyValue getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(PropertyValue propertyValue) {
		this.propertyValue = propertyValue;
	}

	public List<PropertyValue> getPropertyValues() {
		return propertyValues;
	}

	public void setPropertyValues(List<PropertyValue> propertyValues) {
		this.propertyValues = propertyValues;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

}
