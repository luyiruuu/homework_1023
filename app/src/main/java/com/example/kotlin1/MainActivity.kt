package com.example.kotlin1

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // 取得Xml元件
        val edName = findViewById<EditText>(R.id.edName)
        val tvText = findViewById<TextView>(R.id.tvText)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val btnMora = findViewById<Button>(R.id.btnMora)
        val tvName = findViewById<TextView>(R.id.tvName)
        val tvWinner = findViewById<TextView>(R.id.tvWinner)
        val tvMyMora = findViewById<TextView>(R.id.tvMyMora)
        val tvTargetMora = findViewById<TextView>(R.id.tvTargetMora)

        // 設定按鈕點擊事件
        btnMora.setOnClickListener {
            val playerName = edName.text.toString()

            // 檢查姓名是否為空
            if (playerName.isEmpty()) {
                tvText.text = "請輸入玩家姓名"
                return@setOnClickListener
            }

            // 取得玩家及電腦的出拳
            val targetMora = (0..2).random()
            val myMora = getPlayerMora(radioGroup.checkedRadioButtonId)

            // 更新 UI
            tvName.text = "名字\n$playerName"
            tvMyMora.text = "我方出拳\n${getMoraString(myMora)}"
            tvTargetMora.text = "電腦出拳\n${getMoraString(targetMora)}"
            tvWinner.text = getWinner(playerName, myMora, targetMora, tvText)
        }
    }

    // 取得玩家出拳
    private fun getPlayerMora(selectedId: Int): Int {
        return when (selectedId) {
            R.id.btnScissor -> 0
            R.id.btnStone -> 1
            else -> 2
        }
    }

    // 傳入 0, 1, 2，回傳對應的文字
    private fun getMoraString(mora: Int): String {
        return when (mora) {
            0 -> "剪刀"
            1 -> "石頭"
            else -> "布"
        }
    }

    // 判斷勝負並更新 UI
    private fun getWinner(playerName: String, myMora: Int, targetMora: Int, tvText: TextView): String {
        return when {
            myMora == targetMora -> {
                tvText.text = "平局，請再試一次！"
                "勝利者\n平手"
            }
            (myMora == 0 && targetMora == 2) ||
                    (myMora == 1 && targetMora == 0) ||
                    (myMora == 2 && targetMora == 1) -> {
                tvText.text = "恭喜你獲勝了！！！"
                "勝利者\n$playerName"
            }
            else -> {
                tvText.text = "可惜，電腦獲勝了！"
                "勝利者\n電腦"
            }
        }
    }
}
