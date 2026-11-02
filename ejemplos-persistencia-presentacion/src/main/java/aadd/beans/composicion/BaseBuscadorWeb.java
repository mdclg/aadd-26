package aadd.beans.composicion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseBuscadorWeb< T extends Buscable> implements Serializable {

    private String keyword; // Palabra clave para filtrar
    private List<T> resultados; // Listado completo de objetos
    private List<T> resultadosFiltrados; // Listado filtrado según la palabra clave

    public BaseBuscadorWeb() {
        resultados = new ArrayList<>();
        resultadosFiltrados = new ArrayList<>();
    }

    public void filtrar() {
        if (keyword == null || keyword.isEmpty()) {
            resultadosFiltrados = new ArrayList<>(resultados);
        } else {
            resultadosFiltrados = resultados.stream()
                .filter(item -> item.getNombre().toLowerCase().contains(keyword.toLowerCase()) ||
                                item.getDescripcion().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
        }
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<T> getResultados() {
        return resultados;
    }

    public void setResultados(List<T> resultados) {
        this.resultados = resultados;
    }

    public List<T> getResultadosFiltrados() {
        return resultadosFiltrados;
    }
}
