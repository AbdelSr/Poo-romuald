/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.chess.game;
import fr.rphstudio.chess.interf.EmptyCellException;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author biabianydamien
 */
public class ChessModel implements IChess {
    
    private static IChess instance; // by default equals to null
    private Board board ;
    
    // constructor
    private ChessModel(){
        this.reinit() ;
    }
    
    
    public static IChess getInstance() {
        // singleton
        if (ChessModel.instance == null ) {
            
            ChessModel.instance = new  ChessModel();
            
        }
        return ChessModel.instance;
    }     
    
    @Override
    public void reinit() {
        this.board = new Board() ;
    }

    @Override
    public ChessType getPieceType(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        Piece piece = this.board.getPiece(p) ;
        
        if (piece != null){
            return piece.getChessType() ; 
        }
        
        throw new EmptyCellException() ;
    }

    @Override
    public ChessColor getPieceColor(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        Piece piece = this.board.getPiece(p) ;
        
        if (piece != null){
            return piece.getChessColor() ; 
        }
        
        throw new EmptyCellException() ;
    }

    @Override
    public int getNbRemainingPieces(ChessColor color) {
        int pieceNombre = this.board.getPiecesNombre(color) ;
        
        return pieceNombre ;
    }

    @Override
    public List<ChessPosition> getPieceMoves(ChessPosition p) {
        
        List<ChessPosition> listPosition = new ArrayList<ChessPosition>() ;
        
        if (this.board.getPiece(p) != null) {
            
            if (board.getKingState(board.getPiece(p).getChessColor() ) != ChessKingState.KING_ISDEAD) {
                
                listPosition = this.board.getKingSafePositionMove(p) ; 
            }
        }
        
        return listPosition ;
    }

    @Override
    public void movePiece(ChessPosition p0, ChessPosition p1) {
        this.board.movePiece(p0, p1);
    }

    @Override
    public ChessKingState getKingState(ChessColor color) {
        return this.board.getKingState(color) ;
    }

    @Override
    public List<ChessType> getRemovedPieces(ChessColor color) {        
        ArrayList <ChessType> removedPieces = new ArrayList<ChessType>();
        return(removedPieces);
    }

    @Override
    public boolean undoLastMove() {
        boolean changement = this.board.remontada() ;
        return changement;
    }

    @Override
    public long getPlayerDuration(ChessColor color, boolean isPlaying) {
        return 0;
    }
    
}
