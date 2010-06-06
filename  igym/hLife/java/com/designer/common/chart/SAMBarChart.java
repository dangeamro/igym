package com.designer.common.chart;

import java.awt.Color;
import java.awt.GradientPaint;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.Layer;

import com.designer.common.framework.DesignerUtils;
import com.designer.model.statusAndMeasurements.AutomatedMeasurements;

public class SAMBarChart {


	public static ArrayList generateBarChart(HttpServletRequest request,HttpServletResponse response, AutomatedMeasurements automatedMeasurements) {
	    String filename = null;
	    PrintWriter pw;
	    
	    ArrayList<String> charFilename = new ArrayList<String>(); 
		
	   	try {
	   		pw = response.getWriter();	   		
	   		ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
	       
	   	
	   		JFreeChart Weightchart= createBarChart(createBarDataset(automatedMeasurements.getWeight(),null),automatedMeasurements.getWt_Min(),automatedMeasurements.getWt_Max(),0,300 );
	         filename = ServletUtilities.saveChartAsPNG(Weightchart, 300, 50, info, request.getSession());	        
	        //  Write the image map to the PrintWriter
	        ChartUtilities.writeImageMap(pw, filename, info,true);
	        charFilename.add(filename);
	        System.out.println("CHart file Name :: "+filename);
	        
	        JFreeChart LeanBodyMass= createBarChart(createBarDataset(automatedMeasurements.getLeanBodyMass(),null),null,null,0,250 );
	        filename = ServletUtilities.saveChartAsPNG(LeanBodyMass, 300, 50, info, request.getSession());	        
	        //  Write the image map to the PrintWriter
	        ChartUtilities.writeImageMap(pw, filename, info,true);
	        charFilename.add(filename);
	        System.out.println("CHart file Name :: "+filename);
	        
	        JFreeChart BodyFatMass= createBarChart(createBarDataset(automatedMeasurements.getWeight()-automatedMeasurements.getLeanBodyMass(),null),null,null,0,100 );
	        filename = ServletUtilities.saveChartAsPNG(BodyFatMass, 300, 50, info, request.getSession());	        
	        //  Write the image map to the PrintWriter
	        ChartUtilities.writeImageMap(pw, filename, info,true);
	        charFilename.add(filename);
	        System.out.println("CHart file Name :: "+filename);
	        
	        JFreeChart icw= createBarChart(createBarDataset(automatedMeasurements.getIcw(),null),automatedMeasurements.getIcw_Min(),automatedMeasurements.getIcw_max(),0,170 );
	        filename = ServletUtilities.saveChartAsPNG(icw, 300, 50, info, request.getSession());	        
	        //  Write the image map to the PrintWriter
	        ChartUtilities.writeImageMap(pw, filename, info,true);
	        charFilename.add(filename);
	        System.out.println("CHart file Name :: "+filename);
	        
	        JFreeChart ecw= createBarChart(createBarDataset(automatedMeasurements.getEcw(),null),automatedMeasurements.getEcw_min(),automatedMeasurements.getEcw_max(),0,170 );
	        filename = ServletUtilities.saveChartAsPNG(ecw, 300, 50, info, request.getSession());	        
	        //  Write the image map to the PrintWriter
	        ChartUtilities.writeImageMap(pw, filename, info,true);
	        charFilename.add(filename);
	        System.out.println("CHart file Name :: "+filename);
	        
	        JFreeChart tbw= createBarChart(createBarDataset(automatedMeasurements.getTbw(),null),null,null,0,170 );
	        filename = ServletUtilities.saveChartAsPNG(tbw, 300, 50, info, request.getSession());	        
	        //  Write the image map to the PrintWriter
	        ChartUtilities.writeImageMap(pw, filename, info,true);
	        charFilename.add(filename);
	        System.out.println("CHart file Name :: "+filename);
	        
	        JFreeChart rightArm= createBarChart(createBarDataset(automatedMeasurements.getLbmRightArm(),automatedMeasurements.getPcntLbmRightArm()),null,null,0,250 );
	        filename = ServletUtilities.saveChartAsPNG(rightArm, 300, 70, info, request.getSession());	        
	        //  Write the image map to the PrintWriter
	        ChartUtilities.writeImageMap(pw, filename, info,true);
	        charFilename.add(filename);
	        System.out.println("CHart file Name :: "+filename);
	        
	        JFreeChart leftArm= createBarChart(createBarDataset(automatedMeasurements.getLbmLeftArm(),automatedMeasurements.getPcntLbmLeftArm()),null,null,0,250 );
	        filename = ServletUtilities.saveChartAsPNG(leftArm, 300, 70, info, request.getSession());	        
	        //  Write the image map to the PrintWriter
	        ChartUtilities.writeImageMap(pw, filename, info,true);
	        charFilename.add(filename);
	        System.out.println("CHart file Name :: "+filename);
	        
	        JFreeChart trunk= createBarChart(createBarDataset(automatedMeasurements.getLbmTrunk(),automatedMeasurements.getPcntLbmTrunk()),null,null,0,250 );
	        filename = ServletUtilities.saveChartAsPNG(trunk, 300, 70, info, request.getSession());	        
	        //  Write the image map to the PrintWriter
	        ChartUtilities.writeImageMap(pw, filename, info,true);
	        charFilename.add(filename);
	        System.out.println("CHart file Name :: "+filename);
	        
	        JFreeChart rightLeg= createBarChart(createBarDataset(automatedMeasurements.getLbmRightLeg(),automatedMeasurements.getPcntLbmRightLeg()),null,null,0,250 );
	        filename = ServletUtilities.saveChartAsPNG(rightLeg, 300, 70, info, request.getSession());	        
	        //  Write the image map to the PrintWriter
	        ChartUtilities.writeImageMap(pw, filename, info,true);
	        charFilename.add(filename);
	        System.out.println("CHart file Name :: "+filename);
	        
	        JFreeChart leftLeg= createBarChart(createBarDataset(automatedMeasurements.getLbmLeftLeg(),automatedMeasurements.getPcntLbmLeftLeg()),null,null,0,250 );
	        filename = ServletUtilities.saveChartAsPNG(leftLeg, 300, 70, info, request.getSession());	        
	        //  Write the image map to the PrintWriter
	        ChartUtilities.writeImageMap(pw, filename, info,true);
	        charFilename.add(filename);
	        System.out.println("CHart file Name :: "+filename);
	        
	        pw.flush();
	   	}catch (Exception e) { 
	        System.out.println("Exception - " + e.toString());
	        e.printStackTrace(System.out);
	        filename = "public_error_500x300.png";
	    }
	   	System.out.println("Ant Test in Java ");
	    return charFilename;
	}

	private static JFreeChart createBarChart(final CategoryDataset dataset, Double min, Double max, int minRange,int maxRange) {
		 // create the chart...
	    final JFreeChart chart = ChartFactory.createBarChart(null,null,null,dataset,PlotOrientation.HORIZONTAL,false,true, false);

	    //chart.setBackgroundPaint(Color.white);
	    chart.setBackgroundPaint(new Color(16774132));

	    final CategoryPlot plot = chart.getCategoryPlot();
	    plot.setBackgroundPaint(new Color(16774132));
	    plot.setDomainGridlinePaint(Color.white);
	    plot.setRangeGridlinePaint(Color.white);
	    
	    if(min!=null & max!=null){
	    final IntervalMarker target = new IntervalMarker(min, max);        
        	target.setPaint(new Color(222, 222, 255, 128));
        	plot.addRangeMarker(target, Layer.FOREGROUND);
	    }
	    
	    final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
	    //rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	    rangeAxis.setAutoRange(false);      //no use, because in ValueAxis:In resizeRange(% > 0.0)->setAutoRange(true)->autoAdjustRange() still run it 
	      //rangeAxis.setRangeWithMargins(20,100); 
	      rangeAxis.setRange(minRange,maxRange); 
	      plot.setRangeAxis(rangeAxis);
	    
	    final BarRenderer renderer = (BarRenderer) plot.getRenderer();
//	  disable bar outlines...
       
        //DecimalFormat decimalformat1 = new DecimalFormat("$##,###.00");
	    DecimalFormat decimalformat1 = new DecimalFormat("#,###.## lbs");
        renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{0}", decimalformat1)); //i added your line here.
        renderer.setItemLabelsVisible(true);
        renderer.setBaseItemLabelsVisible(true);
        renderer.setMaximumBarWidth(0.50);
        chart.getCategoryPlot().setRenderer(renderer);
        renderer.setToolTipGenerator(new StandardCategoryToolTipGenerator("{0}", decimalformat1)
                );

	    renderer.setDrawBarOutline(false);
	    
	    final GradientPaint gp0 = new GradientPaint(
	        0.0f, 0.0f, Color.blue, 
	        0.0f, 0.0f, Color.GRAY
	    );
	    final GradientPaint gp1 = new GradientPaint(
	        0.0f, 0.0f, Color.green, 
	        0.0f, 0.0f, Color.lightGray
	    );
	    final GradientPaint gp2 = new GradientPaint(
	        0.0f, 0.0f, Color.red, 
	        0.0f, 0.0f, Color.lightGray
	    );
	    renderer.setSeriesPaint(0, gp0);
	    renderer.setSeriesPaint(1, gp1);
	    renderer.setSeriesPaint(2, gp2);

	    final CategoryAxis domainAxis = plot.getDomainAxis();
	    domainAxis.setCategoryLabelPositions(
	        CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
	    );
	    // OPTIONAL CUSTOMISATION COMPLETED.
	    
	    return chart;
	    
	}

	
	
	
	
	
	
	
	
	
	private static DefaultCategoryDataset createBarDataset(Double Value, Double Value2) {
		
		
		
	
		final String series1 = DesignerUtils.formatDouble(Value,"###0.#").toString()+" lbs";
		
		
	  	 
		final String category1 = "";

		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		if (Value2==null){
			dataset.addValue(Value, series1, category1);			
		}
		else{
			final String series2 =DesignerUtils.formatDouble(Value2,"###0.#").toString()+" %";
			dataset.addValue(Value2+5, series1, category1);
			dataset.addValue(Value2, series2, category1);
		}
		
		return dataset;

	   
	}
	
}
