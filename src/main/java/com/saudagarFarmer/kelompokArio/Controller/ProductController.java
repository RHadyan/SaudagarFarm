package com.saudagarFarmer.kelompokArio.Controller;

import com.saudagarFarmer.kelompokArio.Models.ProductDto;
import com.saudagarFarmer.kelompokArio.Models.ProductModels;
import com.saudagarFarmer.kelompokArio.Repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository repoProduct;

    @GetMapping("/product")
    public String showAdd(Model model){
        List<ProductModels> product = repoProduct.findAll();
        model.addAttribute("products", product);

        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);

        return "/product";
    }

    @GetMapping("/add")
    public String showCreate(Model model){
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);
        return "/add";
    }

    @PostMapping("/create")
    public String CreateProduct(
            @Valid @ModelAttribute ProductDto productDto,
            BindingResult result){

        if (productDto.getImageFile().isEmpty()){
            result.addError(new FieldError("productDto","imageFile","the image file is missing"));
        }
        if (result.hasErrors()){
            return  "/add";
        }
//        add image
        MultipartFile image = productDto.getImageFile();
        Date createAt = new Date();
        String storageFileName = createAt.getTime() + "_" + image.getOriginalFilename();
        try {
            String uploadDir = "public/images/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }
            try(InputStream inputStream = image.getInputStream()){
                Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
                        StandardCopyOption.REPLACE_EXISTING);
            }

        }catch (Exception ex){
            System.out.println("Exception" + ex.getMessage());
        }

        ProductModels product = new ProductModels();
        product.setNamaHewan(productDto.getNamaHewan());
        product.setBerat(productDto.getBerat());
        product.setHarga(productDto.getHarga());
        product.setKategori(productDto.getKategori());
        product.setImageFileName(storageFileName);



        repoProduct.save(product);

        return "redirect:/product";
    }

    @GetMapping("/edit")
    public String showEditPage(
            Model model,
            @RequestParam int id
    )
    {
        try {
            ProductModels product = repoProduct.findById(id).get();
            model.addAttribute("product", product);

            ProductDto productDto = new ProductDto();
            productDto.setNamaHewan(product.getNamaHewan());
            productDto.setBerat(product.getBerat());
            productDto.setHarga(product.getHarga());
            productDto.setKategori(product.getKategori());

            model.addAttribute("productDto", productDto);



        }catch (Exception ex){
            System.out.println("Exception" + ex.getMessage());
            return "redirect:/product";
        }
        return "/edit";
    }

    @PostMapping("/edit")
    public String updateProduct(
            Model model,
            @RequestParam int id,
            @Valid @ModelAttribute ProductDto productDto,
            BindingResult result
    ){
        try {
            ProductModels product = repoProduct.findById(id).get();
            model.addAttribute("product",product);

            if (result.hasErrors()){
                return "/product";
            }

            if (!productDto.getImageFile().isEmpty()){
//                delete image lama
                String uploadDir = "public/images/";
                Path oldImagePath = Paths.get(uploadDir + product.getImageFileName());


                try {
                    Files.delete(oldImagePath);
                }
                catch (Exception ex){
                    System.out.println("Exception" + ex.getMessage());
                }
//                save gambar baru
                MultipartFile image = productDto.getImageFile();
                Date createAt = new Date();

                String storageFileName = createAt.getTime() + "_" + image.getOriginalFilename();

                try (InputStream inputStream = image.getInputStream()){
                    Files.copy(inputStream,  Paths.get(uploadDir+storageFileName),
                            StandardCopyOption.REPLACE_EXISTING);
                }
                product.setImageFileName(storageFileName);
            }

            product.setNamaHewan(productDto.getNamaHewan());
            product.setBerat(productDto.getBerat());
            product.setHarga(productDto.getHarga());
            product.setKategori(productDto.getKategori());

            repoProduct.save(product);
        }
        catch (Exception ex){
            System.out.println("Exception" + ex.getMessage());

        }
        return "redirect:/product";
    }

    @GetMapping("/delete")
    public String deleteProduct(
            @RequestParam int id
    ){

        try {
            ProductModels product = repoProduct.findById(id).get();

            Path imagePath = Paths.get("public/images/" + product.getImageFileName());

            try {
                Files.delete(imagePath);
            }catch (Exception ex){
                System.out.println("Exception" + ex.getMessage());
            }
            repoProduct.delete(product);

        }
        catch (Exception ex){
            System.out.println("Exception" + ex.getMessage());


        }
        return "redirect:/product";
    }
}
