package edu.miu.cs545.group5.onlinemarket.controller;

import edu.miu.cs545.group5.onlinemarket.config.ImageUtil;
import edu.miu.cs545.group5.onlinemarket.domain.Product;
import edu.miu.cs545.group5.onlinemarket.domain.Seller;;
import edu.miu.cs545.group5.onlinemarket.service.CategoryService;
import edu.miu.cs545.group5.onlinemarket.service.ProductService;
import edu.miu.cs545.group5.onlinemarket.service.SellerService;
import edu.miu.cs545.group5.onlinemarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/seller/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private UserService userService;

    private final String UPLOAD_DIR = "./static/uploadImage";
    private static String UPLOADED_FOLDER = System.getProperty("java.io.tmpdir");


    @GetMapping("/productAddForm")
    public String getProductForm(@ModelAttribute("product")Product product, Model model){

        Seller seller = (Seller) userService.getLoggedUser().get();
        model.addAttribute("seller", seller);
        model.addAttribute("categories", categoryService.findAllCategory());
        return "productForm";
    }
    @PostMapping("/addProduct")
    public String saveProduct(@Valid Product product, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        Seller seller = (Seller) userService.getLoggedUser().get();

        if (bindingResult.hasErrors()) {
            return "productForm";
        }
        product.setSeller(seller);

        //String uploadDir = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"uploadImage";
        //String  fileName = UUID.randomUUID().toString();
        MultipartFile productImage = product.getMultipartFile();

        if(productImage != null){
            String fileNames = StringUtils.cleanPath(productImage.getOriginalFilename());
            product.setImageName(fileNames);
            product.setFileType(productImage.getContentType());
            try {
                product.setData(productImage.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

      /*  if (productImage != null && !productImage.isEmpty()) {
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
        }*/

            productService.saveProduct(product);
            redirectAttributes.addFlashAttribute("msg", "Success");
            return "redirect:/seller/product/productAddForm";

    }

    @GetMapping("/dashboard")
    public String manageSeller(Model model){
        //model.addAttribute("seller",sellerService.findById((long) 1));
        Seller seller = (Seller) userService.getLoggedUser().get();
        model.addAttribute("seller", seller);
        model.addAttribute("pId", userService.getLoggedUser().get().getId());
        return "dashboardHeader";
    }

    @RequestMapping("/productList/{page}")
    public ModelAndView listProductPageByPage(@PathVariable("page")int page){
        ModelAndView modelAndView = new ModelAndView("manageProduct");
        PageRequest pageable = PageRequest.of(page - 1, 15);
        //Page<Product> productPage = productService.getPaginatedProduct(pageable);

        //Only login Seller do this
        Seller seller = (Seller) userService.getLoggedUser().get();

        Page<Product> productPage = productService.findBySellerId(pageable,userService.getLoggedUser().get().getId());
        int totalPages = productPage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        //Only login Seller do this
        //Seller seller = (Seller) userService.getLoggedUser().get();
        modelAndView.addObject("pId", userService.getLoggedUser().get().getId());
        modelAndView.addObject("seller",seller);


        modelAndView.addObject("activeProductList", true);
        modelAndView.addObject("productList", productPage.getContent());
        modelAndView.addObject("imgUtil", new ImageUtil());
        return modelAndView;
    }

    @RequestMapping("/productDetails/{productId}")
    public String editProduct(@PathVariable("productId")Long productId, Model model){
        Seller seller = (Seller) userService.getLoggedUser().get();
        model.addAttribute("seller", seller);
        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute("productById", productService.findProductById(productId));
        return "productDetails";
    }

    @RequestMapping(value = "/deleteProduct/{productId}")
    public ResponseEntity<?> deleteProductById(@PathVariable("productId")Long productId, Model model){
        Product product = productService.findProductById(productId);
        if(product != null){
            productService.deleteProductById(productId);
        }
        Seller seller = (Seller) userService.getLoggedUser().get();
        model.addAttribute("seller", seller);

        return ResponseEntity.ok("Ok");
    }

    @GetMapping("/editProduct/{productId}")
    public String editProductById(@PathVariable("productId")Long productId,@ModelAttribute("product")Product product, Model model,
                                    RedirectAttributes redirectAttributes){

        //Product product1 =productService.findProductById(productId);
        //product1.setMultipartFile(new CustomMultipartFile(product.getData(),product1.getImageName()));

        model.addAttribute("product", productService.findProductById(productId));
        model.addAttribute("categories", categoryService.findAllCategory());
        Seller seller = (Seller) userService.getLoggedUser().get();
        model.addAttribute("seller", seller);
        model.addAttribute("imgUtil", new ImageUtil());
        return "editProduct";
    }

    @PostMapping("/updateProduct")
    public String updateProductBy(@ModelAttribute("product")Product product,Model model,RedirectAttributes redirectAttributes){
        Product product1 = productService.findProductById(product.getId());
        product.setData(product1.getData());
        product.setSeller(product1.getSeller());
        productService.saveProduct(product);
        redirectAttributes.addFlashAttribute("msg", "Success");
        Seller seller = (Seller) userService.getLoggedUser().get();
        model.addAttribute("seller", seller);
        redirectAttributes.addFlashAttribute("msg", "Success");
        return "sellerDashboard";
    }

    @RequestMapping("/modal")
    public String openModal(){
        return "modal";
    }
}
