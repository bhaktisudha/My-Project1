package com.food.order.nexvent.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class Post {

        @SerializedName("userName")
        @Expose
        private String userName;
        @SerializedName("password")
        @Expose
        private String password;

        public Post(String username, String password) {
            this.userName = username;
            this.password = password;
        }

        public String getUsername() {
            return userName;
        }

        public void setUsername(String username) {
            this.userName = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    }

