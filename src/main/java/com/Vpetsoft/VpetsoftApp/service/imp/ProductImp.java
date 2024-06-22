package com.Vpetsoft.VpetsoftApp.service.imp;
import com.Vpetsoft.VpetsoftApp.entity.Product;
import com.Vpetsoft.VpetsoftApp.repository.ProductRepository;
import com.Vpetsoft.VpetsoftApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> findAll() throws Exception {
        return this.productRepository.findAll();
    }

    @Override
    public Product findById(int id) {
        return this.productRepository.findById(id);
    }

    @Override
    public void create(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public void update(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        this.productRepository.delete(product);
    }

}
