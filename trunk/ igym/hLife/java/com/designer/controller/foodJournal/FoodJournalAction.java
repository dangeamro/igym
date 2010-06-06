package com.designer.controller.foodJournal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.designer.common.chart.NutritionSummaryChart;
import com.designer.common.framework.ApplicationException;
import com.designer.common.framework.DesignerUtils;
import com.designer.controller.user.UserInfo;
import com.designer.dao.foodJournal.FoodJournalDAO;
import com.designer.dao.foodJournal.FoodJournalDAOImpl;
import com.designer.model.foodJournal.FoodItemModel;
import com.designer.model.foodJournal.MealNutrientSummaryModel;
import com.designer.model.foodJournal.ViewMealModel;
import com.designer.model.foodJournal.SuggestedNutrientSummary;


public class FoodJournalAction extends DispatchAction {

	
	public ActionForward addMeal(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request,HttpServletResponse response) {
		
		FoodJournalForm foodJournalForm=(FoodJournalForm)form;
		ActionMessages errors = new ActionMessages();
	
		String userId = (String)request.getParameter("targetUserId");
		if(userId == null || userId.trim().equals(""))
			userId = ((UserInfo)request.getSession().getAttribute("LOGIN_CREDENTIALS")).getUserId();
		
		foodJournalForm.setUserId(userId);
		
		String mealIdList=null;
		if(request.getParameter("mealIdList")!=null && request.getParameter("mealIdList")!=""){
			mealIdList=request.getParameter("mealIdList");
		}
		
		FoodJournalDAO foodJournalDAO = new FoodJournalDAOImpl();
		
		try{
			FoodItemModel foodItemModel= foodJournalForm.populateToModel();
			// foodItemModel.setMealIds(mealIds);
			foodJournalDAO.addMeal(foodItemModel,mealIdList, foodJournalForm);
			
		}catch(ApplicationException ae){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(ae.getMessage()));
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}
	
	public ActionForward getMeals(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){

		ActionMessages errors = new ActionMessages();		
		MealNutrientSummaryModel mealDaysSummary;
		SuggestedNutrientSummary suggestedNutriSummary;
		String mealDate=null;

		String userId = (String)request.getParameter("targetUserId");
		if(userId == null || userId.trim().equals(""))
			userId = ((UserInfo)request.getSession().getAttribute("LOGIN_CREDENTIALS")).getUserId();

		int pageCount=0;
		int pageNum=1;
		if(request.getParameter("pager.offset")!=null){
			String strT = request.getParameter("pageNum");
			if(strT.length()>=1)				
				pageNum=Integer.parseInt(request.getParameter("pageNum"));
		}else{
			pageNum=1;
		}
		
		FoodJournalDAO foodJournalDAO = new FoodJournalDAOImpl();
		
		try{
			ArrayList<ViewMealModel> foodList=foodJournalDAO.getMeals(userId,pageNum);
			//pageCount=foodJournalDAO.getPageCount(userId);
			pageCount=foodJournalDAO.getPageCount(userId);
			if(foodList.size() > 0){
			pageCount=foodJournalDAO.getPageCount(userId);

			Timestamp date = foodList.get(0).getDate();
			mealDate = DesignerUtils.formatDate(date, DesignerUtils.DATE_LONG);
			mealDaysSummary = foodJournalDAO.getNutrientSummaryForDay(userId, date);
			suggestedNutriSummary = foodJournalDAO.getSuggestedNutritionForUser(userId);
			
			String chartFileName = NutritionSummaryChart.generatePieChart(request, response, mealDaysSummary);
				request.setAttribute("MEAL_DATE",mealDate);
				request.setAttribute("PageCount", new Integer(pageCount));
				request.setAttribute("FoodList",foodList);
				request.setAttribute("MealSummary", mealDaysSummary);
				request.setAttribute("SuggestedNutriSummary", suggestedNutriSummary);
				request.setAttribute("fileName", chartFileName);
			}
			
		
		}catch (ApplicationException ae){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(ae.getMessage()));
			saveErrors(request,errors);
			return mapping.findForward("failure");
		}
		
		return mapping.findForward("success");
	}
	
	
	
	public ActionForward updateMeal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		ActionMessages errors = new ActionMessages();
		
		FoodJournalForm foodJournalForm=(FoodJournalForm)form;

		FoodJournalDAO  foodJournalDAO = new FoodJournalDAOImpl();
		
		try{
			foodJournalDAO.updateMeal(foodJournalForm);
		}catch(ApplicationException ae){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(ae.getMessage()));
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}
	
	
	
	public ActionForward deleteFood(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		ActionMessages errors = new ActionMessages();
		
		FoodJournalForm foodJournalForm=(FoodJournalForm)form;
		
				
		FoodJournalDAO foodJournalDAO = new FoodJournalDAOImpl();
		
		try{
			foodJournalDAO.deleteMeal(foodJournalForm.getMealId());
		}catch(ApplicationException ae){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(ae.getMessage()));
			return mapping.findForward("failure");
		}

		return mapping.findForward("success");
	}


	/*
	 * This method reads list of food items from xls
	 */
	public ActionForward getAddFoodTemplate(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request,HttpServletResponse response) {
		
		System.out.println("I am in GetListTest doGet ");
		StringBuffer sb=null;
 		List<String> ar=new ArrayList<String>();
 		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String myDB ="jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=C:/Projects/FCCMPro/Requirement Docs/Food_Journal_Database.xls";
			Connection con = DriverManager.getConnection(myDB);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM [Sheet1$]");

			ResultSetMetaData rsmd =rs.getMetaData();
			int noOfCols=rsmd.getColumnCount();
			while(rs.next()){
				sb=new StringBuffer();
				for(int i=1;i<=noOfCols;i++){
					if (i > 1)sb.append("\t");
					
					String columnValue = rs.getString(i);					
					sb.append(columnValue);
					
				}
				ar.add(sb.toString());
				//System.out.println("");
				
			}
			rs.close();
			stmt.close();
			con.close();

		}
		catch(Exception ex){
			System.err.print("Exception: ");
			System.err.println(ex.getMessage());
		} 		
 		request.setAttribute("DataList",ar);

		return mapping.findForward("success");
	}
	
	public ActionForward getAddFood(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		

		String userId = (String)request.getParameter("targetUserId");
		if(userId == null || userId.trim().equals(""))
			userId = ((UserInfo)request.getSession().getAttribute("LOGIN_CREDENTIALS")).getUserId();
		SuggestedNutrientSummary suggestedNutriSummary;
		MealNutrientSummaryModel mealDaysSummary;
		ActionMessages errors = new ActionMessages();
		FoodJournalDAO foodJournalDAO = new FoodJournalDAOImpl();
		ArrayList<FoodItemModel> foodList = new ArrayList<FoodItemModel>();
		try{
			foodList = foodJournalDAO.getMasterFoodList(userId);
			suggestedNutriSummary = foodJournalDAO.getSuggestedNutritionForUser(userId);
			System.out.println("hey ");
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			String strDate = df.format(System.currentTimeMillis());
			Timestamp datePart = new Timestamp(df.parse(strDate).getTime());
			
			
			mealDaysSummary = foodJournalDAO.getNutrientSummaryForDay(userId, datePart);
			
			
			
		}
		catch(ParseException ae){
			
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(ae.getMessage()));
			return mapping.findForward("failure");
		}
		catch(ApplicationException ae){
			
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(ae.getMessage()));
			return mapping.findForward("failure");
		}
		 
		
		request.setAttribute("foodList",foodList);
		request.setAttribute("daysNutrientSummary",mealDaysSummary);
		request.setAttribute("SuggestedNitriSummary",suggestedNutriSummary);
		System.out.println("FoodList Success ");
		return mapping.findForward("success");
	}
	
	 public ActionForward getAssignMeals(ActionMapping mapping, ActionForm form,
			   HttpServletRequest request, HttpServletResponse response){
			  
			  
			  System.out.println("I am in GetListTest getMeals ");
			  ActionMessages errors = new ActionMessages();
			  String assignedMealList=null;
			  ArrayList<FoodItemModel> foodList = new ArrayList<FoodItemModel>();
			  String userId = (String)request.getParameter("targetUserId");
			  if(userId == null || userId.trim().equals(""))
				  userId = ((UserInfo)request.getSession().getAttribute("LOGIN_CREDENTIALS")).getUserId();				
			  FoodJournalForm foodJournalForm=(FoodJournalForm)form;
			  foodJournalForm.setUserId(userId);
			  FoodJournalDAO foodJournalDAO = new FoodJournalDAOImpl();
			 
			  try{
			   foodList =  foodJournalDAO.getMasterFoodList(userId);
			   
			   foodJournalDAO = new FoodJournalDAOImpl();
			   
			   assignedMealList= foodJournalDAO.getAssignFood(userId);
			   
			   request.setAttribute("AssignList",assignedMealList);
			   request.setAttribute("FoodList",foodList);
			   
			   return mapping.findForward("success");
			  }catch(ApplicationException ae){
			   errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(ae.getMessage()));
			   return mapping.findForward("failure");
			  }
		}
			


		public ActionForward saveAssignMeal(ActionMapping mapping, ActionForm form, 
				HttpServletRequest request,HttpServletResponse response) {
			System.out.println("I am in AddMeal Action  saveAssignMeal" );
			ActionMessages errors = new ActionMessages();

			String userId = (String)request.getParameter("targetUserId");
			if(userId == null || userId.trim().equals(""))
				userId = ((UserInfo)request.getSession().getAttribute("LOGIN_CREDENTIALS")).getUserId();
			FoodJournalForm foodJournalForm=(FoodJournalForm)form;
			foodJournalForm.setUserId(userId);
			String mealIdList=null;
			if(request.getParameter("mealIdList")!=null && request.getParameter("mealIdList")!=""){
				mealIdList=request.getParameter("mealIdList");
	 		}
	 
			FoodJournalDAO foodJournalDAO = new FoodJournalDAOImpl();
			
			try{
				foodJournalDAO.saveAssignMeal(mealIdList,userId);
			}catch(ApplicationException ae){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(ae.getMessage()));
				return mapping.findForward("failure");
			}

			return mapping.findForward("success");
		}
		
	
	
}
