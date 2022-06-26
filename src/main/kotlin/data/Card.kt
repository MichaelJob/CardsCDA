package data

import org.json.JSONObject

class Card(json: JSONObject){
    val id          = json.getInt("id")
    val question        = json.getString("question")
    val answer       = json.getString("answer")

    constructor(jsonString: String) : this(JSONObject(jsonString))

}