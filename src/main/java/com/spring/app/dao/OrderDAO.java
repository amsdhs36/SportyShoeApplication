package com.spring.app.dao;

import java.util.Date;
import java.util.List;

import com.spring.app.model.OrderedProductModel;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDAO extends CrudRepository<OrderedProductModel, Integer>{
		 	 
	 @Query("from OrderedProductModel where categeory=:categeoryId and date=:date")
	 public List<OrderedProductModel> getRequiredCompleteTransactionsData(int categeoryId,Date date);

}
