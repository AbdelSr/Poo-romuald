/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess.ChessType ;
import fr.rphstudio.chess.interf.IChess.ChessColor ;
import fr.rphstudio.chess.interf.IChess.ChessPosition;
import java.util.List;
/**
 *
 * @author serourabderaouf
 */
public class Piece {
    
    private ChessType type ;
    private ChessColor color ;
    private IMove possibleMove ;
    private int nbMoves ;
    
    public Piece(ChessType myType, ChessColor myColor, IMove myPossibleMove) {
        
        this.color = myColor ;
        this.type = myType ;
        this.possibleMove = myPossibleMove ;
        this.nbMoves = 0 ;
    }
    
    public ChessType getChessType() {
        return this.type ;
    }
    
    public ChessColor getChessColor() {
        return this.color ;
    }
    
    public void increaseNbMoves() {
        this.nbMoves++ ;
    }
    
    public void decreaseNbMoves() {
        this.nbMoves-- ;
    }
        
    public boolean hasMoved() {
        return (this.nbMoves != 0) ;
    }
        
    public List<ChessPosition> getMove(ChessPosition position, Board board) {
        return this.possibleMove.getPossibleMove(position, board) ;
    }
    
    public void setChessColor(ChessColor myColor) {
        this.color = myColor ;
    }
    
    public void setChessType (ChessType myType) {
        this.type = myType ;
    }
}
