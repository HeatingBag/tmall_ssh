/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月29日 下午3:13:25
*/
package com.how2java.tmall.service;

import java.util.List;

import com.how2java.tmall.util.Page;

/**
 * 开发分类管理的过程中，Service层用到了一个接口和一个实现类，分别是CategoryService和CategoryServiceImpl。
 * 首先看接口：CategoryService。 其声明的方法基本上就是CURD和分页。
 * 可以预见的是，在后续做产品管理，用户管理，订单管理等等，也会有这么一个非常近似的CURD的接口，换句话说，这里是有做抽象和代码重构(Refactory)的机会和价值的。
 * 然后看实现类：CategoryServiceImpl。 CategoryServiceImpl本身其实就是个架子，真正起作用的是为其注入的DAO对象，所以这个地方也是可以引入委派模式，使得代码调用更加顺畅
 * 
 * 可以预见的在后续做产品管理，用户管理，订单管理等等，也会有这么一个非常近似的CURD的接口，那么我们就做一个BaseService，里面就提供这些CRUD和分页查询的方法
 * 注：提供了一个新的get(int id)方法，不需要指定clazz也行，只需要提供id即可。
 * */

public interface BaseService {

	public Integer save(Object object);

	public void update(Object object);

	public void delete(Object object);

	public Object get(Class clazz, int id);

	public Object get(int id);

	public List list();

	public List listByPage(Page page);

	public int total();

	/*根据父类查询所有子类对象(比如：查询某个分类下所有属性)*/
	public List listByParent(Object object);

	/*根据父类分页查询子类对象(比如：查询某个分类下前5个属性)*/
	public List list(Page page, Object object);

	/*根据父类查询子类对象数量(比如：查询分类下属性数量)*/
	public int total(Object parentObject);
	
	/*多条件查询的需求*/
	public List list(Object ...pairParms);

}
