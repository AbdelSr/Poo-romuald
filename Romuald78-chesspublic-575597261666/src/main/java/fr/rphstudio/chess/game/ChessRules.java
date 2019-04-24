/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.EmptyCellException;
import fr.rphstudio.chess.interf.IChess ;
import fr.rphstudio.chess.interf.OutOfBoardException;
import java.util.List;
/**
 *
 * @author serourabderaouf
 */
public class ChessRules implements IChess {
    
    @Override
    public long getPlayerDuration(IChess.ChessColor color, boolean isPlaying) {
        return 0 ;
    }
    
    public boolean undoLastMove(){
        return false ;
    }
    
    public List<IChess.ChessType> getRemovedPieces(IChess.ChessColor color) {
        return null ;
    }
    
    public IChess.ChessKingState getKingState(IChess.ChessColor color) {
        return null ;
    }
    
    public void movePiece(IChess.ChessPosition p0, IChess.ChessPosition p1) {
      
    }
    
    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition p) {
        return null ;
    }
    
    public int getNbRemainingPieces(IChess.ChessColor color) {
        return 0 ;
    }
    
    public IChess.ChessColor getPieceColor(IChess.ChessPosition p) throws EmptyCellException, OutOfBoardException {
        return null ;
    }
    
    public IChess.ChessType getPieceType(IChess.ChessPosition p) throws EmptyCellException, OutOfBoardException {
        return null ;
    }
    
    public void reinit() {
        
    }
}
