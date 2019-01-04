package com.gift.futurestrading.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gift.futurestrading.member.service.SellerService;
import com.gift.futurestrading.member.vo.SellerFileRequestVo;
import com.gift.futurestrading.member.vo.SellerMypageVo;
import com.gift.futurestrading.member.vo.SellerRequestVo;
/**
 * @author 정진우
 * @ClassName : SellerController
 * @Version : JDK1.8
 * @LatestUpdate : 2018-12-28
 * seller 가입 , 정보수정 , 상세파일 업로드 , 가입 시 필요한 ajax 요청등을 수행
 */

@Controller
public class SellerController {
	@Autowired
	private SellerService sellerService;
	
	/**
	 * 판매자 마이페이지에서 수정할 아이디와 비밀번호를 받아 해당 멤버의 비밀번호를 수정해주는 service계층의 메서드 호출
	 * @author 양진선
	 * @param sellerIdPk, sellerPassword, sellerChangePassword, session, model
	 * @return returnView
	 * @since JDK1.8
	 */
	@RequestMapping(value="/seller/mypage/password/modify", method=RequestMethod.POST)
	public String modifySellerPassword(@RequestParam("sellerIdPk") String sellerIdPk, @RequestParam("sellerPassword") String sellerPassword,  @RequestParam("sellerChangePassword") String sellerChangePassword, HttpSession session, Model model) {
		System.out.println("SellerController.modifySellerPassword() 호출");
		/* 화면에서 넘어온 파라미터를 해쉬맵에 저장 */
		HashMap<String, Object> sellerMypageChangePw = new HashMap<String, Object>();
		sellerMypageChangePw.put("sellerIdPk", sellerIdPk);
		sellerMypageChangePw.put("sellerPassword", sellerPassword);
		sellerMypageChangePw.put("sellerChangePassword", sellerChangePassword);
		
		/* 랜더링할 뷰의 주소를 저장할 변수 */
		String returnView = null;
		
		if(session.getAttribute("sessionLoginMember") != null) {		// session 존재할 때
			System.out.println("session 존재할 때");
			/* service 계층의 메서드 호출 후, 리턴값 출력 */
			sellerService.updateSellerPassword(sellerMypageChangePw);
			
			/* 랜더링 할 뷰로 모델 전달 */
			model.addAttribute("sessionLogin", session.getAttribute("sessionLoginMember"));		
			returnView = "index";
		} else {		// session 존재하지 않을 때
			System.out.println("session 존재하지 않을 때");
			returnView = "index";
		}
		return returnView;
	}
	
	/**
	 * 판매자 마이페이지에서 수정할 정보를 받아 해당 멤버의 개인정보를 수정해주는 service계층의 메서드 호출
	 * @author 양진선
	 * @param sellerMypageVo, session, model
	 * @return returnView
	 * @since JDK1.8
	 */
	@RequestMapping(value="/seller/mypage/information/modify", method=RequestMethod.POST)
	public String modifySellerInformation(SellerMypageVo sellerMypageVo, HttpSession session, Model model) {
		System.out.println("SellerController.modifySellerInformation() 호출");
		/* 랜더링할 뷰의 주소를 저장할 변수 */
		String returnView = null;
		
		if(session.getAttribute("sessionLoginMember") != null) {		// session 존재할 때
			System.out.println("session 존재할 때");
			/* service 계층의 메서드 호출 후, 리턴값 출력 */
			sellerService.updateSellerInformation(sellerMypageVo);
			
			/* 랜더링 할 뷰로 모델 전달 */
			model.addAttribute("sessionLogin", session.getAttribute("sessionLoginMember"));		
			returnView = "index";
		} else {		// session 존재하지 않을 때
			System.out.println("session 존재하지 않을 때");
			returnView = "index";
		}	
		return returnView;
	}

	/**
	 * 회원수정 폼으로 이동하기위해 사용자가 입력한 id, password가 일치하는지 알아보기위한 메서드를 호출한다.
	 * @author 양진선
	 * @param mypageId, mypagePw, session, model
	 * @return returnView
	 * @since JDK1.8
	 */
	@RequestMapping(value="/seller/mypage/information", method=RequestMethod.POST)
	public String getSellerMypageInformation(@RequestParam("mypageId") String mypageId, @RequestParam("mypagePw") String mypagePw, HttpSession session, Model model) {
		System.out.println("SellerController.getSellerMypageInformation() 호출");
		System.out.println(mypageId + " <---mypageId");
		/* 랜더링할 뷰의 주소를 저장할 변수 */
		String returnView = null;
		
		if(session.getAttribute("sessionLoginMember") != null) {		// session 존재할 때
			System.out.println("session 존재할 때");
			/* service 계층의 메서드 호출할 때 넘겨줄 파라미터 */
			HashMap<String, Object> sellerMypageIdPw = new HashMap<String, Object>();
			sellerMypageIdPw.put("mypagePw", mypagePw);
			sellerMypageIdPw.put("mypageId", mypageId);
			
			/* service 계층의 메서드 호출 후, 리턴값 출력 */
			List<SellerMypageVo> returnValue = sellerService.selectIdPwForUpdate(sellerMypageIdPw);
			System.out.println(returnValue + " <---returnValue");
			int countReturnValue = returnValue.size();
			
			/* returnValue에 따라 회원수정 폼으로 이동할 수 있냐, 없냐를 결정 */
			if(countReturnValue != 0) {
				System.out.println("화면에서 입력한 아이디, 비밀번호 일치");			
				/* 랜더링 할 뷰로 모델 전달 */
				model.addAttribute("sellerMypageInformation", returnValue.get(0));
				model.addAttribute("sessionLogin", session.getAttribute("sessionLoginMember"));
				returnView = "member/seller/getMemberSellerMypage";	
			} else {
				System.out.println("화면에서 입력한 아이디, 비밀번호 불일치");	
				model.addAttribute("sessionLogin", session.getAttribute("sessionLoginMember"));
				returnView = "member/seller/getMemberSellerMypage";
			}	
		} else {		// session 존재하지 않을 때
			System.out.println("session 존재하지 않을 때");
			returnView = "index";
		}		
		return returnView;
	}
	
	/**
	 * 해당 url로 요청이 들어왔을 때, 판매자 계정관리뷰로 랜더링 해준다.
	 * @author 양진선
	 * @param session, model
	 * @return returnView
	 * @since JDK1.8
	 */
	@RequestMapping(value="/seller/mypage", method=RequestMethod.GET)
	public String sellerMypage(HttpSession session, Model model) {
		System.out.println("SellerController.sellerMypage() 호출");
		String returnView = "";	// 랜더링할 뷰의 주소를 저장할 변수
		if(session.getAttribute("sessionLoginMember") != null) {	// session 존재할 때
			System.out.println("session 존재할 때");
			model.addAttribute("sessionLogin", session.getAttribute("sessionLoginMember"));	
			returnView = "member/seller/getMemberSellerMypage";			
		} else {
			System.out.println("session 존재하지 않을 때");		// session 존재하지 않을 때
			returnView = "index";
		}			
		return returnView;
	}

	/**
	 * @author 정진우
	 * addseller 요청
	 * 회원가입 초기 화면
	 * @param : 
	 * @return : member/seller/addMemberSeller
	 * 
	 */
	@RequestMapping(value="/signup/addseller", method = RequestMethod.GET)
	public String addSellerForm() {
		System.out.println("SellerController / addSellerForm() 호출");
		return "member/seller/addMemberSeller";
	}
	/**
	 * @author 정진우
	 * addrSeller 요청
	 * 고객(사용자)가 가입 정보 입력 후 회원 가입 요청 
	 * @param : sellerRequestVo
	 * @return : index
	 * 
	 */
	@RequestMapping(value = "/signup/addseller/re", method = RequestMethod.POST)
	public String addSeller(SellerRequestVo sellerRequestVo) {
		int insertResult = sellerService.insertSeller(sellerRequestVo);
		System.out.println(insertResult + " <---insertResult");
		return "index";
	}
	
	/**
	 * @author 정진우
	 * addSellerDetail 요청
	 * 판매자 정보 상세입력 화면 요처잇 렌더링
	 * @param : HttpSession , Model
	 * @return : member/seller/addMemberSellerDetail
	 * 
	 */
	@RequestMapping("/signup/addseller/detail")
	public String addSellerDetail(HttpSession session, Model model) {
		System.out.println("SellerController / addSellerDetail");
		model.addAttribute("sessionLogin", session.getAttribute("sessionLoginMember"));
		return "member/seller/addMemberSellerDetail";
	}
	
	/**
	 * @author 정진우
	 * addSellerDetail 요청
	 * action 및 서브스 호출
	 * @param : SellerFileRequestVo , HttpSession
	 * @return : member/seller/index
	 * 
	 */
	@RequestMapping("/signup/addseller/detail/re")
	public String addSellerDetailAction(SellerFileRequestVo sellerFileRequestVo , HttpSession session, Model model) throws IOException {
		System.out.println("SellerController/ addSellerDetailAction");
		model.addAttribute("sessionLogin", session.getAttribute("sessionLoginMember"));
		String path = session.getServletContext().getRealPath("\\upload\\");
		sellerService.sellerFileUpload(sellerFileRequestVo, path);
		return "index"; 
	} 
	
	/**
	 * @author 정진우
	 * sellerMypage 요청
	 * mypage를 요청했을때 세션값의 유무에따라 화면 결정
	 * @param : HttpSession , Model
	 * @return : member/seller/addMemberSeller
	 * 
	 */
	
/*	@RequestMapping(value="/seller/mypage", method=RequestMethod.GET)
	public String sellerMypage(HttpSession session, Model model) {
		String returnView = null;
		if(session.getAttribute("sessionLoginMember")==null) {
			returnView = "index";
		}else {
			returnView = "member/seller/getMemberSellerMypage";
		}
		model.addAttribute("sessionLogin", session.getAttribute("sessionLoginMember"));
		return returnView;
	}*/

	/**
	 * @author 정진우
	 * idCheck 요청
	 * ajax 비동기적 요청
	 * 데이터베이스 확인후 중복 여부 리턴
	 * @param : String
	 * @return : count
	 * 
	 */
	@RequestMapping(value = "/checkId", method = { RequestMethod.POST })
	@ResponseBody 
	public int checkId(@RequestBody String id) {
		System.out.println("SellerController / checkId");
		System.out.println(id + " <---id");

		String pattern = "^[a-zA-Z0-9]*$";
		String pattern2 = "^[0-9]*$";

		int count = 1; 
		// 문자 숫자
		boolean result = Pattern.matches(pattern, id);
		// 문자만
		boolean result2 = Pattern.matches(pattern2, id);
		// 숫자만
		/*
		 * count o 사용가능 count 1 중복됨 count 2 숫자만 count 3 특수문자가 있음
		 */

		// 특수문자 있는지 확인
		if (!result) {
			System.out.println("특수문자가 포함되어 있음");
			count = 3;
			// 숫자만 있는지 확인
		} else if (result2) {
			count = 2;
		} else {
			System.out.println("완벽함");
			/* id로 중복성 체크 메소드 호출 */ 
			count = sellerService.idCheck(id);
		} 
		return count;  
	}     
	/**
	 * @author 정진우
	 * sellerAccountCheck 요청
	 * ajax비동기적 요청 
	 * 데이터 베이스 확인 후  가능여부 리턴
	 * @param : HashMap<String , Object>
	 * @return : count
	 * 
	 */
	@RequestMapping(value = "/ajaxAccountCheck",method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public int sellerAccountCheck(@RequestBody HashMap<String , Object> ajaxValue) {
		System.out.println("SellerController / sellerAccountCheck");
		System.out.println(ajaxValue.get("sellerId") + " <---userId");     
		System.out.println(ajaxValue.get("sellerName") + " <---sellerName");
		System.out.println(ajaxValue.get("userAccountBank") + " <---userId");     
		System.out.println(ajaxValue.get("userAccountNo") + " <---sellerName");  
		int count = 1;
		count = sellerService.accountCheck(ajaxValue);
		return count; 
	}  
}
