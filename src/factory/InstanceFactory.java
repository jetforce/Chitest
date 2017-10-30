package factory;

import model.instance.Instance;

public class InstanceFactory {
	
	public static Instance createInstance(InstanceAbstractFactory factory) {
		return factory.createInstance();
	}
	
}
