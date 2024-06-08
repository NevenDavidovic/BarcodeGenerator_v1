package com.example.barcodegenerator_v1;

import java.io.IOException;
import java.nio.file.Paths;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

public class BarcodeGenerator {

    public static void generateBarcode(String text, String filePath) throws IOException {
        Code128Writer writer = new Code128Writer();
        BitMatrix matrix = writer.encode(text, BarcodeFormat.CODE_128, 1100, 700);
        MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(filePath));
    }
}