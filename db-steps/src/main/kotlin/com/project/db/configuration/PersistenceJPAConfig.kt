package com.project.db.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = ["com.project.db.repository.jpa"],
    entityManagerFactoryRef = "entityManagerFactory",
    transactionManagerRef = "transactionManager"
)
open class PersistenceJPAConfig {

    @Bean
    open fun entityManagerFactory():LocalContainerEntityManagerFactoryBean{

        val entityManagerFactoryBean = LocalContainerEntityManagerFactoryBean()
        entityManagerFactoryBean.dataSource = dataSource();
        entityManagerFactoryBean.setPackagesToScan("com.project.db.models")

        val vendorAdapter: JpaVendorAdapter =  HibernateJpaVendorAdapter()
        entityManagerFactoryBean.jpaVendorAdapter = vendorAdapter

        entityManagerFactoryBean.setPersistenceUnitManager(persistenceUnitManager())
        entityManagerFactoryBean.persistenceUnitName = "cats"

        return entityManagerFactoryBean
    }

    @Bean
    @ConfigurationProperties(
        prefix = "spring.datasource.cats-db"
    )
    open fun dataSource(): DataSource{
        return DataSourceBuilder.create().build()
    }

    @Bean
    open fun transactionManager(): PlatformTransactionManager{
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = entityManagerFactory().`object`

        return transactionManager
    }

    @Bean
    open fun persistenceUnitManager(): DefaultPersistenceUnitManager{
        val persistenceUnitManager = DefaultPersistenceUnitManager()
        persistenceUnitManager.defaultDataSource = dataSource()
        return persistenceUnitManager
    }
}