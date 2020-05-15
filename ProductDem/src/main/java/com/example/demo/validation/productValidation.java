package com.example.demo.validation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.demo.Form.productForm;
import com.example.demo.model.productInfor;
import com.example.demo.service.ProductService;

@Component
public class productValidation implements Validator {

	@Autowired
	ProductService proService;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz == productForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		productForm prForm = (productForm) target;
		
		productInfor prInfor = null;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "NotEmpty.productForm.id");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nameProduct", "NotEmpty.productForm.nameProduct");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty.productForm.price");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "NotEmpty.productForm.quantity");
		
		String id = prForm.getId();
		if (id != null && !id.equals("")) {
			if (prForm.isNewProduct() == false) {
				if (id.matches("\\S+") == false) {
					int count = 0;
					List<Integer> position = new ArrayList<Integer>();
					for (int i = 0; i < id.length(); i++) {
						if (String.valueOf(id.charAt(i)).equals(" ")==false) {//// \t\n\x0b\r\f
							
							count++;
							position.add(i);
							
						}
					}
					int sum1 = 0;
					int sum2 = 0;
					for (int i = 0; i < count; i++) {
						sum1 = sum1 + position.get(i);
						sum2 = sum2 + position.get(0) + i;
					}
					
					if (sum1 > sum2) {
						errors.rejectValue("id", "Pattern.productForm.id");
					} else {
						String idString = "";
						String[] splitStringid = id.split("\\s+");

						for (String idsplit : splitStringid) {
							idString = idString + idsplit;
						}

						prInfor = this.proService.findbyId(idString);
						if (prInfor != null) {
							errors.rejectValue("id", "Duplicate.productForm.id");
						}
					}
				}
			}

		}
		String price = prForm.getPrice();
		if (price != null && !price.equals("")) {

			if (NumberUtils.isNumber(price) == false) {

				errors.rejectValue("price", "NumberFormatException.productForm.price");
			} else {
				if (Double.parseDouble(price) < 0) {

					errors.rejectValue("price", "NumberFormatException.productForm.price");
				}
			}
		}
		String quantity = prForm.getQuantity();
		if (quantity != null && !quantity.equals("")) {

			if (NumberUtils.isNumber(quantity) == false) {

				errors.rejectValue("quantity", "NumberFormatException.productForm.quantity");
			} else {
				if (Integer.parseInt(quantity) < 0) {

					errors.rejectValue("quantity", "NumberFormatException.productForm.quantity");
				}
			}
		}
	}

}
