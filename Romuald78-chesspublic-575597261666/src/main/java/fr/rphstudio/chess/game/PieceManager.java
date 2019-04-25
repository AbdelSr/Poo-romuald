/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess.ChessColor;
import fr.rphstudio.chess.interf.IChess.ChessType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author biabianydamien
 */
public class PieceManager {
    
    private List<ChessType> whiteList;
    private List<ChessType> blackList;
    
    public PieceManager(){
        
        this.whiteList = new ArrayList();
        this.blackList = new ArrayList();
    }
            
    public List<ChessType> getList(ChessColor color){
        
        if(color == ChessColor.CLR_BLACK){
            return blackList;
        }
        else {
            return whiteList;
        }
    }
    
    public void addPiece (ChessColor color,ChessType type){
        
        if(color == ChessColor.CLR_BLACK){
            this.blackList.add(0,type);
        }
        else {
            this.whiteList.add(0,type);
        }
    }
    
    public void removeLastPiece (ChessColor color ){
        
       if(color == ChessColor.CLR_BLACK){
           
            if(this.blackList.size() > 0){
                
                this.blackList.remove(0); 
             }
        }
        
        else {
           
            if(this.whiteList.size() > 0){
                
                this.whiteList.remove(0); 
             }
        }
    }
}