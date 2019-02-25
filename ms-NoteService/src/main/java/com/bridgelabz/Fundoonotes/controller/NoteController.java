package com.bridgelabz.Fundoonotes.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bridgelabz.Fundoonotes.model.Label;
import com.bridgelabz.Fundoonotes.model.NoteDetails;
import com.bridgelabz.Fundoonotes.service.NoteService;

@Controller
@RequestMapping("/users")
public class NoteController {

	@Autowired
	private NoteService noteService;

	@PostMapping("/createnote")
	public ResponseEntity<?> createNote(@RequestHeader ("token")String token,@RequestBody NoteDetails note, HttpServletRequest request,HttpServletResponse response) {        
		if (noteService.createNote(token,note, request)!=null)
			return new ResponseEntity<String>("Note Succesfully Created",HttpStatus.OK);
		else
			return new ResponseEntity<String>("pls provide details correctly",HttpStatus.CONFLICT);

	}

	@PostMapping(value = "/updatenote")
	public ResponseEntity<String> updateNote( @RequestHeader("token") String token,@RequestParam("noteId") int id,@RequestBody NoteDetails note, HttpServletRequest request) {

		NoteDetails newNote=noteService.updateNote(token,id, note, request);
		if (newNote!=null) {
			return new ResponseEntity<String>("Note Succesfully updated",HttpStatus.OK);
		} else {

			return new ResponseEntity<String>("Note cannot be updated",HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/retrieve")
	public ResponseEntity<?> retrieve(@RequestHeader(value = "token") String token, HttpServletRequest request) {
		List<NoteDetails> notes = noteService.retrieveNote(token, request);
		return new ResponseEntity<List<NoteDetails>>(notes,HttpStatus.OK);

	}

	@DeleteMapping(value = "/deletenote")
	public ResponseEntity<?> deleteNote(@RequestHeader(value = "token") String token,
			@RequestParam("noteId") int noteId, HttpServletRequest request) {
		NoteDetails note = noteService.deleteNote(token, noteId, request);
		if (note != null) {
			return new ResponseEntity<String>("Note  deleted" ,HttpStatus.OK);
		} 
		else
			return new ResponseEntity<String>("User is not authorised", HttpStatus.BAD_REQUEST);
	}


	@PostMapping(value = "/createlabel")
	public ResponseEntity<String> createLabel( @RequestHeader("token") String token,@RequestBody Label label, HttpServletRequest request) {

		Label newLabel=noteService.createLabel(token, label, request);
		if (newLabel!=null) {
			return new ResponseEntity<String>("Label Succesfully Created",HttpStatus.OK);
		} else {

			return new ResponseEntity<String>("Label cannot be created",HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/editlabel")
	public ResponseEntity<String> editLabel( @RequestHeader("token") String token ,int id,@RequestBody Label label, HttpServletRequest request) {

		Label newLabel=noteService.editLabel(token,id, label, request);
		if (newLabel!=null) {
			return new ResponseEntity<String>("Label Succesfully edited",HttpStatus.OK);
		} else {

			return new ResponseEntity<String>("Label cannot be edited",HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping(value = "/retrievelabel")
	public ResponseEntity<?> retrievelabel(@RequestHeader(value = "token") String token, HttpServletRequest request) {
		List<Label> label = noteService.retrieveLabel(token, request);
		return new ResponseEntity<List<Label>>(label,HttpStatus.OK);

	}
	@DeleteMapping(value = "/deletelabel")
	public ResponseEntity<?> deleteLabel(@RequestHeader(value = "token") String token,
			@RequestParam("labelId") int labelId, HttpServletRequest request) {
		if (noteService.deleteLabel(token, labelId, request)) {
			return new ResponseEntity<String>("label  deleted" ,HttpStatus.OK);
		} 
		else
			return new ResponseEntity<String>("User is not authorised", HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/mapnote&label")
    public ResponseEntity<?> mapNoteLabel(@RequestHeader ("token")String token,@RequestParam ("noteId")int noteId,
    		@RequestParam ("labelId")int labelId,HttpServletRequest request) {        

        if(noteService.mapNoteToLabel(token, noteId, labelId,request))
            return new ResponseEntity<>("Mapped successfully",HttpStatus.OK);
        else
            return new ResponseEntity<String>("pls provide details correctly",HttpStatus.CONFLICT);
    }
	
	@DeleteMapping("/removenote&label")
    public ResponseEntity<?> removeNoteLabel(@RequestHeader ("token")String token,@RequestParam ("noteId")int noteId,@RequestParam ("labelId")int labelId,
    		HttpServletRequest request) {        

        if(noteService.removeNoteLabel(token, noteId, labelId,request))
            return new ResponseEntity<>("Labels from particular note has been removed ",HttpStatus.OK);
        else
            return new ResponseEntity<String>("pls provide details correctly",HttpStatus.CONFLICT);
    }

}


