package com.springbeer.beerproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.springbeer.beerproject.entity.Lecture;

public interface LectureRepository extends CrudRepository<Lecture, Integer> {

	@Query(value = "SELECT lect "+
	               "FROM Lecture as lect "+
			       "WHERE lect.memberNo =:memberNo ", nativeQuery = false)
	List<Lecture> findByMemberNo(@Param("memberNo") Integer memberNo);

	List<Lecture> findLectureContentByLectureNo(int lectureNo);

	@Query(value = "SELECT lect " +
				   "FROM Lecture as lect " +
				   "WHERE lect.memberNo =:memberNo AND lect.lectureNo =:lectureNo ", nativeQuery = false)
	Lecture findlectureByMemberNoAndLectureNo(@Param("memberNo") int memberNo, @Param("lectureNo") int lectureNo);
 

}