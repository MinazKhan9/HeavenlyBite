package com.example.heavenlybite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.heavenlybite.adapter.NotificationAdapter
import com.example.heavenlybite.databinding.FragmentNotificationBottomBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NotificationBottomFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentNotificationBottomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationBottomBinding.inflate(layoutInflater, container, false)
        binding.backButton.setOnClickListener {
            dismiss()
        }
        val notification = listOf(
            "Your Order has been cancelled successfully", "Order has been taken by the driver",
            "Congrats Your Order Placed"
        )
        val notificationImage = listOf(R.drawable.sad, R.drawable.on_the_way, R.drawable.done)
        val adapter = NotificationAdapter(
            ArrayList(notification),
            ArrayList(notificationImage)
        )
        binding.notificationRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.notificationRecyclerView.adapter = adapter
        return binding.root
    }

    companion object {
    }
}