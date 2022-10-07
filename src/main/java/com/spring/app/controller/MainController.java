package com.spring.app.controller;

import java.sql.Date;

import java.util.List;

import com.spring.app.model.OrderedProductModel;
import com.spring.app.model.ProductDataModel;
import com.spring.app.model.UserInfoModel;
import com.spring.app.service.OrdersService;
import com.spring.app.service.ProductService;
import com.spring.app.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller

@RequestMapping("/")

public class MainController {
	@RequestMapping(value = "/home", method = RequestMethod.GET)

	public String homeForm() {
		return "index";
	}

	@Autowired
	private ProductService productservice;
	@Autowired
	private OrdersService orderservice;
	@Autowired
	private UserService userservice;

	@RequestMapping(value = "/menshoes", method = RequestMethod.GET)

	public String menShoesForm(ModelMap model) {
		List<ProductDataModel> mensData = productservice.getMensShoeData();
		model.put("menshoeData", mensData);

		return "menShoesForm";
	}

	@RequestMapping(value = "/womenshoes", method = RequestMethod.GET)
	public String womenMethod(ModelMap model) {
		List<ProductDataModel> womensData = productservice.getWomensShoeData();
		model.put("womenshoeData", womensData);

		return "womenShoesForm";
	}

	@RequestMapping("/kidshoes")
	public String kidMethod(ModelMap model) {

		List<ProductDataModel> kidsData = productservice.getKidsShoeData();
		model.put("kidshoeData", kidsData);

		return "kidsShoesForm";
	}

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String signinMethod() {
		return "signinForm";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String sigUpMethod() {
		return "signUpForm";
	}

	@RequestMapping("/selectedShoe")
	public String selectedShoeInfotMethod(ModelMap model, @RequestParam int id) {

		try {

			ProductDataModel selectedShoesData = productservice.getshoesDataByIdService(id);

			model.put("selectedShoesData", selectedShoesData);
			return "selectedShoeInfoForm";

		} catch (Exception e) {
			System.out.println(
					"Exception at com.spring.app.controllers.MainController.selectedShoeInfotMethod(HttpServletRequest, HttpServletResponse) "
							+ e.getMessage());
			return "selectedShoeInfoForm";
		}

	}

	@RequestMapping(value = "/orderinfo", method = RequestMethod.GET)

	public String orderInforMethod(ModelMap model, @RequestParam int id, @RequestParam String name,
			@RequestParam int categeory, @RequestParam double price, @RequestParam String imglink,
			@RequestParam int quantity) {

		try {

			OrderedProductModel orderedshoemodel = new OrderedProductModel();

			double totalPrice = price * quantity;

			orderedshoemodel.setShoeid(id);
			orderedshoemodel.setName(name);
			orderedshoemodel.setCategeory(categeory);
			orderedshoemodel.setPrice(price);
			orderedshoemodel.setImagelink(imglink);
			orderedshoemodel.setQuantity(quantity);
			orderedshoemodel.setTotalprice(totalPrice);

			model.put("orderedshoemodel", orderedshoemodel);

			return "bookproductForm";

		} catch (Exception e) {
			System.out.println(
					"Exception at com.spring.app.controllers.MainController.orderInforMethod(HttpServletRequest, HttpServletResponse) "
							+ e.getMessage());
			return "bookproductForm";
		}

	}

	@GetMapping("/bookandpaymentreturntoHome")
	// @RequestMapping(value = "/bookandpaymentreturntoHome", method =
	// RequestMethod.GET)
	public String bookAndPaymentreturntoHomeMethod(ModelMap model, @RequestParam(name = "id") int shoeid,
			@RequestParam String name, @RequestParam int categeory, @RequestParam double price,
			@RequestParam String imglink, @RequestParam int quantity, @RequestParam double totalprice) {

		try {

			long millis = System.currentTimeMillis();
			Date date = new java.sql.Date(millis);

			orderservice.insertBookingInfoService(shoeid, name, categeory, price, imglink, quantity, totalprice, date);

			return "orderplaceForm";
		} catch (Exception e) {
			System.out.println(
					"Exception at com.spring.app.controllers.MainController.bookAndPaymentreturntoHomeMethod(HttpServletRequest, HttpServletResponse) "
							+ e.getMessage());
			return "orderplaceForm";
		}
	}

	@GetMapping("/adminsignin")
	public String adminSigninMethod(ModelMap model, @RequestParam String username, @RequestParam String password) {

		int rollid = 0;
		try {

			UserInfoModel userInfoModel = userservice.isValidAdminUserService(username, password);

			if (userInfoModel == null) {
				model.put("errorMessage", "Invalid Credentials");
				return "signinForm";
			}
			rollid = userInfoModel.getRollid();

			if (rollid == 1) {
				List<ProductDataModel> mensData = productservice.getMensShoeData();

				model.put("men_women_kids_ShoeDataname", mensData);
				model.put("username", username.toUpperCase());
				model.put("password", password);
				return "adminForm";

			} else if (rollid == 2) {
				model.put("username", username);
				model.put("password", password);
				return "index";
			} else if (rollid == 0) {
				model.put("errorMessage", "Invalid Credentials");
				return "signinForm";

			}
			model.put("errorMessage", "Invalid Credentials");
			return "signinForm";
		} catch (Exception e) {
			System.out.println(
					"Exception at com.spring.app.controllers.MainController.signinMethod(HttpServletRequest, HttpServletResponse) "
							+ e.getMessage());
			model.put("errorMessage", "Exception check Log File");
			return "signinForm";
		}

	}

	@GetMapping("/getcompletemenshoesforAdmin")
	public String getcompletemenshoesforAdmin(ModelMap model) {

		try {
			List<ProductDataModel> mensData = productservice.getMensShoeData();

			model.put("men_women_kids_ShoeDataname", mensData);

			return "adminForm";

		} catch (Exception e) {
			System.out.println("Exception at com.spring.app.controllers.MainController.getcompletemenshoesforAdmin() "
					+ e.getMessage());
			return "adminForm";
		}

	}

	@GetMapping("/getcompletewomenshoesforAdmin")
	public String getcompletewomenshoesforAdmin(ModelMap model) {

		try {
			List<ProductDataModel> womensData = productservice.getWomensShoeData();

			model.put("men_women_kids_ShoeDataname", womensData);

			return "adminForm";

		} catch (Exception e) {
			System.out.println("Exception at com.spring.app.controllers.MainController.getcompletewomenshoesforAdmin() "
					+ e.getMessage());
			return "adminForm";
		}

	}

	@GetMapping("/getcompletekidshoesforAdmin")
	public String getcompletekidshoesforAdmin(ModelMap model) {

		try {
			List<ProductDataModel> kidsData = productservice.getKidsShoeData();

			model.put("men_women_kids_ShoeDataname", kidsData);

			return "adminForm";

		} catch (Exception e) {
			System.out.println("Exception at com.spring.app.controllers.MainController.getcompletekidshoesforAdmin() "
					+ e.getMessage());
			return "adminForm";
		}
	}

	@RequestMapping(value = "/changeadminpassword", method = RequestMethod.GET)

	public String changeAdminPasswordForm() {
		return "changeAdminPasswordForm";
	}

	@RequestMapping(value = "/addNewProductForm", method = RequestMethod.GET)

	public String addNewProductForm() {
		return "addNewProductForm";
	}

	@GetMapping("/customersList")
	public String customersListMethod(ModelMap model) {
		List<UserInfoModel> usersList = null;

		try {

			usersList = userservice.getUserDataService();

			if (!usersList.isEmpty()) {

				model.put("usersList", usersList);
			} else {
				System.out.println("No Users Found...");
			}
			return "customersListForm";

		} catch (Exception e) {
			System.out.println(
					"Exception at com.spring.app.controller.MainController.customersListMethod(HttpServletRequest, HttpServletResponse) "
							+ e.getMessage());
			return "customersListForm";
		}

	}

	@GetMapping("/reports")
	public String reportsMethod(ModelMap model) {

		List<OrderedProductModel> orderedShoeList = null;
		try {

			orderedShoeList = orderservice.getCompleteTransactionsDataService();

			if (orderedShoeList != null) {

				double totalSales = 0;
				for (OrderedProductModel osl : orderedShoeList) {
					totalSales = totalSales + osl.getTotalprice();
				}

				model.put("orderedShoeList", orderedShoeList);
				model.put("totalSales", totalSales);

			}
			return "reportsForm";
		} catch (Exception e) {
			System.out.println("Exception at com.spring.app.controller.MainController.reportsMethod() " + e.getMessage());
			return "reportsForm";
		}
	}

	@GetMapping("/changeadminPassword")
	public String changeadminPasswordMethod(ModelMap model, @RequestParam String currentpassword,
			@RequestParam String newpassword, @RequestParam String confirmpassword) {

		UserInfoModel userInfoModel = null;
		@SuppressWarnings("unused")
		boolean isCorrectPassword = false;
		try {

			userInfoModel = userservice.isCorrectPassword_or_NotService(currentpassword);
			if (userInfoModel.getRollid() != 0) {
				boolean isUpdatedAdminPassword = userservice.updateAdminPasswordService(userInfoModel, currentpassword,
						newpassword, confirmpassword);
				if (isUpdatedAdminPassword == true) {
					System.out.println("Password Updated");
					return "signinForm";
				} else {
					System.out.println("Password Not Updated");
				}
			}

			else {
				System.out.println("No Data Found");
			}

			return "changeAdminPasswordForm";

		} catch (Exception e) {
			System.out.println(
					"Exception at com.spring.app.controllers.MainController.changeadminPasswordMethod(HttpServletRequest, HttpServletResponse) "
							+ e.getMessage());
			return "changeAdminPasswordForm";
		}

	}

	@GetMapping("/requiredreports")
	public String requiredReportsMethod(ModelMap model, @RequestParam String categeory, @RequestParam String date) {

		List<OrderedProductModel> orderedShoeList = null;
		try {

			int categeoryId = 0;
			String categeoryName = categeory;
			if (categeoryName.equals("mens"))
				categeoryId = 1;
			else if (categeoryName.equals("womens"))
				categeoryId = 2;
			else if (categeoryName.equals("kids"))
				categeoryId = 3;
			String jspdate = date;

			Date sqldate = Date.valueOf(jspdate);

			if (categeoryId != 0) {
				orderedShoeList = orderservice.getRequiredCompleteTransactionsDataService(categeoryId, sqldate);
				if (orderedShoeList != null) {
					double totalSales = 0;
					for (OrderedProductModel osl : orderedShoeList) {
						totalSales = totalSales + osl.getTotalprice();
					}

					model.put("orderedShoeList", orderedShoeList);
					model.put("totalSales", totalSales);
					return "reportsForm";
				}
			}
			return "reportsForm";
		} catch (Exception e) {
			System.out.println(
					"Exception at com.spring.app.controllers.MainController.requiredReportsMethod() " + e.getMessage());
			return "reportsForm";
		}
	}

	@RequestMapping("/addnewproduct")
	public String addNewProductMethod(ModelMap model, @RequestParam String weartype, @RequestParam String prod_name,
			@RequestParam double prod_price, @RequestParam String prod_img) {

		@SuppressWarnings("unused")
		ModelAndView mv = null;
		try {

			int categeroy = 0;
			if (weartype.equals("Mens_Wear")) {
				categeroy = 1;
			} else if (weartype.equals("Womens_Wear")) {
				categeroy = 2;
			} else if (weartype.equals("Kids_Wear")) {
				categeroy = 3;
			}

			boolean isInsertedNewProduct = false;
			isInsertedNewProduct = productservice.insertNewProductService(categeroy, prod_price, prod_name, prod_img);
			if (isInsertedNewProduct == true) {
				if (categeroy == 1) {
					List<ProductDataModel> mensData = productservice.getMensShoeData();

					model.put("men_women_kids_ShoeDataname", mensData);
					return "adminForm";
				}

				if (categeroy == 2) {
					List<ProductDataModel> womensData = productservice.getWomensShoeData();

					model.put("men_women_kids_ShoeDataname", womensData);
					return "adminForm";
				}
				if (categeroy == 3) {
					List<ProductDataModel> kidsData = productservice.getKidsShoeData();

					model.put("men_women_kids_ShoeDataname", kidsData);
					return "adminForm";
				}

			} else {
				System.out.println("Product Not Added");
			}
			return "adminForm";

		} catch (Exception e) {
			System.out.println(
					"Exception at com.spring.app.MainController.addNewProducttMethod(HttpServletRequest, HttpServletResponse) "
							+ e.getMessage());
			return "adminForm";
		}

	}

	@GetMapping("/signupnewuser")
	public String sigUpNewUserMethod(ModelMap model, @RequestParam String firstname, @RequestParam String lastname,
			@RequestParam String password, @RequestParam String confirmpassword, @RequestParam String roll,
			@RequestParam Long mobileno, @RequestParam String email) {
		@SuppressWarnings("unused")
		ModelAndView mv = null;
		boolean isInserted = false;
		try {
			int rollid = 0;

			if (roll.equals("admin")) {
				rollid = 1;
			} else {
				rollid = 2;
			}

			isInserted = true;
			userservice.insertUserDataService(firstname, lastname, password, confirmpassword, rollid, mobileno, email);
			if (isInserted == true) {

				return "signinForm";
			}

			if (isInserted == false) {

				return "testErrorForm";
			}
			return "testErrorForm";

		} catch (Exception e) {
			System.out.println(
					"Exception at com.spring.app.controllers.MainController.sigUpNewUserMethod(HttpServletRequest, HttpServletResponse) "
							+ e.getMessage());
			return "testErrorForm";
		}

	}

	@GetMapping("/deleteProduct")
	public String deleteProductMethod(ModelMap model, @RequestParam int id) {

		boolean isdeleted = false;
		List<ProductDataModel> shoesDatalist = null;
		ProductDataModel findwhichCategeory = null;
		try {

			findwhichCategeory = productservice.getshoesDataByIdService(id);
			int categeory = findwhichCategeory.getCategeory();
			isdeleted = productservice.deleteProductwithIdService(id);
			if (isdeleted == true) {
				shoesDatalist = productservice.getShoeDatabyCategeory(categeory);

				model.put("men_women_kids_ShoeDataname", shoesDatalist);

			}

			return "adminForm";
		} catch (Exception e) {
			System.out.println(
					"Exception at com.spring.app.controllers.MainController.deleteProductMethod() " + e.getMessage());
			return "adminForm";
		}
	}

	@GetMapping("/updateProduct")
	public String updateProductMethod(ModelMap model, @RequestParam int id) {

		@SuppressWarnings("unused")
		boolean isupdated = false;
		ProductDataModel shoesData = null;
		// ShoesDataModel findwhichCategeory = null;
		try {

			shoesData = productservice.getshoesDataByIdService(id);

			if (shoesData.getId() > 0) {
				model.put("men_women_kids_ShoeDataname", shoesData);
				return "updateProductForm";
			}
			return "adminForm";
		} catch (Exception e) {
			System.out.println(
					"Exception at com.spring.app.controllers.MainController.deleteProductMethod() " + e.getMessage());
			return "adminForm";
		}
	}

	@GetMapping("/updatetoNewproduct")
	public String updateProductMethod(ModelMap model, @RequestParam String weartype, @RequestParam String prod_name,
			@RequestParam double prod_price, @RequestParam String prod_img, @RequestParam int prod_id) {

		boolean isupdated = false;
		@SuppressWarnings("unused")
		ProductDataModel shoesData = null;
		List<ProductDataModel> shoesDataList = null;
		// ShoesDataModel findwhichCategeory = null;
		try {
			int categeory = 0;
			if (weartype.equals("Mens_Wear")) {
				categeory = 1;
			} else if (weartype.equals("Womens_Wear")) {
				categeory = 2;
			} else if (weartype.equals("Kids_Wear")) {
				categeory = 3;
			}
			isupdated = productservice.updateShoeProductService(categeory, prod_price, prod_name, prod_img, prod_id);
			if (isupdated == true) {
				shoesDataList = productservice.getShoeDatabyCategeory(categeory);

				model.put("men_women_kids_ShoeDataname", shoesDataList);

			}

			return "adminForm";

		} catch (Exception e) {
			System.out.println(
					"Exception at com.spring.app.controllers.MainController.deleteProductMethod() " + e.getMessage());
			return "adminForm";
		}
	}
}
