package factory;

import java.util.ArrayList;

import model.instance.Node;

public class NodeFactory implements InstanceAbstractFactory {
	
	private static final int WEIGHT_START = 1;

	private final int row;
	private final int col;
	private final ArrayList<Double> weights;
	
	public NodeFactory(String[] details) {
		
		row = Integer.parseInt(details[0].split(";")[0]);
		col = Integer.parseInt(details[0].split(";")[1]);
		
		weights = new ArrayList<Double>();
		for(int i = WEIGHT_START; i < details.length; i++) {
			weights.add(new Double(Double.parseDouble(details[i])));
		}
		
	}
	
	@Override
	public Node createInstance() {
		return new Node(row, col, weights);
	}
	
}
