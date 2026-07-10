package br.edu.utfpr.anotacoes;

// TODO requisito 7 "faça com que elas funcionem para validar os atributos dos records que representam as contas"
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.RECORD_COMPONENT})
public @interface NaoNulo {
    String mensagem() default "nao pode ser nulo";
}
