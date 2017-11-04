package com.rover.controller;

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ImageReader {

	public String getImgText(String imageLocation) {
		ITesseract instance = new Tesseract();
		instance.setDatapath("C:\\");
		instance.setLanguage("eng");
		try  {
			String imgText = instance.doOCR(new File(imageLocation));
			return imgText;
		} catch (TesseractException e) {
			e.getMessage();
			return "Error while reading image";
		}
	}

	public static void main(String[] args) {
		ImageReader app = new ImageReader();
		for(int i = 1 ; i < 5 ; i++) {
	 		System.out.println("For "+i+" -  "+app.getImgText("C:\\test\\"+i+".jpg"));
		}
	}

}