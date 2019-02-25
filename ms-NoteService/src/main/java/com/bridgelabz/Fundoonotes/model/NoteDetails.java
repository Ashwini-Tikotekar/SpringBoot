package com.bridgelabz.Fundoonotes.model;

import java.io.Serializable;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@SuppressWarnings("serial")
@Entity
@Table(name= "MyNoteDB")
public class NoteDetails implements Serializable {


	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	

	 @ManyToMany(fetch=FetchType.EAGER,targetEntity= Label.class,cascade = CascadeType.ALL)
	    @JoinTable(name = "Note_Label", joinColumns = { @JoinColumn(name = "noteId") }, inverseJoinColumns = { @JoinColumn(name = "labelId") })
	    List<Label> labelList;

	 private int noteId;
	 
	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCreated_Date() {
		return created_Date;
	}

	public void setCreated_Date(Timestamp created_Date) {
		this.created_Date = created_Date;
	}

	public Timestamp getUpdated_Date() {
		return updated_Date;
	}

	public void setUpdated_Date(Timestamp updated_Date) {
		this.updated_Date = updated_Date;
	}

	public boolean isArchive() {
		return isArchive;
	}

	public void setArchive(boolean isArchive) {
		this.isArchive = isArchive;
	}

	public boolean isPinned() {
		return isPinned;
	}

	public void setPinned(boolean isPinned) {
		this.isPinned = isPinned;
	}

	public boolean isInTrash() {
		return inTrash;
	}

	public void setInTrash(boolean inTrash) {
		this.inTrash = inTrash;
	}

	@Column(name = "Title")
	private String Title;

	@Column(name = "description")
	private String description;

	@CreationTimestamp
	@Column(name = "created_Date")
	private Timestamp created_Date;

	@UpdateTimestamp
	@Column(name="updated_Date")
	private Timestamp updated_Date;

	@Column(name="isArchive")
	private boolean isArchive;

	@Column(name="isPinned")
	private boolean isPinned;

	@Column(name="inTrash")
	private boolean inTrash;
	

	@Column(name="activate_Status")
	private boolean activate_Status;

	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isActivate_Status() {
		return activate_Status;
	}

	public void setActivate_Status(boolean activate_Status) {
		this.activate_Status = activate_Status;
	}

	@Override
	public String toString() {
		return "NoteDetails [noteId=" + noteId + ", Title=" + Title + ", description=" + description + ", created_Date="
				+ created_Date + ", updated_Date=" + updated_Date + ", isArchive=" + isArchive + ", isPinned="
				+ isPinned + ", inTrash=" + inTrash + "]";


	}

	public List<Label> getLabelList() {
		return labelList;
	}

	public void setLabelList(List<Label> labelList) {
		this.labelList = labelList;
	}
}
