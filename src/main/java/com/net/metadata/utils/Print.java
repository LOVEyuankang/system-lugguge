package com.net.metadata.utils;

import java.awt.*;
import java.awt.print.*;

public class Print implements Printable {

	private int pageSize;// 打印的总页数

	private double paperW = 0;// 打印的纸张宽度

	private double paperH = 0;// 打印的纸张高度

	// 实现java.awt.print.Printable接口的打印方法

	// pageIndex:打印的当前页，此参数是系统自动维护的，不需要手动维护，系统会自动递增

	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)

	throws PrinterException {

		if (pageIndex >= pageSize)

			// 退出打印

			return Printable.NO_SUCH_PAGE;

		else {

			Graphics2D g2 = (Graphics2D) graphics;

			g2.setColor(Color.BLUE);

			Paper p = new Paper();

			// 此处的paperW和paperH是从目标打印机的进纸规格中获取的，实际针式打印机的可打印区域是有限的，

			// 距纸张的上下左右1inch(英寸)的中间的距形框为实际可打印区域，超出范围的内容将不会打印出来(没有设置偏移的情况)

			// 如果设置偏移量，那么超出的范围也是可以打印的，这里的pageW和pageH我是直接获取打印机的进纸规格的宽和高

			// 也可以手动指定，从是如果手动指定的宽高和目标打印机的进纸规格相差较大，将会默认以A4纸为打印模版

			p.setImageableArea(0, 0, paperW, paperH);// 设置可打印区域

			p.setSize(paperW, paperH);// 设置纸张的大小

			pageFormat.setPaper(p);

			drawCurrentPageText(g2, pageFormat);// 调用打印内容的方法

			return PAGE_EXISTS;

		}

	}

	// 打印内容

	private void drawCurrentPageText(Graphics2D g2, PageFormat pf) {

		Font font = null;

		// 设置打印的字体

		font = new Font("新宋体", Font.BOLD, 11);

		g2.setFont(font);// 设置字体

		// 此处打印一句话，打印开始位置是(200,200),表示从pf.getPaper()中座标为(200,200)开始打印

		// 此处200的单位是1/72(inch)，inch:英寸，所以这里的长度，在测量后需要进行转换

		g2.drawString("打印测试打印测试打印测试打印测试打印测试打印测试打印测试打印测试打印测试打印测试打印测试", 200, 200);

	}

	// 连接打印机，弹出打印对话框

	public void starPrint() {

		try {

			PrinterJob prnJob = PrinterJob.getPrinterJob();

			PageFormat pageFormat = new PageFormat();

			pageFormat.setOrientation(PageFormat.PORTRAIT);

			prnJob.setPrintable(this);

			// prnJob.setPageable(document);
			// 弹出打印对话框，也可以选择不弹出打印提示框，直接打印

			if (!prnJob.printDialog())

				return;

			// 获取所连接的目标打印机的进纸规格的宽度，单位：1/72(inch)

			paperW = prnJob.getPageFormat(null).getPaper().getWidth();

			// 获取所连接的目标打印机的进纸规格的宽度，单位：1/72(inch)

			paperH = prnJob.getPageFormat(null).getPaper().getHeight();

			// System.out.println("paperW:"+paperW+";paperH:"+paperH);

			prnJob.print();// 启动打印工作

		} catch (PrinterException ex) {

			ex.printStackTrace();

			System.err.println("打印错误：" + ex.toString());

		}

	}

	// 入口方法

	public static void main(String[] args) {

		Print pm = new Print();// 实例化打印类

		pm.pageSize = 2;// 打印两页

		pm.starPrint();

	}

}
