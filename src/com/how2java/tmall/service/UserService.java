/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月30日 下午9:01:24
*/
package com.how2java.tmall.service;

import com.how2java.tmall.pojo.User;

public interface UserService extends BaseService {
	
	boolean isExist(String name);
	
	User get(String name,String password);

}
