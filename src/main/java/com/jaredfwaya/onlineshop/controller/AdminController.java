package com.jaredfwaya.onlineshop.controller;

import javax.validation.Valid;

import com.jaredfwaya.onlineshop.dao.ProductDao;
import com.jaredfwaya.onlineshop.model.Product;
import com.jaredfwaya.onlineshop.model.util.Category;
import com.jaredfwaya.onlineshop.model.util.Condition;
import com.jaredfwaya.onlineshop.model.util.Size;
import com.jaredfwaya.onlineshop.service.ImageService;
import com.jaredfwaya.onlineshop.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
    ProductService productService;
	
	@Autowired
    ImageService imageService;
	
	private static final Logger logger = LoggerFactory
			.getLogger(ProductController.class);

	@RequestMapping
	public String adminPage(){
		return "admin/admin";
	}
	
	@RequestMapping("/customers")
	public String customerPage(){
		return "admin/customers";
	}
	
	@RequestMapping("/products")
	public ModelAndView viewInventory(){
		ModelAndView mv = new ModelAndView("admin/products");
		mv.addObject("products", productDao.findAll());
		return mv;
	}
	
	@RequestMapping("/product/new")
	public ModelAndView addProduct(Product product){
		ModelAndView mv = new ModelAndView("admin/addProduct");
		mv.addObject("product", product);
		mv.addObject("condition", Condition.values());
		mv.addObject("category", Category.values());
		mv.addObject("size", Size.values());
		return mv;
	}
	
	@RequestMapping(value = "/product/new", method = RequestMethod.POST)
	public ModelAndView addProduct(@RequestParam("image") MultipartFile image,
								   @Valid Product product, 
								   BindingResult result,
								   RedirectAttributes attributes) {
		
		if (result.hasErrors() && (result.getErrorCount() > 1  
							   || !result.hasFieldErrors("image"))) {
			logger.info("Returning addProduct.jsp page");
			return addProduct(product);
		}
		
		if (!image.isEmpty()){
			product.setImage(imageService.convert2Byte(image));
		}
		
		productService.saveProduct(product);
		attributes.addFlashAttribute("message", "Product created sucessfully!");
		return new ModelAndView("redirect:/admin/product/new");
	}
	
	@RequestMapping(value = "/product/delete/{id}")
	public ModelAndView deleteProduct(@PathVariable Integer id) {
		productService.delete(id);
		return new ModelAndView("redirect:/admin/productInventory");
	}
	
	@RequestMapping("/product/edit/{id}")
	public ModelAndView updateProduct(@PathVariable("id") Integer id){
		ModelAndView mv = new ModelAndView("admin/editProduct");
		mv.addObject("product", productDao.findOne(id));
		mv.addObject("condition", Condition.values());
		mv.addObject("category", Category.values());
		mv.addObject("size", Size.values());
		return mv;
	}
	
	@RequestMapping(value = "/product/edit/{id}", method = RequestMethod.POST)
	public ModelAndView updateProduct(@PathVariable("id") Integer id, 
									   @Valid Product product, 
									   BindingResult result, 
									   RedirectAttributes attributes,
									   @RequestParam("image") MultipartFile image) {
		
		if (result.hasErrors() && (result.getErrorCount() > 1  
				   			   || !result.hasFieldErrors("image"))) {
			logger.info("Returning editProduct.jsp page");
			return updateProductWithErrors(product);
		}
		
		verifyImageEmpty(image, id, product);
		
		productService.updateProduct(product);
		attributes.addFlashAttribute("message", "Product updated sucessfully!");
		return new ModelAndView("redirect:/admin/productInventory");
	}

	private ModelAndView updateProductWithErrors(Product product){
		ModelAndView mv = new ModelAndView("admin/editProduct");
		mv.addObject("product", product);
		mv.addObject("condition", Condition.values());
		mv.addObject("category", Category.values());
		mv.addObject("size", Size.values());
		return mv;
	}
	
	private void verifyImageEmpty(MultipartFile image, Integer id, Product product) {
		if(!image.isEmpty()) {
			product.setImage(imageService.convert2Byte(image));
		}
		else {
			product.setImage(productDao.findImage(id));
		}
	}
}
