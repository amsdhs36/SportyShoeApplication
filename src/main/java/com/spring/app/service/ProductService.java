package com.spring.app.service;

import java.util.List;


import com.spring.app.dao.ProductDAO;

import com.spring.app.model.ProductDataModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDAO  productDao;
		
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ProductDataModel> getMensShoeData() {
		 
		 List<ProductDataModel> mensData= (List)productDao.findByCategory(1);
	       System.out.println(mensData);
	       return mensData;
	    }
	 
 @SuppressWarnings({ "unchecked", "rawtypes" })
public List<ProductDataModel> getWomensShoeData() {
		 
		 List<ProductDataModel> womensData= (List)productDao.findByCategory(2);
	       System.out.println(womensData);
	       return womensData;
	    }

 @SuppressWarnings({ "unchecked", "rawtypes" })
public List<ProductDataModel> getKidsShoeData() {
	 
	 List<ProductDataModel> kidsData= (List)productDao.findByCategory(3);
        
       return kidsData;
    }
 
@SuppressWarnings({ "unchecked", "rawtypes" })
public List<ProductDataModel> getShoeDatabyCategeory(int categeoryid) {
	 
	 List<ProductDataModel> shoesDatabyCategeory= (List)productDao.findByCategory(categeoryid);
        
       return shoesDatabyCategeory;
    }
 
 public ProductDataModel  getshoesDataByIdService(int seletedShoeId)
{
	 
	  ProductDataModel  selectedShoesData=  productDao.getshoesDataById(seletedShoeId);
       System.out.println(selectedShoesData);
       return selectedShoesData;
    }
 
 public boolean  insertNewProductService(int categeory,double price,String name,String imagelink)
 {
		boolean isInsertedNewProduct = false;
		try {
			
		
	 ProductDataModel s=new ProductDataModel(categeory,price, name, imagelink);
 	  @SuppressWarnings("unused")
	ProductDataModel count= productDao.save(s);
 	 isInsertedNewProduct = true;
         return isInsertedNewProduct;
		} catch (Exception e) {
			System.out.println("Exception at insertNewProductService(int categeory,double price,String name,String imagelink) " + e.getMessage());
			return false;
		}
     }
 
 
 
 public  boolean  deleteProductwithIdService(int id) {
		int count=0;
		boolean isdeleted=false;
		    count=  productDao.deleteProductwithId(id);
		     
	        if(count>0)
	        {
	        	isdeleted=true;
	        }
	       return isdeleted;
	    }
	
 
 public  boolean  updateShoeProductService( int categeory,   double price,
		   String name,  String imagelink,
		   int id) {
		int count=0;
		boolean isUpdated=false;
		    count=  productDao.updateShoeProduct(categeory, price, name, imagelink, id);
		     
	        if(count>0)
	        {
	        	isUpdated=true;
	        }
	       return isUpdated;
	    }
}
