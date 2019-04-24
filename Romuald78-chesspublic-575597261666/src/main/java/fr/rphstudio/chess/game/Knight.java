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
public class Knight implements IMove {
    
    public List<ChessPosition> getPossibleMove(ChessPosition position, Board board){
        
        ArrayList<ChessPosition> listPosition = new ArrayList<ChessPosition>() ;
        
        int[][] nombre = { {2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2} } ;
        
        for(int i=0 ; i < nombre.length ; i++) {
            
            ChessPosition position2 = new ChessPosition(position.x + nombre[i][0], position.y + nombre[i][1]) ;
            
            if (position2.isValid()) {
                
                Piece piece2 = board.getPiece(position2) ;
                
                if (piece2 != null) {
                    
                    if (piece2.getChessColor() == board.getPiece(position).getChessColor() ) {
                        continue ;
                    }
                }
               
                listPosition.add(new ChessPosition(position.x + nombre[i][0], position.y + nombre[i][1])) ;
            }
        }
                
        return listPosition ;
    }
}