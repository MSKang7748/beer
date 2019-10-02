package com.springbeer.beerproject.service;

import java.util.List;

import com.springbeer.beerproject.entity.Lecture;
import com.springbeer.beerproject.entity.LectureBoard;
import com.springbeer.beerproject.entity.LectureBoardFile;
import com.springbeer.beerproject.entity.Subscription;

public interface BoardService {

	List<LectureBoard> findSubsqList();
	
	void writeSubsq(LectureBoard lectureboard);

	LectureBoard boardDetail(int boardNo);
	
	void boarddelete(LectureBoard lectureboard);

	List<Lecture> findlecture(int deleteNo);

	void addLecture(Lecture lecture);

	List<LectureBoard> findlectureByLectureNo(int lectureNo, int deletedNo);

	List<Lecture> findlectureByMemberNo(int memberNo, int deleteNo);

	List<Lecture> findLectureContentByLectureNo(int lectureNo);

	Lecture findlectureByMemberNoAndLectureNo(int memberNo, int lectureNo);

	Subscription findByMemberNoAndLectureNo(int memberNo, int lectureNo);

	void saveByMemberNoAndLectureNo(int memberNo, int lectureNo);

	List<Lecture> findsubsqByMemberNo(int memberNo, int deleteNo);

	Lecture findlectureByLectureNoAndDeleteNo(int lectureNo, int deleteNo);

	void deleteUpdate(Lecture lecture);

}
