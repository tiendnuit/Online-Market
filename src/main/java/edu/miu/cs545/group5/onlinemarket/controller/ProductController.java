package edu.miu.cs545.group5.onlinemarket.controller;

import edu.miu.cs545.group5.onlinemarket.domain.Product;
import edu.miu.cs545.group5.onlinemarket.domain.Seller;
import edu.miu.cs545.group5.onlinemarket.domain.User;
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

        MultipartFile multipartFile = product.getMultipartFile();

        // normalize the file path
        //String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

       /* // save the file on the local file system
        try {
            Path path = Paths.get(UPLOADED_FOLDER + fileName);
            //Path path = Paths.get(uploadDir + fileName);
            Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*if(multipartFile != null){
            product.setImageName(multipartFile.getOriginalFilename());
            Path copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(multipartFile.getOriginalFilename()));
            try {
                Files.copy(multipartFile.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        if(multipartFile != null){
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            product.setImageName(fileName);
            product.setFileType(multipartFile.getContentType());
            try {
                product.setData(multipartFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
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
}
