package com.example.ECommerce_website.controller;


import com.example.ECommerce_website.dtos.ProductDTO;
import com.example.ECommerce_website.model.Category;
import com.example.ECommerce_website.model.Product;
import com.example.ECommerce_website.service.CategoryService;
import com.example.ECommerce_website.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

   // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }

    // Categories section
    @GetMapping("/admin/categories")
    public String getCat(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        return "categories";
    }

    @GetMapping("/admin/categories/add")
    public String getCatAdd(Model model){
        model.addAttribute("category",new Category());
        return "categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public String postCatAdd(@ModelAttribute("category") Category category){
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCat(@PathVariable int id)
    {
        categoryService.deleteCatById(id);
        return "redirect:/admin/categories";
    }
    @GetMapping("/admin/categories/update/{id}")
    public String updateCat(@PathVariable int id, Model model)
    {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent())
        {
            model.addAttribute("category",category.get());
            return "categoriesAdd";
        }
        else
        {
            return "404";
        }
    }

    // Product section
    @GetMapping("/admin/products")
    public String getProducts(Model model){
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }
    @GetMapping("/admin/products/add")
    public String getProductAdd(Model model){
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "productsAdd";
    }

    @PostMapping("/admin/products/add")
    // when we want to send image as a parameter from FORM of HTML
    // then we need to use Request parameter with Interface MultipartFile
    public String postProductAdd(@ModelAttribute("productDTO") ProductDTO productDto,
                                 @RequestParam("productImage")MultipartFile file,
                                 @RequestParam("imgName") String imgName) throws IOException
    {
        // creating Product class object
        // to convert productDTO to product
        Product product = new Product();

        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setWeight(productDto.getWeight());
        product.setDescription(productDto.getDescription());

        Category category = categoryService.getCategoryById(productDto.getCategoryId()).get();

        product.setCategory(category);

        // to save the image name
        String imageUUID; // simple variable
        if (!file.isEmpty()) // if image file is already exist
        {
            imageUUID = file.getOriginalFilename();
            // using class level variable uploadDir
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        }
        else
        {
            imageUUID = imgName;
        }

        product.setImageName(imageUUID);

        productService.addProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProductById(@PathVariable long id)
    {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String updateProductByid(@PathVariable long id, Model model)
    {
        // converting Product to ProductDTO
        Product product = productService.getProductById(id).get();
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(product.getId());
        productDTO.setPrice(product.getPrice());
        productDTO.setWeight(product.getWeight());
        productDTO.setDescription(product.getDescription());
        productDTO.setName(product.getName());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setImageName(product.getImageName());

        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("productDTO",productDTO);


        return "productsAdd";

    }

}



