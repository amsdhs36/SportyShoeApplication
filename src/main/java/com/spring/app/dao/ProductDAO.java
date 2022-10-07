package com.spring.app.dao;

import com.spring.app.model.ProductDataModel;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ProductDAO extends CrudRepository<ProductDataModel, Integer> {

	  @Query("from ProductDataModel where categeory=:categeory")
	    public Iterable<ProductDataModel> findByCategory(int categeory);
	  
	 
	  @Query("from ProductDataModel where id=:seletedShoeId")
	    public  ProductDataModel  getshoesDataById(int seletedShoeId);
	  
	  
	  @Modifying
		 @Transactional
		 @Query("delete from ProductDataModel where id=:id")
		 public int deleteProductwithId(@Param("id") int id);
	  @Modifying
		 @Transactional
		 @Query("update ProductDataModel   set categeory=:categeory, price=:price, name=:name, imagelink=:imagelink  where id=:id")
		 public int updateShoeProduct(@Param("categeory") int categeory, @Param("price") double price,
				 @Param("name") String name, @Param("imagelink") String imagelink,
				 @Param("id") int id);
		 
	  
}

