/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess.ChessColor;
import fr.rphstudio.chess.interf.IChess.ChessPosition;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serourabderaouf
 */
public class King implements IMove {

    @Override
    public List<ChessPosition> getPossibleMove(ChessPosition position, Board board){
        
        List<ChessPosition> listPosition = new ArrayList<ChessPosition>() ;
        
        int[][] nombre = { {1, 1}, {1, 0}, {0, 1}, {-1, -1}, {-1, 0}, {0, -1}, {1, -1}, {-1, 1 } } ;
        
        for(int i=0 ; i < nombre.length ; i++) {
            
            ChessPosition position2 = new ChessPosition(position.x + nombre[i][0], position.y + nombre[i][1]) ;
            
            if (position2.isValid()) {
                
                Piece piece2 = board.getPiece(position2) ;
                
                if (piece2 != null) {
                    
                    if (piece2.getChessColor() == board.getPiece(position).getChessColor() ) {
                        continue ;
                    }
                }
               
                listPosition.add(position2) ;
            }
        }
        
        boolean jouableBL = false ;
        boolean jouableBR = false ;
        boolean jouableWL = false ;
        boolean jouableWR = false ;
        
        if (board.getPiece(position).getChessColor() == ChessColor.CLR_BLACK) {
            if (!board.getPiece(position).hasMoved()) {

                Piece tourG = board.getPiece(new ChessPosition(0,0)) ;
                
                if (tourG != null) {

                    if(!tourG.hasMoved()) {
                        
                        for (int i=1 ; i < 4 ; i++) {
                            
                            if (board.getPiece(new ChessPosition((4 - i), 0)) == null) {
                                jouableBL = true ;
                            }
                            else {
                                jouableBL = false ;
                                break ;
                            }
                        }
                    }
                }
                
                Piece tourD = board.getPiece(new ChessPosition(7,0)) ;
                
                if (tourD != null) {

                    if(!tourD.hasMoved()) {
                        
                        for (int i=1 ; i < 3 ; i++) {
                            
                            if (board.getPiece(new ChessPosition((4 + i), 0)) == null) {
                                jouableBR = true ;
                            }
                            else {
                                jouableBR = false ;
                                break ;
                            }
                        }
                    }
                }
            }
        }
        else {
            if (!board.getPiece(position).hasMoved()) {

                Piece tourG = board.getPiece(new ChessPosition(0,7)) ;
                
                if (tourG != null) {

                    if(!tourG.hasMoved()) {
                        
                        for (int i=1 ; i < 4 ; i++) {
                            
                            if (board.getPiece(new ChessPosition((4 - i), 7)) == null) {
                                jouableWL = true ;
                            }
                            else {
                                jouableWL = false ;
                                break ;
                            }
                        }
                    }
                }
                
                Piece tourD = board.getPiece(new ChessPosition(7,7)) ;
                
                if (tourD != null) {

                    if(!tourD.hasMoved()) {
                        
                        for (int i=1 ; i < 3 ; i++) {
                            
                            if (board.getPiece(new ChessPosition((4 + i), 7)) == null) {
                                jouableWR = true ;
                            }
                            else {
                                jouableWR = false ;
                                break ;
                            }
                        }
                    }
                }
            }
        }
        
        if (jouableBL) {
            listPosition.add(new ChessPosition(0, 0)) ;
        }
        if (jouableBR) {
            listPosition.add(new ChessPosition(7, 0)) ;
        }
        if (jouableWL) {
            listPosition.add(new ChessPosition(0, 7)) ;
        }
        if (jouableWR) {
            listPosition.add(new ChessPosition(7, 7)) ;
        }        
        
        return listPosition ;
    }
}
