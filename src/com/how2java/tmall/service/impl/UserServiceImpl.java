/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月30日 下午9:01:45
*/
package com.how2java.tmall.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.how2java.tmall.pojo.User;
import com.how2java.tmall.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	@Override
	public boolean isExist(String name) {

		List l = list("name", name);
		if (!l.isEmpty())
			return true;
		return false;
	}

	@Override
	public User get(String name, String password) {
		List<User> l = list("name", name, "password", password);
		if (l.isEmpty())
			return null;
		return l.get(0);
	}

}
