package modelo;

import util.BDVeiculos;
import forms.GestaoVeiculos;

public class Teste {
    public static void main(String[] args) {
        BDVeiculos bd = new BDVeiculos();
        GestaoVeiculos menuPrincipal = new GestaoVeiculos(bd);
        menuPrincipal.setVisible(true);
    }
}