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
public class Queen implements IMove {
    
    public List<ChessPosition> getPossibleMove(ChessPosition position, Board board){
        
        List<ChessPosition> listPosition = new ArrayList<ChessPosition>() ;
        List<ChessPosition> listPosition1 = new ArrayList<ChessPosition>() ;        
        List<ChessPosition> listPosition2 = new ArrayList<ChessPosition>() ;
               
        Rook rook = new Rook() ;
        Bishop bishop = new Bishop() ;
        
        listPosition1 = rook.getPossibleMove(position, board) ;
        listPosition2 = bishop.getPossibleMove(position, board) ;
        
        listPosition.addAll(listPosition1) ;
        listPosition.addAll(listPosition2) ;
        
        return listPosition ;
    }
}
