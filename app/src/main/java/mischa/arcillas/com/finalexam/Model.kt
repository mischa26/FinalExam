package mischa.arcillas.com.finalexam

import com.google.gson.annotations.SerializedName

/**
 * Created by Mischa on 21/03/2018.
 */
data class Albums( val name: String,
                   val artist: String,
                   @SerializedName("#text")
                   val text: String)