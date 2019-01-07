package com.gift.futurestrading.order.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gift.futurestrading.member.vo.SellerVo;
import com.gift.futurestrading.order.service.OrderSellService;
import com.gift.futurestrading.order.vo.OrderSellVo;
import com.gift.futurestrading.page.vo.Criteria;
import com.gift.futurestrading.page.vo.PageMaker;

/**
 * @author 정진우
 * @ClassName : OrderSellController
 * @Version : JDK1.8
 * @LatestUpdate : 2018-12-28
 *  주문 등록 , 체결 목록 처리를 위한 Class
 */

@Controller
public class OrderSellController {
	@Autowired
	private OrderSellService orderSellService;

	/**
	 * @author 정진우
	 * listOrder rendering
	 * @param : HttpSession , Model , OrderSellVo
	 * @return : listOrder
	 */
	
	@RequestMapping(value="/seller/order/list", method=RequestMethod.GET)
	public String listOrder(@ModelAttribute("cri") Criteria cri, Model model, HttpSession session) {
		System.out.println("OrderSellController / listOrder()");
		String id = (String) session.getAttribute("sessionLoginId");
		model.addAttribute("sessionLogin", session.getAttribute("sessionLoginMember"));
		List<OrderSellVo> orderSellerList = orderSellService.getSellerOrderAll(cri , id);
		model.addAttribute("orderSellerList", orderSellerList);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(orderSellService.getSellerOrderAllCount(id));
		model.addAttribute("pageMaker", pageMaker);

		return "order/seller/listOrder";
	}
	 
	
	/**
	 * @author 정진우
	 * addsellerOrder 화면 렌더링
	 * @param : 
	 * @return : rder/seller/addMemeberSellerOrderPepper
	 */
	
	@RequestMapping(value="/seller/order", method=RequestMethod.GET)
	public String addsellerOrder(@ModelAttribute("cri") Criteria cri,HttpSession session, Model model) {
		System.out.println("OrderSellController / addSellerOrder()");
		model.addAttribute("sessionLogin", session.getAttribute("sessionLoginMember"));
		String id = (String) session.getAttribute("sessionLoginId");
		List<OrderSellVo> orderSellerList = orderSellService.getSellerOrderViewBottom(id, cri);
		model.addAttribute("orderSellList", orderSellerList);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(orderSellService.getSellerOrderAllCount(id));
		System.out.println(orderSellService.getSellerOrderAllCount(id));
		model.addAttribute("pageMaker", pageMaker);
		/*종가가져옴*/
		int getClosingPriceResult = orderSellService.getClosePriceItemOne();
		model.addAttribute("closingPrice", getClosingPriceResult);
		return "order/seller/addMemeberSellerOrderPepper";
	}
	
	/**
	 * @author 정진우
	 * addSellerOrderAction 요청 
	 * @param : HttpSession , Model , OrderSellVo
	 * @return : index
	 */
	
	@RequestMapping("/seller/order/re")
	public String addSellerOrderAction(HttpSession session, Model model ,OrderSellVo orderSellVo) {
		System.out.println("OrderSellController / OrderSell");
		model.addAttribute("sessionLogin", session.getAttribute("sessionLoginMember"));
		
		System.out.println(model.addAttribute("sessionLogin", session.getAttribute("sessionLoginMember")));
		System.out.println(session.getAttribute("sessionLoginId"));
		orderSellVo.setFkSellerOrderSell((String)session.getAttribute("sessionLoginId"));
		System.out.println(orderSellVo.getFkItemDetailOrderSell()); 
		System.out.println(orderSellVo.getOrderSellAmount()); 
		System.out.println(orderSellVo.getOrderSellPerPrice());
		
		orderSellVo.setOrderSellMethod(orderSellVo.getOrderSellMethod().substring(1));
		orderSellService.addSellerOrder(orderSellVo);
		
		return "redirect:/seller/order";
	}
}
