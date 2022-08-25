/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月30日 下午8:57:16
*/
package com.how2java.tmall.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	private String password;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAnonymousName() {
		if (null == name)
			return null;

		if (name.length() <= 1)
			return "*";

		if (name.length() == 2)
			return name.substring(0, 1) + "*";

		char[] cs = name.toCharArray();
		for (int i = 0; i < cs.length - 1; i++) {
			cs[i] = '*';
		}
		return new String(cs);
	}

}
