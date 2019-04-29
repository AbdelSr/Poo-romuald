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
    private MoveInfo moveInfo ;
    private Piece lastPieceRemoved ;
    private long timeBlack ;
    private long timeWhite ;
    private long depart ;
    
    
    public Board() {
        
        this.cases = new Piece [IChess.BOARD_WIDTH][IChess.BOARD_HEIGHT] ;
        
        // Creating the table of Pieces of our Chess game
        
        // Creating Pawn black et white
        for(int i=0 ; i < IChess.BOARD_WIDTH ; i++) {
            this.cases[i][IChess.BOARD_POS_Y_WHITE_PAWNS] = new Piece(ChessType.TYP_PAWN, ChessColor.CLR_WHITE, new Pawn()) ;
            this.cases[i][IChess.BOARD_POS_Y_BLACK_PAWNS] = new Piece(ChessType.TYP_PAWN, ChessColor.CLR_BLACK, new Pawn()) ;
        }
        
        // White pieces
        this.cases[IChess.BOARD_POS_X_QUEENSIDE_ROOK][7] = new Piece(ChessType.TYP_ROOK, ChessColor.CLR_WHITE, new Rook()) ;
        this.cases[IChess.BOARD_POS_X_QUEENSIDE_KNIGHT][7] = new Piece(ChessType.TYP_KNIGHT, ChessColor.CLR_WHITE, new Knight()) ;
        this.cases[IChess.BOARD_POS_X_QUEENSIDE_BISHOP][7] = new Piece(ChessType.TYP_BISHOP, ChessColor.CLR_WHITE, new Bishop()) ;
        this.cases[IChess.BOARD_POS_X_QUEEN][7] = new Piece(ChessType.TYP_QUEEN, ChessColor.CLR_WHITE, new Queen()) ;
        this.cases[IChess.BOARD_POS_X_KING][7] = new Piece(ChessType.TYP_KING, ChessColor.CLR_WHITE, new King()) ;
        this.cases[IChess.BOARD_POS_X_KINGSIDE_BISHOP][7] = new Piece(ChessType.TYP_BISHOP, ChessColor.CLR_WHITE, new Bishop()) ; 
        this.cases[IChess.BOARD_POS_X_KINGSIDE_KNIGHT][7] = new Piece(ChessType.TYP_KNIGHT, ChessColor.CLR_WHITE, new Knight()) ;
        this.cases[IChess.BOARD_POS_X_KINGSIDE_ROOK][7] = new Piece(ChessType.TYP_ROOK, ChessColor.CLR_WHITE, new Rook()) ;
        
        // Black pieces
        this.cases[IChess.BOARD_POS_X_QUEENSIDE_ROOK][0] = new Piece(ChessType.TYP_ROOK, ChessColor.CLR_BLACK, new Rook()) ;
        this.cases[IChess.BOARD_POS_X_QUEENSIDE_KNIGHT][0] = new Piece(ChessType.TYP_KNIGHT, ChessColor.CLR_BLACK, new Knight()) ;
        this.cases[IChess.BOARD_POS_X_QUEENSIDE_BISHOP][0] = new Piece(ChessType.TYP_BISHOP, ChessColor.CLR_BLACK, new Bishop()) ;
        this.cases[IChess.BOARD_POS_X_QUEEN][0] = new Piece(ChessType.TYP_QUEEN, ChessColor.CLR_BLACK, new Queen()) ;
        this.cases[IChess.BOARD_POS_X_KING][0] = new Piece(ChessType.TYP_KING, ChessColor.CLR_BLACK, new King()) ;
        this.cases[IChess.BOARD_POS_X_KINGSIDE_BISHOP][0] = new Piece(ChessType.TYP_BISHOP, ChessColor.CLR_BLACK, new Bishop()) ; 
        this.cases[IChess.BOARD_POS_X_KINGSIDE_KNIGHT][0] = new Piece(ChessType.TYP_KNIGHT, ChessColor.CLR_BLACK, new Knight()) ;
        this.cases[IChess.BOARD_POS_X_KINGSIDE_ROOK][0] = new Piece(ChessType.TYP_ROOK, ChessColor.CLR_BLACK, new Rook()) ;
        
        // Initialisation of the white/black pieces timer 
        this.timeBlack = 0 ;
        this.timeWhite = 0 ;
        
        // Initialisation of the start time (when the application is started and the Board is instancied)
        this.depart = System.currentTimeMillis();
    }
    
    // This function is in charge of moving pieces
    public Piece movePiece(ChessPosition p0, ChessPosition p1) {
  
        // We save the position before moving 
        this.moveInfo = new MoveInfo(p0, p1, this) ;
        Piece removedPiece = null ;
        
        // Increase the number of moves for the piece we are going to move
        this.cases[p0.x][p0.y].increaseNbMoves();
        
        boolean isRoque = false ;
        
        // Verification if the piece is a pawn who is going to be a Queen
        this.verificationQueen(p0, p1) ;
        
        if (this.cases[p1.x][p1.y] != null) {
            
            // If it's a roque
            if( this.cases[p1.x][p1.y].getChessColor() == this.cases[p0.x][p0.y].getChessColor() ) {
                
                isRoque = true ;
                
                // Left Roque
                if ((p1.x == 0)) {
                    
                    this.cases[3][p1.y] = this.cases[0][p1.y] ;
                    this.cases[0][p1.y] = null ;
                    
                    this.cases[2][p1.y] = this.cases[4][p1.y] ;
                    this.cases[4][p1.y] = null ;
                }
                // Right Roque
                else if (p1.x == 7) {
                    
                    this.cases[5][p1.y] = this.cases[7][p1.y] ;
                    this.cases[7][p1.y] = null ;
                    
                    this.cases[6][p1.y] = this.cases[4][p1.y] ;
                    this.cases[4][p1.y] = null ;
                }
            }
            // If the position we are going to is a ennemie's piece, we add it in our attribut removedPiece ;
            else {
                removedPiece = this.cases[p1.x][p1.y] ;
            }
        }
        // Every other move ecxept the roque
        if(!isRoque) {
            this.cases[p1.x][p1.y] = this.cases[p0.x][p0.y] ;
            this.cases[p0.x][p0.y] = null ; 
        }
        
        return removedPiece ;
    }
    
    // Verification if the piece is going to be a queen
    private void verificationQueen(ChessPosition p0, ChessPosition p1) {
        
        if (this.cases[p0.x][p0.y].getChessType() == ChessType.TYP_PAWN) {
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
    }
    
    // Return the State of the king of the color in parameters
    public ChessKingState getKingState(ChessColor color) {
        
        ChessKingState kingState = ChessKingState.KING_SAFE ;
        
        ChessPosition kingPosition = this.getKingPosition(color) ;
        
        // Getting all ennemie's pieces possibles movements and checking if they can CheckMate our king
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
    
    // Return the position of the king whith the color in parameters
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
    
    // Return the piece in the position x if it's in the board, it can be null
    public Piece getPiece(ChessPosition position) {
        
        Piece piece = null ;
        
        if ((position.y < 8)&&(position.x < 8)&&(position.y >= 0)&&(position.x >= 0)) {
            piece = this.cases[position.x][position.y] ;
        }
            
        return piece ;
    }
    
    // Return a list of possible position who don't put our king in checkmated state
    public List<ChessPosition> getKingSafePositionMove(ChessPosition p) {
        
        List<ChessPosition> newListPosition = new ArrayList<ChessPosition>() ;
        List<ChessPosition> listPosition = this.cases[p.x][p.y].getMove(p, this) ;
        
        // For every possible position we do the move and check if our king is safe or not, if he isn't we add it to the new list and we reiniatialise the preview position 
        for (ChessPosition position : listPosition) {
            
            Piece pieceTemporaire = this.cases[position.x][position.y] ;
            Piece pieceOrigine = this.cases[p.x][p.y] ;
            this.cases[position.x][position.y] = pieceOrigine ;
            this.cases[p.x][p.y] = null ;

            if (this.getKingState(this.cases[position.x][position.y].getChessColor()) == ChessKingState.KING_SAFE) {
            newListPosition.add(position) ;
            }

            this.cases[position.x][position.y] = pieceTemporaire ;
            this.cases[p.x][p.y] = pieceOrigine ;
        }
        
        return newListPosition ;
    }
    
    // Return the number of pieces with the color we put in paramaters
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
    
    // Return the last piece removed
    public Piece getLastPieceRemoved() {
        return this.lastPieceRemoved ;
    }
    
    // rewind
    public boolean remontada() {
        
        if (this.moveInfo != null) {
            
            this.lastPieceRemoved = this.moveInfo.piece1 ;
            
            this.cases[this.moveInfo.p0.x][this.moveInfo.p0.y] = this.moveInfo.piece0 ;
            this.cases[this.moveInfo.p1.x][this.moveInfo.p1.y] = this.moveInfo.piece1 ;
            
            if (this.moveInfo.isRoque()) {
                
                this.cases[this.moveInfo.p1.x][this.moveInfo.p1.y].reinitialiseNbMoves();
                this.cases[this.moveInfo.p0.x][this.moveInfo.p0.y].reinitialiseNbMoves();
                
                if (this.moveInfo.p1.x == 0) {
                    this.cases[3][this.moveInfo.p1.y] = null ;
                    this.cases[2][this.moveInfo.p1.y] = null ;
                }
                else if (this.moveInfo.p1.x == 7) {
                    this.cases[6][this.moveInfo.p1.y] = null ;
                    this.cases[5][this.moveInfo.p1.y] = null ;
                }
            }

            this.moveInfo = null ;
            
            return true ;
        }
        
        return false ;
    }
    
    public long getTimeWhite() {
        return this.timeWhite ;
    }
    
    public long getTimeBlack() {
        return this.timeBlack ;
    }
    
    public void setTimeBlack(Long time) {
        this.timeBlack = time ;
    }
    
    public void setTimeWhite(Long time) {
        this.timeWhite = time ;
    }
    
    public long getPlayerDuration(ChessColor color, boolean isPlaying) {
        
        if (isPlaying) {
            if (color == ChessColor.CLR_BLACK) {
                
                this.timeBlack = System.currentTimeMillis() - this.depart - this.timeWhite;
                return this.timeBlack ;
            }
            else {
                
                this.timeWhite = System.currentTimeMillis() - this.depart - this.timeBlack;
                return this.timeWhite ;
            }
        }
        
        return 0 ;
    }
    
    private boolean isSafe(ChessPosition p){
        
        boolean safe = true ;
        
        boolean breakBoucle = false ;
        
        for(int i=0 ; i < IChess.BOARD_WIDTH; i++) {
            
            for(int j=0 ; j < IChess.BOARD_HEIGHT ; j++) {
            
                if (this.cases[i][j] != null) {
                    
                    if (this.cases[i][j].getChessColor() == ChessColor.CLR_WHITE) {
                        
                        ChessPosition cp = new ChessPosition(i, j) ;
                        List<ChessPosition> listPosition = this.cases[i][j].getMove(cp, this) ;
                        
                        for (ChessPosition position : listPosition) {
                            
                            if (this.cases[position.x][position.y] == this.cases[p.x][p.y]) {
                            
                                safe = false ;
                                breakBoucle = true ;
                                break ;
                            }
                        }
                    }
                }
                
                if (breakBoucle) {
                    break ;
                }
            }
            
            if (breakBoucle) {
                break ;
            }
        }
        
        return safe ;
    }
            
    
    public Piece tourIA() {
        
        List <ChessPosition[]> listPositionDoubleAttackKingSafe = new ArrayList<ChessPosition[]>() ;
        List <ChessPosition[]> listPositionDoubleAttackKing = new ArrayList<ChessPosition[]>() ;
        List <ChessPosition[]> listPositionAttackKingSafe = new ArrayList<ChessPosition[]>() ;
        List <ChessPosition[]> listPositionAttackKing = new ArrayList<ChessPosition[]>() ;
        List <ChessPosition[]> listPositionAttackGoodPieceSafe = new ArrayList<ChessPosition[]>() ;
        List <ChessPosition[]> listPositionAttackGoodPiece = new ArrayList<ChessPosition[]>() ;
        List <ChessPosition[]> listPositionAttackSafe = new ArrayList<ChessPosition[]>() ;
        List <ChessPosition[]> listPositionAttack = new ArrayList<ChessPosition[]>() ;
        List <ChessPosition[]> listPositionDefense = new ArrayList<ChessPosition[]>() ;
        List <ChessPosition[]> listPositionNeutre = new ArrayList<ChessPosition[]>() ;
        Piece returnPiece = null ;
       
        for(int i=0 ; i < IChess.BOARD_WIDTH; i++) {
            
            for(int j=0 ; j < IChess.BOARD_HEIGHT ; j++) {
                
                if (this.cases[i][j] != null) {
                    
                    if (this.cases[i][j].getChessColor() == ChessColor.CLR_BLACK) {

                        ChessPosition cp = new ChessPosition(i, j) ;
                        List<ChessPosition> oldListPosition = this.cases[i][j].getMove(cp, this) ;
                        
                        for (ChessPosition position : oldListPosition) {
                                
                            Piece pieceTemporaire = this.cases[position.x][position.y] ;
                            Piece pieceOrigine = this.cases[i][j] ;
                            this.cases[position.x][position.y] = pieceOrigine ;
                            this.cases[i][j] = null ;

                            if ((this.getKingState(ChessColor.CLR_BLACK) == ChessKingState.KING_SAFE) && (this.getKingState(ChessColor.CLR_WHITE) == ChessKingState.KING_THREATEN) && (pieceTemporaire != null)) {
                                
                                ChessPosition posDepart = new ChessPosition(i, j) ;
                                ChessPosition posArrive = new ChessPosition(position.x, position.y) ;
                                ChessPosition[] positions = {posDepart, posArrive} ;
                                
                                if (this.isSafe(posArrive)) {
                                    listPositionDoubleAttackKingSafe.add(positions) ;
                                }
                                else {
                                    listPositionDoubleAttackKing.add(positions) ;
                                }
                            }
                            if ((this.getKingState(ChessColor.CLR_BLACK) == ChessKingState.KING_SAFE) && (this.getKingState(ChessColor.CLR_WHITE) == ChessKingState.KING_THREATEN)) {
                                    
                                ChessPosition posDepart = new ChessPosition(i, j) ;
                                ChessPosition posArrive = new ChessPosition(position.x, position.y) ;
                                ChessPosition[] positions = {posDepart, posArrive} ;
                                
                                if (this.isSafe(posArrive)) {
                                    listPositionAttackKingSafe.add(positions) ;
                                }
                                else {
                                    listPositionAttackKing.add(positions) ;
                                }
                            }
                            if ((this.getKingState(ChessColor.CLR_BLACK) == ChessKingState.KING_SAFE) && (pieceTemporaire != null)) {
                                
                                if (pieceTemporaire.getChessType() != ChessType.TYP_PAWN) {
                            
                                    ChessPosition posDepart = new ChessPosition(i, j) ;
                                    ChessPosition posArrive = new ChessPosition(position.x, position.y) ;
                                    ChessPosition[] positions = {posDepart, posArrive} ;
                                    
                                    if (this.isSafe(posArrive)) {
                                        listPositionAttackGoodPieceSafe.add(positions) ;
                                    }
                                    else {
                                        listPositionAttackGoodPiece.add(positions) ;
                                    }
                                }
                                else {
                                    ChessPosition posDepart = new ChessPosition(i, j) ;
                                    ChessPosition posArrive = new ChessPosition(position.x, position.y) ;
                                    ChessPosition[] positions = {posDepart, posArrive} ;
                                    
                                    if (this.isSafe(posArrive)) {
                                        listPositionAttackSafe.add(positions) ;
                                    }
                                    else {
                                        listPositionAttack.add(positions) ;
                                    }
                                }
                            }
                            if (this.getKingState(ChessColor.CLR_BLACK) == ChessKingState.KING_SAFE) {
                                
                                ChessPosition posDepart = new ChessPosition(i, j) ;
                                ChessPosition posArrive = new ChessPosition(position.x, position.y) ;
                                ChessPosition[] positions = {posDepart, posArrive} ;
                                
                                if ((this.isSafe(posArrive)) && (pieceOrigine.getChessType() != ChessType.TYP_PAWN)) {
                                        listPositionDefense.add(positions) ;
                                    }
                                else {
                                    listPositionNeutre.add(positions) ;
                                }
                            }

                            this.cases[position.x][position.y] = pieceTemporaire ;
                            this.cases[i][j] = pieceOrigine ;
                        }
                    }
                }
            }
        }
        
        if (!listPositionDoubleAttackKingSafe.isEmpty()) {
            
            int nombreSolutions = listPositionDoubleAttackKingSafe.size() ;
            int nombreH = (int)(Math.random() * nombreSolutions);
            
            ChessPosition[] positions = listPositionDoubleAttackKingSafe.get(nombreH) ;
            
            returnPiece = this.movePiece(positions[0], positions[1]) ;
        }
        else if (!listPositionAttackKingSafe.isEmpty()){
            
            int nombreSolutions = listPositionAttackKingSafe.size() ;
            int nombreH = (int)(Math.random() * nombreSolutions);
            
            ChessPosition[] positions = listPositionAttackKingSafe.get(nombreH) ;
            
            returnPiece = this.movePiece(positions[0], positions[1]) ;
        }
        else if (!listPositionAttackGoodPieceSafe.isEmpty()){
            
            int nombreSolutions = listPositionAttackGoodPieceSafe.size() ;
            int nombreH = (int)(Math.random() * nombreSolutions);
            
            ChessPosition[] positions = listPositionAttackGoodPieceSafe.get(nombreH) ;
            
            returnPiece = this.movePiece(positions[0], positions[1]) ;
        }
        else if (!listPositionDefense.isEmpty()){
            
            int nombreSolutions = listPositionDefense.size() ;
            int nombreH = (int)(Math.random() * nombreSolutions);
            
            ChessPosition[] positions = listPositionDefense.get(nombreH) ;
            
            returnPiece = this.movePiece(positions[0], positions[1]) ;
        }
        else if (!listPositionAttackSafe.isEmpty()){
            
            int nombreSolutions = listPositionAttackSafe.size() ;
            int nombreH = (int)(Math.random() * nombreSolutions);
            
            ChessPosition[] positions = listPositionAttackSafe.get(nombreH) ;
            
            returnPiece = this.movePiece(positions[0], positions[1]) ;
        }
        else if (!listPositionDoubleAttackKing.isEmpty()){
            
            int nombreSolutions = listPositionDoubleAttackKing.size() ;
            int nombreH = (int)(Math.random() * nombreSolutions);
            
            ChessPosition[] positions = listPositionDoubleAttackKing.get(nombreH) ;
            
            returnPiece = this.movePiece(positions[0], positions[1]) ;
        }
        else if (!listPositionAttackGoodPiece.isEmpty()){
            
            int nombreSolutions = listPositionAttackGoodPiece.size() ;
            int nombreH = (int)(Math.random() * nombreSolutions);
            
            ChessPosition[] positions = listPositionAttackGoodPiece.get(nombreH) ;
            
            returnPiece = this.movePiece(positions[0], positions[1]) ;
        }
        else if (!listPositionAttack.isEmpty()){
            
            int nombreSolutions = listPositionAttack.size() ;
            int nombreH = (int)(Math.random() * nombreSolutions);
            
            ChessPosition[] positions = listPositionAttack.get(nombreH) ;
            
            returnPiece = this.movePiece(positions[0], positions[1]) ;
        }
        
        
        return returnPiece ;
    }
}