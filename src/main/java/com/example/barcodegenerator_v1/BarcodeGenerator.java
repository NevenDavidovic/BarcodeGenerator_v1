package com.example.barcodegenerator_v1;

import java.io.IOException;
import java.nio.file.Paths;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.pdf417.PDF417Writer;
import javafx.fxml.FXML;

import static com.example.barcodegenerator_v1.SceneSwitcher.switchScene;

public class BarcodeGenerator {

    public static void generateBarcode(String text, String filePath) throws IOException, WriterException {
        PDF417Writer writer = new PDF417Writer();
        BitMatrix matrix = writer.encode(text, BarcodeFormat.PDF_417, 800, 600);
        MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(filePath));
    }



}
