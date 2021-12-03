package com.example.apptruyen.model

enum class Status(var b: Boolean) {
    FULL(false), HOT(false), NEW(false);

    fun getStatus(a: String): Boolean {
        return !a.isEmpty()
    }
}