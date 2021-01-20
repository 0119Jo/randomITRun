package pack.model;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pack.controller.BoardBean;

@Repository
public class BoardDaoImpl extends SqlSessionDaoSupport implements BoardDaoInter {
	@Autowired
	public BoardDaoImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	//데이터 목록 
	@Override
	public ArrayList<BoardDto> getList() {
		
		return (ArrayList)getSqlSession().selectList("selectList");
	}
	//목록 찾기.
	@Override
	public ArrayList<BoardDto> getSearch(BoardBean bean) {
		
		return (ArrayList)getSqlSession().selectList("searchList", bean);
	}
	//데이터 삽입.
	@Override
	public boolean insert(BoardBean bean) {
		int re = getSqlSession().insert("insertData", bean);
		if(re > 0) 
			return true;
		else
			return false;
	}
	//글 목록중 클릭했을때 글의 내용 보기 및 내용 수정
	@Override
	public BoardDto getDetail(String num) {
		// 글 내용 상세보기, 글 수정, 댓글달때
		
		return getSqlSession().selectOne("selectOne", num);
	}

	@Override
	public boolean update(BoardBean bean) {
		try {
			int re = getSqlSession().update("updateData", bean);
			if(re > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public boolean delete(String num) {
		try {
			int re = getSqlSession().delete("deleteData", num);
			if(re > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
		
		
	}
	//데이터 번호
	@Override
	public int currentNum() {
		// insert시 번호 자동증가를 위해 현재 레코드 중 가장 큰 번호를 얻기위해
		if(getSqlSession().selectOne("currentNum") == null) return 0;
		return getSqlSession().selectOne("currentNum");
	}
	//전체 레코드 수
	@Override
	public int totalCnt() {
		
		return getSqlSession().selectOne("totalCnt");
	}
	//조회수  증가
	@Override
	public boolean updateReadCnt(String num) {
		// 상세 보기 전 조회수 증가 
		int re = getSqlSession().update("updateReadcnt", num);
		if(re > 0)
			return true;
		else
			return false;
	}
	//수정용 :비밀번호 비교
	@Override
	public String selectPass(String num) {
		
		return getSqlSession().selectOne("selectPass", num);
	}

	@Override
	public boolean updateOnum(BoardBean bean) {
		// 댓글에서 onum 갱신
		int re = getSqlSession().update("updateOnum", bean);
		if(re > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean insertReply(BoardBean bean) {
		try {
			int re = getSqlSession().insert("insertReData", bean);
			if(re > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			System.out.println("insertReply error : " + e);
			return false;
		}
		
	}

}
