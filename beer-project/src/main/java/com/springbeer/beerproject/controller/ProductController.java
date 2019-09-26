package com.springbeer.beerproject.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContext;

import com.springbeer.beerproject.common.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springbeer.beerproject.entity.CartEntity;
import com.springbeer.beerproject.entity.ProductEntity;
import com.springbeer.beerproject.entity.ProductFileEntity;
import com.springbeer.beerproject.entity.SubFile;
import com.springbeer.beerproject.entity.Subscription;
import com.springbeer.beerproject.repository.ProductRepository;
import com.springbeer.beerproject.service.CartService;
import com.springbeer.beerproject.service.ProductService;

@Controller
@RequestMapping(value="/product")
public class ProductController { // 웹페이지 Home 으로 가는 컨트롤러 
	
	@Autowired
	ProductService productService;
	CartService cartService;
	
	@RequestMapping(value = "/list")
	public String productList(Model model) {	
		
		List<ProductEntity> products = productService.findAllProducts();
		
		for (ProductEntity product : products) {
			List<ProductFileEntity> productFile = productService.findProductFileByBeerNo(product.getBeerNo());
			product.setProductFileEntity(productFile);
		}

		model.addAttribute("products", products);

		return "/products/productlist";
	}

	@RequestMapping(value = "/productDetail/{beerNo}", method=RequestMethod.GET)
	public String productDetail(@PathVariable Long beerNo, Model model, ProductEntity productEntity) {
	
		List<ProductEntity> products = productService.findProductByBeerNo(beerNo);		
		
		for (ProductEntity product : products) {
			List<ProductFileEntity> productFile = productService.findProductFileByBeerNo(product.getBeerNo());
			product.setProductFileEntity(productFile);
		}
		
		model.addAttribute("products", products);
		
		return "/products/productdetail";
	}
	
	@RequestMapping(value = "/productWrite", method=RequestMethod.GET)
	public String productWriteForm() {
		
	return "/products/productwrite";
	}
	
	@RequestMapping(value = "/productWrite")
	public String productWrite(ProductEntity productEntity, ProductFileEntity productFileEntity, Model model, MultipartHttpServletRequest req ) {
		
		
		MultipartFile mf = req.getFile("beerFile");
		boolean test = mf.isEmpty();
		
		if (test == false) {
			
			ServletContext application = req.getServletContext();
			String path = application.getRealPath("/images/");
			//String path = "/images";
			//File file = new File(path);
			//if (!file.exists()) {
			//	file.mkdirs();
			//}
			
			String userFileName =  mf.getOriginalFilename();
			if (userFileName.contains("\\")) { // iexplore 野껋럩�뒭
				//C:\AAA\BBB\CCC.png -> CCC.png 
				userFileName = userFileName.substring(userFileName.lastIndexOf("\\") + 1);
			}
			String savedFileName = Util.makeUniqueFileName(userFileName);

			try {
				mf.transferTo(new File(path, savedFileName));
				
				ProductFileEntity productFile = new ProductFileEntity();
				productFile.setProductUserFileName(userFileName);
				productFile.setProductSavedFileName(savedFileName);
				ArrayList<ProductFileEntity> beerFile = new ArrayList<ProductFileEntity>();
				beerFile.add(productFile);
				productEntity.setProductFileEntity(beerFile);
				
				productService.writeProduct(productEntity);
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}			
		} 
		else if(test != false) {
			
			productService.writeProduct(productEntity);
			
			return "redirect:/product/list";
		}

		return "redirect:/product/list";
	}
		
	@RequestMapping(value = "/productDelete/{beerNo}", method=RequestMethod.GET)
	public String productDelete(@PathVariable Long beerNo) {

		productService.productDelete(beerNo);
		productService.productImageDelete(beerNo);

	return "redirect:/product/list";
	}
	
	@RequestMapping(value = "/productUpdate/{beerNo}", method=RequestMethod.GET)
	public String productUpdateForm(@PathVariable Long beerNo, Model model, ProductEntity productEntity) {
	
		List<ProductEntity> products = productService.findProductByBeerNo(beerNo);		
		
		for (ProductEntity product : products) {
			List<ProductFileEntity> productFile = productService.findProductFileByBeerNo(product.getBeerNo());
			product.setProductFileEntity(productFile);
		}
		
		model.addAttribute("products", products);
		
		return "/products/productupdate";
	}
	
//	@RequestMapping(value = "/productUpdate")
//	public String productUpdate(ProductEntity productEntity, ProductFileEntity productFileEntity, Model model, MultipartHttpServletRequest req ) {
//		
//		MultipartFile mf = req.getFile("beerFile");
//		boolean test = mf.isEmpty();
//		
//		if (test == false) {
//			
//			ServletContext application = req.getServletContext();
//			String path = application.getRealPath("/images/");
//			//String path = "/images";
//			//File file = new File(path);
//			//if (!file.exists()) {
//			//	file.mkdirs();
//			//}
//			
//			String userFileName =  mf.getOriginalFilename();
//			if (userFileName.contains("\\")) { // iexplore 野껋럩�뒭
//				//C:\AAA\BBB\CCC.png -> CCC.png 
//				userFileName = userFileName.substring(userFileName.lastIndexOf("\\") + 1);
//			}
//			String savedFileName = Util.makeUniqueFileName(userFileName);
//
//			try {
//				mf.transferTo(new File(path, savedFileName));
//				
//				ProductFileEntity productFile = new ProductFileEntity();
//				productFile.setProductUserFileName(userFileName);
//				productFile.setProductSavedFileName(savedFileName);
//				productFile.setBeerNo(productEntity.getBeerNo());
//				productService.insertProductFile(productFile);
//				
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			}			
//		} 
//		else if(test != false) {
//			
//			productService.updateProduct(productEntity);
//			
//			return "redirect:/product/productDetail/" + productEntity.getBeerNo();
//		}
//
//		return "redirect:/product/productDetail/" + productEntity.getBeerNo();
//	}
	
	@RequestMapping(value = "/cart")
	public String productCart(Model model) {
	
	return "/products/cart";
	}
	
	@RequestMapping(value = "/cart", method=RequestMethod.GET)
	public String cart(@RequestParam(name="beerNo") Long beerNo, Model model, CartEntity cartEntity) {

		List<CartEntity> products = cartService.cartFindByBeerNo(beerNo);		
		
		model.addAttribute("products", products);
		
		return "/products/cart";
	}
	
	@RequestMapping(value = "/checkout", method=RequestMethod.GET)
	public String productCheckout(Model model) {
	
	return "/products/checkout";
	}
	
}