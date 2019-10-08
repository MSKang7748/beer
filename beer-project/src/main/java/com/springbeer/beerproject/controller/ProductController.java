package com.springbeer.beerproject.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import com.springbeer.beerproject.common.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springbeer.beerproject.entity.CartEntity;
import com.springbeer.beerproject.entity.MemberEntity;
import com.springbeer.beerproject.entity.ProductEntity;
import com.springbeer.beerproject.entity.ProductFileEntity;
import com.springbeer.beerproject.repository.ProductRepository;
import com.springbeer.beerproject.service.CartService;
import com.springbeer.beerproject.service.ProductService;

@Controller
@RequestMapping(value="/product")
public class ProductController { // 웹페이지 Home 으로 가는 컨트롤러 
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CartService cartService;
	
	@RequestMapping(value = "/list")
	public String productList(Model model) {	
		
		List<ProductEntity> products = productService.findAllProducts();	

		model.addAttribute("products", products);

		return "products/productlist";
	}

	@RequestMapping(value = "/productDetail/{beerNo}", method=RequestMethod.GET)
	public String productDetail(@PathVariable Long beerNo, Model model, ProductEntity productEntity) {
	
		List<ProductEntity> products = productService.findProductByBeerNo(beerNo);		
		
//		for (ProductEntity product : products) {
//			List<ProductFileEntity> productFile = productService.findProductFileByBeerNo(product.getBeerNo());
//			product.setProductFileEntity(productFile);
//		}
		
		model.addAttribute("products", products);
		
		return "products/productdetail";
	}
	
	@RequestMapping(value = "/productWrite", method=RequestMethod.GET)
	public String productWriteForm() {
		
	return "products/productwrite";
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

		productService.productImageDelete(beerNo);
		productService.productDelete(beerNo);

	return "redirect:/product/list";
	}
	
	@RequestMapping(value = "/productUpdate", method=RequestMethod.GET)
	public String productUpdateForm(@RequestParam(name="beerNo") Long beerNo, Model model, ProductEntity productEntity) {
	
		List<ProductEntity> products = productService.findProductByBeerNo(beerNo);		
		
//		for (ProductEntity product : products) {
//			List<ProductFileEntity> productFile = productService.findProductFileByBeerNo(product.getBeerNo());
//			product.setProductFileEntity(productFile);
//		}
		
		model.addAttribute("products", products);
		
		return "products/productupdate";
	}
	
	@RequestMapping(value = "/productUpdate/{beerNo}", method=RequestMethod.POST)
	public String productUpdate(@PathVariable Long beerNo, 
			ProductEntity productEntity, MultipartHttpServletRequest req) {
		
		MultipartFile mf = req.getFile("beerFile");
		boolean test = mf.isEmpty();
		
		if (test == false) {
			
			ServletContext application = req.getServletContext();
			String path = application.getRealPath("/images/");
			
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
				productFile.setBeerNo(productEntity.getBeerNo());
				ArrayList<ProductFileEntity> files = new ArrayList<ProductFileEntity>();
				files.add(productFile);
				productEntity.setProductFileEntity(files);
				
				productService.updateProduct(productEntity);
			
			} catch (Exception ex) {
				ex.printStackTrace();
			}			
		} else if(test == true) {
			
			List<ProductFileEntity> productFile = productService.findProductFileByBeerNo(productEntity.getBeerNo());
			productEntity.setProductFileEntity(productFile);
			
			productService.updateProduct(productEntity);
			return "redirect:/product/productDetail/" + productEntity.getBeerNo();
		}

		return "redirect:/product/productDetail/" + productEntity.getBeerNo();
	}

	@RequestMapping(value = "/cart")
	public String productCart(Model model) {
	
	return "products/cart";
	}
	
	@RequestMapping(value = "/removecart/{cartId}", method=RequestMethod.GET) 
	public String cartDelete(@PathVariable int cartId, HttpSession session) {
	 
		 cartService.cartDelete(cartId);
	
		 MemberEntity memberEntity = (MemberEntity)session.getAttribute("loginuser");
		 return "redirect:/product/cartview/" + memberEntity.getMemberNo(); 
	 }
	
	@GetMapping(value = "/addcart")
	public String addtoCart(CartEntity cart) {
		
		//cartService.addtoCartByMemberNo(cart);
		cartService.addToCart(cart);

		return "redirect:/product/cartview/" + cart.getMemberNo(); // 여기에 MemberNo 더해서 보내야 한다.
	}
	
	@GetMapping(value = "/cartview/{memberNo}")
	public String cartList(@PathVariable int memberNo, Model model) {
		
		List<CartEntity> carts = cartService.cartFindByMemberNo(memberNo);
		
		//System.out.println(products);
		
		float total = 0;
		for (CartEntity cart : carts) {
			total += cart.getProduct().getBeerPrice();
		}
		
		model.addAttribute("carts", carts);
		model.addAttribute("total", total);
		
		return "products/cart";
	}
	
	@RequestMapping(value = "/checkout", method=RequestMethod.GET)
	public String productCheckout(Model model, HttpSession session) {

		MemberEntity member = (MemberEntity) session.getAttribute("loginuser");
		
		model.addAttribute("member", member);
	
	return "products/checkout";
	}
	
	@RequestMapping(value = "/thankyou", method=RequestMethod.GET)
	public String thankyou(Model model, HttpSession session) {

	return "products/thankyou";
	}

	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String productSearch(@RequestParam(name="beerName") String beerName, @RequestParam(name="beerDiv") String beerDiv, Model model) {
		
		
		List<ProductEntity> search = productService.productSearch(beerName, beerDiv);
		
		model.addAttribute("search", search);
		
		return "products/searchproductList"; 
	}
	

	
	
}