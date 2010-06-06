/*
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * ---------------------------
 * WebHitChart.java
 * ---------------------------
 * (C) Copyright 2002-2004, by Richard Atkinson.
 *
 * Original Author:  Richard Atkinson;
 */
package com.designer.common.chart;

import java.awt.Color;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.designer.controller.heartRateMonitor.HeartRateMonitorForm;

public class HRLineChart {

	public static String generateLineChart(HttpServletRequest request, HttpServletResponse response, List<HeartRateMonitorForm> heartRateMonitorFormList) {
		String filename = null;
		PrintWriter pw = null;
	   	try {
	   		pw = response.getWriter();	   		
	   		ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
	       
	   	
	   		JFreeChart lineChart= createChart(createDataset(heartRateMonitorFormList));;
	         filename = ServletUtilities.saveChartAsPNG(lineChart, 500, 300, info, request.getSession());	        
	        //  Write the image map to the PrintWriter
	        ChartUtilities.writeImageMap(pw, filename, info,true);
	        System.out.println("CHart file Name :: "+filename);
	        
	        pw.flush(); 	
	   	}catch(Exception e) { 
	        System.out.println("Exception - " + e.toString());
	        e.printStackTrace(System.out);
	        filename = "public_error_500x300.png";
	    }   
		
		
		
		return filename;
		
	}
	
	
/*public static String generateLineChart(HttpSession session, PrintWriter pw) {
    String filename = null;
   	try {
   		JFreeChart chart= createChart(createDataset() );
        ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
        filename = ServletUtilities.saveChartAsPNG(chart, 500, 300, info, session);

        //  Write the image map to the PrintWriter
        ChartUtilities.writeImageMap(pw, filename, info,true);
        pw.flush();
   	}catch (Exception e) { 
        System.out.println("Exception - " + e.toString());
        e.printStackTrace(System.out);
        filename = "public_error_500x300.png";
    }
    return filename;
}*/


/**
 * Creates a chart.
 * 
 * @param dataset  the data for the chart.
 * 
 * @return a chart.
 */
private static  JFreeChart createChart(final XYDataset dataset) {
    
    // create the chart...
    final JFreeChart chart = ChartFactory.createXYLineChart(
        "Heart Rate Monitor",      // chart title
        "Minutes",				// x axis label
        "Heart Rates",			// y axis label
        dataset,                  // data
        PlotOrientation.VERTICAL,
        true,                     // include legend
        false,                     // tooltips
        false                     // urls
    );

    // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
    chart.setBackgroundPaint(new Color(16774132));
    
//	        final StandardLegend legend = (StandardLegend) chart.getLegend();
  //      legend.setDisplaySeriesShapes(true);
    
    // get a reference to the plot for further customisation...
    final XYPlot plot = chart.getXYPlot();
    
    
    //ValueAxis axis = plot.getDomainAxis();
    NumberAxis axis = (NumberAxis)plot.getDomainAxis();
    axis.setAutoRange(true); 
     NumberTickUnit unit=new NumberTickUnit(2);
    //axis.setFixedDimension(2);
    axis.setTickUnit(unit);
    
    plot.setBackgroundPaint(Color.lightGray);
//    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
    plot.setDomainGridlinePaint(Color.white);
    plot.setRangeGridlinePaint(Color.white);
    
    final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
    //renderer.setSeriesLinesVisible(0, false);
 //   renderer.setSeriesShapesVisible(1, false);
   
    // Changes For Tooltip Start
    renderer.setToolTipGenerator(
            new StandardXYToolTipGenerator(StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
                    new DecimalFormat("0.00"), new DecimalFormat("0")
                )
            );    
    //  Changes For Tooltip End
    
    plot.setRenderer(renderer);

    // change the auto tick unit selection to integer units only...
    final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
    rangeAxis.setRange(60, 200);
    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    // OPTIONAL CUSTOMISATION COMPLETED.
            
    return chart;
    
}



	/**
	 * Creates a sample dataset.
	 * 
	 * @return a sample dataset.
	 */
	private static XYDataset createDataset(
			List<HeartRateMonitorForm> heartRateMonitorFormList) {

		final XYSeriesCollection dataset = new XYSeriesCollection();

		for (HeartRateMonitorForm heartRateMonitorForm : heartRateMonitorFormList) {
			final XYSeries series = new XYSeries(heartRateMonitorForm
					.getDate());
			int i = 0;
			for (Integer reading : heartRateMonitorForm.getReadings()) {
				series.add((++i)*heartRateMonitorForm.getInterval(),reading);
			}

			dataset.addSeries(series);
		}

		return dataset;
	}
   

}