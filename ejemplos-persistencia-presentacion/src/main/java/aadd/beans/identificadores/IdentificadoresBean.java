package aadd.beans.identificadores;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class IdentificadoresBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String text1 = "Texto Inicial 1";
    private String text2 = "Texto Inicial 2";
    private String text3= "Texto Inicial 3";
    private String text4 = "Texto Inicial 4";
    private String text5 = "Texto Inicial 5";
    private String text6 = "Texto Inicial 6";
    private String text7 = "Texto Inicial 7";
    private String text8 = "Texto Inicial 8";
    private String text9 = "Texto Inicial 9";

    
    
    public void changeText() {
    	text1 = "Texto 1 cambiado ";
    	text2 = "Texto 2 cambiado ";
    	text3 = "Texto 3 cambiado ";
    	text4 = "Texto 4 cambiado ";
    	text5 = "Texto 5 cambiado ";
    	text6 = "Texto 6 cambiado ";
    	text7 = "Texto 7 cambiado ";
    	text8 = "Texto 8 cambiado ";
    	text9 = "Texto 9 cambiado ";

    }
	public String getText1() {
		return text1;
	}
	public void setText1(String text1) {
		this.text1 = text1;
	}
	public String getText2() {
		return text2;
	}
	public void setText2(String text2) {
		this.text2 = text2;
	}
	public String getText3() {
		return text3;
	}
	public void setText3(String text3) {
		this.text3 = text3;
	}
	public String getText4() {
		return text4;
	}
	public void setText4(String text4) {
		this.text4 = text4;
	}
	public String getText5() {
		return text5;
	}
	public void setText5(String text5) {
		this.text5 = text5;
	}
	public String getText6() {
		return text6;
	}
	public void setText6(String text6) {
		this.text6 = text6;
	}
	public String getText7() {
		return text7;
	}
	public void setText7(String text7) {
		this.text7 = text7;
	}
	public String getText8() {
		return text8;
	}
	public void setText8(String text8) {
		this.text8 = text8;
	}
	public String getText9() {
		return text9;
	}
	public void setText9(String text9) {
		this.text9 = text9;
	}
  
}