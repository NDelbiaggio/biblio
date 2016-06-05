package ch.ge.hes.delbiaggio.biblio.domaine;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Livre implements Serializable{

	private int no;
	private String titre;
	private String auteur;
	private String editeur;
	private Date annee;
	
	public Livre(int no,String titre,String auteur,String editeur,Date annee){
		this.no = no;
		this.titre = titre;
		this.auteur = auteur;
		this.editeur = editeur;
		this.annee = annee;
		
	}
	
	public Livre(){}
	
	public void setNo(int no) {this.no = no;}
	public void setTitre(String titre) {this.titre = titre;}
	public void setAuteur(String auteur) {this.auteur = auteur;}
	public void setEditeur(String editeur) {this.editeur = editeur;}
	public void setAnnee(Date annee) {this.annee = annee;}
	
	public int getNo() {return no;}
	public String getTitre() {return titre;}
	public String getAuteur() {return auteur;}
	public String getEditeur() {return editeur;}
	public Date getAnnee() {return annee;}

	public boolean equals(Object obj){
		if(!(obj instanceof Livre)){
			return false;
		}
		if(this.no == ((Livre)obj).getNo()){
			return true;
		}
		return false;
	}
	
	public String toString(){return this.titre + " " + " auteur: " + this.auteur + " editeur " + this.editeur + " écrit en " + this.annee;}
	
	public int compareTo(Object obj){
		Livre book = (Livre)obj;
		if(this.equals(book)){
			return 0;
		}else if(this.titre.compareTo(book.getTitre()) > 0){
			return 1;
		}else if(this.titre.compareTo(book.getTitre()) < 0){
			return -1;
		}else{
			return 0;
		}
	}
	
	public int getYear(){
		SimpleDateFormat formater = new SimpleDateFormat("yyyy");
		return Integer.parseInt(formater.format(annee));
	}
}
