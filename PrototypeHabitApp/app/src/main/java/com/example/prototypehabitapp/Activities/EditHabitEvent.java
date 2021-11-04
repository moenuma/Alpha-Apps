/**
 * Copyright 2021 - 2021 CMPUT301F21T03 (Alpha-Apps). All rights reserved. This document nor any
 * part of it may be reproduced, stored in a retrieval system or transmitted in any for or by any
 * means without prior permission of the members of CMPUT301F21T03 or by the professor and any
 * authorized TAs of the CMPUT301 class at the University of Alberta, fall term 2021.
 *
 * Class: EditHabitEvent
 *
 * Description: Handles the user interactions of the event edit page
 *
 * Changelog:
 * =|Version|=|User(s)|==|Date|========|Description|================================================
 *   1.0       Mathew    Oct-21-2021   Created
 *   1.1       Moe       Oct-29-2021   Set up complete button
 *   1.2       Moe       Nov-01-2021   Added receiving event from intent and editing event's comment
 *   1.3       Moe&Jesse Nov-03-2021   Added passing event to intent when complete button is pressed
 *   1.4       Moe       Nov-04-2021   Changed the editHabitEventCompleteButtonPressed function
 *                                      depending on the activity that is passed from
 * =|=======|=|======|===|====|========|===========|================================================
 */

package com.example.prototypehabitapp.Activities;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prototypehabitapp.DataClasses.Event;
import com.example.prototypehabitapp.DataClasses.Habit;
import com.example.prototypehabitapp.R;

import java.util.ArrayList;
import java.util.Objects;

public class EditHabitEvent extends AppCompatActivity {

    private Event event;
    private Habit habit;
    private String prevActivity;
    private ComponentName previousActivity;
    private EditText comments;
    private ArrayList<Event> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the display to be the main page
        setContentView(R.layout.edit_habit_event);

        //get details from bundle
        Intent sentIntent = getIntent();
        event = (Event) sentIntent.getSerializableExtra("EVENT");
        habit = (Habit) sentIntent.getSerializableExtra("HABIT");
        prevActivity = (String) sentIntent.getSerializableExtra("ACTIVITY");
        previousActivity = getCallingActivity();

        comments = findViewById(R.id.edithabitevent_comment);
        comments.setText(event.getComment());

        Button completeButton = findViewById(R.id.edithabitevent_complete);
        completeButton.setOnClickListener(this::editHabitEventCompleteButtonPressed);
    }

    public void editHabitEventCompleteButtonPressed(View view) {
        String commentStr = (String) comments.getText().toString();
        event.setComment(commentStr);
        Intent intent;
        if (Objects.equals("HabitDetails", prevActivity)) {
            finish();
        } else if (Objects.equals("HabitEventDetails", prevActivity)) {
            intent = new Intent(this, HabitEventDetails.class);
            intent.putExtra("HABIT", habit);
            intent.putExtra("EVENT", event);
            startActivity(intent);
        }

    }
}
