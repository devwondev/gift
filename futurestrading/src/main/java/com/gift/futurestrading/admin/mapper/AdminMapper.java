package com.gift.futurestrading.admin.mapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.gift.futurestrading.admin.vo.AdminVo;
import com.gift.futurestrading.member.vo.AccountConsumerVo;
import com.gift.futurestrading.member.vo.ConsumerVo;
import com.gift.futurestrading.member.vo.SellerFileVo;
import com.gift.futurestrading.member.vo.SellerVo;
import com.gift.futurestrading.page.vo.Criteria;
import com.gift.futurestrading.sign.vo.OrderBuyVo;
import com.gift.futurestrading.sign.vo.OrderSellVo;
@Mapper
public interface AdminMapper {
	/*수익관리 select에 셋팅되어있는 연도-1 데이터 조회 (ex.2017년)*/
	List<Map<String,Object>> selectProfitDetail2(String year);
	/*수익관리 select에 처음 셋팅되어있는 연도별 데이터 조회 (ex.2018년)*/
	List<Map<String,Object>> selectProfitDetail(String year);
	/*관리자 비밀번호 체크*/
	String selectAdminPassword(HashMap<String, Object> idAndPassword);
	/*관리자 리스트 전체행을  구함*/
	int selectAdminAllCount();
	/*관리자 리스트*/
	List<AdminVo> selectAdmin(Criteria cri);
	/*관리자 등록*/
	int insertAdmin(AdminVo adminVoRequest);
	/*관리자 한 명의 수정폼 불러오기*/
	AdminVo selectAdminOne(String adminId);
	/*관리자 수정*/
	int updateAdmin(AdminVo adminRequest);
	/*최고관리자 비밀번호 체크*/
	String selectTopAdminPassword(String topAdminPassword);
	/*관리자 삭제*/
	int deleteAdmin(String adminId);
	/*관리자 아이디 중복체크*/
	int selectInputAdminId(String inputAdminId);	
	/*구매자리스트(페이징포함)를 조회하기 위한 select*/
	List<ConsumerVo> selectConsumerAll(Criteria cri);
	/*구매자의 전체 행 구하기위한 select*/
	int selectConsumerAllCount();
	/*판매자리스트(페이징포함)를 조회하기 위한 select*/
	List<SellerVo> selectSellerAll(Criteria cri);
	/*판매자의 전체 행 구하기위한 select*/
	int selectSellerAllCount();
	/*구매자 계좌조회(한명)하기위한 select*/
	AccountConsumerVo selectConsumerAccountOne(ConsumerVo consumerVo);
	/*판매자 서류업로드조회(한명)하기위한 select*/
	SellerFileVo selectSellerDocumentOne(SellerVo sellerVo);
	/*판매자 서류업로드조회 후 승인하기위한 update*/
	int updateSellerRightCheck(HashMap<String, Object> map); 
	/*매수주문리스트 조회하기 위한 select*/
	List<OrderBuyVo> selectOrderBuyAll(Criteria cri);
	/*매수주문리스트 전체 행 구하기 위한 select*/
	int selectOrderBuyAllCount();
	/*매도주문리스트 조회하기 위한 select*/
	List<OrderSellVo> selectOrderSellAll(Criteria cri);
	/*매도주문리스트 전체 행 구하기 위한 select*/
	int selectOrderSellAllCount();
}