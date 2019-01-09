package com.gift.futurestrading.member.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gift.futurestrading.member.mapper.ConsumerMapper;
import com.gift.futurestrading.member.vo.AccountOfConsumerRequestVo;
import com.gift.futurestrading.member.vo.ConsumerMypageVo;
import com.gift.futurestrading.member.vo.ConsumerRequestVo;
import com.gift.futurestrading.member.vo.ConsumerSignDetailVo;
import com.gift.futurestrading.member.vo.InformationOfConsumerAccountVo;
import com.gift.futurestrading.page.vo.Criteria;

@Service
public class ConsumerService {
	@Autowired
	private ConsumerMapper consumerMapper;
	
	/**
	 * 계좌번호 정보를 조회하기위한 mapper 계층의 메서드 호출
	 * 
	 * @param consumerId
	 * @return resultValue
	 * @since JDK1.8
	 */
	public List<InformationOfConsumerAccountVo> getInformationOfAccount(String consumerId) {
		System.out.println("ConsumerService.getInformationOfAccount() 호출");
		/* 계좌번호 정보를 조회하기위한 mapper 계층의 메서드 호출 */
		List<InformationOfConsumerAccountVo> resultValue = new ArrayList<>();
		resultValue = consumerMapper.selectInformationOfAccount(consumerId);
		System.out.println(resultValue + " <---resultValue");
		
		return resultValue;
	}
	
	/**
	 * 구매자 체결내역 검색 결과 전체 행을 구하는 메서드 호출
	 * 
	 * @param getId
	 * @return totalCount
	 * @since JDK1.8
	 */
	public int getSignSearchCount(HashMap<String, Object> map) {
		System.out.println("ConsumerService.getSignSearchCount() 호출");
		int totalCount = consumerMapper.selectSearchCount(map);
		return totalCount;
	}
	
	/**
	 * 검색을 이용해서 날짜별 체결내역을 불러오는 메서드 호출
	 * 
	 * @param startDate, endDate, getId
	 * @return searchListVo
	 * @since JDK1.8
	 */
	public List<ConsumerSignDetailVo> getSearchResult(Criteria cri, String startDate, String endDate, String getId) {
		System.out.println("ConsumerService.getSearchResult() 호출");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pageStart", cri.getPageStart());
		map.put("perPageNum", cri.getPerPageNum());
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("getId", getId);
		List<ConsumerSignDetailVo> searchListVo = consumerMapper.selectSignSearch(map);
		return searchListVo;
	}
	
	/**
	 * 로그인 상태의 아이디로 자신의 체결내역을 불러오는 메서드 호출
	 * 
	 * @param getId
	 * @return consumerSignDetailVo
	 * @since JDK1.8
	 */
	public List<ConsumerSignDetailVo> getSignDetail(Criteria cri, String getId) {
		System.out.println("ConsumerService.getSignDetail() 호출");
		HashMap<String , Object> map = new HashMap<String, Object>();
		map.put("getId", getId);
		map.put("pageStart", cri.getPageStart());
		map.put("perPageNum", cri.getPerPageNum());
		List<ConsumerSignDetailVo> consumerSignList = consumerMapper.selectSignList(map);
		return consumerSignList;
	}
	
	/**
	 * 구매자 체결내역 전체 행을 구하는 메서드 호출
	 * 
	 * @param getId
	 * @return totalCount
	 * @since JDK1.8
	 */
	public int getSignAllCount(String getId) {
		System.out.println("SampleService.getSignAllCount() 호출");
		int totalCount = consumerMapper.selectSignAllCount(getId);
		return totalCount;
	}
	
	/**
	 * 문자열 + 숫자 조합의 기본키 컬럼값 자동증가 구현 및 구매자 계좌등록 메서드 호출
	 * 
	 * @param accountOfConsumerRequest
	 * @return result
	 * @since JDK1.8
	 */
	public int insertAccountOfConsumer(AccountOfConsumerRequestVo accountOfConsumerRequest) {
		System.out.println("ConsumerService.insertAccountOfConsumer() 호출");
		System.out.println(accountOfConsumerRequest.getFkConsumerAccountConsumer()+" <---fkConsumerAccountConsumer");
		System.out.println(accountOfConsumerRequest.getAccountConsumerAccountNo()+" <---accountNo");
		
		Integer pkNoValue;			// 기본키 중 숫자
		String pkStringValue;	// 기본키 중 문자열
		String accountNoPk;		// 문자열  + 숫자
		HashMap<String, Object> map = new HashMap<String, Object>();	// 맵퍼에 전달해줄 해쉬맵
		
		/* 1. account_consumer 테이블의 기본키 컬럼값 자동증가 구현 */
		pkStringValue = "account_no_";
		
		/* Transaction① : 최대값 구하는 메서드 호출 후, 변수에 저장 */
		pkNoValue = (int)consumerMapper.selectPkOfAccountConsumer();
		pkNoValue++;	// 최대값 + 1
		accountNoPk = pkStringValue + pkNoValue;
		System.out.println(pkNoValue + " <---pkNoValue");
		System.out.println(accountNoPk + " <---accountNoPk");
		
		/* 맵에 파라미터 데이터들 저장 */
		map.put("accountNoPk", accountNoPk);
		map.put("fkConsumerAccountConsumer", accountOfConsumerRequest.getFkConsumerAccountConsumer());
		map.put("accountConsumerBankName", accountOfConsumerRequest.getAccountConsumerBankName());
		map.put("accountConsumerName", accountOfConsumerRequest.getAccountConsumerName());
		map.put("accountConsumerAccountNo", accountOfConsumerRequest.getAccountConsumerAccountNo());
		map.put("accountConsumerBankName", accountOfConsumerRequest.getAccountConsumerBankName());
		
		/* 2. Transaction② : 계좌등록을 위해 메서드 호출 */
		int result = (int)consumerMapper.insertAccountOfConsumer(map);
		
		return result;
	}

	/**
	 * 구매자가 입력한 계좌번호가 유효한지 알려주는 메서드
	 * 
	 * @param map
	 * @return selectResult
	 */
	public String selectAccountOfConsumer(HashMap<String, Object> map) {
		System.out.println("ConsumerService.selectAccountOfConsumer() 호출");
		System.out.println(map.get("consumerName")+" <---consumerName");
		System.out.println(map.get("bankName")+" <---bankName");
		System.out.println(map.get("consumerAccount")+" <---consumerAccount");
		
		String selectResult = consumerMapper.selectAccountOfConsumer(map);
		System.out.println(selectResult+" <--selectResult");
		
		return selectResult;
	}
	
	/**
	 * modifyConsumerPassword 컨트롤러로부터 넘겨받은 데이터를 입력값으로 해당 멤버의 비밀번호를 수정
	 * 
	 * @param consumerRequestVo
	 * @return 실행확인을 위해 리턴값 int로 사용
	 * @since JDK1.8
	 */
	public int updateConsumerPassword(Map<String, Object> ConsumerMypageChangePw) {
		System.out.println("ConsumerService.updateConsumerPassword() 호출");
		consumerMapper.updateConsumerMypagePassword(ConsumerMypageChangePw);
		System.out.println("consumerChangePassword : "+ConsumerMypageChangePw.get("consumerChangePassword"));
		return 0;
	}
	
	/**
	 * modifyConsumrInformation 컨트롤러로부터 넘겨받은 데이터를 입력값으로 해당 멤버의 개인정보를 수정
	 * 
	 * @param consumerMypageVo
	 * @return 실행확인을 위해 리턴값 int로 사용
	 * @since JDK1.8
	 */
	public int updateConsumerInformation(ConsumerMypageVo consumerMypageVo) {
		System.out.println("ConsumerService.updateConsumerInformation() 호출");
		consumerMapper.updateConsumerMypageInformation(consumerMypageVo);
		return 0;
	}
	/**
	 * getconsumerMypageInformation 컨트롤러로부터 넘겨받은 데이터를 입력값으로 해당 멤버의 개인정보를 넘김
	 * 
	 * @param ConsumerMypageIdPw
	 * @return consumerMypageVo
	 * @since JDK1.8
	 */
	public ConsumerMypageVo selectConsumerMypageInformation(Map<String, Object> ConsumerMypageIdPw) {
		System.out.println("ConsumerService.selectConsumerMypageInformation() 호출");
		System.out.println("서비스 아이디 :: "+"ConsumerMypageIdPw : "+ConsumerMypageIdPw.get("mypageId"));
		System.out.println("서비스 비번 :: "+"ConsumerMypageIdPw : "+ConsumerMypageIdPw.get("mypagePassword"));
		ConsumerMypageVo consumerMypageVo = consumerMapper.selectConsumerMypageInformation(ConsumerMypageIdPw);
		
		System.out.println("ConsumerMypageId : "+consumerMypageVo.getConsumerIdPk());
		return consumerMypageVo;
	}
	
	/**
	 * 컨트롤러로부터 넘겨받은 데이터를 가공하여 SQL 메서드를 호출할 때 param으로 넘김
	 * 
	 * @param consumerRequestVo
	 * @return
	 * @since JDK1.8
	 */
	public int insertConsumer(ConsumerRequestVo consumerRequestVo) {
		System.out.println("ConsumerService.insertConsumer() 호출");
		/* postcode, rodeAddress, detailAddress 데이터 getting */
		String postCode = consumerRequestVo.getPostcode();
		String rodeAddress = consumerRequestVo.getRoadAddress();
		String detailAddress = consumerRequestVo.getDetailAddress();
		
		/* 해쉬맵에 구매자 정보를 put하여 맵퍼 계층의 메서드를 호출할 때 param으로 넘김 */
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("consumer_id_pk", consumerRequestVo.getConsumerIdPk());
		map.put("consumer_password", consumerRequestVo.getConsumerPassword());
		map.put("consumer_name", consumerRequestVo.getConsumerName());
		map.put("consumer_email", consumerRequestVo.getConsumerEmail());
		map.put("consumer_jumin_no", consumerRequestVo.getConsumerJuminNo());
		map.put("consumer_address_post_code", consumerRequestVo.getPostcode());
		map.put("consumer_address_road", consumerRequestVo.getRoadAddress());
		map.put("consumer_address_detail", consumerRequestVo.getDetailAddress());
		map.put("consumer_phone", consumerRequestVo.getConsumerPhone());
		
		/* 맵퍼 계층의 insertConsumer 메서드 호출 */
		int insertResult = consumerMapper.insertConsumer(map);

		return insertResult;
	}
	
	/**
	 * 컨트롤러로부터 넘어온 아이디와 DB에 저장된 아이디를 비교하여 중복값을 체크
	 * 
	 * @param id
	 * @return
	 * @since JDK1.8
	 */
	public int idCheck(String id) {
		System.out.println("ConsumerService.idCheck() 호출");
		int selectResult = 0;
		
		/* 맵퍼 계층의 selectIdCheck 메서드 호출 */
		selectResult = consumerMapper.selectIdCheck(id);
		System.out.println(selectResult+" <---selectResult");
			
		return selectResult;
	}

}