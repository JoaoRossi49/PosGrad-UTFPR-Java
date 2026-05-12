/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author joaor
 */
public class VeicExistException extends Exception {
    public VeicExistException() {
        System.out.println("Já existe um veículo com esta placa");
    }
}
