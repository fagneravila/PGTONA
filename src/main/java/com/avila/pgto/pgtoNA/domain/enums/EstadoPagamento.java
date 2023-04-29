package com.avila.pgto.pgtoNA.domain.enums;

public enum EstadoPagamento {

    PENDENTE(1,"Pendente"),
    QUITADO(2,"Quitado"),
    CANCELADO(3,"Canceçado");

    private Integer cod;
    private String Descricao;

    EstadoPagamento(Integer cod, String descricao) {
        this.cod = cod;
        Descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return Descricao;
    }

    public static EstadoPagamento toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        
        for (EstadoPagamento x : EstadoPagamento.values()){
            if(cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id Invalido: "+ cod);
    }
}
