package com.gift.futurestrading.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gift.futurestrading.admin.mapper.AdminChartMapper;

@Service
public class AdminChartService {
	@Autowired
	private AdminChartMapper adminChartMapper;

	/**
	 * 구매자 차트
	 * 
	 * 의문점 :int타입으로 받으려했는데 안됨
	 * 
	 * @return consumerList
	 * @since JDK1.8
	 */
	public Map<Object, List<Long>> getConsumerMonthChart(String year) {
		System.out.println("AdminChartService.getConsumerMonthChart() 호출");
		/*2019년도*/
		// count를 int타입으로, List를 Integer타입으로 묶으려 했으나 오류나서 Long타입으로 함
		List<Map<String, Object>> consumerChart = adminChartMapper.selectConsumerMonthChart(year);
		List<Long> list = new ArrayList<>();
		
		for (int i = 0; i < consumerChart.size(); i++) {
			long count = (long) consumerChart.get(i).get("count");			
			list.add(i, count);
		}
		/*2018년도*/
		List<Map<String, Object>> consumerChartBefore = adminChartMapper.selectConsumerMonthChartBefore(year);
		List<Long> listBefore = new ArrayList<>();
		for(int i = 0; i< consumerChartBefore.size(); i++) {
			long count = (long) consumerChartBefore.get(i).get("count");
			listBefore.add(i, count);
		}
		Map<Object, List<Long>> consumerList = new HashMap<>();
		
		/*2019년도에 관한 데이터 put*/
		consumerList.put("year", list);
		/*2018년도에 관한 데이터 put*/
		consumerList.put("yearBefore", listBefore);
 		return consumerList;
	}
	
	/**
	 * 판매자 차트
	 * 
	 * @return consumerList
	 * @since JDK1.8
	 */
	public Map<Object, List<Long>> getSellerMonthChart(String year) {
		System.out.println("AdminChartService.getSellerMonthChart() 호출");
		/*2019년도*/
		// count를 int타입으로, List를 Integer타입으로 묶으려 했으나 오류나서 Long타입으로 함
		List<Map<String, Object>> sellerChart = adminChartMapper.selectSellerMonthChart(year);
		List<Long> list = new ArrayList<>();
		
		for (int i = 0; i < sellerChart.size(); i++) {
			long count = (long) sellerChart.get(i).get("count");			
			list.add(i, count);
		}
		/*2018년도*/
		List<Map<String, Object>> sellerChartBefore = adminChartMapper.selectSellerMonthChartBefore(year);
		List<Long> listBefore = new ArrayList<>();
		for(int i = 0; i< sellerChartBefore.size(); i++) {
			long count = (long) sellerChartBefore.get(i).get("count");
			listBefore.add(i, count);
		}
		Map<Object, List<Long>> sellerList = new HashMap<>();
		
		/*2019년도에 관한 데이터 put*/
		sellerList.put("year", list);
		/*2018년도에 관한 데이터 put*/
		sellerList.put("yearBefore", listBefore);
 		return sellerList;
	}

	/**
	 * 매수주문 차트
	 * 
	 * @return orderBuyList
	 * @since JDK1.8
	 */
	public List<List<Long>> getOrderBuyMonthChart() {
		System.out.println("AdminChartService.getOrderBuyMonthChart() 호출");

		List<Map<String, Object>> orderChart = adminChartMapper.selectOrderBuyMonthChart();
		List<List<Long>> orderBuyList = new ArrayList<>();
		for (int i = 0; i < orderChart.size(); i++) {
			List<Long> list = new ArrayList<>();
			long count = (long) orderChart.get(i).get("count");
			list.add(0, count);
			orderBuyList.add(list);
		}
		return orderBuyList;
	}

	/**
	 * 매도주문 차트
	 * 
	 * 
	 * @return orderSellList
	 * @since JDK1.8
	 */
	public List<List<Long>> getOrderSellMonthChart() {
		System.out.println("AdminChartService.getOrderSellMonthChart() 호출");

		List<Map<String, Object>> orderChart = adminChartMapper.selectOrderSellMonthChart();
		List<List<Long>> orderSellList = new ArrayList<>();
		for (int i = 0; i < orderChart.size(); i++) {
			List<Long> list = new ArrayList<>();
			long count = (long) orderChart.get(i).get("count");
			list.add(0, count);
			orderSellList.add(list);
		}
		return orderSellList;
	}
}
