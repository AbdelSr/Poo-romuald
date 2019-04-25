/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess.ChessPosition;

/**
 *
 * @author serourabderaouf
 */
public class MoveInfo {
    
    public ChessPosition p0 ;
    public Piece piece0 ;
    public ChessPosition p1 ;
    public Piece piece1 ;
    public Board board ;
    
    public MoveInfo(ChessPosition myP0, ChessPosition myP1, Board myBoard) {
        
        this.p0 = myP0 ;
        this.p1 = myP1 ;
        this.board = myBoard ;
        
        this.piece0 = myBoard.getPiece(myP0) ;
        this.piece1 = myBoard.getPiece(myP1) ;
    }
}
