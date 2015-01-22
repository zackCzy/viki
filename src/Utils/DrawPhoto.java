package Utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

public class DrawPhoto {
	private BufferedImage img=new BufferedImage(280, 280, BufferedImage.TYPE_INT_RGB);
	public void  loadImg(InputStream  in) throws IOException{
		BufferedImage tempimg=ImageIO.read(in);
		Graphics2D g=(Graphics2D) img.getGraphics();
		g.drawImage(tempimg, 0, 0, 280, 280, 0, 0, tempimg.getWidth(), tempimg.getHeight(), null);
	}
	public InputStream  drawBigRect(int sx1,int sy1,int sx2,int sy2,int widh,int height) throws IOException{
		BufferedImage tempImg=new BufferedImage(widh,height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g=(Graphics2D) tempImg.getGraphics();
		g.drawImage(img, 0, 0, widh, height, sx1, sy1, sx2, sy2, null);
		ByteArrayOutputStream bs = new ByteArrayOutputStream();  
		ImageOutputStream imOut = ImageIO.createImageOutputStream(bs); 
		ImageIO.write(tempImg, "jpg",imOut); 
		return new ByteArrayInputStream(bs.toByteArray()); 
	}
}
