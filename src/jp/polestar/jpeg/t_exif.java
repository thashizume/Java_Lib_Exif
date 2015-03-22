package jp.polestar.jpeg;

import java.io.IOException;

import com.drew.imaging.ImageProcessingException;

public class t_exif {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		try {
			jp.polestar.jpeg.Exif e = new jp.polestar.jpeg.Exif(args[0]);
			// e.dump();
			System.out.println(e.getLatitude());
			System.out.println(e.getLongitude());
			System.out.println(e.getHeight());
			System.out.println(e.getWidth());
			System.out.println(e.getModel());
			System.out.println(e.getMake());
			System.out.println(e.getLensModel());
			System.out.println(e.getLensMake());
			System.out.println("---");
			e.dump();
			
		} catch (ImageProcessingException | IOException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
			
			System.out.println(e1.getMessage());
		}
		

	}

}
