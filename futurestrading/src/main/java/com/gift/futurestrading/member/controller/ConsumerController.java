// 애플리케이션 클래스
package com.gift.futurestrading.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gift.futurestrading.member.service.ConsumerService;
import com.gift.futurestrading.member.vo.AccountOfConsumerRequestVo;
import com.gift.futurestrading.member.vo.ConsumerMypageVo;
import com.gift.futurestrading.member.vo.ConsumerRequestVo;
import com.gift.futurestrading.member.vo.ConsumerSignDetailVo;
import com.gift.futurestrading.page.vo.Criteria;
import com.gift.futurestrading.page.vo.PageMaker;

@Controller
public class ConsumerController {
	@Autowired
	private ConsumerService consumerService;
	
	@RequestMapping(value="/consumer/mywallet/property", method=RequestMethod.GET)
	public String ConsumerMyWalletProperty(HttpSession session, Model model) {
		System.out.println("ConsumerController.ConsumerMyWlletProperty() 호출");
		model.addAttribute("sessionLogin", session.getAttribute("sessionLoginMember"));
		return "member/consumer/getMemberConsumerWalletProperty";
	}
	
	/**
	 *  해당 url로 요청이 들어왔을 때(ajax), 날짜별 데이터를 보낸다.
	 * 
	 * @param session
	 * @param model, searchDate
	 * @return searchListVo
	 * @since JDK1.8
	 */
	@RequestMapping(value="/consumer/get/search/list", method=RequestMethod.POST)
	@ResponseBody
	public List<ConsumerSignDetailVo> getSearchList(HttpSession session, Model model, @RequestBody HashMap<String, Object> searchDate) {
		System.out.println("ConsumerController.getSearchResult() 호출");
		model.addAttribute("sessionLogin", session.getAttribute("sessionLoginMember"));
		String getId = (String)session.getAttribute("sessionLoginId");
		String startDate = (String) searchDate.get("startDate");
		String endDate = (String) searchDate.get("endDate");
		System.out.println(getId + "<-- getId");
		System.out.println(startDate + "<-- startDate");
		System.out.println(endDate + "<-- endDate");
		List<ConsumerSignDetailVo> searchListVo = consumerService.getSearchResult(startDate, endDate, getId);
		return searchListVo;
	}
	
	/**
	 *  해당 url로 요청이 들어왔을 때, 구매자 체결내역 뷰로 랜더링 해준다.
	 * 
	 * @param session
	 * @param model
	 * @return member/consumer/getSignDetail
	 * @since JDK1.8
	 */
	@RequestMapping(value="/consumer/get/sign/list", method=RequestMethod.GET)
	public String getSignList(@ModelAttribute("cri") Criteria cri, HttpSession session, Model model) {
		System.out.println("ConsumerController.getSignList() 호출");
		model.addAttribute("sessionLogin", session.getAttribute("sessionLoginMember"));
		System.out.println(session.getAttribute("sessionLoginId")+ "<---- getId");
		String getId = (String)session.getAttribute("sessionLoginId");
		List<ConsumerSignDetailVo> signList = consumerService.getSignDetail(cri, getId);
		model.addAttribute("signList", signList);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(consumerService.getSignAllCount(getId));
		model.addAttribute("pageMaker", pageMaker);
		return "member/consumer/getMemberConsumerWalletSignList";
	}
	
	/**
	 *  해당 url로 요청이 들어왔을 때, 구매자 지갑관리 뷰로 랜더링 해준다.
	 * 
	 * @param accountOfConsumerRequest
	 * @param session
	 * @param model
	 * @return index
	 * @since JDK1.8
	 */
	@RequestMapping(value="/consumer/mywallet", method=RequestMethod.GET)
	public String ConsumerMyWllet(HttpSession session, Model model) {
		System.out.println("ConsumerController.ConsumerMyWllet() 호출");
		String returnView = null;
		if(session.getAttribute("sessionLoginMember")==null) {
			returnView = "index";
		}else {
			returnView = "member/consumer/getMemberConsumerWallet";
		}
		model.addAttribute("sessionLogin", session.getAttribute("sessionLoginMember"));
		return returnView;
	}
	
	/**
	 * 계좌등록을 위한 service계층의 메서드 호출
	 * 
	 * @param accountOfConsumerRequest
	 * @param session
	 * @param model
	 * @return index
	 * @since JDK1.8
	 */
	@RequestMapping(value="/consumer/mywallet/addaccount", method=RequestMethod.POST)
	public String addAccountOfConsumer(AccountOfConsumerRequestVo accountOfConsumerRequest, HttpSession session, Model model) {
		System.out.println("ConsumerController.addAccountOfConsumer() 호출 --POST");
		String id = accountOfConsumerRequest.getFkConsumerAccountConsumer();
		System.out.println(id+" <---id");
		
		/* 세션 저장 */
		model.addAttribute("sessionLogin", session.getAttribute("sessionLoginMember"));
		
		/* 계좌등록을 위한 service계층의 메서드 호출 */
		int result = consumerService.insertAccountOfConsumer(accountOfConsumerRequest);
		System.out.println(result + " <---result, 값 받아오기 성공!");
		
		return "index";	
	}
	
	/**
	 * 계좌인증을 위한 메서드(외부 계좌 관리 테이블에 입력한 계좌번호가 유효한지 알아보기위함)
	 * 
	 * @param consumerName
	 * @param bankName
	 * @param consumerAccount
	 * @return selectResult
	 * @since JDK1.8
	 */
	@RequestMapping(value="/accountcheck", method=RequestMethod.POST)	
	@ResponseBody
	public String accountCheck(@RequestParam("consumerName") String consumerName, @RequestParam("bankName") String bankName, @RequestParam("consumerAccount") String consumerAccount) {
		System.out.println("ConsumerController.accountCheck() 호출");
		System.out.println(consumerName+"<---consumerName");
		System.out.println(bankName+"<---bankName");
		System.out.println(consumerAccount+"<---consumerAccount");
		
		/* 해쉬맵에 파라미터들을 put */
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("consumerName", consumerName);
		map.put("bankName", bankName);
		map.put("consumerAccount", consumerAccount);
		
		String selectResult = consumerService.selectAccountOfConsumer(map);
		System.out.println(selectResult+" <---selectResult");
		
		return selectResult;
	}
	
	/**
	 * 해당 url로 요청이 들어왔을 때, 구매자 계좌등록 폼으로 랜더링 해준다.
	 * 
	 * @return member/consumer/addAccountOfMemberConsumer
	 * @since JDK1.8
	 */
	@RequestMapping(value="/consumer/mywallet/account", method=RequestMethod.GET)
	public String addAccountOfConsumer(HttpSession session, Model model) {
		System.out.println("ConsumerController.addAccountOfConsumer() 호출");
		model.addAttribute("sessionLogin", session.getAttribute("sessionLoginMember"));		
		return "member/consumer/addAccountOfMemberConsumer";
	}
	
	/**
	 * 구매자 마이페이지에서 해당멤버의 기존 비밀번호와 변경할 비밀번호를 받아 수정처리 해준다.
	 * 
	 * @return 로그아웃 후 index
	 * @since JDK1.8
	 */
	@RequestMapping(value="/consumer/mypage/password/modify", method=RequestMethod.POST)
	public String modifyConsumerPassword(String consumerIdPk, String consumerPassword, String consumerChangePassword, HttpSession session) {
		System.out.println("ConsumerService.modifyConsumerPassword() 호출");
		Map<String, Object> ConsumerMypageChange = new HashMap<>();
		ConsumerMypageChange.put("consumerIdPk", consumerIdPk);
		ConsumerMypageChange.put("consumerPassword", consumerPassword);
		ConsumerMypageChange.put("consumerChangePassword", consumerChangePassword);
		consumerService.updateConsumerPassword(ConsumerMypageChange);
		session.invalidate();
		return "index";
	}
	
	/**
	 * 구매자 마이페이지에서 수정할 정보를 받아 해당 멤버의 개인정보를 수정해준다.
	 * 
	 * @return index
	 * @since JDK1.8
	 */
	@RequestMapping(value="/consumer/mypage/information/modify", method=RequestMethod.POST)
	public String modifyConsumrInformation(ConsumerMypageVo consumerMypageVo, HttpSession session, Model model) {
		System.out.println("ConsumerService.modifyConsumr() 호출");
		consumerService.updateConsumerInformation(consumerMypageVo);
		model.addAttribute("sessionLogin", session.getAttribute("sessionLoginMember"));
		return "index";
	}
	
	
	/**
	 * 구매자 마이페이지에서 pw를 받아 해당 멤버의 개인정보를 넘겨준다.
	 * 
	 * @return member/consumer/getMemberConsumerMypage
	 * @since JDK1.8
	 */
	@RequestMapping(value="/consumer/get/mypage/information", method=RequestMethod.POST)
	public String getConsumerMypageInformation(String mypagePassword, String mypageId, Model model, HttpSession session) {
		System.out.println("ConsumerController.getconsumerMypageInformation() 호출");
		System.out.println("mypagePassword : " + mypagePassword );
		
		Map<String, Object> ConsumerMypageIdPw = new HashMap<>();
		ConsumerMypageIdPw.put("mypagePassword", mypagePassword);
		ConsumerMypageIdPw.put("mypageId", mypageId);
		
		ConsumerMypageVo consumerMypageVo = consumerService.selectConsumerMypageInformation(ConsumerMypageIdPw);
		model.addAttribute("consumerMypageInformation",consumerMypageVo);
		model.addAttribute("sessionLogin", session.getAttribute("sessionLoginMember"));
		
		System.out.println("컨트롤러 이메일 : "+consumerMypageVo.getConsumerEmail());
		
		return "member/consumer/getMemberConsumerMypage";
	}
	
	
	/**
	 * 해당 url로 요청이 들어왔을 때, 회원가입 권한 선택 폼으로 랜더링 해준다.
	 * 
	 * @return member/choiceSignUp
	 * @since JDK1.8
	 */
	@RequestMapping(value="/singup/choice", method=RequestMethod.GET)
	public String signUpChoice() {
		System.out.println("ConsumerController.choiceSignUp() 호출");
		return "member/signUpChoice";
	}
	
	/**
	 * 해당 url로 요청이 들어왔을 때, 구매자 계정관리뷰로 랜더링 해준다.
	 * 
	 * @return index OR member/consumer/getMemberConsumerMypage
	 * @since JDK1.8
	 */
	@RequestMapping(value="/consumer/mypage", method=RequestMethod.GET)
	public String consumerMypage(HttpSession session, Model model) {
		System.out.println("ConsumerController.getConsumerMypage() 호출");
		String returnView = null;
		if(session.getAttribute("sessionLoginMember")==null) {
			returnView = "index";
		}else {
			returnView = "member/consumer/getMemberConsumerMypage";
		}
		
		model.addAttribute("sessionLogin", session.getAttribute("sessionLoginMember"));
		return returnView;
	}
	
	/**
	 * 해당 url로 요청이 들어왔을 때, 구매자 회원가입폼 뷰로 렌더링 해준다.
	 * 
	 * @return 해당 되는 뷰의 경로
	 * @since JDK1.8
	 */
	@RequestMapping(value="/signup/addconsumer", method=RequestMethod.GET)
	public String addConsumerForm() {
		System.out.println("ConsumerController.joinconsumer() 호출");
		return "member/consumer/addMemberConsumer";
	}
	
	/**
	 * addMemberConsumer.html(구매자 회원가입)에서 데이터를 VO에 받아 service 계층의 메서드를 호출할 때 param으로 넘겨준다.
	 * 
	 * @param consumerRequestVo
	 * @return index
	 * @since JDK1.8
	 */
	@RequestMapping(value="/signup/addconsumer", method=RequestMethod.POST)
	public String addConsumer(ConsumerRequestVo consumerRequestVo) {
		System.out.println("ConsumerController.addConsumer() 호출");
		int insertResult = consumerService.insertConsumer(consumerRequestVo);
		System.out.println(insertResult+" <---insertResult");
		return "index";
	}
	
	/**
	 * 아이디 중복체크를 위해 화면에서 받아온 id 값을 param에 넣어 service 계층의 메서드를 호출할 때 넘겨준다.
	 * ResponseBody 어노테이션: 메소드에서 리턴되는 값이 뷰를 통해서 출력되지 않고 HTTP Response Body 에 직접 쓰여지게 된다.
	 * 
	 * @param id
	 * @return count
	 * @since JDK1.8
	 */
	@RequestMapping(value="/idcheck", method=RequestMethod.POST)	
	@ResponseBody
	public int idCheck(@RequestBody String id) {
		System.out.println("ConsumerController.idCheck() 호출");
		System.out.println(id+" <---id");

		int count = 0;
		
		/* id로 중복성 체크 메소드 호출*/
		count = consumerService.idCheck(id);

		return count;
	}
}