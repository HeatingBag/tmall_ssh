/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月25日 下午3:25:11
*/
package com.how2java.tmall.dao.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * @Repository和@Controller、@Service、@Component的作用差不多，都是把对象交给spring管理
 * @Repository用在持久层的接口上，这个注解是将接口的一个实现类交给spring管理 
 * @Repository("Bean的名称"):定义DAO层Bean
 */

/**
 * DAOImpl 继承了HibernateTemplate，从而提供了各种各样的CRUD方法，满足各种数据库操作的需要。
 * */


@Repository("dao")
public class DAOImpl extends HibernateTemplate {

	/**
	 * @Resource的作用相当于@Autowired，只不过@Autowired按byType自动注入，而@Resource默认按 byName自动注入罢了;
	 * @Resource有两个属性是比较重要的，分是name和type，Spring将@Resource注解的name属性解析为bean的名字，而type属性则解析为bean的类型。
	 * 所以如果使用name属性，则使用byName的自动注入策略，而使用type属性时则使用byType自动注入策略;
	 * 如果既不指定name也不指定type属性，这时将通过反射机制使用byName自动注入策略.
	 * @Resource装配顺序:
	 * 1. 如果同时指定了name和type，则从Spring上下文中找到唯一匹配的bean进行装配，找不到则抛出异常
	 * 2. 如果指定了name，则从上下文中查找名称（id）匹配的bean进行装配，找不到则抛出异常
	 * 3. 如果指定了type，则从上下文中找到类型匹配的唯一bean进行装配，找不到或者找到多个，都会抛出异常
	 * 4. 如果既没有指定name，又没有指定type，则自动按照byName方式进行装配；如果没有匹配，则回退为一个原始类型进行匹配，如果匹配则自动装配；
	 * */
	/**
	 * 重写setSessionFactory方法，并使用@Resource(name="sf")对其注解，以接受sessionFactory注入;
	 * 虽然 DAOImpl 继承了HibernateTemplate ，有setSessionFactory方法，但是HibernateTemplate 中的setSessionFactory方法，并没有被注解，
	 * 所以就不会被注入sf, 因此需要在这里重写这个方法.
	 * 此处的@Resource(name = "sf")对应applicationContext.xml中的bean name="sf";启动注解配置
	 * 提供setSessionFactory()方法， 以用于注入SessionFactory
	 * */

	@Resource(name = "sf")
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	};
}
