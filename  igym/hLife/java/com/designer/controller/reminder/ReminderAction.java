package com.designer.controller.reminder;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.designer.common.framework.ApplicationException;
import com.designer.common.util.ReminderDateCheck;
import com.designer.manager.reminder.ReminderManager;
import com.designer.model.reminder.Module;

public class ReminderAction extends DispatchAction {

	private ReminderManager manager = new ReminderManager();

	public ActionForward getModules(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		List<Module> modules = null;
		try {
			modules = manager.getModules();
		}
		catch(ApplicationException ex)
		{
			ex.printStackTrace();
			return mapping.findForward("failure");
		}
		List<ModuleForm> forms = new ArrayList<ModuleForm>();
		for(Module module : modules)
		{
			ModuleForm moduleForm = new ModuleForm();
			moduleForm.populateFromModel(module);
			forms.add(moduleForm);
		}
		
		request.setAttribute("modules", forms);
		
		return mapping.findForward("success");
	}

	public ActionForward getModule(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		ModuleForm modForm = new ModuleForm();
		try {
			Module module = manager.getModule(request.getParameter("moduleKey"));
			if(module != null)
			{
				modForm.populateFromModel(module);
				request.setAttribute("module", modForm);
			}
		}
		catch(ApplicationException ex)
		{
			ex.printStackTrace();
			return mapping.findForward("failure");
		}
		
		
		return mapping.findForward("success");
	}

	public ActionForward updateReminders(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		List<ReminderDateCheck> dateChecks = new ArrayList<ReminderDateCheck>();
		List<Integer> enabledList = new ArrayList<Integer>();
		
		String [] values = request.getParameter("checks").split(",");
		for(int i = 0; i < values.length; i++)
		{
			if(values[i].equals(""))
				continue;
			String[] tokens = values[i].split(":");
			if(tokens.length != 3)
				continue;
			dateChecks.add(new ReminderDateCheck(Integer.valueOf(tokens[0]), tokens[1], Integer.valueOf(tokens[2])));
		}

		values = request.getParameter("enabledList").split(",");
		for(int i = 0; i < values.length; i++)
		{
			if(values[i].equals(""))
				continue;
			enabledList.add(Integer.valueOf(values[i]));
		}

		try {
			updateDateChecks(dateChecks);
			updateEnabled(enabledList);
		}
		catch(ApplicationException ex)
		{
			ex.printStackTrace();
			return mapping.findForward("failure");
		}
		
		
		return mapping.findForward("success");
	}
	
	private void updateDateChecks(List<ReminderDateCheck> checks) throws ApplicationException
	{
		manager.updateChecks(checks);
	}
	private void updateEnabled(List<Integer> enabledList) throws ApplicationException
	{
		manager.updateEnabled(enabledList);		
	}
}
