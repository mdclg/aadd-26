package aadd.mongo.test;

import java.util.List;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Grade {

	private ObjectId id;
	
	@BsonProperty(value = "student_id")
	private Double studentId;
	
	@BsonProperty(value = "class_id")
	private Double classId;
	private List<Score> scores;
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public Double getStudentId() {
		return studentId;
	}
	public void setStudentId(Double studentId) {
		this.studentId = studentId;
	}
	public Double getClassId() {
		return classId;
	}
	public void setClassId(Double classId) {
		this.classId = classId;
	}
	public List<Score> getScores() {
		return scores;
	}
	public void setScores(List<Score> scores) {
		this.scores = scores;
	}
	
	
}
