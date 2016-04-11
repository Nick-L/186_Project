package images;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import main.GamePanel;

public class Background {

	private BufferedImage originalImage;
	private BufferedImage image;

	private double x;
	private double y;

	public Background(String s) {
		try {
			originalImage = ImageIO.read(getClass().getResourceAsStream(s));
			
			//converts the image into a smaller format
			image = Thumbnails.of(originalImage).size(GamePanel.WIDTH, GamePanel.HEIGHT).asBufferedImage();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setPosition(double x, double y) {
		this.x = (x) % GamePanel.WIDTH;
		this.y = (y) % GamePanel.HEIGHT;
	}

	public void update() {

	}

	public void draw(Graphics2D g) {
		g.drawImage(image, (int) x, (int) y, null);
	}


}
