package org.ivan200sr.appmockito.ejemplos.services;

import org.ivan200sr.appmockito.ejemplos.models.Examen;

public interface ExamenService {
    Examen findExamenPorNombre(String nombre);
}
