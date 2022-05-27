package com.example.openingtheory.Logic;

import static com.example.openingtheory.Logic.BoardLogic.board;

import android.util.Log;

import com.github.bhlangonijr.chesslib.Side;
import com.github.bhlangonijr.chesslib.Square;
import com.github.bhlangonijr.chesslib.move.Move;

public abstract class NewMove {
    static Square square;
    static Square square2;
    public static String SquareClicked(String sq, Move move, Boolean reviseMode){
        Square clickedSquare = Square.fromValue(sq);
        if (square == null){
            square = clickedSquare;
        }
        else{
            square2 = clickedSquare;
            System.out.println(square);
            System.out.println(square2);
            boolean castling = ((square==Square.E1 && square2==Square.G1 && board.getSideToMove()== Side.WHITE)
                    || (square==Square.E8 && square2==Square.G8 && board.getSideToMove()== Side.BLACK)
                    || (square==Square.E1 && square2==Square.C1 && board.getSideToMove()== Side.WHITE)
                    || (square==Square.E8 && square2==Square.C8 && board.getSideToMove()== Side.BLACK));
//            if (!(reviseMode && move.equals(new Move(square, square2)))){
//                Log.d("reviseMode", reviseMode.toString());
//                Log.d("move", move.toString());
//                Log.d("move2", square.toString()+square2.toString());
//                square = null;
//                Log.d("move","failed");
//                return "false";
//            }
            if ((board.isAttackedBy(new Move(square, square2)) || castling) && board.isMoveLegal(new Move(square, square2), true)){
                board.doMove(new Move(square, square2), true);
                System.out.println(board.toString());
                square = null;
            }
            else{
                if (board.getPiece(square2).getPieceSide() == board.getSideToMove()){
                    square = square2;
                    square2 = null;
                }
            }
        }
        return "true";
    }
}
