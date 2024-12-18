package com.example.Preu_TopEducation_Ti.service;


import com.example.Preu_TopEducation_Ti.entities.EstudianteEntity;
import com.example.Preu_TopEducation_Ti.entities.ReporteEntity;
import com.example.Preu_TopEducation_Ti.repositories.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EstudianteService {

    @Autowired // Es una instancia de EstudianteRepository //
    EstudianteRepository estudianteRepository;
    @Autowired
    CuotaService cuotaService;
    @Autowired
    PruebaService pruebaService;

    public EstudianteEntity ingresarestudiante(EstudianteEntity estudiante){ // Guarda en la base de datos los datos del estudiante //
        return estudianteRepository.save(estudiante);
    }

    public void EliminarEstudiante(String rut){ // Elimina de la base de datos los datos del estudiante //
        EstudianteEntity estudiante = estudianteRepository.findByRut(rut);
        estudianteRepository.delete(estudiante);
    }

    // Crea un reporte para cada estudiante con los atributos solicitados y los almacena ReporteEntity //
    public ArrayList<ReporteEntity> crearReporte(){
        ArrayList<EstudianteEntity> estudiantes = estudianteRepository.findAll();
        ArrayList <ReporteEntity> reportes = new ArrayList<>();

        for (EstudianteEntity estudiante : estudiantes){
            int examenesRendidos = pruebaService.calcularCantidadprueba(estudiante.getRut());
            double promedioPuntaje = pruebaService.calcularpromediopuntaje(estudiante.getRut());
            double arancel = cuotaService.calculararancel(estudiante);
            String tipoDePago = estudiante.getTipoDepago();
            int cuotasPactadas = estudiante.getCantidad();

            int cuotasPagadas = cuotaService.registrarPagada(estudiante.getRut());
            double montoPagado = cuotaService.montoCuotasPagadas(estudiante.getRut());
            int cuotasAtrasadas = cuotaService.registrarAtrasadas(estudiante.getRut());
            LocalDate fechaUltimacuota = cuotaService.obtenerFechaultimaCuota(estudiante.getRut());
            double saldoaPagar =cuotaService.montoApagar(estudiante.getRut());


            ReporteEntity reporte = new ReporteEntity();
            reporte.setExamenesRendidos(examenesRendidos);
            reporte.setPromedioPuntaje(promedioPuntaje);
            reporte.setRut(estudiante.getRut());
            reporte.setNombre(estudiante.getNombres());
            reporte.setArancel(arancel);
            reporte.setTipoPago(tipoDePago);
            reporte.setCuotasPactadas(cuotasPactadas);


            reporte.setCuotasPagadas(cuotasPagadas);
            reporte.setCuotasAtrasadas(cuotasAtrasadas);
            reporte.setMontoPagado(montoPagado);
            reporte.setSaldoaPagar(saldoaPagar);
            reporte.setFechaUltimo(fechaUltimacuota);
            reportes.add(reporte);
        }
        return reportes;
    }
    public EstudianteEntity obtenerEstudiantePorRut(String rut) {
        return estudianteRepository.findByRut(rut);
    }

    public void guardar(EstudianteEntity reporte) {
        estudianteRepository.save(reporte);
    }
}
