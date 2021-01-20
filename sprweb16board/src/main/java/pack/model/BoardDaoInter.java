package pack.model;

import java.util.ArrayList;

import pack.controller.BoardBean;

public interface BoardDaoInter {
	ArrayList<BoardDto> getList();
	ArrayList<BoardDto> getSearch(BoardBean bean);
	boolean insert(BoardBean bean);
	BoardDto getDetail(String num);
	boolean update(BoardBean bean);
	boolean delete(String num);
	
	int currentNum();	//가장 최근의 큰번호
	int totalCnt();		//전체레코드 갯수
	boolean updateReadCnt(String num);	//조회수
	String selectPass(String num);
	
	//댓글
	boolean updateOnum(BoardBean bean);		//오더넘버 수정하기
	boolean insertReply(BoardBean bean); 	//댓글쓰기
	
}
