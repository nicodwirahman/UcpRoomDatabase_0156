import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2.data.Dao.DosenDao
import com.example.ucp2.data.entity.Dosen
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Dosen::class], version = 1, exportSchema = false)
abstract class DosenDatabase : RoomDatabase() {
    abstract fun dosenDao(): DosenDao


    companion object{
        @Volatile
        private var Instance:DosenDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): DosenDatabase{
            return (Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    DosenDatabase::class.java,
                    "DosenDatavase"
                )
                    .build().also { Instance = it }
            })
        }
    }
}
