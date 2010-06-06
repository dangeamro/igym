package com.designer.manager;

import com.designer.common.framework.ApplicationException;
import com.designer.dao.goal.GoalDAO;
import com.designer.dao.goal.GoalDAOImpl;
import com.designer.model.goal.Goal;

public class GoalManager extends AbstractManager {

	GoalDAO goalDAO = null;
	
	public GoalManager(){
		goalDAO = new GoalDAOImpl();
	}
	
	public Goal getGoalForUser(String UserId) throws ApplicationException
	{
		return goalDAO.getGoalForUser(UserId);
	}
	
	public void addGoal(Goal goal) throws ApplicationException
	{
		goalDAO.addGoal(goal);
	}
	
	public void updateGoal(Goal goal) throws ApplicationException
	{
		goalDAO.updateGoal(goal);
	}
}
