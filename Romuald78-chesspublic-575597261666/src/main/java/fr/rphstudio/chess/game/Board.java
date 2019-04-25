/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IChess.ChessColor;
import fr.rphstudio.chess.interf.IChess.ChessKingState;
import fr.rphstudio.chess.interf.IChess.ChessPosition;
import fr.rphstudio.chess.interf.IChess.ChessType;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author serourabderaouf
 */
public class Board {
    
    private Piece[][] cases ;
    
    public Board() {
        
        this.cases = new Piece [IChess.BOARD_WIDTH][IChess.BOARD_HEIGHT] ;
        
        for(int i=0 ; i < IChess.BOARD_WIDTH ; i++) {
            this.cases[i][IChess.BOARD_POS_Y_WHITE_PAWNS] = new Piece(ChessType.TYP_PAWN, ChessColor.CLR_WHITE, new Pawn()) ;
            this.cases[i][IChess.BOARD_POS_Y_BLACK_PAWNS] = new Piece(ChessType.TYP_PAWN, ChessColor.CLR_BLACK, new Pawn()) ;
        }
        
        // White
        this.cases[IChess.BOARD_POS_X_QUEENSIDE_ROOK][7] = new Piece(ChessType.TYP_ROOK, ChessColor.CLR_WHITE, new Rook()) ;
        this.cases[IChess.BOARD_POS_X_QUEENSIDE_KNIGHT][7] = new Piece(ChessType.TYP_KNIGHT, ChessColor.CLR_WHITE, new Knight()) ;
        this.cases[IChess.BOARD_POS_X_QUEENSIDE_BISHOP][7] = new Piece(ChessType.TYP_BISHOP, ChessColor.CLR_WHITE, new Bishop()) ;
        this.cases[IChess.BOARD_POS_X_QUEEN][7] = new Piece(ChessType.TYP_QUEEN, ChessColor.CLR_WHITE, new Queen()) ;
        this.cases[IChess.BOARD_POS_X_KING][7] = new Piece(ChessType.TYP_KING, ChessColor.CLR_WHITE, new King()) ;
        this.cases[IChess.BOARD_POS_X_KINGSIDE_BISHOP][7] = new Piece(ChessType.TYP_BISHOP, ChessColor.CLR_WHITE, new Bishop()) ; 
        this.cases[IChess.BOARD_POS_X_KINGSIDE_KNIGHT][7] = new Piece(ChessType.TYP_KNIGHT, ChessColor.CLR_WHITE, new Knight()) ;
        this.cases[IChess.BOARD_POS_X_KINGSIDE_ROOK][7] = new Piece(ChessType.TYP_ROOK, ChessColor.CLR_WHITE, new Rook()) ;
        
        // Black
        this.cases[IChess.BOARD_POS_X_QUEENSIDE_ROOK][0] = new Piece(ChessType.TYP_ROOK, ChessColor.CLR_BLACK, new Rook()) ;
        this.cases[IChess.BOARD_POS_X_QUEENSIDE_KNIGHT][0] = new Piece(ChessType.TYP_KNIGHT, ChessColor.CLR_BLACK, new Knight()) ;
        this.cases[IChess.BOARD_POS_X_QUEENSIDE_BISHOP][0] = new Piece(ChessType.TYP_BISHOP, ChessColor.CLR_BLACK, new Bishop()) ;
        this.cases[IChess.BOARD_POS_X_QUEEN][0] = new Piece(ChessType.TYP_QUEEN, ChessColor.CLR_BLACK, new Queen()) ;
        this.cases[IChess.BOARD_POS_X_KING][0] = new Piece(ChessType.TYP_KING, ChessColor.CLR_BLACK, new King()) ;
        this.cases[IChess.BOARD_POS_X_KINGSIDE_BISHOP][0] = new Piece(ChessType.TYP_BISHOP, ChessColor.CLR_BLACK, new Bishop()) ; 
        this.cases[IChess.BOARD_POS_X_KINGSIDE_KNIGHT][0] = new Piece(ChessType.TYP_KNIGHT, ChessColor.CLR_BLACK, new Knight()) ;
        this.cases[IChess.BOARD_POS_X_KINGSIDE_ROOK][0] = new Piece(ChessType.TYP_ROOK, ChessColor.CLR_BLACK, new Rook()) ;
    }
    
    public void movePiece(ChessPosition p0, ChessPosition p1) {
  
        this.cases[p0.x][p0.y].increaseNbMoves();
        
        boolean isRoque = false ;
        
        this.verificationQueen(p0, p1) ;
        
        if (this.cases[p1.x][p1.y] != null) {
            
            if( this.cases[p1.x][p1.y].getChessColor() == this.cases[p0.x][p0.y].getChessColor() ) {
                
                isRoque = true ;
                
                if ((p1.x == 0)&&(p1.y == 0)) {
                    
                    this.cases[3][0] = this.cases[0][0] ;
                    this.cases[0][0] = null ;
                    
                    this.cases[2][0] = this.cases[4][0] ;
                    this.cases[4][0] = null ;
                }
                else if ((p1.x == 7)&&(p1.y == 0)) {
                    
                    this.cases[5][0] = this.cases[7][0] ;
                    this.cases[7][0] = null ;
                    
                    this.cases[6][0] = this.cases[4][0] ;
                    this.cases[4][0] = null ;
                }
                else if ((p1.x == 0)&&(p1.y == 7)) {
                    
                    this.cases[3][7] = this.cases[0][7] ;
                    this.cases[0][7] = null ;
                    
                    this.cases[2][7] = this.cases[4][7] ;
                    this.cases[4][7] = null ;
                }
                else if ((p1.x == 7)&&(p1.y == 7)) {
                    
                    this.cases[5][7] = this.cases[7][7] ;
                    this.cases[7][7] = null ;
                    
                    this.cases[6][7] = this.cases[4][7] ;
                    this.cases[4][7] = null ;
                }
            }
        }
        if(!isRoque) {
            this.cases[p1.x][p1.y] = this.cases[p0.x][p0.y] ;
            this.cases[p0.x][p0.y] = null ; 
        }
    }
    
    private void verificationQueen(ChessPosition p0, ChessPosition p1) {
        
        if(p1.y == 0) {
            
            if(this.cases[p0.x][p0.y].getChessColor() == ChessColor.CLR_WHITE){
                this.cases[p0.x][p0.y] = new Piece(ChessType.TYP_QUEEN, ChessColor.CLR_WHITE, new Queen()) ;
            }
        }
        else if(p1.y == 7) {
            
            if(this.cases[p0.x][p0.y].getChessColor() == ChessColor.CLR_BLACK){
                this.cases[p0.x][p0.y] = new Piece(ChessType.TYP_QUEEN, ChessColor.CLR_BLACK, new Queen()) ;
            }
        }
    }
    
    public ChessKingState getKingState(ChessColor color) {
        
        ChessKingState kingState = ChessKingState.KING_SAFE ;
        ChessPosition kingPosition = this.getKingPosition(color) ;
        
        for(int i=0 ; i < IChess.BOARD_WIDTH; i++) {
            
            for(int j=0 ; j < IChess.BOARD_HEIGHT ; j++) {
                
                Piece piece = this.cases[i][j] ;
                
                if (piece != null) {
                    if (piece.getChessColor() != color) {

                        List<ChessPosition> listPosition = new ArrayList<ChessPosition>() ;
                        listPosition = piece.getMove(new ChessPosition(i,j), this) ;
                        
                        for (ChessPosition cP : listPosition) {
                            
                            if (cP.equals(kingPosition)) {
                                return ChessKingState.KING_THREATEN ;
                            }
                        }
                    }
                }
            }
        }
        
        return kingState ;
    }
    
    private ChessPosition getKingPosition(ChessColor color) {
        
        for(int i=0 ; i < IChess.BOARD_WIDTH; i++) {
            
            for(int j=0 ; j < IChess.BOARD_HEIGHT ; j++) {
                
                Piece piece = this.cases[i][j] ;
                
                if (piece != null) {
                    
                    if ( (piece.getChessType() == ChessType.TYP_KING) && (piece.getChessColor() == color) ) {

                        return new ChessPosition(i, j) ;
                    }
                }
            }
        }
        
        return null ;
    }
    
    public Piece getPiece(ChessPosition position) {
        
        Piece piece = null ;
        
        if ((position.y < 8)&&(position.x < 8)&&(position.y >= 0)&&(position.x >= 0)) {
            piece = this.cases[position.x][position.y] ;
        }
            
        return piece ;
    }
    
    public int getPiecesNombre(ChessColor color){
        
        int pieceNombre = 0 ;
        
        for(int i=0 ; i < IChess.BOARD_WIDTH; i++) {
            
            for(int j=0 ; j < IChess.BOARD_HEIGHT ; j++) {
                
                if (this.cases[i][j] != null) {
                    if (this.cases[i][j].getChessColor().equals(color)) {
                        pieceNombre ++ ;
                    }
                }
            }
        }
        
        return pieceNombre ;
    }
}