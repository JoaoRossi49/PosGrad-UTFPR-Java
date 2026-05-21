package util;

import java.util.ArrayList;
import java.util.List;
import modelo.Passeio;
import modelo.Carga;

public class BDVeiculos {
    private List<Passeio> listaPasseio = new ArrayList<>();
    private List<Carga> listaCarga = new ArrayList<>();

    public List<Passeio> getListaPasseio() { return listaPasseio; }
    public List<Carga> getListaCarga() { return listaCarga; }

    public void cadastrarPasseio(Passeio p) throws VeicExistException {
        if (existePlaca(p.getPlaca())) {
            throw new VeicExistException();
        }
        listaPasseio.add(p);
    }

    public void cadastrarCarga(Carga c) throws VeicExistException {
        if (existePlaca(c.getPlaca())) {
            throw new VeicExistException();
        }
        listaCarga.add(c);
    }

    public Passeio buscarPasseioPorPlaca(String placa) {
        for (Passeio p : listaPasseio) {
            if (p.getPlaca().equalsIgnoreCase(placa)) return p;
        }
        return null;
    }

    public Carga buscarCargaPorPlaca(String placa) {
        for (Carga c : listaCarga) {
            if (c.getPlaca().equalsIgnoreCase(placa)) return c;
        }
        return null;
    }

    public boolean excluirPasseioPorPlaca(String placa) {
        Passeio p = buscarPasseioPorPlaca(placa);
        if (p != null) {
            return listaPasseio.remove(p);
        }
        return false;
    }

    public boolean excluirCargaPorPlaca(String placa) {
        Carga c = buscarCargaPorPlaca(placa);
        if (c != null) {
            return listaCarga.remove(c);
        }
        return false;
    }

    public void excluirTodosPasseio() {
        listaPasseio.clear();
    }

    public void excluirTodosCarga() {
        listaCarga.clear();
    }

    private boolean existePlaca(String placa) {
        for (Passeio p : listaPasseio) {
            if (p.getPlaca().equalsIgnoreCase(placa)) return true;
        }
        for (Carga c : listaCarga) {
            if (c.getPlaca().equalsIgnoreCase(placa)) return true;
        }
        return false;
    }
}