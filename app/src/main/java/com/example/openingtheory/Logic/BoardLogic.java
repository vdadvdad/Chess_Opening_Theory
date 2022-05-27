package com.example.openingtheory.Logic;


import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Square;
import com.github.bhlangonijr.chesslib.move.Move;

abstract public class BoardLogic {
    static public Board board = new Board();
    public void f(){
        board.doMove(new Move(Square.E2, Square.E4));
        System.out.println(board.toString());

    }
    public void getBoard(){

    }
}
