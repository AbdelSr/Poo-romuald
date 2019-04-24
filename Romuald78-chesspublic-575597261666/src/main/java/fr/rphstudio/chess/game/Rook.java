/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess.ChessPosition;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serourabderaouf
 */
public class Rook implements IMove {
    
    public List<ChessPosition> getPossibleMove(ChessPosition position, Board board){
        
        List<ChessPosition> listPosition = new ArrayList<ChessPosition>() ;
        
        boolean stop = false ;
        int x = 0 ;
        
        for (int i=0 ; i < 7 ; i++) {
            
            x++ ;
            
            ChessPosition position2 = new ChessPosition(position.x + x, position.y) ;
            
            if (position2.isValid()) {
                
                Piece piece2 = board.getPiece(position2) ;
                
                if (piece2 != null) {
                    
                    if (piece2.getChessColor() == board.getPiece(position).getChessColor() ) {
                        stop = true ;
                    }
                    else {
                        listPosition.add(position2) ;
                        stop = true ;
                    }
                }
                else {
                    listPosition.add(position2) ;
                }
            }
            
            if (stop) {
                break ;
            }
        }
        
        stop = false ;
        x = 0 ;
        
        for (int i=0 ; i < 7 ; i++) {
            
            x-- ;
            
            ChessPosition position2 = new ChessPosition(position.x + x, position.y) ;
            
            if (position2.isValid()) {
                
                Piece piece2 = board.getPiece(position2) ;
                
                if (piece2 != null) {
                    
                    if (piece2.getChessColor() == board.getPiece(position).getChessColor() ) {
                        stop = true ;
                    }
                    else {
                        listPosition.add(position2) ;
                        stop = true ;
                    }
                }
                                else {
                    listPosition.add(position2) ;
                }
            }
            
            if (stop) {
                break ;
            }
        }
        
        stop = false ;
        int y = 0 ;
        
        for (int i=0 ; i < 7 ; i++) {
            
            y++ ;
            
            ChessPosition position2 = new ChessPosition(position.x, position.y + y) ;
            
            if (position2.isValid()) {
                
                Piece piece2 = board.getPiece(position2) ;
                
                if (piece2 != null) {
                    
                    if (piece2.getChessColor() == board.getPiece(position).getChessColor() ) {
                        stop = true ;
                    }
                    else {
                        listPosition.add(position2) ;
                        stop = true ;
                    }
                }
                else {
                    listPosition.add(position2) ;
                }
            }
            
            if (stop) {
                break ;
            }
        }
        
        stop = false ;
        y = 0 ;
        
        for (int i=0 ; i < 7 ; i++) {
            
            y-- ;
            
            ChessPosition position2 = new ChessPosition(position.x, position.y + y) ;
            
            if (position2.isValid()) {
                
                Piece piece2 = board.getPiece(position2) ;
                
                if (piece2 != null) {
                    
                    if (piece2.getChessColor() == board.getPiece(position).getChessColor() ) {
                        stop = true ;
                    }
                    else {
                        listPosition.add(position2) ;
                        stop = true ;
                    }
                }
                else {
                    listPosition.add(position2) ;
                }
            }
            
            if (stop) {
                break ;
            }
        }
                
        return listPosition ;
    }
}
