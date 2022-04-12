package car.park.pro.carpark.infrastructure.configuration

import com.zaxxer.hikari.HikariDataSource
import org.jdbi.v3.core.Handle
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.postgres.PostgresPlugin
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class DatabaseConfiguration {

    @Bean
    @Primary
    fun txManager(dataSource: HikariDataSource): TxManager {
        val jdbi = Jdbi
            .create(dataSource)
            .also { PostgresPlugin().customizeJdbi(it) }
        return TxManagerImpl(jdbi)
    }
}

interface TxManager {
    fun <T> inTx(block: (Handle) -> T): T
    fun <T> noTx(block: (Handle) -> T): T
}

class TxManagerImpl(private val jdbi: Jdbi) : TxManager {
    override fun <T> inTx(block: (Handle) -> T): T = jdbi.inTransaction<T, Exception> { handle -> block(handle) }

    override fun <T> noTx(block: (Handle) -> T): T = jdbi.withHandle<T, Exception> { handle -> block(handle) }
}



