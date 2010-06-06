package com.designer.dao.goal;

import com.designer.common.framework.ApplicationException;
import com.designer.model.goal.Goal;

public interface GoalDAO {

	public Goal getGoalForUser(String UserId) throws ApplicationException;
	
	public void addGoal(Goal goal) throws ApplicationException;
	
	public void updateGoal(Goal goal) throws ApplicationException;
}
