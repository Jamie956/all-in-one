package org.example._1_ico_container._3_bean_definition;

import org.example.share.EmptyObject;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MyFactoryBean implements FactoryBean<EmptyObject> {
	@Override
	public EmptyObject getObject() {
		//breakpoint here
		EmptyObject o = new EmptyObject();
		return o;
	}

	@Override
	public Class<?> getObjectType() {
		return EmptyObject.class;
	}
}
