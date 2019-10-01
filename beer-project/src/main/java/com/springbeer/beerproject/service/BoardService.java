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
	
	void boarddelete(int boardNo);

	List<Lecture> findlecture();

	void addLecture(Lecture lecture);

	List<LectureBoard> findlectureByLectureNo(int lectureNo);

	List<Lecture> findlectureByMemberNo(int memberNo);

	List<Lecture> findLectureContentByLectureNo(int lectureNo);

	Lecture findlectureByMemberNoAndLectureNo(int memberNo, int lectureNo);

}
