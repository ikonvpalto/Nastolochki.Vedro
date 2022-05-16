package org.kvpbldsck.nastolochki.vedro.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.michalsvec.singlerowcalendar.calendar.CalendarChangesObserver
import com.michalsvec.singlerowcalendar.calendar.CalendarViewManager
import com.michalsvec.singlerowcalendar.calendar.SingleRowCalendar
import com.michalsvec.singlerowcalendar.calendar.SingleRowCalendarAdapter
import com.michalsvec.singlerowcalendar.selection.CalendarSelectionManager
import com.michalsvec.singlerowcalendar.utils.DateUtils
import org.kvpbldsck.nastolochki.vedro.R
import org.kvpbldsck.nastolochki.vedro.activities.MainActivity
import java.util.*

class EventsFragment : Fragment() {

    private val calendar = Calendar.getInstance()
    private var currentMonth = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        calendar.time = Date()
        currentMonth = calendar[Calendar.MONTH]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_events, container, false)

        setupToolbar(view)
        setupOneLineCalendar(view)

        return view
    }

    private fun setupToolbar(view: View) {
        val toolbar = view.getToolbar()

        (activity as MainActivity).setSupportActionBar(toolbar)
    }

    private fun setupOneLineCalendar(view: View) {
        val calendarViewManager = object : CalendarViewManager {
            override fun setCalendarViewResourceId(
                position: Int,
                date: Date,
                isSelected: Boolean
            ): Int {
                val cal = Calendar.getInstance()
                cal.time = date

                return if (isSelected)
                    R.layout.selected_calendar_item
                else
                    R.layout.calendar_item
            }

            override fun bindDataToCalendarView(
                holder: SingleRowCalendarAdapter.CalendarViewHolder,
                date: Date,
                position: Int,
                isSelected: Boolean
            ) {
                holder.itemView.findViewById<TextView>(R.id.calendar_item_day_of_week).text = DateUtils.getDay3LettersName(date)
                holder.itemView.findViewById<TextView>(R.id.calendar_item_day_of_month).text = DateUtils.getDayNumber(date)
            }
        }

        val calendarChangesObserver = object : CalendarChangesObserver {
        }

        val calendarSelectionManager = object : CalendarSelectionManager {
            override fun canBeItemSelected(position: Int, date: Date): Boolean = true
        }

        view.findViewById<SingleRowCalendar>(R.id.events_single_row_calendar)
            .apply {
                this.calendarViewManager = calendarViewManager
                this.calendarChangesObserver = calendarChangesObserver
                this.calendarSelectionManager = calendarSelectionManager
                setDates(getFutureDatesOfCurrentMonth())
                init()
            }
    }

    private fun getFutureDatesOfCurrentMonth(): List<Date> {
        // get all next dates of current month
        currentMonth = calendar[Calendar.MONTH]
        return getDates(mutableListOf())
    }


    private fun getDates(list: MutableList<Date>): List<Date> {
        // load dates of whole month
        calendar.set(Calendar.MONTH, currentMonth)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        list.add(calendar.time)
        while (currentMonth == calendar[Calendar.MONTH]) {
            calendar.add(Calendar.DATE, +1)
            if (calendar[Calendar.MONTH] == currentMonth)
                list.add(calendar.time)
        }
        calendar.add(Calendar.DATE, -1)
        return list
    }

    private fun View.getToolbar(): Toolbar = findViewById(R.id.events_toolbar)
}