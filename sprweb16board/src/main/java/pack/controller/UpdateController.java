package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pack.model.BoardDaoInter;
import pack.model.BoardDto;

@Controller
public class UpdateController {
	@Autowired
	private BoardDaoInter inter;
	
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("num")String num,
							 @RequestParam("page")String page) {
		//모델에서 수정할 자료 읽기
		BoardDto dto = inter.getDetail(num);

		ModelAndView view = new ModelAndView("update");
		view.addObject("data", dto);
		view.addObject("page", page);
		
		return view;
	}
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView editsubmit(BoardBean bean,
			@RequestParam("page")String page) {	//다른 자료는 전부 bean을 타고 넘어오는데 페이지는 bean에 없기 때문에 따로 @RequestParam을 사용해서 가져온다.
		//비밀번호 체크
		String pass = inter.selectPass(Integer.toString(bean.getNum()));		//bean.getNum()는 수정을 할때 입력하는 비밀번호를 가져옴.
		
		ModelAndView view = new ModelAndView();
		if(bean.getPass().equals(pass)) {	//사용자의 비밀번호와 db의 비밀번호를 비교
			boolean b = inter.update(bean);		
			if(b) {
				//view.setViewName("redirect:list?page=" +page);	//목록보기로 이동
				view.setViewName("redirect:detail?num=" + bean.getNum() + "&page=" + page);		//상세보기로 이동
			}else {
				view.setViewName("redirect:error");
			}
		}else {
			view.setViewName("update");
			view.addObject("msg","비밀번호 불일치");
			view.addObject("page",page);
		}
		return view;
		

	}
}
