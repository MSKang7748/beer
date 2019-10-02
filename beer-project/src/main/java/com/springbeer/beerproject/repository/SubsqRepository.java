package com.springbeer.beerproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.springbeer.beerproject.entity.Lecture;
import com.springbeer.beerproject.entity.Subscription;

public interface SubsqRepository extends CrudRepository<Subscription, Integer> {
	
	@Query(value = "SELECT subsq " +
			       "FROM Subscription as subsq " +
			       "WHERE subsq.memberNo =:memberNo AND subsq.lectureNo =:lectureNo ", nativeQuery = false)
	Subscription findlectureByMemberNoAndLectureNo(@Param("memberNo") int memberNo,@Param("lectureNo") int lectureNo); // 기본 CRUD가 수행 됨./ 사용하는 table명과 primary의 자료형을 명시 해 놓음.

	
	@Query(value = "select lect " +
			"FROM Subscription as subsq INNER JOIN Lecture as lect " +
			"ON subsq.memberNo = :memberNo and subsq.lectureNo = lect.lectureNo and lect.deleteNo = :deleteNo ", nativeQuery = false )
	List<Lecture> findlByMemberNo(@Param("memberNo") int memberNo, @Param("deleteNo") int deleteNo);

}