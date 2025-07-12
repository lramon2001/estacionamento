package com.lucasramon.estacionamento.infra.pdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import com.lucasramon.estacionamento.dominio.casos_de_uso.faturamento.GerarPdfFaturamentoCasoDeUso;

@Service
public class GerarPdfFaturamentoImpl implements GerarPdfFaturamentoCasoDeUso{

    @Override
    public byte[] gerarPdf(LocalDate inicio, LocalDate fim) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.newLineAtOffset(100, 750);
            contentStream.showText("Faturamento entre: " + inicio + " e " + fim);
            contentStream.endText();

            contentStream.close();

            document.save(outputStream);

        } catch (IOException e) {
            e.printStackTrace(); 
        }

        return outputStream.toByteArray();
    }
    
}
