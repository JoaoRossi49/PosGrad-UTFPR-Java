//João Vitor de Rossi Figueiredo
package avaliacao;

import modelo.PessoaJuridica;
import util.NumException;

public class TstConta {

    public static void main(String[] args) {
        PessoaJuridica pj = new PessoaJuridica();
        NumException exception = new NumException();

        try {
            pj.setNumeroConta(1002);
        } catch (NumException e) {
            e.impMsg();
        }

        pj.setCnpj(514885595);
        pj.getEnder().setRua("Avenida Central de cornélio java");
        pj.getResponsavel().setCpf(1555514811);
        pj.getResponsavel().setNome("Carlos Silva");

        System.out.println(pj.getNumeroConta());
        pj.validar();
        System.out.println(pj.getCnpj());
        System.out.println(pj.getEnder().getRua());
        System.out.println(pj.getResponsavel().getCpf());
        pj.getResponsavel().verifDoc();
        System.out.println(pj.getResponsavel().getNome());
        pj.verifDoc();
    }
}