/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月29日 下午3:17:30
*/
package com.how2java.tmall.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.how2java.tmall.service.BaseService;
import com.how2java.tmall.util.Page;

/**
 * 提供了一个BaseServiceImpl类来实现BaseService这个接口
 * */
@Service
public class BaseServiceImpl extends ServiceDelegateDAO implements BaseService {

	/**
	 * Autowired，英文单词的含义是自动装配
	 * 在spring的世界当中，自动装配指的就是使用将Spring容器中的bean自动的和我们需要这个bean的类组装在一起。
	 * 这个注解的作用下的定义就是:将Spring容器中的bean自动的和我们需要这个bean的类组装在一起协同使用。
	 * java的注解实现的核心技术是反射!!
	 * 注解只能是被看作元数据，它不包含任何业务逻辑。注解更像是一个标签，一个声明，表面被注释的这个地方，将具有某种特定的逻辑;
	 * Autowired注解可以应用在构造方法，普通方法，参数，字段，以及注解这五种类型的地方；
	 *使用@Autowired注入的bean对于目标类来说，从代码结构上来讲也就是一个普通的成员变量，@Autowired和spring一起工作，通过反射为这个成员变量赋值，也就是将其赋为期望的类实例。
	 * */

//	@Autowired
//	DAOImpl dao;

	protected Class clazz;

	/**
	 * Clazz对象的处理:
	 * Class clazz =Category.class;表示这个BaseServiceImpl是专门用于进行Category类的CURD的
	 * 1. 声明clazz的时候，不再指向Category.class 对象:protected Class clazz;
	 * 2.在构造方法中，借助异常处理和反射得到Category.class或者Product.class。 即要做到哪个类继承了BaseServiceImpl，clazz 就对应哪个类对象。
	 * 比如是 CategoryServiceImpl继承了BaseServiceImpl,那么这个clazz的值就是Category.class
	 * 比如是 ProductServiceImpl继承了BaseServiceImpl,那么这个clazz的值就是Product.class
	 * */
	public BaseServiceImpl() {

		/**
		 * 2.1 首先要获取是哪个类继承了BaseServiceImpl，这里用到了面向对象知识里的：实例化子类，父类的构造方法一定会被调用 这么一个知识点：
		 * 所以在父类BaseServiceImpl里故意抛出一个异常，然后手动捕捉住它，
		 * 在其对应的StackTrace里的第二个(下标是1) 栈跟踪元素StackTraceElement ，即对应子类。 
		 * 这样我们就拿到了子类名称 CategoryServiceImpl或者ProductServiceImpl
		 * */
		try {
			throw new Exception();
		} catch (Exception e) {
			StackTraceElement stes[] = e.getStackTrace();
			String serviceImpleClassName = stes[1].getClassName();
			try {

				/**
				 * 2.2 拿到了CategoryServiceImpl或者ProductServiceImpl，通过字符串替换,
				 * 拼接和反射，就得到了对应的实体类的类对象Category.class或者Product.class对象。
				 * 注:这样的做法是建立在服务实现类是放在xxx.service.impl包下的，而实体类是放在xxx.pojo包下的。
				 * */
				Class serviceImplClazz = Class.forName(serviceImpleClassName);
				String serviceImpleClassSimpleName = serviceImplClazz.getSimpleName();
				String pojoSimpleName = serviceImpleClassSimpleName.replaceAll("ServiceImpl", "");
				String pojoPackageName = serviceImplClazz.getPackage().getName().replaceAll(".service.impl", ".pojo");
				String pojoFullName = pojoPackageName + "." + pojoSimpleName;
				clazz = Class.forName(pojoFullName);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}

//  因为继承了ServiceDelegateDAO，所以就继承了update和delete方法	
//	@Override
//	public void update(Object object) {
//		dao.update(object);
//
//	}
//
//	@Override
//	public void delete(Object object) {
//		dao.delete(object);
//
//	}

	/**
	 *   作为委派模式，ServiceDelegateDAO提供的save方法如下：
	 *   public Serializable save(Object entity) throws DataAccessException {
	 *   return dao.save(entity);}
	 *   其返回类型是Serializable(自增长id) ，所以按照方法重写原则，作为其子类BaseServiceImpl重写的save方法,
	 *   其返回类型只能是Serializable 或者Serializable 的子类，所以这里选择的是Integer
	 *   BaseServiceImpl又实现了BaseService接口，而BaseService接口中声明了一个save方法。倘若BaseService接口中的save方法返回的是void，那么就矛盾了。
	 *   所以在BaseService中声明的save方法，返回的是Integer，用于和委派模式进行兼容。
	 * */

	@Override
	public Integer save(Object object) {
		return (Integer) super.save(object);
	}

	@Override
	public Object get(Class clazz, int id) {
		return super.get(clazz, id);
	}

	@Override
	public Object get(int id) {
		return get(clazz, id);
	}

	/**
	 * CategoryServiceImpl 实现了CategoryService 接口，提供list()方法的具体实现。
	 * 同时自动装配(注入) 了DAOImpl的实例dao。
	 * 
	 * 使用委派模式之后，就直接调用findByCriteria(dc)即可，根本意识不到dao对象的存在。
	 * 
	 * */

	@Override
	public List list() {
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);
		dc.addOrder(Order.desc("id"));
		return findByCriteria(dc);
	}

	@Override
	public List<Object> listByPage(Page page) {
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);
		dc.addOrder(Order.desc("id"));
		return findByCriteria(dc, page.getStart(), page.getCount());
	}

	@Override
	public int total() {
		String hql = "select count(*) from " + clazz.getName();
		List<Long> l = find(hql);
		if (l.isEmpty())
			return 0;
		Long result = l.get(0);

		return result.intValue();
	}

	/**
	 * 本次重构思路:就是根据父类对象，查询子类对象的集合。
	 * 查询父类下的所有子类对象，以为分类和属性为例，就是查询某个分类下的所有属性:
	 * 借助反射获取父类的类型名称
	 * 把第一个字母变成小写
	 * */

	@Override
	public List listByParent(Object parent) {
		String parentName = parent.getClass().getSimpleName();
		String parentNameWithFirstLetterLower = StringUtils.uncapitalize(parentName);
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);
		dc.add(Restrictions.eq(parentNameWithFirstLetterLower, parent));
		dc.addOrder(Order.desc("id"));
		return findByCriteria(dc);
	}

	@Override
	public List list(Page page, Object parent) {
		String parentName = parent.getClass().getSimpleName();
		String parentNameWithFirstLetterLower = StringUtils.uncapitalize(parentName);
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);
		dc.add(Restrictions.eq(parentNameWithFirstLetterLower, parent));
		dc.addOrder(Order.desc("id"));
		return findByCriteria(dc, page.getStart(), page.getCount());
	}

	@Override
	public int total(Object parentObject) {
		String parentName = parentObject.getClass().getSimpleName();
		String parentNameWithFirstLetterLower = StringUtils.uncapitalize(parentName);

		String sqlFormat = "select count(*) from %s bean where bean.%s = ?";
		String hql = String.format(sqlFormat, clazz.getName(), parentNameWithFirstLetterLower);

		List<Long> l = this.find(hql, parentObject);
		if (l.isEmpty())
			return 0;
		Long result = l.get(0);
		return result.intValue();
	}

	
	/**
	 * 这里使用了可变数量的参数的形式，因为有可能是两对参数，也有可能是三对参数，考虑到兼容性，使用了可变数量的参数。
	 * 需要注意的是，调用这个方法的时候，应该提供偶数个参数，否则会出错。
	 * 1. 把这个可变数量的参数，按照key,value,key,value,key,value的预判，取出来，并放进Map里
	 * 2. 遍历这个Map,借助DetachedCriteria，按照 key,value的方式设置查询条件
	 * 2.1 当value是null的时候，需要使用dc.add(Restrictions.isNull(key)); 这样风格的代码进行查询。
	 * 3. 按照id倒排序
	 * 4. 返回查询结果
	 * */
	
	@Override
	public List list(Object... pairParms) {

		HashMap<String, Object> m = new HashMap<>();
		for (int i = 0; i < pairParms.length; i = i + 2) 
			m.put(pairParms[i].toString(), pairParms[i + 1]);

			DetachedCriteria dc = DetachedCriteria.forClass(clazz);

			Set<String> ks = m.keySet();
			for (String key : ks) {
				if (null == m.get(key))
					dc.add(Restrictions.isNull(key));
				else
					dc.add(Restrictions.eq(key, m.get(key)));
			}
			dc.addOrder(Order.desc("id"));
			return this.findByCriteria(dc);
		

	}

}
