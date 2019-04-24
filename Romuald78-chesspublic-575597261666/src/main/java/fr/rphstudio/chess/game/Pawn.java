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
public class Pawn implements IMove {
    
    public List<ChessPosition> getPossibleMove(ChessPosition position, Board board){
        
        ArrayList<ChessPosition> listPosition = new ArrayList<ChessPosition>() ;
        
        if (board.getPiece(position).getChessColor().equals(ChessColor.CLR_BLACK)) {
            
            ChessPosition position1 = new ChessPosition(position.x, position.y + 1) ;
            
            if (position1.isValid()) {
                
                Piece piece1 = board.getPiece(position1) ;
                
                if (piece1 == null) {
                    
                    listPosition.add(position1) ;
                    
                    if (!board.getPiece(position).hasMoved()) {

                    ChessPosition position3 = new ChessPosition(position.x, position.y + 2) ;
                    
                        Piece piece3 = board.getPiece(position3) ;

                        if (piece3 == null) {

                            listPosition.add(position3) ;
                        }
                        
                    }
                }
            }
            
            int[][] nombre = { {1, 1}, {-1, 1} } ;
            
            for(int i=0 ; i < nombre.length ; i++) {
            
                ChessPosition position2 = new ChessPosition(position.x + nombre[i][0], position.y + nombre[i][1]) ;

                if (position2.isValid()) {

                    Piece piece2 = board.getPiece(position2) ;

                    if (piece2 != null) {

                        if (piece2.getChessColor() == board.getPiece(position).getChessColor() ) {
                            continue ;
                        }
                        else {
                            listPosition.add(position2) ;
                        }
                    }
                }
            }
        }
        else {
            
            ChessPosition position1 = new ChessPosition(position.x, position.y - 1) ;
            
            if (position1.isValid()) {
                
                Piece piece1 = board.getPiece(position1) ;
                
                if (piece1 == null) {
                    
                    listPosition.add(position1) ;
                    
                    if (!board.getPiece(position).hasMoved()) {

                    ChessPosition position3 = new ChessPosition(position.x, position.y - 2) ;
                    
                        Piece piece3 = board.getPiece(position3) ;

                        if (piece3 == null) {

                            listPosition.add(position3) ;
                        }
                        
                    }
                }
            }
            
            int[][] nombre = { {1, -1}, {-1, -1} } ;
            
            for(int i=0 ; i < nombre.length ; i++) {
            
                ChessPosition position2 = new ChessPosition(position.x + nombre[i][0], position.y + nombre[i][1]) ;

                if (position2.isValid()) {

                    Piece piece2 = board.getPiece(position2) ;

                    if (piece2 != null) {

                        if (piece2.getChessColor() == board.getPiece(position).getChessColor() ) {
                            continue ;
                        }
                        else {
                            listPosition.add(position2) ;
                        }
                    }
                }
            }
        }

        return listPosition ;
    }
}
