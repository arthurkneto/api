package br.com.klug.api.services;

import br.com.klug.api.model.Category;
import br.com.klug.api.model.Product;
import br.com.klug.api.repository.CategoryRepository;
import br.com.klug.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    @Autowired
    CategoryRepository categoryRepository;

    public List<Product> getAll(){
        return repository.findAll();
    }

    public Product getById(Long id){
        return repository.findById(id).orElseThrow();
    }

    public void save(Product product){

        if( product.getCategories() != null ){
            List<Category> categoriasAAdicionar = new ArrayList<>();
            for( Category category : product.getCategories() ){
                categoriasAAdicionar.add(categoryRepository.getReferenceById(category.getId()));
            }
            product.setCategories(categoriasAAdicionar);
        }

        repository.save(product);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

}
