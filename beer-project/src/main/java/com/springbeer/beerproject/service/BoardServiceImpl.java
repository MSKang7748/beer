package com.springbeer.beerproject.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbeer.beerproject.entity.Lecture;
import com.springbeer.beerproject.entity.LectureBoard;
import com.springbeer.beerproject.entity.LectureBoardFile;
import com.springbeer.beerproject.entity.Subscription;
import com.springbeer.beerproject.repository.LectureBoardRepository;
import com.springbeer.beerproject.repository.LectureRepository;
import com.springbeer.beerproject.repository.SubsqRepository;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	SubsqRepository subsqRepository;
	
	@Autowired
	LectureBoardRepository lectureboardRepository;
	
	@Autowired
	LectureRepository lectureRepository;

	@Override
	public List<LectureBoard> findSubsqList() {
		List<LectureBoard> board = (List<LectureBoard>) lectureboardRepository.findAll();
		return board;
	}

	@Override
	public void writeSubsq(LectureBoard lectureboard) {
		Collection<LectureBoardFile> files = lectureboard.getLectureboardfile();
		lectureboard.setLectureboardfile(null);
		lectureboardRepository.save(lectureboard);
		for(LectureBoardFile f:files) {
			f.setBoardNo(lectureboard.getBoardNo());
			lectureboardRepository.save(f);
		}
		
	}

	@Override
	public LectureBoard boardDetail(int boardNo) {
		LectureBoard details = lectureboardRepository.findByBoardNo(boardNo);
		
		return details;
	}

	@Override
	public void boarddelete(LectureBoard lectureboard) {
		lectureboardRepository.save(lectureboard);
		
	}

	@Override
	public List<Lecture> findlecture(int deleteNo) {
		List<Lecture> findlecture = (List<Lecture>)lectureRepository.findByDeleteNo(deleteNo); 
		return findlecture;
	}

	@Override
	public void addLecture(Lecture lecture) {
		lectureRepository.save(lecture);
		
	}

	@Override
	public List<LectureBoard> findlectureByLectureNo(int lectureNo, int deletedNo) {
		List<LectureBoard> findlecturelist = lectureboardRepository.findByLectureNo(lectureNo, deletedNo);
		
		return findlecturelist;
	}

	@Override
	public List<Lecture> findlectureByMemberNo(int memberNo, int deleteNo) {
		List<Lecture> findlectures = lectureRepository.findByMemberNo(memberNo, deleteNo);
		
		return findlectures;
	}

	@Override
	public List<Lecture> findLectureContentByLectureNo(int lectureNo) {
		List<Lecture> findlc = lectureRepository.findLectureContentByLectureNo(lectureNo);
		
		return findlc;
	}

	@Override
	public Lecture findlectureByMemberNoAndLectureNo(int memberNo, int lectureNo) {
		Lecture findtype = lectureRepository.findlectureByMemberNoAndLectureNo(memberNo, lectureNo);
		
		return findtype;
	}

	@Override
	public Subscription findByMemberNoAndLectureNo(int memberNo, int lectureNo) {
		Subscription findsubsq = subsqRepository.findlectureByMemberNoAndLectureNo(memberNo, lectureNo);
		
		return findsubsq;
	}

	@Override
	public void saveByMemberNoAndLectureNo(int memberNo, int lectureNo) {
		Subscription subsq = new Subscription();
		subsq.setLectureNo(lectureNo);
		subsq.setMemberNo(memberNo);
		
		subsqRepository.save(subsq);
	}

	@Override
	public List<Lecture> findsubsqByMemberNo(int memberNo, int deleteNo) {
		
		List<Lecture> findsubsq = subsqRepository.findlByMemberNo(memberNo, deleteNo);
		
		return findsubsq;
	}

	@Override
	public Lecture findlectureByLectureNoAndDeleteNo(int lectureNo, int deleteNo) {
		Lecture lect = lectureRepository.findLectureByLectureNoAndDeleteNo(lectureNo, deleteNo);
		
		return lect;
	}

	@Override
	public void deleteUpdate(Lecture lecture) {
		lectureRepository.save(lecture);
	}

	@Override
	public List<Lecture> findAlltoDash(int deleteNo) {
		
		List<Lecture> findlecture = (List<Lecture>)lectureRepository.findByDeleteNo(deleteNo); 
		return findlecture;
	}

	@Override
	public Lecture findLectureByLectureNo(int lectureNo) {
		Lecture lecture = lectureRepository.findByLectureNo(lectureNo);
		return lecture;
	}

}
