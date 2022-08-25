/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月30日 下午7:59:47
*/
package com.how2java.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.Property;
import com.how2java.tmall.pojo.PropertyValue;
import com.how2java.tmall.service.PropertyValueService;
import com.how2java.tmall.service.PropertyService;

@Service
public class PropertyValueServiceImpl extends BaseServiceImpl implements PropertyValueService {

	@Autowired
	PropertyService propertyService;
	
	/**
	 * 初始化PropertyValue:对于PropertyValue的管理，没有增加，只有修改。 所以需要通过初始化来进行自动地增加，以便于后面的修改。
	 * 根据产品获取分类，然后获取这个分类下的所有属性集合
	 * 然后用属性和产品去查询，看看这个属性和这个产品，是否已经存在属性值了。
	 * 如果不存在，那么就创建一个属性值，并设置其属性和产品，接着插入到数据库中。
	 * */

	@Override
	public void init(Product product) {

		List<Property> propertys = propertyService.listByParent(product.getCategory());
		for (Property property : propertys) {
			PropertyValue propertyValue = get(property, product);
			if (null == propertyValue) {
				propertyValue = new PropertyValue();
				propertyValue.setProduct(product);
				propertyValue.setProperty(property);
				save(propertyValue);
			}
		}

	}

	private PropertyValue get(Property property, Product product) {
		List<PropertyValue> result = this.list("property", property, "product", product);
		if (result.isEmpty())
			return null;
		return result.get(0);
	}

}
