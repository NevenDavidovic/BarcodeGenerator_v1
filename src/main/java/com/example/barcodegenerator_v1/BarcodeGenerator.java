package com.example.barcodegenerator_v1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.pdf417.PDF417Writer;

import javafx.scene.image.Image;



public class BarcodeGenerator {

    public static Image generateBarcode(String text, int width, int height) throws WriterException, IOException {
        PDF417Writer writer = new PDF417Writer();
        BitMatrix matrix = writer.encode(text, BarcodeFormat.PDF_417, width, height);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "png", outputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        return new Image(inputStream);
    }



}
