package com.example.expressease.BaseDeDatos

class CacheLimpiar {
    private val cache = HashMap<String, CacheItem>()

    fun addToCache(key: String, value: Any) {
        cache[key] = CacheItem(value, System.currentTimeMillis())
    }

    fun getFromCache(key: String): Any? {
        val item = cache[key]
        return item?.value
    }

    fun clearCache() {
        cache.clear()
    }

    fun onUserChange() {
        // Evento de cambio de usuario
        // Realizar liberación de caché no relevante
        val currentTime = System.currentTimeMillis()
        val userKeysToRemove = ArrayList<String>()

        for ((key, item) in cache) {
            if (item.timestamp < currentTime - CACHE_VALIDITY_PERIOD) {
                // Marcar elementos no relevantes para ser eliminados
                userKeysToRemove.add(key)
            }
        }

        // Eliminar elementos no relevantes
        for (keyToRemove in userKeysToRemove) {
            cache.remove(keyToRemove)
        }
    }

    companion object {
        const val CACHE_VALIDITY_PERIOD = 600000 // 10 minutos en milisegundos
    }
}

data class CacheItem(val value: Any, val timestamp: Long)