package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pack.model.MemDaoInter;

@Controller
public class InsertController {
	@Autowired
	private MemDaoInter daoInter;
	
	@RequestMapping(value = "insert", method = RequestMethod.GET)
	public String insert() {
		return "insform";
	}
	
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String submit(MemBean bean) {
		//System.out.println(bean.getName()); 이렇게 자꾸 확인해보고 체크할것.
		boolean b = daoInter.insertData(bean);
		if(b) {
			return "redirect:/list";	//redirect
		}else {
			return "error";		//forwarding
		}
		
	}
}
