package com.qzgf.test;

import java.awt.Font;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class JfreeChartTest {
	public static JFreeChart createChart() throws IOException {   
		// 数据集   
        DefaultPieDataset dpd = new DefaultPieDataset();   
        dpd.setValue("冠发", 40);   
        dpd.setValue("冠通", 0);   
        dpd.setValue("冠兴", 20);   
        dpd.setValue("泉邮", 40);   
        // 创建PieChart对象   
        JFreeChart chart = ChartFactory.createPieChart("泉州邮政分公司人员分布图", dpd,   
                true, true, false);   
        utils.setFont(chart);   
        return chart;  
    }    
}

class utils {   
    public static void setFont(JFreeChart chart) {   
        Font font = new Font("宋体", Font.ITALIC, 12);   
        PiePlot plot = (PiePlot) chart.getPlot();   
        chart.getTitle().setFont(font);   
        plot.setLabelFont(font);   
        chart.getLegend().setItemFont(font);   
    }   
}  

