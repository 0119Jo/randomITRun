package pack.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pack.model.BoardDaoInter;
import pack.model.BoardDto;

@Controller
public class ListController {
	@Autowired
	private BoardDaoInter inter;
	
	private int tot;	//전체레코드 수
	private int plist = 10;	//한 페이지당 보여질 행의 수
	private int pagesu;		//전체 페이지 수를 기억
	
	public ArrayList<BoardDto> getListData(ArrayList<BoardDto> list, int page) {
		ArrayList<BoardDto> result = new ArrayList<BoardDto>();
		int start = (page - 1) * plist;
		int size = plist <= list.size() - start?plist:list.size() - start;	//삼항 연산자
		//System.out.println("(page - 1) * plist = " + (page - 1) * plist);
		//System.out.println("plist = "+ plist);
		//System.out.println("list.size() - start = " + (list.size() - start));
		//System.out.println("(plist <= list.size() - start) = "+(plist <= list.size() - start));
		//
		for (int i = 0; i < size; i++) {
			result.add(i, list.get(start + i));
			System.out.println("(start + i) = "+(start + i));
		}
		System.out.println("size = " + size);
		return result;
	}
	
	public int getPageSu() {	//총 페이지수 얻기
		pagesu = tot / plist;
		if(tot % plist > 0) pagesu += 1;	//자투리 페이지 연산처리
		return pagesu;
	}
	
	
	@RequestMapping("list")
	public Model process(Model model, HttpServletRequest request) {
		int page = 0;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			page = 1;
		}
		if(page <= 0) page = 1;

		tot = inter.totalCnt();	//전체 레코드 수 얻기
		
		ArrayList<BoardDto> list = inter.getList();
		ArrayList<BoardDto> result = getListData(list, page);
		
		//model.addAttribute("data", list);	//페이징 없이 작업할 경우
		//페이징 처리 한것.
		model.addAttribute("data", result);
		model.addAttribute("pagesu", getPageSu());		
		model.addAttribute("page", page);
		
		return model;
	}
}
