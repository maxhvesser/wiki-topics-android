package no.mhl.wikitopics.util

import android.widget.RadioGroup

/**
 * Due to there being no easy way to get the index of a currently selected radio button inside of a
 * radio group, this helping function solves the issue. Retrieving the index of a child which is
 * found via a findViewById lookup on the currently selected child id.
 */
fun RadioGroup.checkedIndex(): Int = indexOfChild(findViewById(checkedRadioButtonId))