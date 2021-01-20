package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.model.BoardDaoInter;

@Controller
public class DeleteController {
	@Autowired
	private BoardDaoInter inter;
	
	@RequestMapping("delete")
	public String del(@RequestParam("num")String num,
			@RequestParam("page")String page) {
		// 사실은 삭제하기전에 비밀번호를 한번 더 비교한뒤 삭제하게끔 해야한다. 수정 참조
		if(inter.delete(num))
			return "redirect:list?page=" + page;
		else
			return "redirect:error";
	}
}
