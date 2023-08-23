package br.com.klug.api.controller;

import br.com.klug.api.model.Category;
import br.com.klug.api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    /**
     * Adicionar Nova Categoria
     * @param category
     */
    @PostMapping("/")
    public void adicionarCategoria(@RequestBody Category category){
        categoryService.salvarCategoria(category);
    }

    /**
     * Retornar todas as categorias do banco de cados
     * @return Categorias
     */
    @GetMapping("")
    public List<Category> retornarTodas(){
        return categoryService.retornarTodasAsCategorias();
    }

    /**
     * Buscar uma categoria por ID
     * @param id da categoria a ser retornada
     * @return Categoria com o id selecionado
     */
    @GetMapping("/{id}")
    public ResponseEntity<Category> buscarPorId(@PathVariable Long id){
        try {
            Category category = categoryService.retornarCategoriaPorId(id);
            return new ResponseEntity<>(category,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Apagar uma categoria por id
     * @param id da categoria
     */
    @DeleteMapping("/{id}")
    public void removerCategoria(@PathVariable Long id){
        categoryService.removeCategoria(id);
    }

    /**
     * Atualizar uma categoria no banco de dados
     * @param category com os novos dados
     * @param id da categoria no banco de dados
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Category> atualizaCategoria(@RequestBody Category category, @PathVariable Long id){
        try {
            Category categoriaNoBancoDeDados = categoryService.retornarCategoriaPorId(id);
            category.setId(id);
            categoryService.salvarCategoria(category);
            return new ResponseEntity<>(category,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
