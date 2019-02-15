package ru.evotor.framework.core

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.evotor.framework.Volume
import ru.evotor.framework.payment.AmountOfRubles
import java.math.BigDecimal

private const val TRUE = 1
private const val FALSE = 0

private fun android.database.Cursor.safeGetColumnIndex(columnName: String) =
        this.getColumnIndex(columnName).let { index ->
            if (index != -1 && !this.isNull(index)) index else null
        }

internal fun android.database.Cursor.safeGetString(columnName: String): String? =
        safeGetColumnIndex(columnName)?.let { getString(it) }

internal fun android.database.Cursor.safeGetInt(columnName: String): Int? =
        safeGetColumnIndex(columnName)?.let { getInt(it) }

internal fun android.database.Cursor.safeGetLong(columnName: String): Long? =
        safeGetColumnIndex(columnName)?.let { getLong(it) }

internal fun android.database.Cursor.safeGetBoolean(columnName: String): Boolean? =
        safeGetInt(columnName)?.let { int ->
            when (int) {
                TRUE -> true
                FALSE -> false
                else -> null
            }
        }

internal fun android.database.Cursor.safeGetList(columnName: String): List<String>? =
        safeGetString(columnName)?.let { jsonString ->
            Gson().fromJson(jsonString, object : TypeToken<List<String>>() {}.type)
        }

//Лучше использовать safeGetEnumByName
internal fun <T : Enum<*>> android.database.Cursor.safeGetEnumByOrdinal(columnName: String, values: Array<T>): T? =
        safeGetInt(columnName)?.let { ordinal ->
            if (ordinal in 0..values.size) values[ordinal] else null
        }

internal fun <T : Enum<T>> android.database.Cursor.safeGetEnumByName(columnName: String, clazz: Class<T>): T? =
        safeGetColumnIndex(columnName)?.let { java.lang.Enum.valueOf(clazz, getString(it)) }

internal fun android.database.Cursor.safeGetBigDecimal(columnName: String): BigDecimal? =
        safeGetLong(columnName)?.let { BigDecimal(it) }

internal fun android.database.Cursor.safeGetPercents(columnName: String): Float? =
        safeGetLong(columnName)?.let {
            if (it > 0) it.toFloat() / 1000f else null
        }