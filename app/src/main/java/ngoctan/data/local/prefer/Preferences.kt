package ngoctan.data.local.prefer

import android.content.Context
import ngoctan.traininng.androidproject.R
import kotlin.reflect.KProperty
import androidx.core.content.edit


abstract class Preferences(
    private val context: Context
) {
    private val pref = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    enum class StrorageType {
        String, Int, Float, Long, Boolean
    }

    interface PreferenceDelegate<T> {
        operator fun setValue(thisReference: Any, property: KProperty<*>, value: T)
        operator fun getValue(thisReference: Any, property: KProperty<*>): T
    }

    @Suppress("UNCHECKED_CAST")
    inner class GenericDelegate<T>(
        private val preferenceKey: String,
        private val storageType: StrorageType,
        private val defaultValue: T
    ): PreferenceDelegate<T> {
        override fun setValue(thisReference: Any, property: KProperty<*>, value: T) {
            when (storageType) {
                StrorageType.String -> pref.edit(true) { putString(preferenceKey, value as String) }
                StrorageType.Int -> pref.edit(true) { putInt(preferenceKey, value as Int) }
                StrorageType.Float -> pref.edit(true) { putFloat(preferenceKey, value as Float) }
                StrorageType.Long -> pref.edit(true) { putLong(preferenceKey, value as Long) }
                StrorageType.Boolean -> pref.edit(true) { putBoolean(preferenceKey, value as Boolean) }
            }
        }

        override fun getValue(thisReference: Any, property: KProperty<*>): T {
            return when (storageType) {
                StrorageType.String -> pref.getString(preferenceKey, defaultValue as String) as T
                StrorageType.Int -> pref.getInt(preferenceKey, defaultValue as Int) as T
                StrorageType.Float -> pref.getFloat(preferenceKey, defaultValue as Float) as T
                StrorageType.Long -> pref.getLong(preferenceKey, defaultValue as Long) as T
                StrorageType.Boolean -> pref.getBoolean(preferenceKey, defaultValue as Boolean) as T
            }
        }
    }

    fun stringRef(
        key: String,
        defaultValue: String
    ) = GenericDelegate(key, StrorageType.String, defaultValue)

    fun booleanRef(
        key: String,
        defaultValue: Boolean
    ) = GenericDelegate(key, StrorageType.Boolean, defaultValue)
}