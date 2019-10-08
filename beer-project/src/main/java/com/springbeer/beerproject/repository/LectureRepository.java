package com.springbeer.beerproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.springbeer.beerproject.entity.Lecture;

public interface LectureRepository extends CrudRepository<Lecture, Integer> {

	@Query(value = "SELECT lect "+
	               "FROM Lecture as lect "+
			       "WHERE lect.memberNo =:memberNo AND lect.deleteNo =:deleteNo ", nativeQuery = false)
	List<Lecture> findByMemberNo(@Param("memberNo") Integer memberNo, @Param("deleteNo") int deleteNo);

	List<Lecture> findLectureContentByLectureNo(int lectureNo);

	@Query(value = "SELECT lect " +
				   "FROM Lecture as lect " +
				   "WHERE lect.memberNo =:memberNo AND lect.lectureNo =:lectureNo ", nativeQuery = false)
	Lecture findlectureByMemberNoAndLectureNo(@Param("memberNo") int memberNo, @Param("lectureNo") int lectureNo);


	@Query(value = "SELECT lect " +
	               "FROM Lecture as lect " +
			       "WHERE lect.deleteNo =:deleteNo ", nativeQuery = false)
	List<Lecture> findByDeleteNo(@Param("deleteNo") int deleteNo);

	@Query(value = "SELECT lect "+
                   "FROM Lecture as lect "+
		           "WHERE lect.lectureNo =:lectureNo AND lect.deleteNo =:deleteNo ", nativeQuery = false)
	Lecture findLectureByLectureNoAndDeleteNo(@Param("lectureNo") int lectureNo, @Param("deleteNo") int deleteNo);

	Lecture findByLectureNo(int lectureNo);

	@Query(value = "SELECT lect " +
                   "FROM Lecture as lect " +
		           "WHERE lect.deleteNo =:deleteNo " + 
		           "ORDER BY lect.lectureCnt DESC  ", nativeQuery = false)
	List<Lecture> findListByDeleteNo(@Param("deleteNo") int deleteNo);

	@Query(value = "SELECT lect " +
                   "FROM Lecture as lect " +
	               "WHERE lect.deleteNo =:deleteNo ", nativeQuery = false)
	List<Lecture> findAllbyDeleteNo(@Param("deleteNo") int deleteNo);
 

}