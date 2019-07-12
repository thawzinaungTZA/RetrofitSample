package com.example.retrofitsample.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofitsample.R
import com.example.retrofitsample.api.RestAdapter
import com.example.retrofitsample.api.PostApi
import com.example.retrofitsample.model.Post
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadPosts()
    }

    private fun loadPosts() {
        val apiCalls = RestAdapter.getClient().create(PostApi::class.java)
        val postsCall = apiCalls.getAllPosts()
        postsCall.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    var responseString = ""
                    val postList = response.body()
                    if (postList != null) {
                        for (post in postList) {
                            responseString += ("UserID: ${post.userId}\nPostID: ${post.id}\nTitle: ${post.title}\nBody: ${post.body}\n\n")
                        }
                        txtPosts.text = responseString
                    } else {
                        txtPosts.text = getString(R.string.empty)
                    }
                } else {
                    txtPosts.text = getString(R.string.unsuccessful)
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                txtPosts.text = t.localizedMessage
            }
        })
    }
}
