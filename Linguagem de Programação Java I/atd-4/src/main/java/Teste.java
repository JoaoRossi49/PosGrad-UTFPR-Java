public class Teste {
    public static void main(String[] args) {

        //#region Veiculos de passio
        Passeio p1 = new Passeio();
        p1.setPlaca("ACS4431");
        p1.setVelocMax(100.0f);
        p1.setQtdePassageiros(5);
        p1.getMotor().setQtdPist(4);
        p1.getMotor().setPotencia(100);

        Passeio p2 = new Passeio();
        p2.setPlaca("CAG3414");
        p2.setVelocMax(110.0f);
        p2.setQtdePassageiros(5);
        p2.getMotor().setQtdPist(4);
        p2.getMotor().setPotencia(110);

        Passeio p3 = new Passeio();
        p3.setPlaca("GGH3344");
        p3.setVelocMax(120.0f);
        p3.setQtdePassageiros(2);
        p3.getMotor().setQtdPist(6);
        p3.getMotor().setPotencia(200);

        Passeio p4 = new Passeio();
        p4.setPlaca("HTE445");
        p4.setVelocMax(130.0f);
        p4.setQtdePassageiros(5);
        p4.getMotor().setQtdPist(4);
        p4.getMotor().setPotencia(120);

        Passeio p5 = new Passeio();
        p5.setPlaca("OKI8893");
        p5.setVelocMax(140.0f);
        p5.setQtdePassageiros(7);
        p5.getMotor().setQtdPist(6);
        p5.getMotor().setPotencia(250);

        //#endregion

        //#region Veículos de carga
        Carga c1 = new Carga();
        c1.setPlaca("PLQ1012");
        c1.setVelocMax(80.0f);
        c1.setCargaMax(5000);
        c1.setTara(2000);
        c1.getMotor().setQtdPist(6);
        c1.getMotor().setPotencia(280);

        Carga c2 = new Carga();
        c2.setPlaca("XYZ2002");
        c2.setVelocMax(85.0f);
        c2.setCargaMax(8000);
        c2.setTara(3000);
        c2.getMotor().setQtdPist(8);
        c2.getMotor().setPotencia(350);

        Carga c3 = new Carga();
        c3.setPlaca("PPS4492");
        c3.setVelocMax(75.0f);
        c3.setCargaMax(10000);
        c3.setTara(4000);
        c3.getMotor().setQtdPist(10);
        c3.getMotor().setPotencia(450);

        Carga c4 = new Carga();
        c4.setPlaca("TTU9988");
        c4.setVelocMax(90.0f);
        c4.setCargaMax(4000);
        c4.setTara(1500);
        c4.getMotor().setQtdPist(6);
        c4.getMotor().setPotencia(220);

        Carga c5 = new Carga();
        c5.setPlaca("ZXB4483");
        c5.setVelocMax(82.0f);
        c5.setCargaMax(1548);
        c5.setTara(5000);
        c5.getMotor().setQtdPist(120);
        c5.getMotor().setPotencia(540);
        //#endregion
    
        System.out.println("======= PASSEIO =======");
        imprimirPasseio(p1);
        imprimirPasseio(p2);
        imprimirPasseio(p3);
        imprimirPasseio(p4);
        imprimirPasseio(p5);

        System.out.println("======= CARGA =======");
        imprimirCarga(c1);
        imprimirCarga(c2);
        imprimirCarga(c3);
        imprimirCarga(c4);
        imprimirCarga(c5);
    }

    public static void imprimirPasseio(Passeio p) {
        System.out.println("Placa: " + p.getPlaca() + " | Velocidade: " + p.calcVel(p.getVelocMax()) + " m/h");
    }

    public static void imprimirCarga(Carga c) {
        System.out.println("Placa: " + c.getPlaca() + " | Velocidade: " + c.calcVel(c.getVelocMax()) + " cm/h");
    }
}