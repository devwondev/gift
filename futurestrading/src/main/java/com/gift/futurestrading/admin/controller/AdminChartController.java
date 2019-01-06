package com.gift.futurestrading.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gift.futurestrading.admin.service.AdminChartService;

@Controller
@Transactional
public class AdminChartController {
	@Autowired
	private AdminChartService adminChartService;
	
	/**
	 * 구매자 차트
	 * 
	 * 의문점 :int타입으로 받으려했는데 안됨
	 * 
	 * @return consumerChart
	 * @since JDK1.8
	 */
	@RequestMapping(value="/admin/get/consumer/month/chart", method=RequestMethod.GET)
	@ResponseBody
	public List<List<Long>> getConsumerMonthChart(){
		System.out.println("AdminChartController.getConsumerMonthChart()호출");
		List<List<Long>> consumerChart = adminChartService.getConsumerMonthChart();
		return consumerChart;
	}
	
	/**
	 * 판매자 차트
	 * 
	 * @return sellerChart
	 * @since JDK1.8
	 */
	@RequestMapping(value="/admin/get/seller/month/chart", method=RequestMethod.GET)
	@ResponseBody
	public List<List<Long>> getConsumerSellerChart(){
		System.out.println("AdminChartController.getConsumerSellerChart()호출");
		List<List<Long>> sellerChart = adminChartService.getSellerMonthChart();
		return sellerChart;
	}
	
	/**
	 * 매수주문 차트
	 * 
	 * 
	 * @return orderBuyChart
	 * @since JDK1.8
	 */
	@RequestMapping(value="/admin/get/order/buy/month/chart", method=RequestMethod.GET)
	@ResponseBody
	public List<List<Long>> getOrderBuyMonthChart(){
		System.out.println("AdminChartController.getOrderBuyMonthChart()호출");
		List<List<Long>> orderBuyChart = adminChartService.getOrderBuyMonthChart();
		return orderBuyChart;
	}
	
	/**
	 * 매도주문 차트
	 * 
	 * 
	 * @return orderSellChart
	 * @since JDK1.8
	 */
	@RequestMapping(value="/admin/get/order/sell/month/chart", method=RequestMethod.GET)
	@ResponseBody
	public List<List<Long>> getOrderSellMonthChart(){
		System.out.println("AdminChartController.getOrderSellMonthChart()호출");
		List<List<Long>> orderSellChart = adminChartService.getOrderSellMonthChart();
		return orderSellChart;
	}
}
