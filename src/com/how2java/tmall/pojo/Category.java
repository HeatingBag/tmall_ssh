/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月25日 下午3:14:48
*/
package com.how2java.tmall.pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Id;

/**
 * @是hibernate的类注解的通用标识符，统一在applicationContext.xml中通过"packagesToScan"扫描并且判定
 * @Entity 表示这是一个实体类，用于映射表
 */
/**
 *  @Table(name = "category") 表示这是一个类，映射到的表名:category 
 *  */
@Entity
@Table(name = "category")
public class Category {

	/**
	 * @Id 表示这是主键
	 * @GeneratedValue(strategy = GenerationType.IDENTITY):
	 * 表示主键由数据库自动生成（主要是自动增长类型），也就是说自增长方式使用mysql自带的
	 * @Column(name = "id") 表示映射到字段id
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int id;

	String name;
	
	/**
	 * 两个瞬时字段products和productsByRow。
	 * products代表一个分类下有多个产品。
	 * productsByRow这个属性的类型是List<List<Product>> productsByRow。
	 * 即一个分类又对应多个 List<Product>，提供这个属性，是为了在首页竖状导航的分类名称右边显示推荐产品列表。
	 * */

	/**
	 * products比较好理解，代表一个分类下有多个产品。
	 * productsByRow这个属性的类型是List<List<Product>> productsByRow:
	 * 即一个分类又对应多个 List<Product>，提供这个属性，是为了在首页竖状导航的分类名称右边显示推荐产品列表。
	 * 
	 * */
	
	@Transient
	List<Product> products;

	@Transient
	List<List<Product>> productsByRow;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "Category[id=" + id + ", name=" + name + "]";
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<List<Product>> getProductsByRow() {
		return productsByRow;
	}

	public void setProductsByRow(List<List<Product>> productsByRow) {
		this.productsByRow = productsByRow;
	}

}
