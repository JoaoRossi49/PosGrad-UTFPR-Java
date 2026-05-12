/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.ArrayList;
import java.util.List;
import modelo.Passeio;
import modelo.Carga;

/**
 *
 * @author joaor
 */
public class BDVeiculos {
    private List<Passeio> listaPasseio = new ArrayList<Passeio>();
    private List<Carga> listaCarga = new ArrayList<Carga>();

    public List<Passeio> getListaPasseio() { return listaPasseio; }
    public List<Carga> getListaCarga() { return listaCarga; }
}
