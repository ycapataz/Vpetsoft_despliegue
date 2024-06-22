package com.Vpetsoft.VpetsoftApp.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.Vpetsoft.VpetsoftApp.dto.AppointmentDto;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfService {

    public ByteArrayInputStream generatePdf(List<AppointmentDto> appointments) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        for (AppointmentDto appointment : appointments) {
            document.add(new Paragraph("ID: " + appointment.getId()));
            document.add(new Paragraph("Fecha: " + appointment.getDate()));
            document.add(new Paragraph("Hora: " + appointment.getHour()));
            document.add(new Paragraph("Creado por: " + appointment.getIdempleado()));
            document.add(new Paragraph("Especialidad: " + appointment.getIdespecialidad()));
            document.add(new Paragraph("Mascota: " + appointment.getIdmascota()));
            document.add(new Paragraph(" "));
        }

        document.close();
        return new ByteArrayInputStream(out.toByteArray());
    }
}