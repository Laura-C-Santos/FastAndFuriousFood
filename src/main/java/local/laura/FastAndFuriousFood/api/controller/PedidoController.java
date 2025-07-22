/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.laura.FastAndFuriousFood.api.controller;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import local.laura.FastAndFuriousFood.domain.model.Pedido;
import local.laura.FastAndFuriousFood.domain.model.StatusPedido;
import local.laura.FastAndFuriousFood.domain.repository.PedidoRepository;
import local.laura.FastAndFuriousFood.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ppjatb
 */
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<Pedido> buscar(@PathVariable Long pedidoId) {
        Optional<Pedido> pedido = pedidoRepository.findById(pedidoId);
        return pedido.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido adicionar(@Valid @RequestBody Pedido pedido) {
        return pedidoService.salvar(pedido);
    }

    @PutMapping("/{pedidoId}")
    public ResponseEntity<Pedido> atualizar(@PathVariable Long pedidoId, @Valid @RequestBody Pedido pedido) {
        if (!pedidoRepository.existsById(pedidoId)) {
            return ResponseEntity.notFound().build();
        }
        pedido.setId(pedidoId);
        pedido = pedidoService.salvar(pedido);
        return ResponseEntity.ok(pedido);
    }

    @DeleteMapping("/{pedidoId}")
    public ResponseEntity<Void> cancelar(@PathVariable Long pedidoId) {
        if (!pedidoRepository.existsById(pedidoId)) {
            return ResponseEntity.notFound().build();
        }
        pedidoService.cancelar(pedidoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public List<Pedido> listarPorStatus(@PathVariable StatusPedido status) {
        return pedidoService.listarPorStatus(status);
    }

    @PutMapping("/status/{pedidoId}")
    public ResponseEntity<Void> alterarStatus(@PathVariable Long pedidoId, @RequestParam StatusPedido status) {
        if (!pedidoRepository.existsById(pedidoId)) {
            return ResponseEntity.notFound().build();
        }
        pedidoService.alterarStatus(pedidoId, status);
        return ResponseEntity.ok().build();
    }
}


