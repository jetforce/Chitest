package grouper;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import model.Group;
import model.instance.Instance;

public abstract class Grouper<T extends Group<?>, S extends Instance> {
	
	protected abstract LinkedHashMap<String, T> group(ArrayList<S> respondents);
	
}
