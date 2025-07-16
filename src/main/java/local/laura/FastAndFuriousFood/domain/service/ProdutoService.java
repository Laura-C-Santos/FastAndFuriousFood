/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.laura.FastAndFuriousFood.domain.service;

import java.util.List;
import local.laura.FastAndFuriousFood.domain.exception.DomainException;
import local.laura.FastAndFuriousFood.domain.model.Produto;
import local.laura.FastAndFuriousFood.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ppjatb
 */
@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;
    
    public Produto salvar(Produto produto) {
    Produto existente = produtoRepository.findByNomeAndCategoria(
        produto.getNome(), produto.getCategoria());

    if (existente != null && !existente.equals(produto)) {
        throw new DomainException("Já existe um produto com esse nome na mesma categoria.");
    }

    return produtoRepository.save(produto);
}
    
    public void excluir(long produtoId){
        produtoRepository.deleteById(produtoId);
    }
}

