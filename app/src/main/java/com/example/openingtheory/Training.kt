package com.example.openingtheory

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.openingtheory.Logic.BoardLogic
import com.example.openingtheory.Logic.BoardLogic.board
import com.example.openingtheory.Logic.NewMove
import com.github.bhlangonijr.chesslib.Board
import com.github.bhlangonijr.chesslib.Piece
import com.github.bhlangonijr.chesslib.Square
import com.github.bhlangonijr.chesslib.move.Move
import com.github.bhlangonijr.chesslib.move.MoveList
var movesEnded = 0
var text = mutableStateOf("")
var piecesList: MutableList<Int> = mutableStateListOf()
var moveList: MoveList = MoveList()
var currentMove = 0
var boardEnabled = false
var reviseMode = mutableStateOf(false)
var reviseStarted = false
fun getCurrentMove(currentMove: Int): Move {
    return moveList[currentMove]
}
@Composable
fun Training(navController: NavController, san: String){
    moveList.loadFromSan(san)
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
        Text(text = text.value, textAlign = TextAlign.Center, fontSize = 20.sp)
        Card(modifier = Modifier
            .width(300.dp)
            .height(300.dp)
            .padding(10.dp)){
            Column(){
                val context = LocalContext.current
                for (i in 1..8){
                    Row(modifier = Modifier.weight(1f)){
                        for (j in 1..8){
                            if ((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)){
                                var square = (64 + j).toChar() + (9 - i).toString()
                                SquareButton(color = Color.Gray, size = 35.dp, square = square, i,j, context)
                            }
                            else{
                                var square = (64 + j).toChar() + (9 - i).toString()
                                SquareButton(color = Color.Green, size = 35.dp, square = square,i,j, context)
                            }
                        }
                    }
                }
            }
        }
        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()){
            Button(onClick = {
                if (currentMove < 9 && !reviseMode.value){
                    board.doMove(moveList[currentMove])
                    currentMove += 1
                    updateBoard()
                }
                if (currentMove == 9){
                    reviseMode.value = true
                    currentMove = 0
                }
            },
                colors = ButtonDefaults.buttonColors(Color.Green),
                enabled = !reviseMode.value){
                Text("→")
            }
        }
        Row(horizontalArrangement = Arrangement.Center){
            Button(onClick = {
                board = Board()
                reviseStarted = true
                boardEnabled = true
                currentMove = 0

            },
            colors = ButtonDefaults.buttonColors(Color.Green),
            enabled = (reviseMode.value && !reviseStarted)){
                Text("Перейти к закреплению")
            }
        }
    }

}
@Composable
fun SquareButton(color: Color, size: Dp, square: String, i: Int, j: Int, context: Context){
    Button(
        onClick = {
            if (boardEnabled && reviseMode.value){
                Log.d("currentMove", getCurrentMove(currentMove).toString())
                Log.d("currentMoveInt", currentMove.toString())
                var r = NewMove.SquareClicked(square, getCurrentMove(currentMove), reviseStarted)
                Log.d("r", r)
                updateBoard()
                if (r.equals("true")){
                    currentMove += 1
                }

                else{
                    Toast.makeText(context, "Неверный ход", Toast.LENGTH_SHORT)
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
fun updateBoard(){
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
}