package pack.model;

import java.util.List;

import pack.controller.MemBean;

public interface MemDaoInter {		//다형성을 위한 추상메소드 만듦
	List<MemDto> getDataAll();
	MemDto selectPart(String num);
	boolean insertData(MemBean bean);
	boolean updateData(MemBean bean);
	boolean deleteData(String num);
}
