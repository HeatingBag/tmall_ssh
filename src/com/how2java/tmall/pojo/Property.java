/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月30日 上午7:44:08
*/
package com.how2java.tmall.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "property")
public class Property {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	/**
	 * @ManyToOne 表示多对一关系
	 * @JoinColumn(name="cid") 表示关系字段是cid
	 * 对比xml中的映射方式：<many-to-one name="category" class="Category" column="cid" />
	 * */
	
	@ManyToOne
	@JoinColumn(name = "cid")
	private Category category;

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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
