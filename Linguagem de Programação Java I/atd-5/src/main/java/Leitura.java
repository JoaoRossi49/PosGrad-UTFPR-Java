/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author joaor
 */
class Leitura {

    private java.util.Scanner scanner = new java.util.Scanner(System.in);

    public String entDados(String rotulo) {
        System.out.print(rotulo);
        return scanner.nextLine();
    }
}
