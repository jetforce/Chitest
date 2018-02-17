package factory;

import java.util.ArrayList;

import model.instance.Instance;

public class GenericInstanceFactory implements InstanceAbstractFactory {
	
	private final String tag;
	private final ArrayList<Double> weights;
	
	public GenericInstanceFactory(String[] details) {
		
		tag = details[0];
		
		weights = new ArrayList<Double>();
		for(int i = 1; i < details.length; i++) {
			weights.add(new Double(Double.parseDouble(details[i])));
		}
		
	}

	@Override
	public Instance createInstance() {
		return new Instance(tag, weights);
	}
	
	
	
}
