/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月25日 下午3:46:12
*/
package com.how2java.tmall.service.impl;

import org.springframework.stereotype.Service;

import com.how2java.tmall.service.CategoryService;

/**
 * CategoryServiceImpl 实现了CategoryService 接口，提供list()方法的具体实现。
 * 同时自动装配(注入) 了DAOImpl的实例dao。
 * */

/**
 * @Service也是和applicationContext.xml相对应，根据配置applicationContext会扫描com.how2java.*下所有的代码，
 * 如果一个类带了@Service注解，将自动注册到Spring容器，不需要再在applicationContext.xml配置文件中定义bean了
 * @Service("serviceName")注解相当于applicationContext.xml配置文件中配置的<bean id="serviceName">，表示给当前类命名一个别名，方便注入到其他需要用到的类中
 * @Service注解也可以不指定serviceName，不加的话，默认别名就是当前类名，但是首字母小写
 * 此处的@Service相当于<bean id="com.how2java.tmall.service.impl.CategoryServiceImpl">,
 * @Service也相当于一种说明。这个类是一个服务类，用于spring
 * */

/**
 * CategoryServiceImpl也不需要自己提供实现了，继承BaseServiceImpl 并实现接口CategoryService 
 * */

@Service
public class CategoryServiceImpl extends BaseServiceImpl implements CategoryService {
	
	

//
//	/**
//	 * Autowired，英文单词的含义是自动装配
//	 * 在spring的世界当中，自动装配指的就是使用将Spring容器中的bean自动的和我们需要这个bean的类组装在一起。
//	 * 这个注解的作用下的定义就是:将Spring容器中的bean自动的和我们需要这个bean的类组装在一起协同使用。
//	 * java的注解实现的核心技术是反射!!
//	 * 注解只能是被看作元数据，它不包含任何业务逻辑。注解更像是一个标签，一个声明，表面被注释的这个地方，将具有某种特定的逻辑;
//	 * Autowired注解可以应用在构造方法，普通方法，参数，字段，以及注解这五种类型的地方；
//	 *使用@Autowired注入的bean对于目标类来说，从代码结构上来讲也就是一个普通的成员变量，@Autowired和spring一起工作，通过反射为这个成员变量赋值，也就是将其赋为期望的类实例。
//	 * */
//
//	@Autowired
//	DAOImpl dao;
//
//	/**
//	 * CategoryServiceImpl 实现了CategoryService 接口，提供list()方法的具体实现。
//	 * 同时自动装配(注入) 了DAOImpl的实例dao。
//	 * */
//
//	@Override
//	public List list() {
//		DetachedCriteria dc = DetachedCriteria.forClass(Category.class);
//		dc.addOrder(Order.desc("id"));
//		return dao.findByCriteria(dc);
//	}
//
//	/**
//	 * 编写sql的查询语句，然后返回查询出来的总数；
//	 * */
//	@Override
//	public int total() {
//		String hql = "select count(*) from Category";
//		List<Long> l = dao.find(hql);
//		if (l.isEmpty())
//			return 0;
//		Long result = l.get(0);
//
//		return result.intValue();
//	}
//
//	/**
//	 * 根据每一页的数量查询，同样的，倒序
//	 * */
//	@Override
//	public List<Category> listByPage(Page page) {
//		DetachedCriteria dc = DetachedCriteria.forClass(Category.class);
//		dc.addOrder(Order.desc("id"));
//		return dao.findByCriteria(dc, page.getStart(), page.getCount());
//	}
//
//	@Override
//	public void save(Category category) {
//		dao.save(category);
//
//	}
//
//	@Override
//	public void delete(Category category) {
//		dao.delete(category);
//	}
//
//	@Override
//	public Category get(Class clazz, int id) {
//		return (Category) dao.get(clazz, id);
//	}
//
//	@Override
//	public void update(Category category) {
//		dao.update(category);
//
//	}

}
