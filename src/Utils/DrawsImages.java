package Utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class DrawsImages {
	public int width, height;
	static final String text = "aQsd5fW2hEjRk1lTqYwUeIr8tOPyAuSi4oDpFzGx3cHvJbKn6m9LZXCV7BNM";
	public Graphics2D g = null;
	public BufferedImage bi = null;
	public String str="";
	public DrawsImages(int width, int height) {
		this.width = width;
		this.height = height;
		bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D)bi.getGraphics();
	}

	// 设置背景颜色
	public void setBackground(Color c) {
		g.setColor(c);
		g.fillRect(0, 0, width, height);
	}

	// 设置字体大小和字数
	public void setConten(int num, int size) {
		g.setFont(new Font("宋体", Font.CENTER_BASELINE|Font.BOLD, size));
		g.setColor(Color.BLACK);
		str="";
		int x = (width-size*num)/2;
		for (int i = 0; i < num; i++) {
			double degree	=new Random().nextInt()%30*Math.PI/180;
			int y=height-(height-size)/2;
			g.rotate(degree,x,y);		
			String temp = text.charAt(new Random().nextInt(text.length())) + "";
			str+=temp;
			g.drawString(temp, x, y);
			g.rotate(-degree,x,y);	
			x += size+3;
		}
	}
	//设置边框
	public void setBorder(int x, int y, int x1, int y1, Color c) {
		g.setColor(c);
		g.drawRect(x, y, x1, y1);
	}
	//设置干扰线
	public void setLine(int num,  Color c) {
		g.setColor(c);
		for (int i = 0; i < num; i++) {
			g.drawLine(new Random().nextInt(width),
					new Random().nextInt(height), new Random().nextInt(width),
					new Random().nextInt(height));
		}
	}
	public String getResult(){
		return str;
	}
	public void InputImage(OutputStream os) throws IOException {
		ImageIO.write(bi, "jpg", os);
	}
	public void InputImage(File os) throws IOException {
		ImageIO.write(bi, "jpg", os);
	}
}
