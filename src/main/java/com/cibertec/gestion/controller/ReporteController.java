package com.cibertec.gestion.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cibertec.gestion.dto.AbastecimientoReporte;
import com.cibertec.gestion.entity.Producto;
import com.cibertec.gestion.service.AbastecimientoService;
import com.cibertec.gestion.service.ProductoService;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class ReporteController {

    @Autowired
    private AbastecimientoService abastecimientoService;
    @Autowired
    private ProductoService productoService;

    @GetMapping("/reporte/productos")
    public void generarReporteProductos(HttpServletResponse response) {
        List<Producto> listaProductos = productoService.obtenerTodosProductos();
        String nombreJrxml = "Reporte_Producto.jrxml";

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaProductos);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ds", dataSource);

        generarReporte(response, nombreJrxml, parameters, new JREmptyDataSource());
    }

    @GetMapping("/reporte/abastecimiento")
    public void generarReporteAbastecimiento(HttpServletResponse response) {
        List<AbastecimientoReporte> listaAbastecimientos = abastecimientoService.obtenerTodo();
        String nombreJrxml = "Reporte_Abastecimiento.jrxml";

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaAbastecimientos);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ds", dataSource);

        generarReporte(response, nombreJrxml, parameters, new JREmptyDataSource());
    }

    private void generarReporte(HttpServletResponse response, String nombreJrxml, Map<String, Object> parameters, JRDataSource dataSource) {
        try {
            InputStream reportStream = getClass().getResourceAsStream("/reports/" + nombreJrxml);
            if (reportStream == null) {
                throw new RuntimeException("No se encuentra el recurso del reporte: /reports/" + nombreJrxml);
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            response.setContentType("application/pdf");
            String nombreArchivoPdf = nombreJrxml.replace(".jrxml", ".pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivoPdf + "\"");
            
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
