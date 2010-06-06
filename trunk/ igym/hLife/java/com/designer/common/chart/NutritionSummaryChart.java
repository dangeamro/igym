package com.designer.common.chart;

import java.awt.Color;
import java.io.PrintWriter;
import java.text.AttributedString;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import com.designer.model.foodJournal.MealNutrientSummaryModel;


public class NutritionSummaryChart {
	
	public static String generatePieChart(HttpServletRequest request, HttpServletResponse response, MealNutrientSummaryModel nutrientSummary) {

		String filename = null;
		PrintWriter pw = null;


		try 
		{
	   		pw = response.getWriter();	   		
	   		ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
	       
	   		JFreeChart lineChart= createChart(createDataset(nutrientSummary));
	         
	   		filename = ServletUtilities.saveChartAsPNG(lineChart, 250, 100, info, request.getSession());

	        //  Write the image map to the PrintWriter
	        ChartUtilities.writeImageMap(pw, filename, info,true);
	        System.out.println("Pie Chart file Name :: " + filename);
	        
	        pw.flush(); 	
		}
		catch(Exception e) 
		{ 
	        System.out.println("Exception - " + e.toString());
	        e.printStackTrace(System.out);
	        filename = "public_error_500x300.png";
		}
        
		return filename;
	}

	private static JFreeChart createChart(final PieDataset dataset) {
        
        final JFreeChart chart = ChartFactory.createPieChart(
            "",  // chart title
            dataset,             // data
            false,               // include legend
            true,
            false
        );

       
        chart.setBackgroundPaint(new Color(16774132));
        
        final PiePlot plot = (PiePlot) chart.getPlot();
        plot.setInteriorGap(0.0);
        plot.setBackgroundAlpha(0);
        plot.setDirection(Rotation.CLOCKWISE);
        //plot.setBaseSectionOutlinePaint(new Color(16774132));
        //plot.setBackgroundPaint(new Color(16774132));
        //plot.setLabelLinkPaint(Color.BLACK);                
       
        
        return chart;
        
    }

	
    private static PieDataset createDataset(MealNutrientSummaryModel nutrientSummary) {
		final DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Protien", nutrientSummary.getTotalProtienPcnt());
        dataset.setValue("Fat", nutrientSummary.getTotalFatsPcnt());
        dataset.setValue("Carbohydrates", nutrientSummary.getTotalCarbohydratesPcnt());
        dataset.setValue("Others", nutrientSummary.getOthersPcnt());
        return dataset;        
    }

//    static class CustomLabelGenerator implements PieSectionLabelGenerator {
        
        /**
         * Generates a label for a pie section.
         * 
         * @param dataset  the dataset (<code>null</code> not permitted).
         * @param key  the section key (<code>null</code> not permitted).
         * 
         * @return the label (possibly <code>null</code>).
         */
 /*       public String generateSectionLabel(final PieDataset dataset, final Comparable key) {
            String result = null;    
            if (dataset != null) {
                if (!key.equals("Two")) {
                    result = key.toString()+"%";   
                }
            }
            return result;
        }

		public AttributedString generateAttributedSectionLabel(PieDataset arg0, Comparable arg1) {
			// TODO Auto-generated method stub
			return null;
		}

    }*/

}
