/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dsproject;


/**
 *
 * @author saeid zangeneh
 */
///Structure of qoutes in  the file :qouteContent,qouteID,posVotes,negVotes,WriterID,savedOrNot,owner


public class Quote {
    //content of qoute
    private String content;
    //uniuqe auto increment identefir for each qoute
    private int QID;
    //id of user that writes this qoute
    private int UID;
    //votes
    private int pVote;
    private int nVote;
    //knowing that the qoute should be saved or not !
    private int save;
    //person who said this qoute !
    private String qouteOwner;
    
    
    public Quote(String content,int QID,int UID,String qouteOwner) {
        this.content = content;
        
        this.QID = QID;
        this.UID = UID;
        this.pVote=0;
        this.nVote=0;
        this.save=0;
        this.qouteOwner=qouteOwner;
       
    }

   public Quote() {
    }


    public int getpVote() {
        return pVote;
    }


    public void setpVote(int pVote) {
        this.pVote = pVote;
    }


    public int getnVote() {
        return nVote;
    }


    public void setnVote(int nVote) {
        this.nVote = nVote;
    }


    public int getSave() {
        return save;
    }


    public void setSave(int save) {
        this.save = save;
    }


    public String getQouteOwner() {
        return qouteOwner;
    }


    public void setQouteOwner(String qouteOwner) {
        this.qouteOwner = qouteOwner;
    }

    
    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public int getQID() {
        return QID;
    }


    public void setQID(int QID) {
        this.QID = QID;
    }


    public int getUID() {
        return UID;
    }


    public void setUID(int UID) {
        this.UID = UID;
    }

//qouteContent,qouteID,posVotes,negVotes,WriterID,savedOrNot,owner
   public String toSstring(Quote q) {
        return q.getContent()+","+q.getQID()+","+q.getpVote()+","+q.getnVote()+","+q.getQID()+","+q.getSave()+","+q.getQouteOwner();
    }
    
    
    
    
}

