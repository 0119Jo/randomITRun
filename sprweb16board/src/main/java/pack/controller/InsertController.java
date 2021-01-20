package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pack.model.BoardDaoInter;

@Controller
public class InsertController {
	@Autowired
	private BoardDaoInter inter;
	
	@RequestMapping(value = "insert", method = RequestMethod.GET)
	private String write() {
		return "insform";
	}
	
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	private String writeSubmit(BoardBean bean) {
		//....
		//System.out.println( bean.getName());
		bean.setBdate();
		int newNum = inter.currentNum()+ 1;	//새글 번호
		bean.setNum(newNum);
		bean.setGnum(newNum);
		
		boolean b = inter.insert(bean);
		if(b) {
			return "redirect:/list?page=1";
		}else {
			return "redirect:/error";
		}
		
	}
}
