package com.bridgelabz.Fundoonotes.service;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

import com.bridgelabz.Fundoonotes.model.Label;
import com.bridgelabz.Fundoonotes.model.NoteDetails;


public interface NoteService {
	NoteDetails createNote(String token,NoteDetails note,HttpServletRequest request);

//	NoteDetails activateUser(String token, HttpServletRequest request);

	Optional<NoteDetails> getNoteDetails(int id);
	
	NoteDetails updateNote(String token,int id, NoteDetails note,HttpServletRequest request);
	
	List<NoteDetails> retrieveNote(String token, HttpServletRequest request);
	
	NoteDetails deleteNote(String token, int noteId, HttpServletRequest request);
	
	Label createLabel(String token, Label label, HttpServletRequest request);
	
	Label editLabel(String token,int id,Label label, HttpServletRequest request);
	
	List<Label> retrieveLabel(String token, HttpServletRequest request);
	
	boolean deleteLabel(String token, int labelId, HttpServletRequest request);
	
    boolean mapNoteToLabel(String token, int noteId, int labelId,HttpServletRequest request);
    
   boolean removeNoteLabel(String token, int noteId,int labelId,HttpServletRequest request);
}
