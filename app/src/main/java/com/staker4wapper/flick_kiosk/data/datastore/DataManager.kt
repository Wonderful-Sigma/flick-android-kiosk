package newjeans.bunnies.data


import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class DataManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        private const val TAG = "DatManager"
        private val COIN = stringPreferencesKey("coin")
    }

    private val Context.coinDataStore by preferencesDataStore("COIN_DATASTORE")

    suspend fun saveCoin(coin: String) {
        context.coinDataStore.edit { prefs ->
            prefs[COIN] = coin.toString()
        }
    }

    fun getCoin(): Flow<String> {
        return context.coinDataStore.data.catch { exception ->
            if (exception is IOException) {
                exception.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { prefs ->
            prefs[COIN] ?: ""
        }
    }
}