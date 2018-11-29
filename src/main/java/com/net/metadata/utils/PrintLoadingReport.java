package com.net.metadata.utils;


import com.net.metadata.entity.Brs_luggage_view;

import java.awt.*;
import java.awt.print.*;
import java.util.ArrayList;
import java.util.List;

public class PrintLoadingReport implements Printable{
	
	private String fltno;
	private int total_luggage;
	private int total_car;
	private String trailer_time;
	private List<Brs_luggage_view> list;
	
	public PrintLoadingReport() {
	}
	
	public PrintLoadingReport(String fltno,int total_luggage,int total_car,
			String trailer_time,List<Brs_luggage_view> list){
		this.fltno=fltno;
		this.total_luggage=total_luggage;
		this.total_car=total_car;
		this.trailer_time=trailer_time;
		this.list=list;
	}
	 public int pageSize=1;//打印的总页数

	   private double paperW=0;//打印的纸张宽度

	   private double paperH=0;//打印的纸张高度

	   //实现java.awt.print.Printable接口的打印方法

	   //pageIndex:打印的当前页，此参数是系统自动维护的，不需要手动维护，系统会自动递增

	   public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)

	        throws PrinterException {

	      if (pageIndex >= pageSize)

	        //退出打印

	        return Printable.NO_SUCH_PAGE;

	      else {

	        Graphics2D g2 = (Graphics2D) graphics;

	        g2.setColor(Color.BLUE);

	        Paper p = new Paper();

	        //此处的paperW和paperH是从目标打印机的进纸规格中获取的，实际针式打印机的可打印区域是有限的，

	        //距纸张的上下左右1inch(英寸)的中间的距形框为实际可打印区域，超出范围的内容将不会打印出来(没有设置偏移的情况)

	        //如果设置偏移量，那么超出的范围也是可以打印的，这里的pageW和pageH我是直接获取打印机的进纸规格的宽和高

	        //也可以手动指定，从是如果手动指定的宽高和目标打印机的进纸规格相差较大，将会默认以A4纸为打印模版

	        p.setImageableArea(0, 0, paperW, paperH);// 设置可打印区域

	        p.setSize(paperW,paperH);// 设置纸张的大小

	        pageFormat.setPaper(p);

	        drawCurrentPageText(g2, pageFormat);//调用打印内容的方法

	        return PAGE_EXISTS;

	      }

	   }

	 

	   // 打印内容

	   private void drawCurrentPageText(Graphics2D g2, PageFormat pf) {

	      Font font = null;
	      Font font2 = null;
	      //设置打印的字体

	      font = new Font("新宋体", Font.BOLD, 16);
	      font2 = new Font("新宋体", Font.PLAIN, 11);

	      g2.setFont(font);// 设置字体
	      Stroke s= new BasicStroke(0.5f, BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND);
	      g2.setStroke(s);

	      //此处打印一句话，打印开始位置是(200,200),表示从pf.getPaper()中座标为(200,200)开始打印

	      //此处200的单位是1/72(inch)，inch:英寸，所以这里的长度，在测量后需要进行转换

	      g2.drawString("行李装车报表",250,60);
	      
	      g2.setFont(font2);
	      g2.drawString("航班号:"+fltno, 50, 80);
	      g2.drawLine(90,81,140,81);
	      g2.drawString("行李总数:"+total_luggage,160,80);
	      g2.drawLine(210,81,230,81);
	      g2.drawString("装车总数:"+total_car,250,80);
	      g2.drawLine(300,81,310,81);
	      g2.drawString("装车时间:"+trailer_time,330,80);
	      g2.drawLine(380,81,550,81);
	      
	      
	      int linesize=list.size()/4;//打印行李行数，一行4条数据
	      int linemod=list.size()%4;//整除后的余数
	      if(linesize>0){
	    	  g2.drawLine(50, 85, 565, 85);
	    	  for(int i=0;i<linesize;i++){
	    		  g2.drawString(list.get(i*4+0).getBgcode(),70,100+30*(i));
	    		  g2.drawString(list.get(i*4+1).getBgcode(),210,100+30*(i));
	    		  g2.drawString(list.get(i*4+2).getBgcode(),350,100+30*(i));
	    		  g2.drawString(list.get(i*4+3).getBgcode(),490,100+30*(i));
		    	  g2.drawLine(50, 80+30*(i+1), 565, 80+30*(i+1));
	    	  }
	      }
	      if(linemod==0){
	    	  g2.drawLine(50,85,50,80+30*linesize);
	    	  g2.drawLine(170,85,170,80+30*linesize);
	    	  g2.drawLine(310,85,310,80+30*linesize);
	    	  g2.drawLine(450,85,450,80+30*linesize);
	    	  g2.drawLine(565,85,565,80+30*linesize);
	      }else{
	    	  g2.drawLine(50, 80+30*(linesize+1), 565, 80+30*(linesize+1));
	    	  g2.drawLine(50,85,50,80+30*(linesize+1));
	    	  g2.drawLine(170,85,170,80+30*(linesize+1));
	    	  g2.drawLine(310,85,310,80+30*(linesize+1));
	    	  g2.drawLine(450,85,450,80+30*(linesize+1));
	    	  g2.drawLine(565,85,565,80+30*(linesize+1));
	    	  if(linemod==1){
		    	  g2.drawString(list.get(list.size()-1).getBgcode(),70,100+30*(linesize));    	  
		      }else if(linemod==2){
		    	  g2.drawString(list.get(list.size()-2).getBgcode(),70,100+30*(linesize));  
		    	  g2.drawString(list.get(list.size()-1).getBgcode(),210,100+30*(linesize));     	  
		      }else if(linemod==3){
		    	  g2.drawString(list.get(list.size()-3).getBgcode(),70,100+30*(linesize));  
		    	  g2.drawString(list.get(list.size()-2).getBgcode(),210,100+30*(linesize)); 
		    	  g2.drawString(list.get(list.size()-1).getBgcode(),350,100+30*(linesize));  
		      }
	      }
	      int j=0;
	      if(linemod>0){
	    	  j=linesize+2;
	      }else{
	    	  j=linesize+1;
	      }
	      g2.drawString("查询员:",60,100+30*j);
	      g2.drawLine(100, 100+30*j, 200, 100+30*j);
	      g2.drawString("分拣员:",220,100+30*j);
	      g2.drawLine(260, 100+30*j, 360, 100+30*j);
	      g2.drawString("班长:",380,100+30*j);
	      g2.drawLine(410, 100+30*j, 520, 100+30*j);

	   }

	   //连接打印机，弹出打印对话框

	   public void starPrint() {

	      try {

	        PrinterJob prnJob = PrinterJob.getPrinterJob();

	        PageFormat pageFormat = new PageFormat();

	        pageFormat.setOrientation(PageFormat.PORTRAIT);

	        prnJob.setPrintable(this);

	        //prnJob.setPageable(document);
	        //弹出打印对话框，也可以选择不弹出打印提示框，直接打印

	        if (!prnJob.printDialog())

	           return;

	        //获取所连接的目标打印机的进纸规格的宽度，单位：1/72(inch)

	        paperW=prnJob.getPageFormat(null).getPaper().getWidth();

	        //获取所连接的目标打印机的进纸规格的宽度，单位：1/72(inch)

	        paperH=prnJob.getPageFormat(null).getPaper().getHeight();

	        //System.out.println("paperW:"+paperW+";paperH:"+paperH);

	        prnJob.print();//启动打印工作

	      } catch (PrinterException ex) {

	        ex.printStackTrace();

	        System.err.println("打印错误：" + ex.toString());

	      }

	   }

	   //入口方法

	   public static void main(String[] args) {
		   List<Brs_luggage_view> list=new ArrayList<Brs_luggage_view>();
		   for(int i=1;i<=50;i++){
			   Brs_luggage_view b=new Brs_luggage_view();
			   b.setBgcode(String.valueOf(123456789+i*1000000000));
			   list.add(b);
		   }
		   PrintLoadingReport pm = new PrintLoadingReport(
				   "CA1234",100,2,"2016-09-17 17:00",list);// 实例化打印类

	      pm.pageSize = 1;//打印两页

	      pm.starPrint();

	   }

}
