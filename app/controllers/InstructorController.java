package controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import play.db.jpa.Blob;
import play.libs.Files;
import play.libs.IO;
import play.mvc.Controller;

public class InstructorController extends Controller{

	public static void renderImage(String imageName) throws IOException{
		System.out.println("image name " + imageName);
		File f = new File("public/images/" + imageName);
		System.out.println("File exists " + f.exists());
//		renderBinary(file)
		renderBinary(f, null);
	}

}
