package br.com.klug.api.controller;

import br.com.klug.api.model.Product;
import br.com.klug.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping("")
    public List<Product> list(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable Long id){
        try {
            Product entity = service.getById(id);
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public void add(@RequestBody Product entity) {
        service.save(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@RequestBody Product entity, @PathVariable Long id) {
        try{
            Product existingProduct = service.getById(id);
            entity.setId(id);
            service.save(entity);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
