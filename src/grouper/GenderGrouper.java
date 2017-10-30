package grouper;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import model.ComparisonGroup;
import model.instance.Child;
import model.instance.Respondent;

public class GenderGrouper extends Grouper<ComparisonGroup, Respondent> {
	
	private final String GENDER_GROUP = "Gender Group";
	public static final String[] GENDER_GROUPS = {"Gender 1", "Gender 2", "ALL GENDER"};
	
	@Override
	public LinkedHashMap<String, ComparisonGroup> group(ArrayList<Respondent> respondents) {
		
		LinkedHashMap<String, ComparisonGroup> groups = new LinkedHashMap<String, ComparisonGroup>();
		for(String groupName : GENDER_GROUPS) {
			groups.put(groupName, new ComparisonGroup(groupName, GENDER_GROUP));
		}
		
		for(Respondent child : respondents) {
			if(((Child) child).getGender() == 1) {
				groups.get(GENDER_GROUPS[0]).addMember(child);
				groups.get(GENDER_GROUPS[2]).addMember(child);
			} else if(((Child) child).getGender() == 2) {
				groups.get(GENDER_GROUPS[1]).addMember(child);
				groups.get(GENDER_GROUPS[2]).addMember(child);
			}
		}
		
		return groups;
		
	}
	
}
