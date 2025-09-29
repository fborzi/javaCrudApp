package com.programacion.javacrud.services;

import com.programacion.javacrud.dtos.ProductDTO;
import com.programacion.javacrud.dtos.ProductResponseDTO;
import com.programacion.javacrud.entities.Category;
import com.programacion.javacrud.entities.Product;
import com.programacion.javacrud.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository,
                          CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    // The C (create) in CRUD
    public Product createProduct(ProductDTO productDTO) throws IllegalArgumentException {
        Product product = getProduct(productDTO);

        if(productDTO.getCategoryId() != null){
            Optional<Category> category = categoryService.getCategoryById(productDTO.getCategoryId());
            category.ifPresent(product::setCategory);
        }else{
            throw new IllegalArgumentException("Category ID cannot be null");
        }

        return productRepository.save(product);
    }

    private static Product getProduct(ProductDTO productDTO) {

        Product product = new Product();

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setAvailable(productDTO.isAvailable());

        return product;
    }

    // The R (read) in CRUD
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAllWithRefs()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<ProductResponseDTO> getProductResponseById(Integer id) {
        return productRepository.findById(id).map(this::toResponse);
    }

    private ProductResponseDTO toResponse(Product p) {
        return new ProductResponseDTO(
                p.getId(),
                p.getName(),
                p.getDescription(),
                p.getPrice(),
                p.isAvailable(),
                p.getCategory() != null ? p.getCategory().getId() : null,
                p.getCategory() != null ? p.getCategory().getName() : null
            );
    }

    // The U (update) in CRUD
    public Product updateProduct(ProductDTO productDTO) throws IllegalArgumentException, EntityNotFoundException {
        if (productDTO.getId() == null) {
            throw new IllegalArgumentException("The product ID must not be null");
        }

        Product product = productRepository.findById(productDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Product with ID: " + productDTO.getId() + " not found"));

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setAvailable(productDTO.isAvailable());

        if (productDTO.getCategoryId() != null) {
            Category category = categoryService.getCategoryById(productDTO.getCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Category not found"));
            product.setCategory(category);
        }
        return productRepository.save(product);
    }

    // The D (delete) in CRUD
    public void deleteProduct(Integer id) throws IllegalArgumentException, EntityNotFoundException {
        if (id == null) {
            throw new IllegalArgumentException("The product ID must not be null");
        }

        if(!productRepository.existsById(id)){
            throw new EntityNotFoundException("Product with ID: " + id + " not found");
        }

        productRepository.deleteById(id);
    }
}
