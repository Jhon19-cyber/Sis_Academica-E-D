package com.cibertec.controller;

import com.cibertec.model.Docente;
import com.cibertec.model.Estudiante;
import com.cibertec.service.DocenteService;
import com.cibertec.service.EstudianteService;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.element.Cell;


import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;



@Controller
public class ReporteController {

    @Autowired
    private DocenteService docenteService;

    @Autowired
    private EstudianteService estudianteService;

    // metodo descargar
    @GetMapping("/reporte/docentes/download")
    public void downloadDocentes(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=reporte_docentes.pdf");

        PdfWriter writer = new PdfWriter(response.getOutputStream());
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // lista
        List<Docente> docentes = docenteService.findAll();

        // titulo
        Paragraph titulo = new Paragraph("Reporte de Docentes")
                .setFontSize(18)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(20);
        document.add(titulo);

        //tabla
        float[] columnWidths = {3, 3, 4, 2, 3}; 
        Table table = new Table(columnWidths);
        table.setWidth(UnitValue.createPercentValue(100)); 

        // encabezados 
        table.addHeaderCell(new Cell().add(new Paragraph("Nombres").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table.addHeaderCell(new Cell().add(new Paragraph("Apellidos").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table.addHeaderCell(new Cell().add(new Paragraph("Email").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table.addHeaderCell(new Cell().add(new Paragraph("Celular").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table.addHeaderCell(new Cell().add(new Paragraph("Profesión").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY));

        // datos docentes
        for (Docente docente : docentes) {
            table.addCell(new Cell().add(new Paragraph(docente.getNombres())));
            table.addCell(new Cell().add(new Paragraph(docente.getApellidos())));
            table.addCell(new Cell().add(new Paragraph(docente.getEmail())));
            table.addCell(new Cell().add(new Paragraph(docente.getCelular() != null ? String.valueOf(docente.getCelular()) : "Sin celular")));
            table.addCell(new Cell().add(new Paragraph(docente.getProfesion() != null ? docente.getProfesion().getDes_profesion() : "Sin profesión")));
        }

        // añadir al documento
        document.add(table);

        document.close();
    }
   
    
    @GetMapping("/reporte/estudiantes/download")
    public void downloadEstudiantes(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=reporte_estudiantes.pdf");

        PdfWriter writer = new PdfWriter(response.getOutputStream());
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // lista
        List<Estudiante> estudiantes = estudianteService.findAll();

        // titulo 
        Paragraph titulo = new Paragraph("Reporte de Estudiantes")
                .setFontSize(18)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(20);
        document.add(titulo);

        //tabla
        float[] columnWidths = {3, 3, 4, 2, 3}; 
        Table table = new Table(columnWidths);
        table.setWidth(UnitValue.createPercentValue(100)); 

        // encabezados 
        table.addHeaderCell(new Cell().add(new Paragraph("Nombres").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table.addHeaderCell(new Cell().add(new Paragraph("Apellidos").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table.addHeaderCell(new Cell().add(new Paragraph("Correo").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table.addHeaderCell(new Cell().add(new Paragraph("Teléfono").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table.addHeaderCell(new Cell().add(new Paragraph("Curso").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY));

        // datos estudiantes
        for (Estudiante estudiante : estudiantes) {
            table.addCell(new Cell().add(new Paragraph(estudiante.getNombres())));
            table.addCell(new Cell().add(new Paragraph(estudiante.getApellidos())));
            table.addCell(new Cell().add(new Paragraph(estudiante.getCorreo())));
            table.addCell(new Cell().add(new Paragraph(estudiante.getTelefono() != null ? String.valueOf(estudiante.getTelefono()) : "Sin teléfono")));
            table.addCell(new Cell().add(new Paragraph(estudiante.getCurso() != null ? estudiante.getCurso().getDes_curso() : "Sin curso")));
        }

        // añadir al documento
        document.add(table);
        
        document.close();
    }

    
    
}

