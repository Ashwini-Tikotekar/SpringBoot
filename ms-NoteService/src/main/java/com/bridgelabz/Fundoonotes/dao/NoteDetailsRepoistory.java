package com.bridgelabz.Fundoonotes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.Fundoonotes.model.NoteDetails;


public interface NoteDetailsRepoistory extends JpaRepository<NoteDetails,Integer>
{
List<NoteDetails> findAllById(int userId);
}


