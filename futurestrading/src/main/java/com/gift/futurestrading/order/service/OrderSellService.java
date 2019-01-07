package com.gift.futurestrading.order.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gift.futurestrading.member.vo.SellerVo;
import com.gift.futurestrading.order.mapper.OrderSellMapper;
import com.gift.futurestrading.order.vo.OrderSellVo;
import com.gift.futurestrading.page.vo.Criteria;

/**
 * @author 정진우
 * @ClassName : OrderSellService
 * @Version : JDK1.8
 * @LatestUpdate : 2018-12-28
 * 주문 등록 , 체결 목록 처리
 */

@Service
public class OrderSellService {
	@Autowired
	private OrderSellMapper orderSellMapper;
	
	/**
	 * @author 정진우
	 * addSellerOrder 요청 처리
	 * 데이터베이스 요회후 가능여부 리턴 (0 or 1)
	 * @param : String
	 * @return : int
	 */
	
	public int addSellerOrder(OrderSellVo orderSellVo) {
		System.out.println("sellerService.insertseller() 호출");
		/* PK값 자동 증가 코드 */
		int sellerMaxId = orderSellMapper.selectOneAutoMax();
		System.out.println(sellerMaxId+"sellerMaxId 호출");
		sellerMaxId += 1;
		String orderSellerPkLetter = "order_sell_"+sellerMaxId;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("order_sell_pk",orderSellerPkLetter);
		map.put("fk_seller__order_sell", orderSellVo.getFkSellerOrderSell());
		map.put("fk_item_detail__order_sell", orderSellVo.getFkItemDetailOrderSell());
		map.put("order_sell_seller_name", orderSellVo.getFkSellerOrderSell());
		map.put("order_sell_method", orderSellVo.getOrderSellMethod());
		map.put("order_sell_per_price", orderSellVo.getOrderSellPerPrice());
		map.put("order_sell_amount", orderSellVo.getOrderSellAmount());

		int insertResult = orderSellMapper.insertOrderSeller(map);

		return insertResult;
	}
	/** 판매자 리스트
	 * 판매자 리스트 select
	 * @param Criteria cri
	 * @return sellerList
	 * @since JDK1.8
	 */
	public List<OrderSellVo> getSellerOrderAll(Criteria cri , String id){
		System.out.println("AdminService.getSellerAll() 호출");
		HashMap<String , Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pageStart", cri.getPageStart());
		map.put("perPageNum", cri.getPerPageNum());
		List<OrderSellVo> sellerList = orderSellMapper.selectOrderSellAll(map);
		return sellerList;
	}
	/** 판매자 리스트
	 * 판매자의 전체 행 구하기위한 select
	 * 
	 * @return adminMapper.selectSellerAllCount()
	 * @since JDK1.8
	 */
	public int getSellerOrderAllCount(String id) {
		System.out.println("SampleService.selectSellerAllCount() 호출");
		return orderSellMapper.selectOrderSellAllCount(id);
	}
	/**
	 * 멤버별 매수 주문 리스트
	 * 주문페이지에 들어갈 매수주문 리스트
	 * @param String 
	 * @return 
	 * @since JDK1.8
	 */
	public List<OrderSellVo> getSellerOrderViewBottom(String id, Criteria cri) {
		System.out.println("OrderSellService.getOrderSellList() 호출");		
		HashMap<String , Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pageStart", cri.getPageStart());
		map.put("perPageNum", 5);
		List<OrderSellVo> sellerList = orderSellMapper.selectOrderSellAll(map);
		return sellerList;
	}
	/**
	 * 종가조회
	 * 품목에 따른 종가조회에 필요한 메서드
	 * @param map 
	 * @return 
	 * @since JDK1.8
	 */
	public int getClosePriceItemOne() {
		System.out.println("OrderSellService.getClosePriceItemOne() 호출");	
		int getClosingPriceResult = orderSellMapper.selectClosePriceItemOne();
		return getClosingPriceResult;
	}
	
}
