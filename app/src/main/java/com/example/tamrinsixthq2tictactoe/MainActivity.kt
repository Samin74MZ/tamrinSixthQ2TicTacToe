package com.example.tamrinsixthq2tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.tamrinsixthq2tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
 lateinit var binding: ActivityMainBinding
    enum class Turn{
        CROSS,
        NOUGHT
    }
    private var firstTurn=Turn.CROSS
    private var currentTurn=Turn.CROSS

    private var noughtsScore=0
    private var crossesScore=0

    private  var boardList= mutableListOf<Button>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBoard()
    }
    private fun initBoard(){
        boardList.add(binding.button1)
        boardList.add(binding.button2)
        boardList.add(binding.button3)
        boardList.add(binding.button4)
        boardList.add(binding.button5)
        boardList.add(binding.button6)
        boardList.add(binding.button7)
        boardList.add(binding.button8)
        boardList.add(binding.button9)
    }
    fun boardTapped(view:View){
        if (view !is Button)
            return
        addToBoard(view)
        if (checkForVictory(NOUGHT)){
            noughtsScore++
            result("player $NOUGHT winner")
            resetBoard(view)
        }
        if (checkForVictory(CROSS)){
            crossesScore++
            result("player $CROSS winner")
            resetBoard(view)
        }
      if(fullBoard()&&!checkForVictory(CROSS)&&!checkForVictory(NOUGHT)){
          result("Draw")
          resetBoard(view)
      }
    }

    private fun checkForVictory(s: String): Boolean {
        //Horizontal Victory
        if (match(binding.button1,s)&&match(binding.button2,s)&&(match(binding.button3,s)))
        return true
      if (match(binding.button4,s)&&match(binding.button5,s)&&(match(binding.button6,s)))
            return true
      if (match(binding.button7,s)&&match(binding.button8,s)&&(match(binding.button9,s)))
            return true
        //Vertical Victory
        if (match(binding.button1,s)&&match(binding.button4,s)&&(match(binding.button7,s)))
            return true
        if (match(binding.button2,s)&&match(binding.button5,s)&&(match(binding.button8,s)))
            return true
        if (match(binding.button3,s)&&match(binding.button6,s)&&(match(binding.button9,s)))
            return true
        //Diagonal Victory
        if (match(binding.button1,s)&&match(binding.button5,s)&&(match(binding.button9,s)))
            return true
        if (match(binding.button3,s)&&match(binding.button5,s)&&(match(binding.button7,s)))
            return true
           return false
    }
private  fun match(button: Button,symbol:String)=button.text==symbol
    private fun result(title: String) {
        var massage="\n Noughts $noughtsScore \n \n Crosses $crossesScore"
       binding.turnTV.text=title

    }
    private fun resetBoard(view: View){
        if (view==binding.button) {
            for (button in boardList) {
                button.text = ""
            }
            if (firstTurn == Turn.NOUGHT) {
                firstTurn == Turn.CROSS
                binding.turnTV.text= "Turn $CROSS"
            }
            if (firstTurn == Turn.CROSS) {
                firstTurn == Turn.NOUGHT
                binding.turnTV.text= "Turn $NOUGHT"
            }
            currentTurn = firstTurn
        }
    }
    private fun fullBoard(): Boolean {
         for (button in boardList){
             if (button.text=="")
                 return false
         }
        return true
    }

    private fun addToBoard(button: Button) {
      if (button.text!="")
          return
        if (currentTurn==Turn.NOUGHT){
            button.text= NOUGHT
            currentTurn=Turn.CROSS
        }
        else if (currentTurn==Turn.CROSS){
            button.text= CROSS
            currentTurn=Turn.NOUGHT
        }
        setTurnLabel()
    }
    private fun setTurnLabel(){
        var turnText=""
        if (currentTurn==Turn.CROSS){
            turnText="Turn $CROSS"
        }
        if (currentTurn==Turn.NOUGHT){
            turnText="Turn $NOUGHT"
        }
        binding.turnTV.text=turnText
    }
    companion object{
        const val NOUGHT="O"
        const val CROSS="X"
    }
}