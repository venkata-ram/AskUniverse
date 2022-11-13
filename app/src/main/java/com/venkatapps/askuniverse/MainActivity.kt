package com.venkatapps.askuniverse

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amrdeveloper.lottiedialog.LottieDialog
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {
    private lateinit var optionsEditText: TextInputEditText
    private lateinit var backgroundImage: ImageView
    private lateinit var optionsRecyclerView: RecyclerView
    private lateinit var addButton: Button
    private lateinit var clearButton: Button
    private lateinit var askUniverseButton: Button
    private lateinit var adapter: OptionsRecyclerViewAdapter
    private val options = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        setupViews()
        setAdapter()
        setUpListener()
    }

    private fun setupViews() {
        optionsEditText = findViewById(R.id.options_edit_text)
        backgroundImage = findViewById(R.id.background)
        optionsRecyclerView = findViewById(R.id.options_recyclerview)
        addButton = findViewById(R.id.add_button)
        clearButton = findViewById(R.id.clear_button)
        askUniverseButton = findViewById(R.id.ask_universe_btn)

        // set background
        Glide.with(this)
            .load(AppCompatResources.getDrawable(this, R.drawable.img_universe))
            .into(backgroundImage)
    }

    private fun setAdapter() {
        val layoutManager = LinearLayoutManager(this)
        optionsRecyclerView.layoutManager = layoutManager
        adapter = OptionsRecyclerViewAdapter(this)
        optionsRecyclerView.adapter = adapter
        adapter.setData(options)
    }

    private fun setUpListener() {
        addButton.setOnClickListener {
            val option = optionsEditText.text.toString()
            options.add(option)
            adapter.setData(options)
            optionsEditText.text?.clear()
            askUniverseButton.visibility = VISIBLE
        }

        clearButton.setOnClickListener {
            options.clear()
            adapter.setData(options)
            askUniverseButton.visibility = GONE
        }
        askUniverseButton.setOnClickListener {
            if (options.isNotEmpty()) {
                val randomNumber = (0 until options.size).random()
                val universeReply = options[randomNumber].uppercase()
                showResult(universeReply)
            }
        }
        optionsEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                performAdd()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun showResult(universeReply: String) {
        val dialog: LottieDialog = LottieDialog(this)
            .setAnimation(R.raw.output)
            .setAnimationRepeatCount(LottieDialog.INFINITE)
            .setAutoPlayAnimation(true)
            .setTitle("THE UNIVERSE SAYS")
            .setTitleColor(Color.WHITE)
            .setMessage(universeReply)
            .setMessageColor(Color.WHITE)
            .setDialogBackground(Color.parseColor("#1A237E"))
            .setCancelable(true)
        dialog.show()
    }

    private fun performAdd() {
        optionsEditText.clearFocus()
        val `in` : InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        `in`.hideSoftInputFromWindow(optionsEditText.windowToken, 0)
        addButton.performClick()
    }
}