package com.avila.pgto.pgtoNA.domain;

import com.avila.pgto.pgtoNA.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {

    // classe abstrata por que nao queo instaciancar o pagamento somente as subclasse
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    private Integer estado;


    @JsonIgnore
    @JoinColumn(name="pedido_id")
    @OneToOne
    @MapsId
    private Pedido pedido;

    protected Pagamento() {
    }

    public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
        this.id = id;
        this.estado = (estado == null) ? null : estado.getCod();
        this.pedido = pedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EstadoPagamento getEstado() {

        return EstadoPagamento.toEnum(estado);
    }

    public void setEstado(EstadoPagamento estado) {

        this.estado = estado.getCod();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pagamento pagamento)) return false;
        return Objects.equals(getId(), pagamento.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
