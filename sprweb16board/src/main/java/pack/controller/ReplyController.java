package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pack.model.BoardDaoInter;

@Controller
public class ReplyController {
	@Autowired
	private BoardDaoInter inter;
	
	@RequestMapping(value = "reply", method = RequestMethod.GET)
	public ModelAndView reply(@RequestParam("num")String num,
			@RequestParam("page")String page) {
		ModelAndView view = new ModelAndView("reply");
		view.addObject("data", inter.getDetail(num));
		return view;
	}
	@RequestMapping(value = "reply", method = RequestMethod.POST)
	public String replysubmit(BoardBean bean,
			@RequestParam("page")String page) {
		//Onum 갱신
		bean.setOnum(bean.getOnum() + 1);
		inter.updateOnum(bean);		//편의상 반환 값 무시
		
		
		//	댓글 저장
		bean.setBdate();	//작성일
		bean.setNum(inter.currentNum() + 1);	//새글 번호
		bean.setNested(bean.getNested() + 1); 	//들여쓰기
		
		if(inter.insertReply(bean)) {
			return "redirect:list?page=" + page;	//추가 후 글 목록 보기
		}else {
			return "redirect:error";
		}
		
		
	}
}
