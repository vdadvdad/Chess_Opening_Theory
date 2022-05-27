package com.example.openingtheory

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.openingtheory.Logic.BoardLogic
import com.example.openingtheory.Logic.NewMove
import com.github.bhlangonijr.chesslib.Piece
import com.github.bhlangonijr.chesslib.Square

@Composable
fun Training(navController: NavController){
    for (i in 1..64){
        piecesList.add(R.drawable.empty_square)
    }
    for (i in 1..8){
        for (j in 1..8){
            var square = (64 + j).toChar() + (9 - i).toString()
            var piece = BoardLogic.board.getPiece(Square.fromValue(square))
            when (piece){
                Piece.BLACK_BISHOP -> piecesList[(j - 1) * 8 + i - 1] = R.drawable.black_bishop
                Piece.BLACK_QUEEN -> piecesList[(j - 1) * 8 + i - 1] = R.drawable.black_queen
                Piece.BLACK_PAWN -> piecesList[(j - 1) * 8 + i - 1] = R.drawable.black_pawn
                Piece.BLACK_ROOK -> piecesList[(j - 1) * 8 + i - 1] = R.drawable.black_rook
                Piece.BLACK_KNIGHT -> piecesList[(j - 1) * 8 + i - 1] = R.drawable.black_knight
                Piece.BLACK_KING -> piecesList[(j - 1) * 8 + i - 1] = R.drawable.black_king
                Piece.WHITE_BISHOP -> piecesList[(j - 1) * 8 + i - 1] = R.drawable.white_bishop
                Piece.WHITE_QUEEN -> piecesList[(j - 1) * 8 + i - 1] = R.drawable.white_queen
                Piece.WHITE_PAWN -> piecesList[(j - 1) * 8 + i - 1] = R.drawable.white_pawn
                Piece.WHITE_ROOK -> piecesList[(j - 1) * 8 + i - 1] = R.drawable.white_rook
                Piece.WHITE_KNIGHT -> piecesList[(j - 1) * 8 + i - 1] = R.drawable.white_knight
                Piece.WHITE_KING -> piecesList[(j - 1) * 8 + i - 1] = R.drawable.white_king
                Piece.NONE -> piecesList[(j - 1) * 8 + i - 1] = R.drawable.empty_square
            }

        }
    }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        Card(modifier = Modifier
            .width(300.dp)
            .height(300.dp)
            .padding(10.dp)){
            Column(){
                for (i in 1..8){
                    Row(modifier = Modifier.weight(1f)){
                        for (j in 1..8){
                            if ((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)){
                                var square = (64 + j).toChar() + (9 - i).toString()
                                SquareButton(color = Color.Gray, size = 35.dp, square = square, i,j)
                            }
                            else{
                                var square = (64 + j).toChar() + (9 - i).toString()
                                SquareButton(color = Color.Green, size = 35.dp, square = square,i,j)
                            }
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun SquareButton(color: Color, size: Dp, square: String, i: Int, j: Int){
    Button(
        onClick = {
            NewMove.SquareClicked(square)
            for (i in 1..8){
                for (j in 1..8){
                    var square = (64 + j).toChar() + (9 - i).toString()
                    var piece = BoardLogic.board.getPiece(Square.fromValue(square))
                    when (piece){
                        Piece.BLACK_BISHOP -> piecesList[(j - 1) * 8 + i - 1] = R.drawable.black_bishop
                        Piece.BLACK_QUEEN -> piecesList[(j - 1) * 8 + i - 1] = R.drawable.black_queen
                        Piece.BLACK_PAWN -> piecesList[(j - 1) * 8 + i - 1] = R.drawable.black_pawn
                        Piece.BLACK_ROOK -> piecesList[(j - 1) * 8 + i - 1] = R.drawable.black_rook
                        Piece.BLACK_KNIGHT -> piecesList[(j - 1) * 8 + i - 1] = R.drawable.black_knight
                        Piece.BLACK_KING -> piecesList[(j - 1) * 8 + i - 1] = R.drawable.black_king
                        Piece.WHITE_BISHOP -> piecesList[(j - 1) * 8 + i - 1] = R.drawable.white_bishop
                        Piece.WHITE_QUEEN -> piecesList[(j - 1) * 8 + i - 1] = R.drawable.white_queen
                        Piece.WHITE_PAWN -> piecesList[(j - 1) * 8 + i - 1] = R.drawable.white_pawn
                        Piece.WHITE_ROOK -> piecesList[(j - 1) * 8 + i - 1] = R.drawable.white_rook
                        Piece.WHITE_KNIGHT -> piecesList[(j - 1) * 8 + i - 1] = R.drawable.white_knight
                        Piece.WHITE_KING -> piecesList[(j - 1) * 8 + i - 1] = R.drawable.white_king
                        Piece.NONE -> piecesList[(j - 1) * 8 + i - 1] = R.drawable.empty_square
                    }

                }
            }
        },

        modifier = Modifier.size(size),
        colors = ButtonDefaults.buttonColors(color),
        shape = RectangleShape,
        contentPadding = PaddingValues(0.dp),
        elevation = ButtonDefaults.elevation(0.dp),
    ){
        print(square)
        Image(painter = painterResource((piecesList[(j - 1) * 8 + i - 1])),
            modifier = Modifier.fillMaxSize(0.8f),
            contentDescription = null)
    }
}