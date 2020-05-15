package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Form.productForm;
import com.example.demo.Form.searchForm;
import com.example.demo.entity.product;
import com.example.demo.model.productInfor;
import com.example.demo.service.ProductService;
import com.example.demo.validation.productValidation;

@Controller
public class mainController {
	@Autowired
	productValidation proValidation;

	@Autowired
	ProductService productService;
	static List<productInfor> listproductByName = new ArrayList<productInfor>();
//
//	@InitBinder
//	public void myInitBinder(WebDataBinder dataBinder) {
//		Object target = dataBinder.getTarget();
//		if (target == null) {
//			return;
//		}
//		if (target.getClass() == productForm.class) {
//
//			dataBinder.setValidator(proValidation);
//		}
//	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		return "index";
	}
	@RequestMapping(value = "/testable", method = RequestMethod.GET)
	public String testtable(Model model) {
		return "testable";
	}


	@RequestMapping(value = "/viewListProduct", method = RequestMethod.GET)
	public String viewListProduct(Model model) {
		List<productInfor> listprInfor = this.productService.listProduct();
		System.out.println("name sp:"+listprInfor.get(0).getNameProduct());
		model.addAttribute("listProduct", listprInfor);
		return "viewListProduct";
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public String viewaddProduct(Model model) {
		productForm prForm = new productForm();
		model.addAttribute("productForm", prForm);
		return "addProduct";
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String saveAddproduct(Model model, //
			@ModelAttribute("productForm")  productForm productForm, //
			BindingResult result, //
			final RedirectAttributes redirectAttributes) {
		proValidation.validate(productForm, result);
		if (result.hasErrors()) {
			return "addProduct";
		}
		this.productService.addProduct(productForm);
		return "redirect:/viewListProduct";
	}

	@RequestMapping(value = "/updateProduct", method = RequestMethod.GET)
	public String viewProduct(Model model, @RequestParam(value = "id") String id) {
		productInfor proInfor = this.productService.findbyId(id);
		productForm prForm = null;
		if (proInfor != null) {
			prForm = new productForm(proInfor);
			prForm.setNewProduct(true);
			model.addAttribute("proInfor", prForm);
		}
		return "updateProduct";
	}

	@RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
	public String updateProduct(@Validated @ModelAttribute productForm proInfor, BindingResult result,
			Model model) throws IOException {
		proValidation.validate(proInfor, result);
		if (result.hasErrors()) {
			return "updateProduct";
		}
		if (proInfor.getId() != null) {
			productInfor proInfor1 = this.productService.findbyId(proInfor.getId());
			if(proInfor!=null) {
			byte[] image=null;
			if(proInfor.getFileData().getOriginalFilename().equals("")) {
				image=proInfor1.getImage();
			}else {
				image=proInfor.getFileData().getBytes();
			}
			proInfor1.setId(proInfor.getId());
			proInfor1.setNameProduct(proInfor.getNameProduct());
			proInfor1.setPrice(proInfor.getPrice());
			proInfor1.setQuantity(Integer.parseInt(proInfor.getQuantity()));
			proInfor1.setImage(image);
			this.productService.update(proInfor1);
			}
		}
		return "redirect:/viewListProduct";
	}

	@RequestMapping(value = "/searchProduct", method = RequestMethod.GET)
	public String viewsearchProduct(Model model) {
		searchForm seForm = new searchForm();
		model.addAttribute("nameProduct", seForm);
		return "searchProduct";
	}

	@RequestMapping(value = "/searchProduct", method = RequestMethod.POST)
	public String SearchProductHandle(@ModelAttribute("nameProduct") searchForm searchForm, Model model) {
		listproductByName = this.productService.findProduct(searchForm.getNameProduct());
		return "redirect:/listSearchbyName";
	}

	@RequestMapping(value = "/listSearchbyName", method = RequestMethod.GET)
	public String viewSearchProductHandle(Model model) {
		model.addAttribute("listProduct", listproductByName);
		return "listSearchbyName";
	}

	@RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
	public String deleteProduct(@RequestParam(value = "id") String id, Model model) {
		productInfor proInfor = this.productService.findbyId(id);
		if (proInfor != null) {
			this.productService.deleteProduct(proInfor);
		}
		return "redirect:/viewListProduct";
	}

	@RequestMapping(value = "/imageProduct", method = RequestMethod.GET)
	public void imageProduct(Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id") String id) {
		productInfor pro = null;
		if (id != null) {
			pro = this.productService.findbyId(id);
		}

		if (pro != null && pro.getImage() != null) {
			response.setContentType("image/jpeg,image/jpg,image/png,image/gif");
			try {
				response.getOutputStream().write(pro.getImage());
				response.getOutputStream().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
