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
        
        boolean jouableL = false ;
        boolean jouableR = false ;
        
        ////////////////////////////////////////////
        
        if (!board.getPiece(position).hasMoved()) {

            Piece tourG = board.getPiece(new ChessPosition(0,position.y)) ;
                
            if (tourG != null) {

                if(!tourG.hasMoved()) {
                        
                    for (int i=1 ; i < 4 ; i++) {
                            
                        if (board.getPiece(new ChessPosition((4 - i), position.y)) == null) {
                            jouableL = true ;
                        }
                        else {
                            jouableL = false ;
                            break ;
                        }
                    }
                }
            }
                
            Piece tourD = board.getPiece(new ChessPosition(7,position.y)) ;
                
            if (tourD != null) {

                if(!tourD.hasMoved()) {
                        
                    for (int i=1 ; i < 3 ; i++) {
                            
                        if (board.getPiece(new ChessPosition((4 + i), position.y)) == null) {
                            jouableR = true ;
                        }
                        else {
                            jouableR = false ;
                            break ;
                        }
                    }
                }
            }
        }
        
        if (jouableL) {
            listPosition.add(new ChessPosition(0, position.y)) ;
        }
        if (jouableR) {
            listPosition.add(new ChessPosition(7, position.y)) ;
        }
     
        
        return listPosition ;
    }
}
