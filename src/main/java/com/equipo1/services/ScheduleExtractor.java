/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.equipo1.services;

import java.io.IOException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import java.util.*;

/**
 *
 * @author XPxTBxLLX
 */
public class ScheduleExtractor extends PDFTextStripper {

    // Cada token: [x, y, texto]
    public List<Object[]> tokens = new ArrayList<>();

    public ScheduleExtractor() throws IOException {
        setSortByPosition(true);
    }

    @Override
    protected void writeString(String text, List<TextPosition> textPositions) throws IOException {
        if (!textPositions.isEmpty()) {
            float x = textPositions.get(0).getXDirAdj();
            float y = textPositions.get(0).getYDirAdj();
            tokens.add(new Object[]{x, y, text});
        }
        super.writeString(text, textPositions);
    }
}
