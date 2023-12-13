package com.example.locato.Chat

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.locato.Chat.adapter.RecentChatRecyclerAdapter
import com.example.locato.Chat.utils.FirebaseUtil.allChatroomCollectionReference
import com.example.locato.Chat.utils.FirebaseUtil.currentUserId
import com.example.locato.Chat.model.ChatroomModel
import com.example.locato.R
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query

class ChatFragment : Fragment() {
    var recyclerView: RecyclerView? = null
    var adapter: RecentChatRecyclerAdapter? = null
    lateinit var searchButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chat, container, false)
        recyclerView = view.findViewById(R.id.recyler_view)
        setupRecyclerView()
        searchButton = view.findViewById(R.id.search_user_btn)
      searchButton.setOnClickListener(){
          val intent = Intent(context, SearchUserActivity::class.java)
          startActivity(intent)
      }
        return view
    }

    fun setupRecyclerView() {
        val query = allChatroomCollectionReference()
            .whereArrayContains("userIds", currentUserId()!!)
            .orderBy("lastMessageTimestamp", Query.Direction.DESCENDING)
        val options = FirestoreRecyclerOptions.Builder<ChatroomModel>()
            .setQuery(query, ChatroomModel::class.java).build()
        adapter = RecentChatRecyclerAdapter(options, context!!)
        recyclerView!!.layoutManager = LinearLayoutManager(context)
        recyclerView!!.adapter = adapter
        adapter!!.startListening()
    }

    override fun onStart() {
        super.onStart()
        if (adapter != null) adapter!!.startListening()
    }

    override fun onStop() {
        super.onStop()
        if (adapter != null) adapter!!.stopListening()
    }

    override fun onResume() {
        super.onResume()
        if (adapter != null) adapter!!.notifyDataSetChanged()
    }
}