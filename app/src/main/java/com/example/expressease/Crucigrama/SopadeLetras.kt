package com.example.expressease.Crucigrama

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.expressease.R

class SopadeLetras : AppCompatActivity() {
    private lateinit var crosswordTable: TableLayout
    private lateinit var checkButton: Button
    private val letters = arrayOf(
        arrayOf("H", "A", "L", "A", "T", "H", "O", "L", "A", "I", "S", "I", "O", "N"),
        arrayOf("C", "E", "L", "K", "L", "P", "P", "S", "I", "L", "L", "C", "P", "N"),
        arrayOf("P", "A", "L", "A", "B", "R", "A", "N", "I", "P", "J", "E", "T", "I"),
        arrayOf("M", "G", "N", "D", "A", "C", "N", "N", "C", "O", "E", "L", "L", "A"),
        arrayOf("U", "T", "E", "L", "E", "V", "I", "S", "I", "O", "N", "U", "X", "M"),
        arrayOf("N", "O", "B", "J", "E", "T", "I", "V", "O", "I", "F", "L", "O", "N"),
        arrayOf("D", "E", "L", "A", "L", "B", "R", "S", "I", "L", "L", "A", "P", "N"),
        arrayOf("O", "A", "L", "P", "B", "O", "A", "N", "I", "O", "J", "R", "T", "I"),
        arrayOf("M", "L", "N", "N", "U", "C", "I", "N", "C", "O", "E", "E", "L", "A"),
        arrayOf("I", "N", "T", "R", "O", "D", "U", "C", "I", "O", "N", "N", "X", "M")
    )
    private val wordsToFind = listOf(
        "HOLA", "SILLA", "TELEVISION", "MUNDO", "CELULAR",
        "PALABRA", "OBJETIVO", "PAN", "INTRODUCION", "CINCO"
    )
    private val selectedCells = HashSet<Pair<Int, Int>>()

    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sopade_letras)

        crosswordTable = findViewById(R.id.crosswordTable)
        fillCrosswordTable()

        checkButton = findViewById(R.id.checkButton)
        checkButton.setOnClickListener {
            checkAllWordsSelected()
        }
    }


    private fun fillCrosswordTable() {
        for (rowIndex in letters.indices) {
            val tableRow = TableRow(this)
            for (colIndex in letters[rowIndex].indices) {
                val cardView = CardView(this)
                val params = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(5, 5, 5, 5)
                cardView.layoutParams = params
                cardView.radius = 8f
                cardView.setContentPadding(20, 20, 20, 20)
                cardView.setCardBackgroundColor(Color.WHITE)
                cardView.cardElevation = 2f

                val textView = TextView(this)
                textView.text = letters[rowIndex][colIndex]
                textView.gravity = Gravity.CENTER
                textView.setTextColor(Color.BLACK)
                cardView.addView(textView)

                cardView.setOnClickListener {
                    handleCellSelection(rowIndex, colIndex, cardView)
                }

                tableRow.addView(cardView)
            }
            crosswordTable.addView(tableRow)
        }
    }

    private fun checkWords() {
        for (word in wordsToFind) {
            // Check horizontally
            for (row in letters.indices) {
                val rowString = letters[row].joinToString("")
                if (rowString.contains(word)) {
                    markWord(row, rowString.indexOf(word), row, rowString.indexOf(word) + word.length - 1)
                    return
                }
            }
            // Check vertically
            for (col in letters[0].indices) {
                val colString = (0 until letters.size).joinToString("") { letters[it][col].toString() }
                if (colString.contains(word)) {
                    markWord(colString.indexOf(word), col, colString.indexOf(word) + word.length - 1, col)
                    return
                }
            }
        }
    }

    private fun markWord(startRow: Int, startCol: Int, endRow: Int, endCol: Int) {
        for (row in startRow..endRow) {
            for (col in startCol..endCol) {
                val cardView = (crosswordTable.getChildAt(row) as TableRow).getChildAt(col) as CardView
                cardView.setCardBackgroundColor(Color.YELLOW)
                selectedCells.add(Pair(row, col))
            }
        }
    }

    private fun handleCellSelection(row: Int, col: Int, cardView: CardView) {
        val cell = Pair(row, col)
        if (!selectedCells.contains(cell)) {
            selectedCells.add(cell)
            cardView.setCardBackgroundColor(Color.YELLOW)
        } else {
            selectedCells.remove(cell)
            cardView.setCardBackgroundColor(Color.WHITE)
        }
        checkWords()
    }

    private fun checkAllWordsSelected() {
        val allWordsSelected = wordsToFind.all { word ->
            selectedCells.any { cell ->
                val (row, col) = cell
                word == getHorizontalWord(row, col) || word == getVerticalWord(row, col)
            }
        }
    }
    private fun getHorizontalWord(row: Int, col: Int): String {
        val rowString = letters[row].joinToString("")
        return rowString.substring(col)
    }

    private fun getVerticalWord(row: Int, col: Int): String {
        val colString = (0 until letters.size).joinToString("") { letters[it][col].toString() }
        return colString.substring(row)
    }
}