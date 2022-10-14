package org.ivan200sr.appmockito.ejemplos.repositories;

import org.ivan200sr.appmockito.ejemplos.models.Examen;

import java.util.List;

public interface ExamenRepository {
    List<Examen> findAll();
    Examen guardar (Examen examen);
}
