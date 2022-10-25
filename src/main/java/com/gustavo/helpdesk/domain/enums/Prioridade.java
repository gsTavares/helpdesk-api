package com.gustavo.helpdesk.domain.enums;

public enum Prioridade {
    BAIXA(0, "BAIXA"), MEDIA(1, "MEDIA"), ALTA(2, "ALTA");

    private Integer codigo;
    private String descricao;

    private Prioridade(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return this.codigo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public static Prioridade toPrioridade(Integer codigo) {
        if(codigo == null) {
            return null;
        }

        for(Prioridade prioridade : Prioridade.values()) {
            if(prioridade.getCodigo() == codigo) {
                return prioridade;
            }
        }

        throw new IllegalArgumentException("Prioridade inválida");
    }
}
