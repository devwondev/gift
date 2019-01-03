package com.gift.futurestrading.member.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.gift.futurestrading.member.vo.AccountCheckResultVo;
import com.gift.futurestrading.member.vo.SellerMypageVo;

@Mapper
public interface SellerMapper {
	
	/* 판매자 마이페이지 회원정보 수정처리를 위한 update */
	int updateSellerMypageInformation(SellerMypageVo sellerMypageVo);
	
	/* 회원수정 폼으로 이동하기위해 id, password가 일치하는지 알아보기위한 select */
	List<SellerMypageVo> selectIdPwForUpdate(HashMap<String, Object> sellerMypageIdPw);
	
	//id 유효성 확인
	int selectOneIdCheck(String id);
	//계좌 유효성 확인
	AccountCheckResultVo selectAccountCheck(HashMap<String, Object> map);
	//seller회원가입
	int insertSeller(HashMap<String, Object> map);
	//sellerFileUpload
	int insertSellerFile (HashMap<String, Object> map);
	//PK값 자동증가 코드시 최대값 가져오기
	int selectOneAutoMax(); 
}
