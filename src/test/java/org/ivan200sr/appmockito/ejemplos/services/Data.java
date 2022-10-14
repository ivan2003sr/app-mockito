package org.ivan200sr.appmockito.ejemplos.services;

import org.ivan200sr.appmockito.ejemplos.models.Examen;

import java.util.Arrays;
import java.util.List;

public class Data {

    public final static List<Examen> EXAMENES = Arrays.asList(new Examen(5L,"Matematicas"),
            new Examen(6L,"Lengua"),new Examen(7L,"Historia"));
    public final static List<String> PREGUNTAS = Arrays.asList("Aritmética","Integrales", "Derivadas",
            "Trigonometría","Geometría");
   // public final static Examen EXAMEN = new Examen(8L, "Física");
    public final static Examen EXAMEN = new Examen(null,"Física");

}
