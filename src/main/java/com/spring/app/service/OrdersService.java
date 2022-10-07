package com.spring.app.service;

import java.util.Date;
import java.util.List;

import com.spring.app.dao.OrderDAO;
import com.spring.app.model.OrderedProductModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
	public class OrdersService {
		@Autowired
		private OrderDAO orderDao;
		
		 public void insertBookingInfoService(int shoeid, String name, int categeory,
				 double price, String imglink, int quantity, double totalprice,Date date )
		 {
			 OrderedProductModel  orderedProductModel =new  OrderedProductModel(shoeid, categeory, price, quantity, date, name, imglink, totalprice);
			 orderDao.save(orderedProductModel);
		 }

@SuppressWarnings({ "unchecked", "rawtypes" })
public List<OrderedProductModel> getCompleteTransactionsDataService() {
	 
	 List<OrderedProductModel> completeOrdersData= (List)orderDao.findAll();
     
      return completeOrdersData;
   }

@SuppressWarnings({ "unchecked", "rawtypes" })
public List<OrderedProductModel> getRequiredCompleteTransactionsDataService(int categeoryId,Date date)
{
List<OrderedProductModel> orderedShoeList= (List)orderDao.getRequiredCompleteTransactionsData(categeoryId, date);

return orderedShoeList;

}
}
 
