package org.ivan200sr.appmockito.ejemplos.repositories;

import org.ivan200sr.appmockito.ejemplos.models.Examen;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExamenRepositoryImpl implements ExamenRepository {
    @Override
    public List<Examen> findAll() {
        return Collections.emptyList();//Arrays.asList(new Examen(5L,"Matematicas"),new Examen(6L,"Lengua"),new Examen(7L,"Historia"));
    }

    @Override
    public Examen guardar(Examen examen) {
        return null;
    }
}
