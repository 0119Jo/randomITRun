package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pack.model.BoardDaoInter;

@Controller
public class DetailController {
	@Autowired
	private BoardDaoInter inter;
	
	@RequestMapping("detail")
	public ModelAndView detailProcess(
			@RequestParam("num") String num,
			@RequestParam("page") String page) {
		// 조회수 증가 작업 선행
		inter.updateReadCnt(num);
		
		// 상세보기 진행 후 jsp로 출력
		ModelAndView view = new ModelAndView("detail");
		view.addObject("data", inter.getDetail(num));
		view.addObject("page", page);	//원래페이지로 돌아가기 위해 페이지를 계속 가지고다녀야함.
		return view;
		
	}
}
