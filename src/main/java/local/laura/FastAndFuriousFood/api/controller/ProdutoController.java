/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.laura.FastAndFuriousFood.api.controller;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import local.laura.FastAndFuriousFood.domain.model.Produto;
import local.laura.FastAndFuriousFood.domain.repository.ProdutoRepository;
import local.laura.FastAndFuriousFood.domain.service.ProdutoService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



/**
 *
 * @author ppjatb
 */

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private ProdutoService produtoService;
    
    @GetMapping("/produtos")
    public List<Produto> listas(){
        return produtoRepository.findAll();
    
    }
    
    @GetMapping("/cat/{categoria}")
    public List<Produto> listarPorCategoria(@PathVariable String categoria) {
        return produtoRepository.findByCategoria(categoria); // Certifique que esse método existe no repository
    }
    
    @GetMapping("/produtos/{produtoID}")
    public ResponseEntity<Produto> buscar(@PathVariable Long produtoID){
        
        Optional<Produto> produto = produtoRepository.findById(produtoID);
        
        if(produto.isPresent()){
            return ResponseEntity.ok(produto.get());
        }else{
            return ResponseEntity.notFound().build();
        }
        
    
    }

   @PostMapping("/produtos")
   @ResponseStatus(HttpStatus.CREATED)
   public Produto adicionar(@Valid @RequestBody Produto produto){
   
       return produtoService.salvar(produto);
   }
   
    @PutMapping("/produtos/{produtoID}")
    public ResponseEntity<Produto> atualizar(@Valid @PathVariable Long produtoID, @RequestBody Produto produto){
       
        if(!produtoRepository.existsById(produtoID)){
            return ResponseEntity.notFound().build();
        }
        
        produto.setId(produtoID);
        produto = produtoService.salvar(produto);
        return ResponseEntity.ok(produto);
        }
        
    @DeleteMapping("/produtos/{produtoID}")
    public ResponseEntity<Void> excluir(@PathVariable Long produtoID) {
        
        if (!produtoRepository.existsById(produtoID)) {
            return ResponseEntity.notFound().build();
        }
        produtoService.excluir(produtoID);
        return ResponseEntity.noContent().build();
        
    }
}

