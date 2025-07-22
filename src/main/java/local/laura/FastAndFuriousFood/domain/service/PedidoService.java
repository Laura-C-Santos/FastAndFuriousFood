/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.laura.FastAndFuriousFood.domain.service;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import local.laura.FastAndFuriousFood.domain.exception.DomainException;
import local.laura.FastAndFuriousFood.domain.model.ItemPedido;
import local.laura.FastAndFuriousFood.domain.model.Pedido;
import local.laura.FastAndFuriousFood.domain.model.Produto;
import local.laura.FastAndFuriousFood.domain.model.StatusPedido;
import local.laura.FastAndFuriousFood.domain.repository.PedidoRepository;
import local.laura.FastAndFuriousFood.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ppjatb
 */
@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Pedido salvar(Pedido pedido) {
        // ⚠ Garante que cada ItemPedido tenha o Pedido associado
        for (ItemPedido item : pedido.getItens()) {
            item.setPedido(pedido);

            // ⚠ Verifica se o produto existe e carrega da base
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                .orElseThrow(() -> new DomainException("Produto com ID " + item.getProduto().getId() + " não encontrado."));
            item.setProduto(produto);
        }

        // ⚡ Define status inicial e data se necessário
        if (pedido.getStatus() == null) {
            pedido.setStatus(StatusPedido.ABERTO);
        }
        if (pedido.getDataHoraCriacao() == null) {
            pedido.setDataHoraCriacao(LocalDateTime.now());
        }

        return pedidoRepository.save(pedido);
    }

    public void cancelar(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
            .orElseThrow(() -> new DomainException("Pedido não encontrado."));
        pedido.setStatus(StatusPedido.CANCELADO);
        pedidoRepository.save(pedido);
    }

    public void alterarStatus(Long pedidoId, StatusPedido status) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
            .orElseThrow(() -> new DomainException("Pedido não encontrado."));
        pedido.setStatus(status);
        pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPorStatus(StatusPedido status) {
        return pedidoRepository.findByStatus(status);
    }
}
