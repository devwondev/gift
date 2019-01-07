package com.gift.futurestrading.order.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gift.futurestrading.member.vo.SellerVo;
import com.gift.futurestrading.order.vo.OrderSellVo;
import com.gift.futurestrading.page.vo.Criteria;

/**
 * @author 정진우
 * @ClassName : OrderSellMapper
 * @Version : JDK1.8
 * @LatestUpdate : 2018-12-28
 */

@Mapper
public interface OrderSellMapper {
	// 주문 INSERT
	int insertOrderSeller(HashMap<String, Object> map);
	// PK자동증가 코드를 위해 최대값을 가져오는 쿼리
	int selectOneAutoMax(); 
	/*판매자리스트(페이징포함)를 조회하기 위한 select*/
	List<OrderSellVo> selectOrderSellAll(HashMap<String, Object> map);
	/*판매자의 전체 행 구하기위한 select*/
	int selectOrderSellAllCount(String id);
	int selectClosePriceItemOne();
}
