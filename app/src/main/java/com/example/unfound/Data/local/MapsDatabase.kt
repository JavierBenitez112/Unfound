import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.unfound.Data.local.dao.PlaceDao
import com.example.unfound.Data.local.entity.PlaceEntity

@Database(entities = [PlaceEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun placeDao(): PlaceDao
}