package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pack.model.MemDaoInter;

@Controller
public class DeleteController {
	@Autowired
	private MemDaoInter daoInter;
	
	@RequestMapping(value="delete")
	public String del(@RequestParam("num") String num) {
		if(daoInter.deleteData(num)) {
			return "redirect:/list";		//무조건 redirect방식으로 해야 수정사항이 적용된 리스트가 보인다.
		}else {
			return "error";
		}
	}
}
