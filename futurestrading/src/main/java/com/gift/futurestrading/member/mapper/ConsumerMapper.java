package com.gift.futurestrading.member.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gift.futurestrading.member.vo.ConsumerMypageVo;
import com.gift.futurestrading.member.vo.ConsumerSignDetailVo;

@Mapper
public interface ConsumerMapper {
	/* 구매자 날짜별 체결내역 select*/
	List<ConsumerSignDetailVo> selectSignSearch(String startDate, String endDate, String getId);
	
	/* 구매자 체결내역 select(페이징)*/
	List<ConsumerSignDetailVo> selectSignList(HashMap<String, Object> map);
	
	/* 구매자 체결내역 전체행을  select*/
	int selectSignAllCount(String getId);
	
	/* 문자  + 숫자 조합의 primary key에서 숫자를 가져오기위한(기본키값 자동증가 구현을 위해) select */
	Integer selectPkOfAccountConsumer();
	
	/* 구매자 계좌등록을 위한 insert */
	Integer insertAccountOfConsumer(HashMap<String, Object> map);
	
	/* 구매자 계좌인증을 위한 select */
	String selectAccountOfConsumer(HashMap<String, Object> map);
	
	/* 구매자 회원가입을 위한 insert */
	int insertConsumer(HashMap<String, Object> map);
	
	/* 구매자 회원가입시 아이디 중복확인을 위한 select */
	int selectIdCheck(String id);
	
	/* 구매자 마이페이지 회원정보 수정폼을 위한 select */
	ConsumerMypageVo selectConsumerMypageInformation(Map<String, Object> ConsumerMypageIdPw);
	
	/* 구매자 마이페이지 회원정보 수정처리를 위한 update */
	int updateConsumerMypageInformation(ConsumerMypageVo consumerMypageVo);
	
	/* 구매자 마이페이지 비밀번호 수정처리를 위한 update*/
	int updateConsumerMypagePassword(Map<String, Object> ConsumerMypageChangePw);
}