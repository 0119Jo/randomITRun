package pack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pack.model.MemDaoInter;
import pack.model.MemDto;

@Controller
public class ListController {
	@Autowired
	private MemDaoInter daoInter;
	
	@RequestMapping("list")
	public ModelAndView list() {
		/*
		List<MemDto> list = daoInter.getDataAll();
		ModelAndView andView = new ModelAndView();
		andView.setViewName("list");
		andView.addObject("list", list);
		
		return andView;
		위와 아래는 같은것이다.
		*/
		return new ModelAndView("list", "list", daoInter.getDataAll());//"list.jsp", "list라는 key", daoInter.getDataAll()로 데이터가져오기
	}
}
