package com.springbeer.beerproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.springbeer.beerproject.entity.LectureBoard;
import com.springbeer.beerproject.entity.LectureBoardFile;

public interface LectureBoardRepository extends CrudRepository<LectureBoard, Integer> {

	@Query(value ="SELECT board " +
				  "FROM LectureBoard as board " +
			      "WHERE board.lectureNo =:lectureNo", nativeQuery = false)
	List<LectureBoard> findByLectureNo(@Param("lectureNo") int lectureNo);

	
//	@Query(value ="INSERT INTO LectureBoardFile (subsqSavedFileName, subsqUserFileName, boardNo) " +
//			      "VALUES( :#{#boardFile.subsqSavedFileName}, :#{#boardFile.subsqUserFileName}, :#{#boardFile.boardNo} )", nativeQuery = false)
	void save(@Param("boardFile") LectureBoardFile boardFile);
	
	void deleteByBoardNo(int boardNo);

	LectureBoard findByBoardNo(int boardNo);

}