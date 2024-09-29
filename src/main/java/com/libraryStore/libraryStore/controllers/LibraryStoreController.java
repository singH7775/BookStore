package com.libraryStore.libraryStore.controllers;

import java.util.List;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.libraryStore.libraryStore.dto.ProductDTO;
import com.libraryStore.libraryStore.models.Member;
import com.libraryStore.libraryStore.models.Product;
import com.libraryStore.libraryStore.repo.MemberRepository;
import com.libraryStore.libraryStore.repo.ProductRepository;
import com.libraryStore.libraryStore.services.OrderItemService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/store")
public class LibraryStoreController {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@GetMapping("/storepage")
	public String showProductList(Model model) {
		List<Product> products = (List<Product>) productRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		model.addAttribute("products", products);
		return "storepage";
	}
	
	@GetMapping("/editproduct/{id}")
	public String showEditForm(@PathVariable int id, Model model) {
	    Optional<Product> productOptional = productRepository.findById(id);
	    
	    if (productOptional.isPresent()) {
	        Product product = productOptional.get();
	        model.addAttribute("product", product);
	        return "editProduct";
	    } else {
	        model.addAttribute("error", "Product not found");
	        return "storepage";
	    }
	}
	
	@PostMapping("/editproduct")
	public String showEditForm(@ModelAttribute Product product, BindingResult result) {
		if (result.hasErrors()) {
			return "editProduct";
		}
		
		productRepository.save(product);
		return "redirect:/store/storepage";
	}
	
	@PostMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable int id) {
		productRepository.deleteById(id);
		return "redirect:/store/storepage";
	}
	
	@GetMapping("/addProduct")
	public String getProductPage(Model model) {
		model.addAttribute("productDTO", new ProductDTO());
		return "addProduct";
	}
	
	@PostMapping("/addProduct")
	public String addProduct(@ModelAttribute ProductDTO product, BindingResult result) {
		if (result.hasErrors()) {
			return "storepage";
		}
		
		Product newProduct = new Product();
		newProduct.setProductName(product.getProductName());
		newProduct.setPrice(product.getPrice());
		
		productRepository.save(newProduct);
		return "redirect:/store/storepage";	
	}
	
	@GetMapping("/memberOrders")
	public String displayMemberOrders(Model model, HttpSession session) {
		Optional<Member> findMember = memberRepository.findById((Integer) session.getAttribute("memberId"));
		
		if (findMember.isPresent()) {
			Member member = findMember.get();
			model.addAttribute("orders", member.getOrderItems());
		} else {
			return "storepage";
		}
		
		return "memberOrders";
	}
	
	@PostMapping("/orderProduct/{id}")
	public String orderProduct(@PathVariable int id, HttpSession session) {
		Optional<Member> currentMember = memberRepository.findById((Integer) session.getAttribute("memberId"));
		
		if (currentMember.isPresent()) {
			Member member = currentMember.get();
			
			Optional<Product> currentProduct = productRepository.findById(id);
			if (currentProduct.isPresent()) {
				Product product = currentProduct.get();
				orderItemService.addProductToMember(member, product);
			}
		}
		
		return "redirect:/store/memberOrders";
	}
	
	@PostMapping("/cancelOrder/{id}")
	public String cancelOrder(@PathVariable int id, HttpSession session) {
		Optional<Member> currentMember = memberRepository.findById((Integer) session.getAttribute("memberId"));
		
		if (currentMember.isPresent()) {
			Member member = currentMember.get();
			
			Optional<Product> currentProduct = productRepository.findById(id);
			if (currentProduct.isPresent()) {
				Product product = currentProduct.get();
				orderItemService.removeOrderToMember(member, product);
			}
		}
		
		return "redirect:/store/memberOrders";
	}
	
}