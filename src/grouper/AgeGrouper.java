package grouper;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import model.ComparisonGroup;
import model.instance.Child;
import model.instance.Respondent;

public class AgeGrouper extends Grouper<ComparisonGroup, Respondent> {
	
	private final String AGE_GROUP = "Age Group";
	public static final String[] AGE_GROUPS = {"9 to 11", "12 to 14", "15 to 17", "ALL AGE"};
	
	@Override
	public LinkedHashMap<String, ComparisonGroup> group(ArrayList<Respondent> respondents) {
		
		LinkedHashMap<String, ComparisonGroup> groups = new LinkedHashMap<String, ComparisonGroup>();
		for(String groupName : AGE_GROUPS) {
			groups.put(groupName, new ComparisonGroup(groupName, AGE_GROUP));
		}
		
		for(Respondent child : respondents) {
			if(((Child) child).getAge() >= 9 && ((Child) child).getAge() <= 11) {
				groups.get(AGE_GROUPS[0]).addMember(child);
				groups.get(AGE_GROUPS[3]).addMember(child);
			} else if(((Child) child).getAge() >= 12 && ((Child) child).getAge() <= 14) {
				groups.get(AGE_GROUPS[1]).addMember(child);
				groups.get(AGE_GROUPS[3]).addMember(child);
			} else if(((Child) child).getAge() >= 15 && ((Child) child).getAge() <= 17) {
				groups.get(AGE_GROUPS[2]).addMember(child);
				groups.get(AGE_GROUPS[3]).addMember(child);
			}
		}
		
		return groups;
	}

}
