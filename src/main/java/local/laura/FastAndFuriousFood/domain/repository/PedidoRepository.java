/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package local.laura.FastAndFuriousFood.domain.repository;

import java.util.List;
import local.laura.FastAndFuriousFood.domain.model.Pedido;
import local.laura.FastAndFuriousFood.domain.model.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ppjatb
 */

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByStatus(StatusPedido status);
}

