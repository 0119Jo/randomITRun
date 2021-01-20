package pack.model;

import java.util.List;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pack.controller.MemBean;

@Repository
public class MemDaoImpl extends SqlSessionDaoSupport implements MemDaoInter{
	@Autowired
	public MemDaoImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	@Override
	public List<MemDto> getDataAll() {	//두개 이상의 데이터는 selectList로 반환한다.
		
		return getSqlSession().selectList("selectAll");	//DataMapper.xml의 id
	}
	
	@Override
	public MemDto selectPart(String num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("selectPart", num);
	}
	
	@Override
	public boolean insertData(MemBean bean) {
		//성공하면 트루, 실패하면 false를 리턴
		try {
			getSqlSession().insert("insertData", bean);
			return true;
		} catch (Exception e) {
			System.out.println("insertData err : " + e);
			return false;
		}
		
	}
	
	@Override
	public boolean updateData(MemBean bean) {
		try {
			getSqlSession().update("updateData", bean);
			return true;
		} catch (Exception e) {
			System.out.println("updateData err : " + e);
			return false;
		}
		
	}
	
	@Override
	public boolean deleteData(String num) {
		int re = getSqlSession().delete("deleteData", num);
		if(re > 0)
			return true;
		else	
			return false;
	}
	
	
	
}
