package com.designer.common.chart;

import java.awt.Color;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;

import com.designer.model.statusAndMeasurements.AutomatedMeasurements;


public class CompareMeasurementsChart {
	
	public static ArrayList<String> generateCompareChart(HttpServletRequest request, HttpServletResponse response, List<AutomatedMeasurements> autoMeasureList) {
		String filename = null;
		PrintWriter pw = null;
		ArrayList<String> charFilename = new ArrayList<String>(); 
		
	   	try {
	   		pw = response.getWriter();	   		
	   		ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
	       
	   	
	   		JFreeChart BodyMassChart= createChart(createDatasetBodyMass(autoMeasureList),null,null,null);;
	         filename = ServletUtilities.saveChartAsPNG(BodyMassChart, 700, 300, info, request.getSession());	        
	        //  Write the image map to the PrintWriter
	        ChartUtilities.writeImageMap(pw, filename, info,true);
	        charFilename.add(filename);
	        System.out.println("CHart file Name :: "+filename);
	        
	        JFreeChart BodyWaterChart= createChart(createDatasetBodyWater(autoMeasureList),null,null,null);;
	         filename = ServletUtilities.saveChartAsPNG(BodyWaterChart, 700, 300, info, request.getSession());	        
	        //  Write the image map to the PrintWriter
	        ChartUtilities.writeImageMap(pw, filename, info,true);
	        charFilename.add(filename);
	        System.out.println("CHart file Name :: "+filename);
	        
	        JFreeChart ObesityChart= createChart(createDatasetObesity(autoMeasureList),null,null,null);;
	         filename = ServletUtilities.saveChartAsPNG(ObesityChart, 700, 300, info, request.getSession());	        
	        //  Write the image map to the PrintWriter
	        ChartUtilities.writeImageMap(pw, filename, info,true);
	        charFilename.add(filename);
	        System.out.println("CHart file Name :: "+filename);
	        
	        JFreeChart leanBodyArmChart= createChart(createDatasetLeanBodyArm(autoMeasureList),null,null,null);;
	         filename = ServletUtilities.saveChartAsPNG(leanBodyArmChart, 700, 200, info, request.getSession());	        
	        //  Write the image map to the PrintWriter
	        ChartUtilities.writeImageMap(pw, filename, info,true);
	        charFilename.add(filename);
	        System.out.println("CHart file Name :: "+filename);
	        
	        JFreeChart leanBodyTrunkChart= createChart(createDatasetLeanBodyTrunk(autoMeasureList),null,null,null);;
	         filename = ServletUtilities.saveChartAsPNG(leanBodyTrunkChart, 700, 200, info, request.getSession());	        
	        //  Write the image map to the PrintWriter
	        ChartUtilities.writeImageMap(pw, filename, info,true);
	        charFilename.add(filename);
	        System.out.println("CHart file Name :: "+filename);
	        
	        JFreeChart leanBodyLegChart= createChart(createDatasetLeanBodyLeg(autoMeasureList),null,null,null);;
	         filename = ServletUtilities.saveChartAsPNG(leanBodyLegChart, 700, 200, info, request.getSession());	        
	        //  Write the image map to the PrintWriter
	        ChartUtilities.writeImageMap(pw, filename, info,true);
	        charFilename.add(filename);
	        System.out.println("CHart file Name :: "+filename);
	        
	        pw.flush(); 	
	   	}catch(Exception e) { 
	        System.out.println("Exception - " + e.toString());
	        e.printStackTrace(System.out);
	        filename = "public_error_500x300.png";
	    }   
		
		
		
		return charFilename;
		
	}
	
	private static JFreeChart createChart(XYDataset dataset, String Heading, String xAxisLabel, String yAxisLabel) {

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
        		Heading,  // title
        		xAxisLabel,             // x-axis label
        		yAxisLabel,   // y-axis label
            dataset,            // data
            true,               // create legend?
            true,               // generate tooltips?
            false               // generate URLs?
        );

        chart.setBackgroundPaint(new Color(16774132));

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        //plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        
        
        /*if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setDefaultShapesVisible(true);
            renderer.setDefaultShapesFilled(true);
        }*/
        final XYLineAndShapeRenderer r = new XYLineAndShapeRenderer();
        r.setToolTipGenerator(
            new StandardXYToolTipGenerator(
                "{0}:{2},  {1}",
                new SimpleDateFormat("dd-MMM-yyyy"), new DecimalFormat("0.00")
            )
        );

        
        plot.setRenderer(r);
        //plot.setRenderer(renderer2);
        
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("dd-MMM"));
        
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
	    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	    rangeAxis.setAutoRange(true);      //no use, because in ValueAxis:In resizeRange(% > 0.0)->setAutoRange(true)->autoAdjustRange() still run it
	    //rangeAxis.setAutoRangeIncludesZero(true);
	    plot.setRangeAxis(rangeAxis);
        
        return chart;

    }
	private static XYDataset createDatasetBodyMass(List<AutomatedMeasurements> autoMeasurelist) {

		TimeSeries s1 = new TimeSeries("Total Weight", FixedMillisecond.class);
		TimeSeries s2 = new TimeSeries("Lean Body Mass", FixedMillisecond.class);
		TimeSeries s3 = new TimeSeries("Body Fat Mass", FixedMillisecond.class);
	          
		for(AutomatedMeasurements automatedMeasurements : autoMeasurelist){
			 
			Date date = automatedMeasurements.getDate_TimeAsDate();
			System.out.println("weight "+automatedMeasurements.getWeight()+" lbm "+automatedMeasurements.getLeanBodyMass());
			s1.add(new FixedMillisecond(date),automatedMeasurements.getWeight());
			s2.add(new FixedMillisecond(date),automatedMeasurements.getLeanBodyMass());
			s3.add(new FixedMillisecond(date),automatedMeasurements.getWeight()-automatedMeasurements.getLeanBodyMass());
			
		}        

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(s2);
        dataset.addSeries(s3);
        dataset.setDomainIsPointsInTime(true);

        return dataset;

    }
	
	private static XYDataset createDatasetBodyWater(List<AutomatedMeasurements> autoMeasurelist) {

		TimeSeries s1 = new TimeSeries("IntraCellular Water", FixedMillisecond.class);
		TimeSeries s2 = new TimeSeries("ExtraCellular Water", FixedMillisecond.class);
		TimeSeries s3 = new TimeSeries("Total Body Water", FixedMillisecond.class);
	          
		for(AutomatedMeasurements automatedMeasurements : autoMeasurelist){
			 
			Date date = automatedMeasurements.getDate_TimeAsDate();
			//System.out.println("weight "+automatedMeasurements.getWeight()+" lbm "+automatedMeasurements.getLeanBodyMass());
			s1.add(new FixedMillisecond(date),automatedMeasurements.getIcw());
			s2.add(new FixedMillisecond(date),automatedMeasurements.getEcw());
			s3.add(new FixedMillisecond(date),automatedMeasurements.getTbw());
			
		}        

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(s2);
        dataset.addSeries(s3);
        dataset.setDomainIsPointsInTime(true);

        return dataset;

    }

	
	private static XYDataset createDatasetObesity(List<AutomatedMeasurements> autoMeasurelist) {

		TimeSeries s1 = new TimeSeries("BMI (kg/m^2)", FixedMillisecond.class);
		TimeSeries s2 = new TimeSeries("PBF (%)", FixedMillisecond.class);
		//TimeSeries s3 = new TimeSeries("Total Body Water", FixedMillisecond.class);
	          
		for(AutomatedMeasurements automatedMeasurements : autoMeasurelist){
			 
			Date date = automatedMeasurements.getDate_TimeAsDate();
			//System.out.println("weight "+automatedMeasurements.getWeight()+" lbm "+automatedMeasurements.getLeanBodyMass());
			s1.add(new FixedMillisecond(date),automatedMeasurements.getBmi());
			s2.add(new FixedMillisecond(date),automatedMeasurements.getPbf());
			//s3.add(new FixedMillisecond(date),automatedMeasurements.getTbw());
			
		}        

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(s2);
        //dataset.addSeries(s3);
        dataset.setDomainIsPointsInTime(true);

        return dataset;

    }
	
	private static XYDataset createDatasetLeanBodyArm(List<AutomatedMeasurements> autoMeasurelist) {

		
		TimeSeries s1 = new TimeSeries("Left Arm", FixedMillisecond.class);
		TimeSeries s2 = new TimeSeries("Right Arm", FixedMillisecond.class);
		
	          
		for(AutomatedMeasurements automatedMeasurements : autoMeasurelist){
			 
			Date date = automatedMeasurements.getDate_TimeAsDate();
			//System.out.println("weight "+automatedMeasurements.getWeight()+" lbm "+automatedMeasurements.getLeanBodyMass());
			
			s1.add(new FixedMillisecond(date),automatedMeasurements.getLbmLeftArm());
			s2.add(new FixedMillisecond(date),automatedMeasurements.getLbmRightArm());
			
			
		}        

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(s2);
        
        dataset.setDomainIsPointsInTime(true);

        return dataset;

    }
	
	private static XYDataset createDatasetLeanBodyTrunk(List<AutomatedMeasurements> autoMeasurelist) {

		
		TimeSeries s3 = new TimeSeries("Trunk", FixedMillisecond.class);
		
	          
		for(AutomatedMeasurements automatedMeasurements : autoMeasurelist){
			 
			Date date = automatedMeasurements.getDate_TimeAsDate();
			//System.out.println("weight "+automatedMeasurements.getWeight()+" lbm "+automatedMeasurements.getLeanBodyMass());
			
			s3.add(new FixedMillisecond(date),automatedMeasurements.getLbmTrunk());
			
			
		}        

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        
        dataset.addSeries(s3);
        
        dataset.setDomainIsPointsInTime(true);

        return dataset;

    }
	
	private static XYDataset createDatasetLeanBodyLeg(List<AutomatedMeasurements> autoMeasurelist) {

		
		
		TimeSeries s4 = new TimeSeries("Left Leg", FixedMillisecond.class);
		TimeSeries s5 = new TimeSeries("Right Leg", FixedMillisecond.class);
	          
		for(AutomatedMeasurements automatedMeasurements : autoMeasurelist){
			 
			Date date = automatedMeasurements.getDate_TimeAsDate();
			//System.out.println("weight "+automatedMeasurements.getWeight()+" lbm "+automatedMeasurements.getLeanBodyMass());
			
			
			s4.add(new FixedMillisecond(date),automatedMeasurements.getLbmLeftLeg());
			s5.add(new FixedMillisecond(date),automatedMeasurements.getLbmRightLeg());
			
		}        

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        
        dataset.addSeries(s4);
        dataset.addSeries(s5);
        dataset.setDomainIsPointsInTime(true);

        return dataset;

    }




}
