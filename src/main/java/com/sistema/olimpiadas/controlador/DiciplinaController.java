package com.sistema.olimpiadas.controlador;
import com.sistema.olimpiadas.servicio.DisciplinaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DiciplinaController {
    @Autowired
    private DisciplinaServicio disciplinaServicio;
}
