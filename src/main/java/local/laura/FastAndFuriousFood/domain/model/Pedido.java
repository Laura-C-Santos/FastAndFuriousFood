/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.laura.FastAndFuriousFood.domain.model;

import ch.qos.logback.core.status.Status;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author ppjatb
 */

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHoraCriacao = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.ABERTO;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens;

    public Pedido() {
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    
}
