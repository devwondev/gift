package com.gift.futurestrading.admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminChartMapper {
	/*월별 구매자 회원가입 현황 차트 구현위한 select*/
	//2019년
	List<Map<String, Object>> selectConsumerMonthChart(String year);
	//2018년
	List<Map<String, Object>> selectConsumerMonthChartBefore(String year);
	/*월별 판매자 회원가입 현황 차트 구현위한 select*/
	//2019년
	List<Map<String, Object>> selectSellerMonthChart(String year);
	//2018년
	List<Map<String, Object>> selectSellerMonthChartBefore(String year);
	/*월별 매수주문 차트 구현위한 select*/
	List<Map<String, Object>> selectOrderBuyMonthChart();
	/*월별 매도주문 차트 구현위한 select*/
	List<Map<String, Object>> selectOrderSellMonthChart();
}
