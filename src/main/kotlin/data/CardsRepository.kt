package data

import org.json.JSONArray
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets


object CardsRepository {

    lateinit var data: List<Card>
    
    fun loadCards() {
        val cards = mutableListOf<Card>()
        
        val json = JSONArray(loadFromAsset( "sampledata.json"))
        
        for (i in 0 until json.length()) {
            cards.add(Card(json.getJSONObject(i)))
        }
        
        data = cards
    }
    
    private fun loadFromAsset(fileName: String): String {
        val classLoader = javaClass.classLoader
        var inputStream: InputStream? = null
        try {
            val file = File(classLoader.getResource(fileName).file)
            inputStream = FileInputStream(file)

            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            return String(buffer, StandardCharsets.UTF_8)

        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }

    }
}


