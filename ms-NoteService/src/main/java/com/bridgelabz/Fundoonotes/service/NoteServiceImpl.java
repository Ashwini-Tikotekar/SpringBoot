package com.bridgelabz.Fundoonotes.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.Fundoonotes.dao.LabelDetailsRepoistory;
import com.bridgelabz.Fundoonotes.dao.NoteDetailsRepoistory;
import com.bridgelabz.Fundoonotes.model.Label;
import com.bridgelabz.Fundoonotes.model.NoteDetails;
//import com.bridgelabz.Fundoonotes.utility.EmailUtility;
import com.bridgelabz.Fundoonotes.utility.TokenGenerator;
@Service
public class NoteServiceImpl implements NoteService{ 


	@Autowired
	private NoteDetailsRepoistory notedetailsrepoistory;

	@Autowired
	private LabelDetailsRepoistory labeldetailsrepoistory;

	@Autowired
	private TokenGenerator tokenGenerator;


	//		@Autowired
	//	private EmailUtility emailUtility;

	@Override
	public NoteDetails createNote(String token, NoteDetails note, HttpServletRequest request) {
		int userId=tokenGenerator.VerifyToken(token);
		note.setId(userId);;
		NoteDetails savedNote=notedetailsrepoistory.save(note);
		return savedNote;
	}

	public NoteDetails updateNote(String token,int id,NoteDetails note, HttpServletRequest request) {
		int userId =tokenGenerator.VerifyToken(token);
		Optional<NoteDetails> optional = notedetailsrepoistory.findById(id);

		if(optional.isPresent())
		{
			NoteDetails existingNote=optional.get();
			if(existingNote.getId()==userId) {
				existingNote.setDescription(note.getDescription());
				existingNote.setTitle(note.getTitle());

				notedetailsrepoistory.save(existingNote);
				return existingNote;
			}
		}
		return null;
	}  	

	@Override
	public Optional<NoteDetails>getNoteDetails(int id) {

		return notedetailsrepoistory.findById(id);
	}

	public List<NoteDetails> retrieveNote(String token, HttpServletRequest request) {
		int userId = tokenGenerator.VerifyToken(token);
		List<NoteDetails> notes = notedetailsrepoistory.findAllById(userId);
		if (!notes.isEmpty()) {     
			return notes;
		}
		return null;
	}


	public NoteDetails deleteNote(String token, int id,HttpServletRequest request) {
		int userId = tokenGenerator.VerifyToken(token);
		Optional<NoteDetails> optional = notedetailsrepoistory.findById(id);

		if(optional.isPresent())
		{
			NoteDetails existingNote=optional.get();
			if (existingNote.getId()==userId) {
				notedetailsrepoistory.delete(existingNote);
				return existingNote;
			}
		}
		return null;
	}

	public Label createLabel(String token,Label label,  HttpServletRequest request) {
		int userId = tokenGenerator.VerifyToken(token);
		if(userId>0) {
			label.setUserId(userId);
			labeldetailsrepoistory.save(label);
			return label;
		}
		else 
			return null;
	}

	public Label editLabel(String token,int id,Label label, HttpServletRequest request) {
		int userId = tokenGenerator.VerifyToken(token);
		Optional<Label> optional = labeldetailsrepoistory.findById(id);

		if(optional.isPresent())
		{
			Label existingLabel=optional.get();
			if(existingLabel.getUserId()==userId) {
				existingLabel.setLabelName(label.getLabelName());
				labeldetailsrepoistory.save(existingLabel);
				return existingLabel;
			}
		}
		return null;
	} 

	public List<Label> retrieveLabel(String token, HttpServletRequest request) {
		int userId = tokenGenerator.VerifyToken(token);
		List<Label> label = labeldetailsrepoistory.findAllByUserId(userId);
		if (!label.isEmpty()) {     
			return label;
		}
		return null;
	}
	@Transactional
	public boolean deleteLabel(String token, int labelId,HttpServletRequest request) {
		int userId = tokenGenerator.VerifyToken(token);
		List<Label> existingLabel = labeldetailsrepoistory.findByUserId(userId);
		if(existingLabel!=null)
		{
				labeldetailsrepoistory.deleteByLabelId(labelId);
				return true;
			}
		
		return false;
	}
	public boolean mapNoteToLabel(String token, int noteId, int labelId,HttpServletRequest request) {
        int userId = tokenGenerator.VerifyToken(token);
           Optional<Label> optional1=labeldetailsrepoistory.findById(labelId);
           Optional<NoteDetails> optional2=notedetailsrepoistory.findById(noteId);
           if (optional1.isPresent() && optional1.isPresent())
           {
        	   NoteDetails note = optional2.get();
               Label label = optional1.get();
               if(label.getUserId()==userId && note.getId()==userId)
               {
               List<Label> labels = note.getLabelList();
               labels.add(label);
               if (!labels.isEmpty())
               {
               note.setLabelList(labels);
               notedetailsrepoistory.save(note);
               }
                   return true;
           }}
           return false;
	
}

	    public boolean removeNoteLabel(String token, int noteId, int labelId, HttpServletRequest request) {
	        int userId = tokenGenerator.VerifyToken(token);
	        Optional<Label> optionallabel=labeldetailsrepoistory.findById(labelId);
	        Optional<NoteDetails> optionalnote=notedetailsrepoistory.findById(noteId);
	        if (optionallabel.isPresent() && optionalnote.isPresent()) {   
	        	NoteDetails note= optionalnote.get();
	            List<Label> labels = note.getLabelList();
	            if (!labels.isEmpty()) {
	                labels = labels.stream().filter(label -> label.getLabelId() != labelId).collect(Collectors.toList());
	                note.setLabelList(labels);
	                notedetailsrepoistory.delete(note);
	                return true;
	            } }
	        return false;
	    }

	
}
