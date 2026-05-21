package util;

public class VelocException extends Exception {
    public VelocException() {
        super("A velocidade máxima está fora dos limites brasileiros.");
    }
}