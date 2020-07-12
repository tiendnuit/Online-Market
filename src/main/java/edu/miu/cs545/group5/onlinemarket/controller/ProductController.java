package edu.miu.cs545.group5.onlinemarket.controller;

import edu.miu.cs545.group5.onlinemarket.domain.Product;
import edu.miu.cs545.group5.onlinemarket.domain.Seller;
import edu.miu.cs545.group5.onlinemarket.domain.User;
import edu.miu.cs545.group5.onlinemarket.exception.UploadFileException;
import edu.miu.cs545.group5.onlinemarket.service.CategoryService;
import edu.miu.cs545.group5.onlinemarket.service.ProductService;
import edu.miu.cs545.group5.onlinemarket.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SellerService sellerService;

    private final String UPLOAD_DIR = "./static/uploadImage";
    private static String UPLOADED_FOLDER = System.getProperty("java.io.tmpdir");


    @GetMapping("/productAddForm")
    public String getProductForm(@ModelAttribute("product")Product product, Model model){
        model.addAttribute("categories", categoryService.findAllCategory());
        return "productForm";
    }
    @PostMapping("/addProduct")
    public String saveProduct(@Valid Product product, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        User user = new Seller();
        user.setId((long) 1);

        if (bindingResult.hasErrors()) {
            return "productForm";
        }
        product.setSeller((Seller) user);
        //String uploadDir = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"uploadImage";
        String  fileName = UUID.randomUUID().toString();
        MultipartFile productImage = product.getMultipartFile();
        if (productImage != null && !productImage.isEmpty()) {
            try {
                ClassLoader classLoader = getClass().getClassLoader();
                String rootDirectory = classLoader.getResource(".").getFile();
                //System.out.println("===> " + rootDirectory + "static/images/" + UUID.randomUUID().toString() + ".png");
                File newFile = new File(rootDirectory + "static/images/" + fileName + ".png");//new File(rootDirectory + "/" + student.getId() + ".png");
                //System.out.println("===> " + newFile.getAbsoluteFile());
                productImage.transferTo(newFile);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                //throw new RuntimeException("Student Image saving failed", e);
                throw new UploadFileException("Student Image saving failed");
            }
        }

        if(productImage != null){
            //String fileName = StringUtils.cleanPath(productImage.getOriginalFilename());
            product.setImageName(fileName);
            /*product.setFileType(productImage.getContentType());
            try {
                product.setData(productImage.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }

            productService.saveProduct(product);
            redirectAttributes.addFlashAttribute("msg", "Success");
            return "redirect:/product/productAddForm";

    }

    @GetMapping("/dashboard")
    public String manageSeller(Model model){
        model.addAttribute("seller",sellerService.findById((long) 1));

        return "dashboardHeader";
    }

    @RequestMapping("/productList/{page}")
    public ModelAndView listProductPageByPage(@PathVariable("page")int page){
        ModelAndView modelAndView = new ModelAndView("manageProduct");
        PageRequest pageable = PageRequest.of(page - 1, 15);
        Page<Product> productPage = productService.getPaginatedProduct(pageable);
        int totalPages = productPage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        //Only login Seller do this
        Seller seller = sellerService.findById((long) 1);
        modelAndView.addObject("seller",seller);


        modelAndView.addObject("activeProductList", true);
        modelAndView.addObject("productList", productPage.getContent());
        return modelAndView;
    }

    @RequestMapping("/productDetails/{productId}")
    public String editProduct(@PathVariable("productId")Long productId, Model model){
       model.addAttribute("productById", productService.findProductById(productId));
        return "productDetails";
    }

    @DeleteMapping("/deleteProduct/{productId}")
    public String deleteProductById(@PathVariable("productId")Long productId){
        Product product = productService.findProductById(productId);
        if(product != null){
            productService.deleteProductById(productId);
        }
        return "manageProduct";
    }

    @GetMapping("/editProduct/{productId}")
    public String editProductById(@PathVariable("productId")Long productId, Model model){
        model.addAttribute("productEdit", productService.findProductById(productId));
        return "editProduct";
    }

    @PostMapping("/updateProduct")
    public String updateProductBy(@ModelAttribute("product")Product product){
        productService.saveProduct(product);
        return "manageProduct";
    }
}
