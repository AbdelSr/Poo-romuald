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
    
    // constructor
    private ChessModel(){
        
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
        System.out.println("reinit method from IChess interface");
        System.out.println("void = methode ne retourne rien");
    }

    @Override
    public ChessType getPieceType(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        throw new EmptyCellException(); 
        
    }

    @Override
    public ChessColor getPieceColor(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        throw new EmptyCellException();     
    }

    @Override
    public int getNbRemainingPieces(ChessColor color) {
        return 5;
    }

    @Override
    public List<ChessPosition> getPieceMoves(ChessPosition p) {
        ArrayList <ChessPosition> pieceMoves = new ArrayList<ChessPosition>();
        return(pieceMoves);
    }

    @Override
    public void movePiece(ChessPosition p0, ChessPosition p1) {
    }

    @Override
    public ChessKingState getKingState(ChessColor color) {
        return ChessKingState.KING_SAFE;
    }

    @Override
    public List<ChessType> getRemovedPieces(ChessColor color) {        
        ArrayList <ChessType> removedPieces = new ArrayList<ChessType>();
        return(removedPieces);
    }

    @Override
    public boolean undoLastMove() {
        return true;
    }

    @Override
    public long getPlayerDuration(ChessColor color, boolean isPlaying) {
        return 0;
    }
    
}
