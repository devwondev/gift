package com.gift.futurestrading.admin.service;

import java.util.ArrayList;
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
	public List<List<Long>> getConsumerMonthChart() {
		System.out.println("AdminChartService.getConsumerMonthChart() 호출");
		List<Map<String, Object>> consumerChart = adminChartMapper.selectConsumerMonthChart();
		List<List<Long>> consumerList = new ArrayList<>();
		for (int i = 0; i < consumerChart.size(); i++) {
			List<Long> list = new ArrayList<>();
			long count = (long) consumerChart.get(i).get("count");
			list.add(0, count);
			consumerList.add(list);
		}
		return consumerList;
	}

	/**
	 * 판매자 차트
	 * 
	 * @return consumerList
	 * @since JDK1.8
	 */
	public List<List<Long>> getSellerMonthChart() {
		System.out.println("AdminChartService.getSellerMonthChart() 호출");
		List<Map<String, Object>> sellerChart = adminChartMapper.selectSellerMonthChart();
		List<List<Long>> sellerList = new ArrayList<>();
		for (int i = 0; i < sellerChart.size(); i++) {
			List<Long> list = new ArrayList<>();
			long count = (long) sellerChart.get(i).get("count");
			list.add(0, count);
			sellerList.add(list);
		}
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
